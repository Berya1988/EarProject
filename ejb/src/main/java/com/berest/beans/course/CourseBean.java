package com.berest.beans.course;

import com.berest.connection.DataSourceConnection;

import javax.ejb.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by Oleg on 16.05.2016.
 */
public class CourseBean implements EntityBean {
    private Integer courseId;
    private String name;
    private String description;
    private String teacher;
    private Integer locationId;
    private EntityContext context;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public EntityContext getContext() {
        return context;
    }

    public CourseBean() {
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        System.out.println("Course bean method ejbFindByPrimaryKey() was called.");
        //logger.info("Student bean method ejbFindByPrimaryKey() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM COURSES WHERE COURSE_ID = ?");
            statement.setInt(1, key);
            result = statement.executeQuery();
            if(result.next()) {
                return key;
            }
            else {
                throw new EJBException("Can't load data by id  due to SQLException");
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data by id  due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
        //logger.info("Student bean context was set.");
        System.out.println("Course bean context was set.");
        this.context = entityContext;
    }

    public void unsetEntityContext() throws EJBException {
        System.out.println("Course bean context was unset.");
        //logger.info("Student bean context was unset.");
        this.context = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
        System.out.println("Course bean method ejbRemove() was called.");
        //logger.info("Student bean method ejbRemove() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.courseId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("DELETE FROM COURSES WHERE COURSE_ID = ?");
            statement.setInt(1, this.courseId);
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
        System.out.println("Course bean was activated.");
        this.courseId = (Integer) context.getPrimaryKey();
    }

    public void ejbPassivate() throws EJBException {
        System.out.println("Course bean was passivated.");
        //logger.info("Student bean was passivated.");
    }

    public void ejbLoad() throws EJBException {
        System.out.println("Course bean method ejbLoad() was called.");
        //logger.info("Student bean method ejbLoad() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.courseId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("SELECT * FROM COURSES WHERE COURSE_ID  = ?");
            statement.setInt(1,this.courseId);
            result = statement.executeQuery();
            if(result.next()) {
                this.courseId = result.getInt("COURSE_ID");
                this.name = result.getString("NAME");
                this.description = result.getString("DESCRIPTION");
                this.teacher = result.getString("TEACHER");
                this.locationId = result.getInt("LOCATION_ID");
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbStore() throws EJBException {
        System.out.println("Course bean method ejbStore() was called.");
        //logger.info("Student bean method ejbStore() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE COURSES SET LOCATION_ID = ?, NAME = ?, DESCRIPTION = ?, TEACHER = ? WHERE COURSE_ID = ?");
            statement.setInt(1, getLocationId());
            statement.setString(2, getName());
            statement.setString(3, getDescription());
            statement.setString(4, getTeacher());
            statement.setInt(5, getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Integer ejbCreate(Integer courseId, String name, String description, String teacher, Integer locationId) throws CreateException {
        String key[] = {"COURSE_ID"};
        System.out.println("Course bean method ejbCreate() was called.");
        long k;
        //logger.info("Student bean method ejbCreate() was called.");

        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO COURSES(COURSE_ID, LOCATION_ID, NAME, DESCRIPTION, TEACHER) VALUES (NULL, ?, ?, ?, ?)", key);
            statement.setInt(1, locationId);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, teacher);
            statement.execute();
            result = statement.getGeneratedKeys();
            System.out.println("Auto Generated Primary Key 1: " + result.toString());
            if (result.next()) {
                k = result.getLong(1);
                System.out.println("Auto Generated Primary Key " + k);

                courseId = toIntExact(k);
                this.courseId = courseId;
                this.name = name;
                this.description = description;
                this.teacher = teacher;
                this.locationId = locationId;
                System.out.println("Auto Generated Primary Key int " + courseId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't create new data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return courseId;
    }

    public void ejbPostCreate(Integer courseId, String name, String description, String treacher, Integer locationId) throws CreateException {

    }

    public void ejbHomeUpdateById(Integer courseId, String name, String description, String teacher, Integer locationId) {
        System.out.println("Course bean method update() was called.");
        //logger.info("Student bean method ejbStore() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE COURSES SET LOCATION_ID = ?, NAME = ?, DESCRIPTION = ?, TEACHER = ? WHERE COURSE_ID = ?");
            statement.setInt(1, getLocationId());
            statement.setString(2, getName());
            statement.setString(3, getDescription());
            statement.setString(4, getTeacher());
            statement.setInt(5, getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Collection ejbFindAllCoursesByLocationId(int locationId) throws FinderException {
        System.out.println("Course bean method ejbFindAllCoursesByLocationId() was called.");
        //logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lCourse = new ArrayList<Integer>();
        try {
            if(locationId == 0){
                statement = connection.prepareStatement("SELECT * FROM COURSES WHERE LOCATION_ID IS NULL");
            }
            else{
                statement = connection.prepareStatement("SELECT * FROM COURSES WHERE LOCATION_ID = ?");
                statement.setInt(1, locationId);
            }
            result = statement.executeQuery();
            while(result.next()){
                this.courseId = result.getInt("COURSE_ID");
                lCourse.add(this.courseId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lCourse;
    }

    public Collection ejbFindAllCoursesByStudentId(int studentId) throws FinderException {
        System.out.println("Course bean method ejbFindAllCoursesByStudentId() was called.");
        //logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lCourse = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT STUDENTS.STUDENT_FIO, COURSES.NAME, COURSES.COURSE_ID FROM STUDENTS, COURSES, ENROLLMENT WHERE COURSES.COURSE_ID = ENROLLMENT.COURSE_ID AND STUDENTS.STUDENT_ID = ENROLLMENT.STUDENT_ID AND STUDENTS.STUDENT_ID = ?");
            statement.setInt(1, studentId);
            result = statement.executeQuery();
            while(result.next()){
                this.courseId = result.getInt("COURSE_ID");
                lCourse.add(this.courseId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lCourse;
    }
}
