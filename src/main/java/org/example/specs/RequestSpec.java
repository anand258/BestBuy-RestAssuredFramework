package org.example.specs;

import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;

/**
 * Developed by Anand Singh on 26/Oct/2021, 12:57 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class RequestSpec {

    static RequestSpecification requestSpecification = RestAssured.given();

    public static RequestSpecification setBaseURI (String baseURI){
        return requestSpecification.baseUri(baseURI);
    }

    public static void setConfig() throws FileNotFoundException {
        PrintStream fileOutPutStream = new PrintStream("logging.txt");
        RestAssured.config = config().logConfig(new LogConfig().defaultStream(fileOutPutStream));
    }

    public static void resetBaseURI (){
        RestAssured.baseURI = "";
    }

    public static RequestSpecification setOAuth (String consumerKey, String consumerSecret){
        return given().auth().oauth(consumerKey,consumerSecret,"","", OAuthSignature.HEADER);
    }

    public static RequestSpecification setHeaders(HashMap<String,String> headers){
        requestSpecification = given().headers(headers);
        return requestSpecification;
    }

    public static RequestSpecification setContentType (ContentType type){
        return requestSpecification.contentType(type);
    }

    public Response getRequest(String endPoint){
        Response response = given().when().log().all().get(endPoint).then().log().all().extract().response();
        return response;
    }

    public Response getRequest(RequestSpecification requestSpecification,String endPoint){
        Response response = given().spec(requestSpecification).when().log().all().get(endPoint).then().log().all().extract().response();
        return response;
    }

    public Response postRequest(String endPoint, String body){
        Response response = given().body(body).when().log().all().post(endPoint).then().extract().response();
        return response;
    }

    public Response postRequest(RequestSpecification requestSpecification,String endPoint, String body){
        Response response = given().spec(requestSpecification).body(body).when().log().all().post(endPoint).then().log().all().extract().response();
        return response;
    }

    public Response putRequest(String endPoint, String body){
        Response response = given().body(body).when().log().all().put(endPoint).then().extract().response();
        return response;
    }

    public Response putRequest(RequestSpecification requestSpecification,String endPoint, String body){
        Response response = given().spec(requestSpecification).body(body).when().log().all().put(endPoint).then().log().all().extract().response();
        return response;
    }

    public Response deleteRequest(String endPoint){
        Response response = given().when().log().all().delete(endPoint).then().log().all().extract().response();
        return  response;
    }

    public Response deleteRequest(RequestSpecification requestSpecification,String endPoint){
        Response response = given().spec(requestSpecification).when().log().all().delete(endPoint).then().log().all().extract().response();
        return  response;
    }

    public Response patchRequest(String endPoint, String body){
        Response response = given().body(body).when().log().all().patch(endPoint).then().log().all().extract().response();
        return response;
    }

    public Response patchRequest(RequestSpecification requestSpecification, String endPoint, String body){
        Response response = given().spec(requestSpecification).body(body).when().log().all().patch(endPoint).then().log().all().extract().response();
        return response;
    }

}
