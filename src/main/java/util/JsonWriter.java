package util;

import model.SummaryInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonWriter {
    private static final Logger logger = LogManager.getLogger(JsonWriter.class.getName());

    private JsonWriter(){}

    public static void writeJson(SummaryInfo summaryInfo) {
        logger.info("JSON writing started");
        try {
            if (Files.notExists(Paths.get("JSONs"))) {
                Files.createDirectory(Paths.get("JSONs"));
                logger.info("Directory created successfully");
            }
        } catch (IOException ex) {
            logger.warn("Directory has already created", ex);
        }

        writeStudents(summaryInfo);
        writeUniversities(summaryInfo);
        writeStatisticsList(summaryInfo);

        logger.info("JSON writing finished");
    }

    private static void writeStudents(SummaryInfo summaryInfo) {
        String studentsJson = JsonUtil.writeListToJson(summaryInfo.getStudentList());
        try(FileOutputStream outputStream =
                    new FileOutputStream("JSONs/students" + summaryInfo.getProcessDate().getTime() + ".json")) {
            outputStream.write(studentsJson.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("Students JSON writing failed", e);
        }
    }

    private static void writeUniversities(SummaryInfo summaryInfo) {
        String universitiesJson = JsonUtil.writeListToJson(summaryInfo.getUniversityList());
        try(FileOutputStream outputStream =
                    new FileOutputStream("JSONs/universities" + summaryInfo.getProcessDate().getTime() + ".json")) {
            outputStream.write(universitiesJson.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("Universities JSON writing failed", e);
        }
    }

    private static void writeStatisticsList(SummaryInfo summaryInfo) {
        String studentsJson = JsonUtil.writeListToJson(summaryInfo.getStatisticsList());
        try(FileOutputStream outputStream =
                    new FileOutputStream("JSONs/statistics" + summaryInfo.getProcessDate().getTime() + ".json")) {
            outputStream.write(studentsJson.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("Statistics JSON writing failed", e);
        }
    }
}
