package org.example.payloads;

import org.example.common.Constants;

/**
 * Developed by Anand Singh on 26/Oct/2021, 9:26 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class ReqBody {
    public static String addProductPayload(String name, String type, String price, String shipping, String upc, String description,
                                           String manufacturer, String model, String url, String image){
        return "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"type\": \""+type+"\",\n" +
                "  \"price\": "+price+",\n" +
                "  \"shipping\": "+shipping+",\n" +
                "  \"upc\": \""+upc+"\",\n" +
                "  \"description\": \""+description+"\",\n" +
                "  \"manufacturer\": \""+manufacturer+"\",\n" +
                "  \"model\": \""+model+"\",\n" +
                "  \"url\": \""+url+"\",\n" +
                "  \"image\": \""+image+"\"\n" +
                "}";
    }

    public static String updateProductPayload(String name, String type, String price, String shipping, String upc, String description,
                                           String manufacturer, String model, String url, String image){
        return "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"type\": \""+type+"\",\n" +
                "  \"price\": "+(Double.parseDouble(price)+ Constants.updatePrice)+",\n" +
                "  \"shipping\": "+(Double.parseDouble(shipping)+Constants.updateShippingPrice)+",\n" +
                "  \"upc\": \""+upc+"\",\n" +
                "  \"description\": \""+description+"\",\n" +
                "  \"manufacturer\": \""+manufacturer+"\",\n" +
                "  \"model\": \""+model+"\",\n" +
                "  \"url\": \""+url+"\",\n" +
                "  \"image\": \""+image+"\"\n" +
                "}";
    }
}
