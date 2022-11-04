import com.google.gson.reflect.TypeToken;
import comparator.StudentComparator;
import comparator.StudentComparatorVariant;
import comparator.UniversityComparator;
import comparator.UniversityComparatorVariant;
import util.ComparatorUtil;
import util.ExcelReader;
import model.Student;
import model.University;
import util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorVariant.byUniversityId);
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorVariant.byShortName);

        //составление и сортировка списка студентов
        List<Student> studentList = ExcelReader.readStudents();
        System.out.println("Unsorted:");
        studentList.forEach(System.out::println);
        studentList.sort(studentComparator);
        System.out.println("Sorted:");
        studentList.forEach(System.out::println);

        //составление и сортировка списка университетов
        List<University> universityList = ExcelReader.readUniversities();
        System.out.println("Unsorted:");
        universityList.forEach(System.out::println);
        universityList.sort(universityComparator);
        System.out.println("Sorted:");
        universityList.forEach(System.out::println);

        //сериализыция списка студентов
        String studentListJson = JsonUtil.getStudentListJson(studentList);
        System.out.println(studentListJson);

        //десериализация списка студентов
        List<Student> studentsFromJson = JsonUtil.getStudentListFromJson(studentListJson, new TypeToken<List<Student>>(){}.getType());
        studentsFromJson.forEach(System.out::println);


        //сериализация списка университетов
        String universityListJson = JsonUtil.getUniversityListJson(universityList);
        System.out.println(universityListJson);

        //десериализация списка университетов
        List<University> universitiesFromJson = JsonUtil.getUniversityListFromJson(universityListJson, new TypeToken<List<University>>(){}.getType());
        universitiesFromJson.forEach(System.out::println);


        //проверка корректности десериализации
        if (studentList.size() == studentsFromJson.size()) System.out.println("Student list deserialized correctly");
            else System.out.println("WARN: Student list deserialized incorrectly");

        if (universityList.size() == universitiesFromJson.size()) System.out.println("University list deserialized correctly");
            else System.out.println("WARN: University list deserialized incorrectly");

        //работа с исходной коллекцией студентов
        studentList.forEach(student -> {
            String serializedStudent = JsonUtil.getStudentJson(student);
            System.out.println(">>>Serialized student:\n" + serializedStudent);
            Student deserializedStudent = JsonUtil.getStudentClassFromJson(serializedStudent);
            System.out.println("<<<Deserialized student:\n" + deserializedStudent);
        });

        //работа с исходной коллекцией университетов
        universityList.forEach(university -> {
            String serializedUniversity = JsonUtil.getUniversityJson(university);
            System.out.println(">>>Serialized university:\n" + serializedUniversity);
            University deserializedUniversity = JsonUtil.getUniversityClassFromJson(serializedUniversity);
            System.out.println("<<<Deserialized university:\n" + deserializedUniversity);
        });
    }
}
