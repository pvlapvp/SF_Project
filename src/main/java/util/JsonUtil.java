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
    public static String writeListToJson(List<?> list) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(list);
    }
}
