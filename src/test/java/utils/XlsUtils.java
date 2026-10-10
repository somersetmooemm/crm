package utils;

import com.codeborne.xlstest.XLS;
import org.apache.poi.ss.usermodel.*;

import java.io.File;

public class XlsUtils {

    /**
     * Получить весь текст.
     */
    public static String getText(File file) {
        Workbook workbook = new XLS(file).excel;
        DataFormatter formatter = new DataFormatter();
        StringBuilder result = new StringBuilder();

        for (Sheet sheet : workbook) {
            for (Row row : sheet) {
                for (Cell cell : row) {

                    String value = formatter.formatCellValue(cell);

                    if (!value.isBlank()) {
                        result.append(value).append(" ");
                    }
                }
                result.append(System.lineSeparator());
            }
        }
        return result.toString().trim();
    }

    /**
     * Получить текст конкретного листа.
     */
    public static String getSheetText(File file, String sheetName) {
        Sheet sheet = getSheet(file, sheetName);
        DataFormatter formatter = new DataFormatter();
        StringBuilder result = new StringBuilder();

        for (Row row : sheet) {
            for (Cell cell : row) {

                String value = formatter.formatCellValue(cell);

                if (!value.isBlank()) {
                    result.append(value).append(" ");
                }
            }

            result.append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    /**
     * Получить значение конкретной ячейки.
     * \<p>
     * Нумерация строки и столбца начинается с 0.
     */
    public static String getCellValue(File file, String sheetName, int rowNumber, int columnNumber) {
        Sheet sheet = getSheet(file, sheetName);
        Row row = sheet.getRow(rowNumber);

        if (row == null) {
            throw new IllegalArgumentException(
                    "Строка " + rowNumber + " не найдена"
            );
        }

        Cell cell = row.getCell(columnNumber);

        if (cell == null) {
            return "";
        }

        return new DataFormatter().formatCellValue(cell);
    }

    /**
     * Получить количество листов.
     */
    public static int getSheetCount(File file) {
        return new XLS(file)
                .excel
                .getNumberOfSheets();
    }

    /**
     * Получить лист.
     */
    private static Sheet getSheet(File file, String sheetName) {
        Sheet sheet = new XLS(file).excel.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException("Лист '" + sheetName + "' не найден");
        }

        return sheet;
    }
}

