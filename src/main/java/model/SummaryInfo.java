package model;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class SummaryInfo {
    @XmlElementWrapper(name = "studentsInfo")
    @XmlElement(name = "studentEntry")
    private List<Student> studentList;
    @XmlElementWrapper(name = "universityInfo")
    @XmlElement(name = "universityEntry")
    private List<University> universityList;
    @XmlElementWrapper(name = "statisticsInfo")
    @XmlElement(name = "statisticsEntry")
    private List<Statistics> statisticsList;

    @XmlElement(name = "processedAt")
    private Date processDate;

    public SummaryInfo() {
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public SummaryInfo setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        return this;
    }

    public List<University> getUniversityList() {
        return universityList;
    }

    public SummaryInfo setUniversityList(List<University> universityList) {
        this.universityList = universityList;
        return this;
    }

    public List<Statistics> getStatisticsList() {
        return statisticsList;
    }

    public SummaryInfo setStatisticsList(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
        return this;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public SummaryInfo setProcessDate(Date processDate) {
        this.processDate = processDate;
        return this;
    }
}
