import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static List<Student> readStudents() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("src/main/resources/universityInfo.xlsx");
        XSSFSheet sheet = workbook.getSheet("Студенты");
        Iterator<Row> rowIterator = sheet.iterator();

        ArrayList<Student> students = new ArrayList<>();

        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            students.add(new Student(
                    row.getCell(1).getStringCellValue(),
                    row.getCell(0).getStringCellValue(),
                    (int) row.getCell(2).getNumericCellValue(),
                    (float) row.getCell(3).getNumericCellValue()
            ));
        }
        return students;
    }

    public static List<University> readUniversities() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("src/main/resources/universityInfo.xlsx");
        XSSFSheet sheet = workbook.getSheet("Университеты");
        Iterator<Row> rowIterator = sheet.iterator();

        ArrayList<University> universities = new ArrayList<>();

        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            universities.add(new University(
                    row.getCell(0).getStringCellValue(),
                    row.getCell(1).getStringCellValue(),
                    row.getCell(2).getStringCellValue(),
                    (int)row.getCell(3).getNumericCellValue(),
                    StudyProfile.valueOf(row.getCell(4).getStringCellValue())
            ));
        }
        return universities;
    }
}
