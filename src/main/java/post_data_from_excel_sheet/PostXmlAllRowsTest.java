package post_data_from_excel_sheet;

import io.restassured.RestAssured;
import java.util.List;

public class PostXmlAllRowsTest {
    public static void main(String[] args) {
        sendAllRowsToWebhook();
    }
    public static void sendAllRowsToWebhook() {
        System.out.println("🚀 Starting method: sendAllRowsToWebhook");

        // ✅ Step 1: Read all rows from Excel
        System.out.println("📥 Reading Excel data...");
        List<String[]> allData = ExcelUtils.getAllData("src/main/resources/testdata.xlsx");
        System.out.println("✅ Read " + allData.size() + " rows from Excel");

        // ✅ Step 2: Define Webhook.site URL
        String webhookUrl = "https://webhook.site/9cd6c863-3564-4286-9958-3b7ae263cd54";
        System.out.println("🔗 Webhook URL set: " + webhookUrl);

        // ✅ Step 3: Loop through each row and send XML
        int counter = 1;
        for (String[] row : allData) {
            String name = row[0];
            String role = row[1];

            String xml = "<user><name>" + name + "</name><role>" + role + "</role></user>";
            System.out.println("📤 Sending row " + counter + ": " + xml);

            try {
                RestAssured
                        .given()
                        .header("Content-Type", "application/xml")
                        .body(xml)
                        .when()
                        .post(webhookUrl)
                        .then()
                        .statusCode(200); // Validate successful POST

                System.out.println("✅ Successfully sent row " + counter);
            } catch (Exception e) {
                System.out.println("❌ Failed to send row " + counter + ": " + e.getMessage());
            }

            counter++;
        }

        System.out.println("🏁 Finished sending all rows.");
    }
}
