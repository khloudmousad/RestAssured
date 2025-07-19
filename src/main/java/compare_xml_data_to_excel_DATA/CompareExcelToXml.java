package compare_xml_data_to_excel_DATA;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class CompareExcelToXml {

    public static void main(String[] args) throws Exception {

        System.out.println("📁 [PHASE 1] Reading Excel data...");
        DataFormatter formatter = new DataFormatter();

        List<Map<String, String>> expectedData = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/main/resources/testdata2.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        for (int i = 1; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            String name = formatter.formatCellValue(row.getCell(0));
            String price = formatter.formatCellValue(row.getCell(1));

            Map<String, String> rowMap = new HashMap<>();
            rowMap.put("name", name.trim());
            rowMap.put("price", price.trim());
            expectedData.add(rowMap);
        }
        workbook.close();
        fis.close();
        System.out.println("✅ Finished reading Excel. Rows found: " + expectedData.size());

        System.out.println("🌐 [PHASE 2] Fetching XML from API...");
        String url = "https://www.w3schools.com/xml/simple.xml";

        Response response = RestAssured
                .given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();

        XmlPath xmlPath = new XmlPath(response.asString());
        List<String> actualNames = xmlPath.getList("breakfast_menu.food.name");
        List<String> actualPrices = xmlPath.getList("breakfast_menu.food.price");
        System.out.println("✅ Received XML data with " + actualNames.size() + " food items.");

        System.out.println("🧪 [PHASE 3] Comparing Excel vs XML data...");
        for (Map<String, String> expected : expectedData) {
            String expectedName = expected.get("name");
            String expectedPrice = expected.get("price");

            boolean found = false;

            for (int i = 0; i < actualNames.size(); i++) {
                if (actualNames.get(i).equals(expectedName)) {
                    found = true;
                    String actualPrice = actualPrices.get(i);
                    if (!expectedPrice.equals(actualPrice)) {
                        throw new AssertionError("❌ Price mismatch for " + expectedName +
                                ": expected " + expectedPrice + ", but got " + actualPrice);
                    } else {
                        System.out.println("✅ Match: " + expectedName + " — " + expectedPrice);
                    }
                    break;
                }
            }

            if (!found) {
                throw new AssertionError("❌ Item not found in XML: " + expectedName);
            }
        }

        System.out.println("\n🎉 [PHASE 4] SUCCESS: All Excel items matched with XML!");
    }
}
