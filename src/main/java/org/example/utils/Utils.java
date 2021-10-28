package org.example.utils;

import io.restassured.path.json.JsonPath;
import org.example.common.Base;

import java.util.ArrayList;

/**
 * Developed by Anand Singh on 27/Oct/2021, 9:50 AM.
 * Copyright (c) 2021. All rights reserved.
 */
public class Utils extends Base {

    static Xls_Reader reader;

    public static ArrayList<Object[]> getDataFromExcel(String sheetName){
        ArrayList<Object[]> myData = new ArrayList<>();
        try {
            reader = new Xls_Reader(getProperty("xlsxFilePath"), sheetName);
        } catch (Exception e){
            e.printStackTrace();
        }
        for(int rowCount = 2; rowCount<=reader.getRowCount(sheetName); rowCount++){
            String name = reader.getCellData(sheetName, "name", rowCount);
            String type = reader.getCellData(sheetName, "type", rowCount);
            String price = reader.getCellData(sheetName, "price", rowCount);
            String shipping = reader.getCellData(sheetName, "shipping", rowCount);
            String upc = reader.getCellData(sheetName, "upc", rowCount);
            String description = reader.getCellData(sheetName, "description", rowCount);
            String manufacturer = reader.getCellData(sheetName, "manufacturer", rowCount);
            String model = reader.getCellData(sheetName, "model", rowCount);
            String url = reader.getCellData(sheetName, "url", rowCount);
            String image = reader.getCellData(sheetName, "image", rowCount);


            Object ob[] = {name, type, price, shipping, upc, description, manufacturer, model, url, image};
            myData.add(ob);
        }
        return myData;
    }

    public static JsonPath rawToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
