package post_data_from_excel_sheet;

import io.restassured.RestAssured;


import java.util.List;

public class PostXmlAllRowsTest {


    public void sendAllRowsToWebhook() {
        // ✅ Step 1: Read all rows from Excel
        List<String[]> allData = ExcelUtils.getAllData("src/main/resources/testdata.xlsx");

        // ✅ Step 2: Define Webhook.site URL
        String webhookUrl = "https://webhook.site/9cd6c863-3564-4286-9958-3b7ae263cd54"; // Replace with your URL

        // ✅ Step 3: Loop through each row and send XML
        for (String[] row : allData) {
            String name = row[0];
            String role = row[1];

            // Build XML payload
            String xml = "<user><name>" + name + "</name><role>" + role + "</role></user>";

            System.out.println("Sending: " + xml);

            // Send request
            RestAssured
                    .given()
                    .header("Content-Type", "application/xml")
                    .body(xml)
                    .when()
                    .post(webhookUrl)
                    .then()
                    .statusCode(200); // Validate successful POST
        }
    }
}
