import comparator.StudentComparator;
import comparator.StudentComparatorVariant;
import comparator.UniversityComparator;
import comparator.UniversityComparatorVariant;
import model.Statistics;
import model.SummaryInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import util.*;
import model.Student;
import model.University;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException {
        Configurator.initialize(null, "src/main/resources/logging.properties");
        logger.info("Application started");
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorVariant.byUniversityId);
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorVariant.byShortName);

        List<Student> students = ExcelReader.readStudents();
        logger.info("List of Students has read");
        students.sort(studentComparator);
        List<University> universities = ExcelReader.readUniversities();
        universities.sort(universityComparator);
        logger.info("List of Universities has read");
        List<Statistics> statisticsList = CollectionHandler.getStatisticList(students, universities);
        logger.info("List of Statistics classes has created");
        XlsWriter.generateXlsTable(statisticsList, "src/main/resources/out.xlsx");

        SummaryInfo summary = new SummaryInfo()
                .setStudentList(students)
                .setUniversityList(universities)
                .setStatisticsList(statisticsList)
                .setProcessDate(new Date());

        XmlWriter.generateXmlReq(summary);
        JsonWriter.writeJson(summary);
    }
}
