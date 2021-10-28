**Rest Assured Framework**<br/>
Using BestBuy API to automate the testcases<br/>

**Prerequisites:**<br/>
git clone https://github.com/belkozavr/api-playground.git<br/>
cd api-playground<br/>
npm install<br/>
go to api-playground folder -> config -> default.json -> update port to 8080.<br/>
npm start<br/>

**Folder structure:**<br/>
<pre>
-extentReport -- Here report will be generated
-logs -- Here executing logs will be generated

-src
  -main
    -java
      -org.example
        -commons
          -Base class -- For loading the property files
          -Constants -- To store constant values
        -listeners --
          -ExtentReportNG -- ExtentReport listener
          -Listener -- Generic listener
        -payloads
          -ReqBody -- For adding/updating product
        -specs
          -RequestSpec -- For preparing requestSpecification
        -utils
          -Xls_Reader --To retrive the data from excel using Apache POI
          -Utils - custom functions
    -resource
      -dev.properties -- property file to get baseURL, resources, filePath.
      -log4j2.xml --log4j2 config file
      -TestCases.xlsx --Test data 
  -test
    -java
      -org.example
        -dataProviders
          -BestBuyDataProvider -- data provider to feed the test data to the test cases. 
        -tests
          -AddProductTest -- testcase to add new product and verify by retrieving it again.
          -UpdateProductTest -- testcase to add and update product details and verify by update functionality.
          -DeleteProductTest -- testcase to add and delete product and verify the delete functionality
          
 logging.txt -- for logging the request and reponses 
 pom.xml -- for adding dependencies
 testng.xml -- for triggering the testcases
  <pre/>
