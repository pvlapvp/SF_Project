package util;

import model.Statistics;
import model.Student;
import model.StudyProfile;
import model.University;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

//утилитный класс для обработки коллекций студентов и университетов
public class CollectionHandler {


    public static List<Statistics> getStatisticList(List<Student> studentList, List<University> universityList) {
        final List<Statistics> statisticsList = new ArrayList<>();
        //по каждому профилю проходимся и вначале отбираем университеты с текущим профилем
        for (StudyProfile studyProfile: StudyProfile.values()){
            //если университеты с таким профилем есть, отсеиваем университеты без студентов, точнее, выбираем только университеты со студентами
            if (universityList.stream().noneMatch(university -> university.getMainProfile().equals(studyProfile))) continue;
            universityList
                    .stream()
                    .filter(university -> university.getMainProfile().equals(studyProfile))
                    .forEach(university -> {
                        if (studentList.stream().noneMatch(student -> student.getUniversityId().equals(university.getId()))) {
                            //здесь мы уже знаем, что в этом универсистеты студенты есть, формируем статистику
                            Statistics statistics = new Statistics("", 0, 0, "");
                            statistics.setStudyProfile(studyProfile.name());
                            statistics.setProfileStudentsAmount(studentList
                                    .stream()
                                    .filter(student -> student.getUniversityId().equals(university.getId()))
                                    .count()
                            );
                            statistics.setUniversityName(university.getFullName());

                            //используем удобный класс для подсчета всяких статистических штучек, вроде среднего, максимального и т.д.
                            DoubleSummaryStatistics summaryStatistics = new DoubleSummaryStatistics();
                            studentList.stream()
                                    .filter(student -> student.getUniversityId().equals(university.getId()))
                                    .forEach(student -> {
                                        summaryStatistics.accept(student.getAvgExamScore());
                                    });

                            BigDecimal bigAvgExamScore = BigDecimal.valueOf(summaryStatistics.getAverage());
                            bigAvgExamScore = bigAvgExamScore.setScale(2, RoundingMode.FLOOR);
                            statistics.setAvgExamScore(bigAvgExamScore.doubleValue());
                            statisticsList.add(statistics);
                        }
                    });
        }

        return statisticsList;
    }
    //TODO: реализовать метод, получающий на вход коллекции студентов и университетов, возвращающий коллекцию элементов класса Statistics
    //TODO: С помощью Java Stream API собрать статистику.
    // Необходимо для каждого профиля обучения, по которому есть хотя бы один университет, создать экземпляр класса Statistics. Заполнить все его поля
}
