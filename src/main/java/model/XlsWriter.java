package model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsWriter {

    private static int rowIndex = 0;
    private static final String[] headerStrings = new String[] {"studyProfile", "avgExamScore", "profileStudentsAmount", "universityName"};;

    /*
            TODO: реализовать метод генерации таблицы и её записи в файл. Метод получает на вход коллекцию объектов статистики и путь к файлу
            TODO: реализовать с помощью Apache POI создание нового Workbook, добавить на него новую страницу.
             В странице заполнить заголовок с текстовыми наименованиями (то есть подписать колонки таблицы).
             Заголовки должны иметь настроенный стиль — как минимум, сделать всё жирным шрифтом и с указанным размером шрифта.
            TODO: Также в этом методе реализовать заполнение строк таблицы данными, хранящимися в коллекции элементов Statistics
            TODO: После генерации в этом же методе файл необходимо создать (используя FileOutputStream), задав ему полученное на вход метода имя
             */
    public static void generateXlsTable(List<Statistics> statisticsList, String fileName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("statistics");

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 280);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        Row row = sheet.createRow(rowIndex);

        int i = 0;
        for (String s: headerStrings) {
            Cell cell = row.createCell(i);
            cell.setCellValue(s);
            cell.setCellStyle(cellStyle);
            sheet.autoSizeColumn(i);
            i++;
        }

        rowIndex++;
        row = sheet.createRow(rowIndex);
        for (Statistics stat: statisticsList) {
            Cell cell = row.createCell(0);
            cell.setCellValue(stat.getStudyProfile());
            cell = row.createCell(1);
            cell.setCellValue(stat.getAvgExamScore());
            cell = row.createCell(2);
            cell.setCellValue(stat.getProfileStudentsAmount());
            cell = row.createCell(3);
            cell.setCellValue(stat.getUniversityName());
            rowIndex++;
            row = sheet.createRow(rowIndex);
        }

        workbook.write(new FileOutputStream(fileName));
        workbook.close();
        rowIndex = 0;
    }

    public static void main(String[] args) throws IOException {
        List<Statistics> statisticsList = new ArrayList<>();
        statisticsList.add(new Statistics("a", 4, 40, "aa"));
        statisticsList.add(new Statistics("b", 3, 50, "bb"));
        statisticsList.add(new Statistics("c", 2, 67, "cc"));
        statisticsList.add(new Statistics("d", 5, 45, "dd"));
        statisticsList.add(new Statistics("e", 4, 67, "ee"));
        statisticsList.add(new Statistics("f", 3, 34, "ff"));
        statisticsList.add(new Statistics("g", 2, 55, "gg"));

        generateXlsTable(statisticsList, "e:/1.xlsx");
    }
}
