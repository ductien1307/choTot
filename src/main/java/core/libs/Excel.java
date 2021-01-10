package core.libs;

import core.AbstractTest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;

public class Excel extends AbstractTest {

    public Row get_row(Sheet sheet, String TC_ID) {
        Row row = null;
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i < rowCount; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String temp = getExcelCellString(row.getCell(j));
                if (TC_ID.equals(temp)) {
                    return row;
                }

            }
        }
        return row;
    }

    public String get_num_cell(String filePath, String fileName, String sheetName, Integer num_cell) throws IOException {
        String os = "";
        if (isWindows()) {
            os = "\\";
        } else if (isMac()) {
            os = "/";
        }
        Integer num_cell_return = null;
        File file = new File(filePath + os + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook lwWorkbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            lwWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            lwWorkbook = new HSSFWorkbook(inputStream);
        }
        Sheet lwSheet = lwWorkbook.getSheet(sheetName);
        Row row = lwSheet.getRow(0);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = row.getCell(j);
            String num_cell_location = getExcelCellString(cell);
            double temp = Double.parseDouble(num_cell_location);

            String temp_1 = String.format("%.0f", temp);
            String temp_2 = num_cell.toString();
            if (temp_1.equals(temp_2)) {
                num_cell_return = j;
            }
        }
        return num_cell_return.toString();
    }

    public Sheet get_data_sheet_excel(String filePath, String fileName, String sheetName) throws IOException {
        File file = new File(filePath + "/" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook lwWorkbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            lwWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            lwWorkbook = new HSSFWorkbook(inputStream);
        }
        Sheet lwSheet = lwWorkbook.getSheet(sheetName);
        return lwSheet;
    }

    public Integer get_num_row_excel(String filePath, String fileName, String sheetName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook lwWorkbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            lwWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            lwWorkbook = new HSSFWorkbook(inputStream);
        }
        Sheet lwSheet = lwWorkbook.getSheet(sheetName);
        int rowCount = lwSheet.getLastRowNum() - lwSheet.getFirstRowNum();
        return rowCount;
    }

    public String getExcelCellString(Cell cell) {
        if (cell == null) {
            return null;
        }
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case Cell.CELL_TYPE_STRING:
                        return cell.getRichStringCellValue().getString();
                    case CELL_TYPE_NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    default:
                        break;
                }
            default:
                break;
        }
        return null;
    }
}
