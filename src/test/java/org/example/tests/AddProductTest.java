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
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * Developed by Anand Singh on 26/Oct/2021, 8:40 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class AddProductTest extends Base {

    public static Logger Log = LogManager.getLogger(AddProductTest.class.getName());

    RequestSpecification requestSpecification;

    // setting the base uri
    @BeforeTest
    public void setup() {
        requestSpecification = RequestSpec.setBaseURI(getProperty("baseURI"));
        Log.info("Setting baseURI");
    }

    // testcase to add new product and verify by retrieving it again.
    @Test(dataProvider = "ProductDetails", dataProviderClass = BestBuyDataProvider.class, groups = "ProductTestCases", priority = 1)
    public void addProductValidation(String name, String type, String price, String shipping, String upc, String description,
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

        String getResponseStr = getResponse.getBody().asString();
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("name").toString(), name);
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("type").toString(), type);
        softAssert.assertEquals(Double.parseDouble(Utils.rawToJson(getResponseStr).get("price").toString()), Double.parseDouble(price));
        softAssert.assertEquals(Double.parseDouble(Utils.rawToJson(getResponseStr).get("shipping").toString()), Double.parseDouble(shipping));
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("upc").toString(), upc);
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("description").toString(), description);
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("manufacturer").toString(), manufacturer);
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("model").toString(), model);
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("url").toString(), url);
        softAssert.assertEquals(Utils.rawToJson(getResponseStr).get("image").toString(), image);
        Log.info("asserting other fields");

        softAssert.assertAll();
    }

    // reset the base uri
    @AfterTest
    public void cleanup(){
        RequestSpec.resetBaseURI();
        Log.info("resetting the baseURI");
    }

}
