package com.berest.beans.grade;

import com.berest.connection.DataSourceConnection;

import javax.ejb.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by Oleg on 16.05.2016.
 */
public class GradeBean implements EntityBean {
    private Integer gradeId;
    private Integer studentId;
    private Integer courseId;
    private Date date;
    private int credits;
    private String description;
    private EntityContext context;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntityContext getContext() {
        return context;
    }

    public GradeBean() {
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        System.out.println("Grade bean method ejbFindByPrimaryKey() was called.");
        //logger.info("Student bean method ejbFindByPrimaryKey() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM GRADES WHERE GRADE_ID = ?");
            statement.setInt(1, key);
            result = statement.executeQuery();
            if(result.next()) {
                return key;
            }
            else {
                throw new EJBException("Can't load data by id due to SQLException");
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data by id due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
        //logger.info("Student bean context was set.");
        System.out.println("Grade bean context was set.");
        this.context = entityContext;
    }

    public void unsetEntityContext() throws EJBException {
        System.out.println("Grade bean context was unset.");
        //logger.info("Student bean context was unset.");
        this.context = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
        System.out.println("Grade bean method ejbRemove() was called.");
        //logger.info("Student bean method ejbRemove() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.gradeId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("DELETE FROM GRADES WHERE GRADE_ID = ?");
            statement.setInt(1, this.gradeId);
            statement.execute();
        } catch (SQLException e) {
            throw new EJBException("Can't delete data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbActivate() throws EJBException {
        //logger.info("Student bean was activated.");
        System.out.println("Grade bean was activated.");
        this.gradeId = (Integer) context.getPrimaryKey();
    }

    public void ejbPassivate() throws EJBException {
        System.out.println("Grade bean was passivated.");
        //logger.info("Student bean was passivated.");
    }

    public void ejbLoad() throws EJBException {
        System.out.println("Grade bean method ejbLoad() was called.");
        //logger.info("Student bean method ejbLoad() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.gradeId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("SELECT * FROM GRADES WHERE GRADE_ID = ?");
            statement.setInt(1, this.gradeId);
            result = statement.executeQuery();
            if(result.next()) {
                this.gradeId = result.getInt("GRADE_ID");
                this.studentId = result.getInt("STUDENT_ID");
                this.courseId = result.getInt("COURSE_ID");
                this.date = result.getDate("DATE_OF_EVALUATION");
                this.credits = result.getInt("CREDITS");
                this.description = result.getString("DESCRIPTION");
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbStore() throws EJBException {
        System.out.println("Grade bean method ejbStore() was called.");
        //logger.info("Student bean method ejbStore() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE GRADES SET STUDENT_ID = ?, COURSE_ID = ?, DATE_OF_EVALUATION = ?, CREDITS = ?, DESCRIPTION =? WHERE GRADE_ID = ?");
            statement.setInt(1, getStudentId());
            statement.setInt(2, getCourseId());
            statement.setDate(3, getDate());
            statement.setInt(4, getCredits());
            statement.setString(5, getDescription());
            statement.setInt(6, getGradeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Integer ejbCreate(Integer gradeId, Integer studentId, Integer courseId, Date date, int credits, String description) throws CreateException {
        String key[] = {"GRADE_ID"};
        System.out.println("Grade bean method ejbCreate() was called.");
        long k;
        //logger.info("Student bean method ejbCreate() was called.");

        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO GRADES(GRADE_ID, STUDENT_ID, COURSE_ID, DATE_OF_EVALUATION, CREDITS, DESCRIPTION) VALUES (NULL, ?, ?, ?, ?, ?)", key);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setDate(3, date);
            statement.setInt(4, credits);
            statement.setString(5, description);
            statement.execute();
            result = statement.getGeneratedKeys();
            System.out.println("Auto Generated Primary Key 1: " + result.toString());
            if (result.next()) {
                k = result.getLong(1);
                System.out.println("Auto Generated Primary Key " + k);
                gradeId = toIntExact(k);
                this.gradeId = gradeId;
                this.studentId = studentId;
                this.courseId = courseId;
                this.date = date;
                this.credits = credits;
                this.description = description;
                System.out.println("Auto Generated Primary Key int " + gradeId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't create new data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return gradeId;
    }

    public void ejbPostCreate(Integer gradeId, Integer studentId, Integer courseId, Date date, int credits, String description) throws CreateException {

    }

    public void ejbHomeUpdateById(Integer gradeId, Integer studentId, Integer courseId, Date date, int credits, String description) {
        System.out.println("Grade bean method update() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE GRADES SET STUDENT_ID = ?, COURSE_ID = ?, DATE_OF_EVALUATION = ?, CREDITS = ?, DESCRIPTION =? WHERE GRADE_ID = ?");
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setDate(3, date);
            statement.setInt(4, credits);
            statement.setString(5, description);
            statement.setInt(6, gradeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Collection ejbFindAllGradesByCourseAndStudent(int courseId, int studentId) throws FinderException {
        System.out.println("Grade bean method ejbFindAllStudentsInCourse() was called.");
        //logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lGrade = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT * FROM GRADES WHERE STUDENT_ID = ? AND COURSE_ID = ?");
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            result = statement.executeQuery();
            while(result.next()){
                this.gradeId = result.getInt("GRADE_ID");
                lGrade.add(this.gradeId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lGrade;
    }
}
