package ua.berest.lab3.controller;

import com.berest.beans.student.*;
import org.apache.log4j.Logger;
import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.*;
import ua.berest.lab3.model.Student;

import java.rmi.RemoteException;
import java.util.*;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

/**
 * Created by Oleg on 26.01.2016.
 */
public class OracleDataAccess implements ModelDataAccess {
    static final Logger logger = Logger.getLogger(OracleDataAccess.class);
    private static final OracleDataAccess instance = new OracleDataAccess();

    private OracleDataAccess() {
    }

    public static OracleDataAccess getInstance() {
        return instance;
    }

    @Override
    public List<Student> getAllStudents() throws DataAccessException {
        List<Student> lStudent = new ArrayList<Student>();
        ArrayList<StudentRemote> lId;
        StudentRemote studentRemote;
        Student student;
        StudentHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("StudentEJB");
            home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<StudentRemote>) home.findAllStudents();
            System.out.println("Student id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Student was added with id: " + lId.get(i).getStudentId() + "; Student name by id: " + lId.get(i).getName());
                studentRemote = home.findByPrimaryKey(lId.get(i).getStudentId());
                student = new StudentImpl(studentRemote.getStudentId(), studentRemote.getName(), studentRemote.getGroup(), studentRemote.getMail(), studentRemote.getPhone(), studentRemote.getAddress());
                lStudent.add(student);
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lStudent;
    }

    @Override
    public List<Student> getAllStudentsInCourse(int courseId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Student> getAllStudentsOutOfCourse(int courseId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Location> getAllLocationsByParentId(int locationId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Grade> getAllGradesByCourseAndStudent(int courseId, int studentId) throws DataAccessException {
        return null;
    }

    @Override
    public void addStudent(Student student) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("StudentEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        StudentHome home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        try {
            home.create(null, student.getFio(), student.getGroup(), student.getMail(), student.getPhone(), student.getAddress());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't insert new data due to RemoteException", e);
        } catch (CreateException e) {
            throw new DataAccessException("Can't insert new data due to CreateException", e);
        }
    }

    @Override
    public void addLocation(Location location) throws DataAccessException {

    }

    @Override
    public void addCourse(Course course) throws DataAccessException {

    }

    @Override
    public void addGrade(Grade grade) throws DataAccessException {

    }

    @Override
    public void removeStudent(int studentId) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("StudentEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }

        StudentHome home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        try {
            try {
                home.findByPrimaryKey(studentId);
            } catch (FinderException e) {
                throw new DataAccessException("Can't delete data due to FinderException", e);
            }
            home.remove(studentId);
        } catch (RemoteException e) {
            throw new DataAccessException("Can't delete data due to RemoteException", e);
        } catch (RemoveException e) {
            throw new DataAccessException("Can't delete data due to RemoveException", e);
        }
    }

    @Override
    public void removeLocation(int locationId) throws DataAccessException {

    }

    @Override
    public void removeCourse(int courseId) throws DataAccessException {

    }

    @Override
    public void removeGrade(int gradeId) throws DataAccessException {

    }

    @Override
    public void updateStudent(Student student) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("StudentEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        StudentHome home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        try {
            home.updateById(student.getStudentId(), student.getFio(), student.getGroup(), student.getMail(), student.getPhone(), student.getAddress());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't update data due to RemoteException", e);
        }
    }

    @Override
    public void updateLocation(Location location) throws DataAccessException {

    }

    @Override
    public void updateCourse(Course course) throws DataAccessException {

    }

    @Override
    public void updateGrade(Grade grade) throws DataAccessException {

    }

    @Override
    public void enrollStudent(int studentId, int courseId) throws DataAccessException {

    }

    @Override
    public void removeEnrollment(int studentId, int courseId) throws DataAccessException {

    }

    @Override
    public Student getStudentById(int studentId) throws DataAccessException {
        StudentRemote studentRemote;
        Student student;
        try {
            Context initial = new InitialContext();
            Object objref = initial.lookup("StudentEJB");
            StudentHome home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
            studentRemote = home.findByPrimaryKey(studentId);
            student = new StudentImpl(studentRemote.getStudentId(), studentRemote.getName(), studentRemote.getGroup(), studentRemote.getMail(), studentRemote.getPhone(), studentRemote.getAddress());
        } catch (Exception e)
        {
            throw new DataAccessException("Can't get student by id", e);
        }
        return student;
    }

    @Override
    public Course getCourseById(int courseId) throws DataAccessException {
        return null;
    }

    @Override
    public Location getLocationById(int locationId) throws DataAccessException {
        return null;
    }

    @Override
    public Grade getGradeById(int gradeId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Course> getCoursesByLocationId(int locationId) throws DataAccessException {
        return null;
    }

    @Override
    public Map<Integer, String> getLocationHierarchy(int locationId) throws DataAccessException {
        return null;
    }

    @Override
    public Map<Integer, String> getAllLocations() throws DataAccessException {
        return null;
    }

    @Override
    public int getTotalCountOfStudents() throws DataAccessException {
        ArrayList<StudentRemote> lId;
        StudentHome home = null;
        Context initial = null;
        try {
            initial = new InitialContext();
            Object objref = initial.lookup("StudentEJB");
            home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }
        try {
            lId = (ArrayList<StudentRemote>) home.findAllStudents();
            System.out.println("Student id list: " + lId.size());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lId.size();
    }

    @Override
    public List<Student> getAllStudentsByPage(int startIndex, int range) throws DataAccessException {
        List<Student> lStudent = new ArrayList<Student>();
        ArrayList<StudentRemote> lId;
        StudentRemote studentRemote;
        Student student;
        StudentHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("StudentEJB");
            home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<StudentRemote>) home.findAllStudentsByPage(startIndex, range);
            System.out.println("Student id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Student was added with id: " + lId.get(i).getStudentId() + "; Student name by id: " + lId.get(i).getName());
                studentRemote = home.findByPrimaryKey(lId.get(i).getStudentId());
                student = new StudentImpl(studentRemote.getStudentId(), studentRemote.getName(), studentRemote.getGroup(), studentRemote.getMail(), studentRemote.getPhone(), studentRemote.getAddress());
                lStudent.add(student);
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lStudent;
    }
}
