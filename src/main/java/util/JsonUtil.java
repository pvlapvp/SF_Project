package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Student;
import model.University;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class JsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String getStudentJson(Student student) {
        return gson.toJson(student);
    }

    public static String getStudentListJson(Collection<Student> studentList) {
        return gson.toJson(studentList);
    }

    public static String getUniversityJson(University university) {
        return gson.toJson(university);
    }

    public static String getUniversityListJson(Collection<University> universityList) {
        return gson.toJson(universityList);
    }

    public static Student getStudentClassFromJson(String studentJson) {
        return gson.fromJson(studentJson, Student.class);
    }

    public static List<Student> getStudentListFromJson(String studentListJson, Type type) {
        return gson.fromJson(studentListJson, type);
    }

    public static University getUniversityClassFromJson(String universityJson) {
        return gson.fromJson(universityJson, University.class);
    }

    public static List<University> getUniversityListFromJson(String universityListJson, Type type) {
        return gson.fromJson(universityListJson, type);
    }
}
