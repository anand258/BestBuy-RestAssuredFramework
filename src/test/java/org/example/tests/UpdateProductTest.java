package org.example.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.Base;
import org.example.common.Constants;
import org.example.dataProviders.BestBuyDataProvider;
import org.example.payloads.ReqBody;
import org.example.specs.RequestSpec;
import org.example.utils.Utils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Developed by Anand Singh on 28/Oct/2021, 4:58 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class UpdateProductTest extends Base {

    public static Logger Log = LogManager.getLogger(DeleteProductTest.class.getName());
    RequestSpecification requestSpecification;

    // setting the base uri
    @BeforeTest
    public void setup(){
        requestSpecification = RequestSpec.setBaseURI(getProperty("baseURI"));
        Log.info("Setting baseURI");
    }

    // testcase to add and update product details and verify by update functionality.
    @Test(dataProvider = "ProductDetails", dataProviderClass = BestBuyDataProvider.class, groups = "ProductTestCases", priority = 2)
    public void updateProductValidation(String name, String type, String price, String shipping, String upc, String description,
                                        String manufacturer, String model, String url, String image){
        SoftAssert softAssert = new SoftAssert();

        RequestSpec request = new RequestSpec();
        requestSpecification = request.setContentType(ContentType.JSON);
        Log.info("Setting contentType");

        Response addResponse = request.postRequest(requestSpecification,
                getProperty("addProductResource"),
                ReqBody.addProductPayload(name, type, price, shipping, upc, description, manufacturer, model, url, image));
        Log.info("initiating post request");

        softAssert.assertEquals(addResponse.getStatusCode(), 201);
        Log.info("asserting post request status code");

        String addResponseStr = addResponse.getBody().asString();
        int id = Utils.rawToJson(addResponseStr).get("id");
        Log.info("retrieving product id");

        Response updateResponse = request.patchRequest(requestSpecification,
                getProperty("updateProductResource")+id,
                ReqBody.updateProductPayload(name, type, price, shipping, upc, description, manufacturer, model, url, image));
        Log.info("initiating patch request");

        softAssert.assertEquals(updateResponse.getStatusCode(), 200);
        Log.info("asserting patch request status code");

        Response getResponse = request.getRequest(requestSpecification,getProperty("getProductResource")+id);
        Log.info("initiating get request");
        softAssert.assertEquals(getResponse.getStatusCode(), 200);
        Log.info("asserting get request status code");

        String getResponseStr = getResponse.getBody().asString();

        softAssert.assertEquals(Double.parseDouble(Utils.rawToJson(getResponseStr).get("price").toString()),
                Double.parseDouble(price)+ Constants.updatePrice);
        softAssert.assertEquals(Double.parseDouble(Utils.rawToJson(getResponseStr).get("shipping").toString()),
                Double.parseDouble(shipping)+ Constants.updateShippingPrice);
        Log.info("validating updated fields");

        softAssert.assertAll();
    }

    // reset the base uri
    @AfterTest
    public void cleanup(){
        RequestSpec.resetBaseURI();
        Log.info("resetting the baseURI");
    }
}
