package ua.berest.lab3.model;

import java.sql.Date;

/**
 * Created by Oleg on 26.01.2016.
 */
public interface Grade {
    int getGradeId();
    void setGradeId(int gradeId);
    int getCourseId();
    void setCourseId(int courseId);
    int getStudentId();
    void setStudentId(int studentId);
    int getCredits();
    void setCredits(int credits);
    Date getDate();
    void setDate(Date date);
    String getDescription();
    void setDescription(String description);
}
