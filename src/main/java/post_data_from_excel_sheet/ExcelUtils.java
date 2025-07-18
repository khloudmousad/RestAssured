package post_data_from_excel_sheet;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<String[]> getAllData(String filePath) {
        List<String[]> dataList = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(new File(filePath)); // creat stream to read the excel file bytes
            Workbook workbook = WorkbookFactory.create(file); //creak book that represent the excal file and access its sheet and ro
            Sheet sheet = workbook.getSheetAt(0); // First sheet

            // Loop through all rows except header (start from 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    String role = row.getCell(1).getStringCellValue();
                    dataList.add(new String[]{name, role});
                }
            }

            workbook.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
