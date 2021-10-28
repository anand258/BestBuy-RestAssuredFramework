package org.example.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.Base;
import org.example.dataProviders.BestBuyDataProvider;
import org.example.payloads.ReqBody;
import org.example.specs.RequestSpec;
import org.example.utils.Utils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Developed by Anand Singh on 28/Oct/2021, 4:59 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class DeleteProductTest extends Base {

    public static Logger Log = LogManager.getLogger(DeleteProductTest.class.getName());
    RequestSpecification requestSpecification;

    // setting the base uri
    @BeforeTest
    public void setup(){
        requestSpecification = RequestSpec.setBaseURI(getProperty("baseURI"));
        Log.info("Setting baseURI");
    }

    // testcase to add and delete product and verify the delete functionality
    @Test(dataProvider = "ProductDetails", dataProviderClass = BestBuyDataProvider.class, groups = "ProductTestCases", priority = 3)
    public void deleteProductValidation(String name, String type, String price, String shipping, String upc, String description,
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

        Response getResponse = request.getRequest(requestSpecification,getProperty("getProductResource")+id);
        Log.info("initiating get request");
        softAssert.assertEquals(getResponse.getStatusCode(), 200);
        Log.info("asserting get request status code");

        Response deleteResponse = request.deleteRequest(requestSpecification,
                getProperty("updateProductResource")+id);
        Log.info("initiating delete request");

        softAssert.assertEquals(deleteResponse.getStatusCode(), 200);
        Log.info("asserting delete request status code");

        Response getResponseAfterDelete = request.getRequest(requestSpecification,getProperty("getProductResource")+id);
        Log.info("again initiating get request");
        softAssert.assertEquals(getResponseAfterDelete.getStatusCode(), 404);
        Log.info("asserting get request status code");

        String getResponseAfterDeleteStr = getResponseAfterDelete.getBody().asString();

        softAssert.assertEquals(Utils.rawToJson(getResponseAfterDeleteStr).get("message").toString(), "No record found for id '"+id+"'");
        Log.info("asserting msg fields");

        softAssert.assertAll();
    }

    // reset the base uri
    @AfterTest
    public void cleanup(){
        RequestSpec.resetBaseURI();
        Log.info("resetting the baseURI");
    }
}
