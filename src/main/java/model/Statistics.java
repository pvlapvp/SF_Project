package model;

/*
класс Statistics (с конструктором, геттерами и сеттерами), в котором должны быть следующие поля:
профиль обучения,
средний балл за экзамен,
количество студентов по профилю,
количество университетов по профилю,
названия университетов.
 */
public class Statistics {
    private String studyProfile;
    private double avgExamScore;
    private double profileStudentsAmount;
    private String universityName;

    public Statistics(String studyProfile, double avgExamScore, double profileStudentsAmount, String universityName) {
        this.studyProfile = studyProfile;
        this.avgExamScore = avgExamScore;
        this.profileStudentsAmount = profileStudentsAmount;
        this.universityName = universityName;
    }

    public String getStudyProfile() {
        return studyProfile;
    }

    public void setStudyProfile(String studyProfile) {
        this.studyProfile = studyProfile;
    }

    public double getAvgExamScore() {
        return avgExamScore;
    }

    public void setAvgExamScore(double avgExamScore) {
        this.avgExamScore = avgExamScore;
    }

    public double getProfileStudentsAmount() {
        return profileStudentsAmount;
    }

    public void setProfileStudentsAmount(double profileStudentsAmount) {
        this.profileStudentsAmount = profileStudentsAmount;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
