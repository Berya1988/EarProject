package ua.berest.lab3.model;

import java.sql.Date;

/**
 * Created by Oleg on 24.01.2016.
 */
public class GradeImpl implements Grade {
    private int gradeId;
    private int studentId;
    private int courseId;
    private Date date;
    private int credits;
    private String description;

    public GradeImpl(int gradeId, int studentId, int courseId, Date date, int credits, String description) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.credits = credits;
        this.description = description;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Date getDate() {
        return (Date)this.date.clone();
    }

    public void setDate(Date date) {
        this.date = (Date)date.clone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GradeImpl other = (GradeImpl) obj;
        return !(gradeId != other.gradeId || courseId != other.courseId || studentId != other.studentId || credits != other.credits || !date.equals(other.date) || !description.equals(other.description));
    }

    @Override
    protected Object clone() {
        try{
            GradeImpl copy = (GradeImpl)super.clone();
            copy.date = getDate();
            return copy;
        }
        catch(CloneNotSupportedException e){
            throw new AssertionError("Impossible");
        }
    }

    @Override
    public String toString() {
        return "Grade id = " + gradeId + ", course id = " + courseId + ", student id = " + studentId + ", credits = " + credits + ", date = " + date+ ", description = " + description + ".";
    }
}
