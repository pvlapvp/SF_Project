import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorVariant.byUniversityId);
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorVariant.byShortName);

        List<Student> studentList = ExcelReader.readStudents();
        System.out.println("Unsorted:");
        studentList.forEach(System.out::println);
        Collections.sort(studentList, studentComparator);
        System.out.println("Sorted:");
        studentList.forEach(System.out::println);


        List<University> universityList = ExcelReader.readUniversities();
        System.out.println("Unsorted:");
        universityList.forEach(System.out::println);
        Collections.sort(universityList, universityComparator);
        System.out.println("Sorted:");
        universityList.forEach(System.out::println);
    }
}
