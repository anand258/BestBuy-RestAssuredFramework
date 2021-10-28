package org.example.dataProviders;

import org.example.utils.Utils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Developed by Anand Singh on 28/Oct/2021, 5:01 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class BestBuyDataProvider {

    // data provider function to drive the data from excel for multiple products
    @DataProvider(name = "ProductDetails")
    public Iterator<Object[]> dataProvider(){
        ArrayList<Object[]> testData = Utils.getDataFromExcel("testData");
        return testData.iterator();
    }
}
