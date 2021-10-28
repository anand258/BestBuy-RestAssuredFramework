package org.example.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Developed by Anand Singh on 26/Oct/2021, 1:03 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class Base {

    static Properties prop;

    public static String getProperty(String propertyName){
        try{
            FileInputStream fin = new FileInputStream("/Users/anasingh/IdeaProjects/RestApiFramework/src/main/resources/dev.properties");
            prop = new Properties();
            prop.load(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propertyName);
    }
}
