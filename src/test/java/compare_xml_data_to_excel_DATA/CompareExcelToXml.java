package compare_xml_data_to_excel_DATA;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.*;

public class CompareExcelToXml {

    public static void main(String[] args) throws Exception {

        // 🔧 Formatter to safely read any cell as a string (numeric, text, date, etc.)
        DataFormatter formatter = new DataFormatter();

        // 1️⃣ Read expected data from Excel
        List<Map<String, String>> expectedData = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/main/resources/testdata2.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        for (int i = 1; i <= lastRow; i++) { // start at 1 to skip header
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // ✅ Use DataFormatter to avoid cell type exceptions
            String name = formatter.formatCellValue(row.getCell(0));
            String price = formatter.formatCellValue(row.getCell(1));

            Map<String, String> rowMap = new HashMap<>();
            rowMap.put("name", name.trim());
            rowMap.put("price", price.trim());

            expectedData.add(rowMap);
        }

        workbook.close();
        fis.close();

        // 2️⃣ Fetch actual data from XML (W3Schools breakfast menu)
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

        System.out.println("✅ Starting comparison...\n");

        // 3️⃣ Compare each Excel row with the XML data
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

        System.out.println("\n🎉 All Excel items matched with XML!");
    }
}
