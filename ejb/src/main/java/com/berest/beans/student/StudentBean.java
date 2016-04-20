package com.berest.beans.student;

//import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Oleg on 04.04.2016.
 */
public class StudentBean implements EntityBean {
    //static final Logger logger = Logger.getLogger(StudentBean.class);
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
        Connection connection = connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.studentId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENT_ID = ?");
            statement.setString(1, String.valueOf(this.studentId));
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
            e.printStackTrace();
        }
        finally {
            disconnect(connection, result, statement);
        }
    }

    public void ejbStore() throws EJBException{
        System.out.println("Student bean method ejbStore() was called.");
        //logger.info("Student bean method ejbStore() was called.");
        Connection connection = connect();
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
            e.printStackTrace();
        }
        finally {
            disconnect(connection, result, statement);
        }
    }

    public void ejbRemove() throws RemoveException, EJBException {
        System.out.println("Student bean method ejbRemove() was called.");
        //logger.info("Student bean method ejbRemove() was called.");
        Connection connection = connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.studentId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENT_ID = ?");
            statement.setString(1, String.valueOf(this.studentId));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            disconnect(connection, result, statement);
        }
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        System.out.println("Student bean method ejbFindByPrimaryKey() was called.");
        //logger.info("Student bean method ejbFindByPrimaryKey() was called.");
        Connection connection = connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENT_ID = ?");
            statement.setInt(1, key);
            result = statement.executeQuery();
            if(result.next()) {
                this.studentId = result.getInt("STUDENT_ID");
                this.name = result.getString("STUDENT_FIO");
                this.group = result.getString("STUDENT_GROUP");
                this.mail = result.getString("MAIL");
                this.phone = result.getString("PHONE_NUMBER");
                this.address = result.getString("ADDRESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            disconnect(connection, result, statement);
        }
        return key;
    }

    public Integer ejbCreate(Integer id, String name, String group, String mail, String phoneNo, String address) throws CreateException {
        System.out.println("Student bean method ejbCreate() was called.");
        //logger.info("Student bean method ejbCreate() was called.");
        Connection connection = connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO STUDENTS (STUDENT_ID, STUDENT_FIO, STUDENT_GROUP, MAIL, PHONE_NUMBER, ADDRESS) VALUES (NULL, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, group);
            statement.setString(3, mail);
            statement.setString(4, phoneNo);
            statement.setString(5, address);
            statement.execute();
            statement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENT_FIO = ? AND STUDENT_GROUP = ? AND MAIL = ? AND PHONE_NUMBER = ? AND ADDRESS = ?");
            statement.setString(1, name);
            statement.setString(2, group);
            statement.setString(3, mail);
            statement.setString(4, phoneNo);
            statement.setString(5, address);
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
            e.printStackTrace();
        }
        finally {
            disconnect(connection, result, statement);
        }
        return id;
    }

    public void ejbPostCreate(Integer id, String name, String group, String mail, String phoneNo, String address) throws CreateException {
        System.out.println("Student bean method ejbPostCreate() was called.");
        //logger.info("Student bean method ejbPostCreate() was called.");
    }

    private Connection connect(){
        DataSource ds;
        Context ctx;
        Hashtable ht = new Hashtable();
        Connection connection = null;

        ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL, "t3://localhost:7001");
        try {
            ctx = new InitialContext(ht);
            ds = (javax.sql.DataSource) ctx.lookup("JNDI_Name0");
            try {
                connection = ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void disconnect(Connection connection, ResultSet result, Statement statement) {
        try {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
            if(result != null)
                result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection ejbFindAllStudents() {
        System.out.println("Student bean method ejbFindAllStudents() was called.");
        //logger.info("Student bean method ejbFindAllStudents() was called.");
        Connection connection = connect();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            disconnect(connection, result, statement);
        }
        return lStudent;
    }
}
