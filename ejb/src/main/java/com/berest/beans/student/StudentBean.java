package com.berest.beans.student;

import com.berest.connection.DataSourceConnection;
import org.apache.log4j.Logger;

import javax.ejb.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by Oleg on 04.04.2016.
 */
public class StudentBean implements EntityBean {
    static final Logger logger = Logger.getLogger(StudentBean.class);
    private Integer studentId;
    private String name;
    private String group;
    private String mail;
    private String phone;
    private String address;
    private EntityContext context;

    public Integer getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public StudentBean() {
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
        //logger.info("Student bean context was set.");
        System.out.println("Student bean context was set.");
        this.context = entityContext;
    }

    public void unsetEntityContext() throws EJBException {
        System.out.println("Student bean context was unset.");
        //logger.info("Student bean context was unset.");
        this.context = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
        System.out.println("Student bean method ejbRemove() was called.");
        //logger.info("Student bean method ejbRemove() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.studentId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENT_ID = ?");
            statement.setInt(1, this.studentId);
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
        System.out.println("Student bean was activated.");
        this.studentId = (Integer) context.getPrimaryKey();
    }

    public void ejbPassivate() throws EJBException {
        System.out.println("Student bean was passivated.");
        //logger.info("Student bean was passivated.");
    }

    public void ejbLoad() throws EJBException {
        System.out.println("Student bean method ejbLoad() was called.");
        //logger.info("Student bean method ejbLoad() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.studentId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENT_ID = ?");
            statement.setInt(1, this.studentId);
            result = statement.executeQuery();
            if(result.next()) {
                this.studentId = result.getInt("STUDENT_ID");
                this.name = result.getString("STUDENT_FIO");
                this.group = result.getString("STUDENT_GROUP");
                this.mail = result.getString("MAIL");
                this.phone = result.getString("PHONE_NUMBER");
                this.address = result.getString("ADDRESS");
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbStore() throws EJBException{
        System.out.println("Student bean method ejbStore() was called.");
        //logger.info("Student bean method ejbStore() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE STUDENTS SET STUDENT_FIO = ?, STUDENT_GROUP = ?, MAIL = ?, PHONE_NUMBER = ?, ADDRESS = ? WHERE STUDENT_ID = ?");
            statement.setString(1, getName());
            statement.setString(2, getGroup());
            statement.setString(3, getMail());
            statement.setString(4, getPhone());
            statement.setString(5, getAddress());
            statement.setInt(6, getStudentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        System.out.println("Student bean method ejbFindByPrimaryKey() was called.");
        //logger.info("Student bean method ejbFindByPrimaryKey() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENT_ID = ?");
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

    public Integer ejbCreate(Integer id, String name, String group, String mail, String phoneNo, String address) throws CreateException {
        String key[] = {"STUDENT_ID"};
        System.out.println("Student bean method ejbCreate() was called.");
        long k;
        //logger.info("Student bean method ejbCreate() was called.");

        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO STUDENTS (STUDENT_ID, STUDENT_FIO, STUDENT_GROUP, MAIL, PHONE_NUMBER, ADDRESS) VALUES (null, ?, ?, ?, ?, ?)", key);
            statement.setString(1, name);
            statement.setString(2, group);
            statement.setString(3, mail);
            statement.setString(4, phoneNo);
            statement.setString(5, address);
            statement.execute();
            result = statement.getGeneratedKeys();
            System.out.println("Auto Generated Primary Key 1: " + result.toString());
            if (result.next()) {
                k = result.getLong(1);
                System.out.println("Auto Generated Primary Key " + k);

                id = toIntExact(k);
                this.studentId = id;
                this.name = name;
                this.group = group;
                this.address = address;
                this.phone = phoneNo;
                this.mail = mail;
                System.out.println("Auto Generated Primary Key int " + id);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't create new data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return id;
    }

    public void ejbPostCreate(Integer id, String name, String group, String mail, String phoneNo, String address) throws CreateException {
        System.out.println("Student bean method ejbPostCreate() was called.");
        //logger.info("Student bean method ejbPostCreate() was called.");
    }

    public Collection ejbFindAllStudents() throws FinderException {
        System.out.println("Student bean method ejbFindAllStudents() was called.");
        logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        System.out.println("Connected!");
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lStudent = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT STUDENT_ID FROM STUDENTS ORDER BY STUDENT_FIO");
            result = statement.executeQuery();
            while(result.next()){
                this.studentId = result.getInt("STUDENT_ID");
                lStudent.add(this.studentId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lStudent;
    }

    public Collection ejbFindAllStudentsByPage(int page, int range) throws FinderException {
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lStudent = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT * FROM ( SELECT p.*, ROWNUM rn FROM (SELECT STUDENTS.* FROM STUDENTS ORDER BY STUDENT_FIO)p WHERE ROWNUM < 10000)WHERE rn BETWEEN ? AND ?");
            statement.setInt(1, ((page - 1) * range + 1));
            statement.setInt(2, (page * range));
            //logger.info("From " + ((page - 1) * range + 1) + " to " + (page * range));
            result = statement.executeQuery();
            while(result.next()){
                this.studentId = result.getInt("STUDENT_ID");
                lStudent.add(this.studentId);           }
        } catch (Exception e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lStudent;
    }

    public void ejbHomeUpdateById(Integer id, String name, String group, String mail, String phoneNo, String address) {
        System.out.println("Student bean method update() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE STUDENTS SET STUDENT_FIO = ?, STUDENT_GROUP = ?, MAIL = ?, PHONE_NUMBER = ?, ADDRESS = ? WHERE STUDENT_ID = ?");
            statement.setString(1, name);
            statement.setString(2, group);
            statement.setString(3, mail);
            statement.setString(4, phoneNo);
            statement.setString(5, address);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Collection ejbFindAllStudentsInCourse(int courseId) throws FinderException {
        System.out.println("Student bean method ejbFindAllStudentsInCourse() was called.");
        //logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lStudent = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT * FROM STUDENTS, ENROLLMENT WHERE ENROLLMENT.STUDENT_ID = STUDENTS.STUDENT_ID AND ENROLLMENT.COURSE_ID = ? ORDER BY STUDENT_FIO");
            statement.setInt(1, courseId);
            result = statement.executeQuery();
            while(result.next()){
                this.studentId = result.getInt("STUDENT_ID");
                lStudent.add(this.studentId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lStudent;
    }

    public Collection ejbFindAllStudentsOutOfCourse(int courseId) throws FinderException {
        System.out.println("Student bean method ejbFindAllStudentsInCourse() was called.");
        //logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lStudent = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("(SELECT s.STUDENT_ID, s.STUDENT_FIO, s.STUDENT_GROUP, s.MAIL, s.PHONE_NUMBER, s.ADDRESS FROM STUDENTS s) MINUS (SELECT s.STUDENT_ID, s.STUDENT_FIO, s.STUDENT_GROUP, s.MAIL, s.PHONE_NUMBER, s.ADDRESS FROM STUDENTS s, ENROLLMENT WHERE ENROLLMENT.STUDENT_ID = s.STUDENT_ID AND ENROLLMENT.COURSE_ID = ?) ORDER BY STUDENT_FIO");
            statement.setInt(1, courseId);
            result = statement.executeQuery();
            while(result.next()){
                this.studentId = result.getInt("STUDENT_ID");
                lStudent.add(this.studentId);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lStudent;
    }

    public void ejbHomeEnrollStudent(int studentId, int courseId) {
        System.out.println("Student bean method ejbHomeEnrollStudent() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO ENROLLMENT (STUDENT_ID, COURSE_ID, FINALGRADE) VALUES (?, ?, NULL)");
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbHomeDeleteEnrollment(int studentId, int courseId) {
        System.out.println("Student bean method ejbHomeEnrollStudent() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM ENROLLMENT WHERE STUDENT_ID = ? AND COURSE_ID = ?");            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }
}
