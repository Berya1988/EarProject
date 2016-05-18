package ua.berest.lab3.controller;

import com.berest.beans.course.CourseHome;
import com.berest.beans.course.CourseRemote;
import com.berest.beans.grade.GradeHome;
import com.berest.beans.grade.GradeRemote;
import com.berest.beans.location.LocationHome;
import com.berest.beans.location.LocationRemote;
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
            lId = (ArrayList<StudentRemote>) home.findAllStudentsInCourse(courseId);
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
    public List<Student> getAllStudentsOutOfCourse(int courseId) throws DataAccessException {
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
            lId = (ArrayList<StudentRemote>) home.findAllStudentsOutOfCourse(courseId);
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
    public List<Location> getAllLocationsByParentId(int locationId) throws DataAccessException {
        List<Location> lLocation = new ArrayList<Location>();
        ArrayList<LocationRemote> lId;
        LocationRemote locationRemote;
        Location location;
        LocationHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("LocationEJB");
            home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<LocationRemote>) home.findAllLocationsByParentId(locationId);
            System.out.println("Location id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Location was added with id: " + lId.get(i).getLocationId() + "; Location name by id: " + lId.get(i).getName());
                locationRemote = home.findByPrimaryKey(lId.get(i).getLocationId());
                location = new LocationImpl(locationRemote.getLocationId(), locationRemote.getName(), locationRemote.getParentId(), locationRemote.isCourse(), locationRemote.getDescription());
                lLocation.add(location);
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lLocation;
    }

    @Override
    public List<Grade> getAllGradesByCourseAndStudent(int courseId, int studentId) throws DataAccessException {
        List<Grade> lGrade = new ArrayList<Grade>();
        ArrayList<GradeRemote> lId;
        GradeRemote gradeRemote;
        Grade grade;
        GradeHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("GradeEJB");
            home = (GradeHome) PortableRemoteObject.narrow(objref, GradeHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<GradeRemote>) home.findAllGradesByCourseAndStudent(courseId, studentId);
            System.out.println("Grade id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Grade was added with id: " + lId.get(i).getGradeId());
                gradeRemote = home.findByPrimaryKey(lId.get(i).getGradeId());
                grade = new GradeImpl(gradeRemote.getGradeId(), gradeRemote.getStudentId(), gradeRemote.getCourseId(), gradeRemote.getDate(), gradeRemote.getCredits(), gradeRemote.getDescription());
                lGrade.add(grade);
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lGrade;
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
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("LocationEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        LocationHome home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
        try {
            home.create(null, location.getName(), location.getParentId(), location.getCourse(),  location.getDescription());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't insert new data due to RemoteException", e);
        } catch (CreateException e) {
            throw new DataAccessException("Can't insert new data due to CreateException", e);
        }
    }

    @Override
    public void addCourse(Course course) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("CourseEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        CourseHome home = (CourseHome) PortableRemoteObject.narrow(objref, CourseHome.class);
        try {
            home.create(null, course.getName(), course.getDescription(), course.getTeacher(), course.getLocationId());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't insert new data due to RemoteException", e);
        } catch (CreateException e) {
            throw new DataAccessException("Can't insert new data due to CreateException", e);
        }
    }

    @Override
    public void addGrade(Grade grade) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("GradeEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        GradeHome home = (GradeHome) PortableRemoteObject.narrow(objref, GradeHome.class);
        try {
            home.create(null, grade.getStudentId(), grade.getCourseId(), grade.getDate(), grade.getCredits(), grade.getDescription());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't insert new data due to RemoteException", e);
        } catch (CreateException e) {
            throw new DataAccessException("Can't insert new data due to CreateException", e);
        }
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
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("LocationEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }

        LocationHome home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
        try {
            try {
                home.findByPrimaryKey(locationId);
            } catch (FinderException e) {
                throw new DataAccessException("Can't delete data due to FinderException", e);
            }
            home.remove(locationId);
        } catch (RemoteException e) {
            throw new DataAccessException("Can't delete data due to RemoteException", e);
        } catch (RemoveException e) {
            throw new DataAccessException("Can't delete data due to RemoveException", e);
        }
    }

    @Override
    public void removeCourse(int courseId) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("CourseEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }

        CourseHome home = (CourseHome) PortableRemoteObject.narrow(objref, CourseHome.class);
        try {
            try {
                home.findByPrimaryKey(courseId);
            } catch (FinderException e) {
                throw new DataAccessException("Can't delete data due to FinderException", e);
            }
            home.remove(courseId);
        } catch (RemoteException e) {
            throw new DataAccessException("Can't delete data due to RemoteException", e);
        } catch (RemoveException e) {
            throw new DataAccessException("Can't delete data due to RemoveException", e);
        }
    }

    @Override
    public void removeGrade(int gradeId) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("GradeEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }

        GradeHome home = (GradeHome) PortableRemoteObject.narrow(objref, GradeHome.class);
        try {
            try {
                home.findByPrimaryKey(gradeId);
            } catch (FinderException e) {
                throw new DataAccessException("Can't delete data due to FinderException", e);
            }
            home.remove(gradeId);
        } catch (RemoteException e) {
            throw new DataAccessException("Can't delete data due to RemoteException", e);
        } catch (RemoveException e) {
            throw new DataAccessException("Can't delete data due to RemoveException", e);
        }
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
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("LocationEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        LocationHome home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
        try {
            home.updateById(location.getLocationId(), location.getName(), location.getParentId(), location.getCourse(),  location.getDescription());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't update data due to RemoteException", e);
        }
    }

    @Override
    public void updateCourse(Course course) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("CourseEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        CourseHome home = (CourseHome) PortableRemoteObject.narrow(objref, CourseHome.class);
        try {
            home.updateById(course.getCourseId(), course.getName(), course.getDescription(), course.getTeacher(), course.getLocationId());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't update data due to RemoteException", e);
        }
    }

    @Override
    public void updateGrade(Grade grade) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("GradeEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        GradeHome home = (GradeHome) PortableRemoteObject.narrow(objref, GradeHome.class);
        try {
            home.updateById(grade.getGradeId(), grade.getStudentId(), grade.getCourseId(), grade.getDate(), grade.getCredits(), grade.getDescription());
        } catch (RemoteException e) {
            throw new DataAccessException("Can't update data due to RemoteException", e);
        }
    }

    @Override
    public void enrollStudent(int studentId, int courseId) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("StudentEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        StudentHome home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        try {
            home.enrollStudent(studentId, courseId);
        } catch (RemoteException e) {
            throw new DataAccessException("Can't update data due to RemoteException", e);
        }
    }

    @Override
    public void removeEnrollment(int studentId, int courseId) throws DataAccessException {
        Object objref = null;
        try {
            Context initial = new InitialContext();
            objref = initial.lookup("StudentEJB");
        } catch (NamingException e) {
            throw new DataAccessException("Can't insert new data", e);
        }
        StudentHome home = (StudentHome) PortableRemoteObject.narrow(objref, StudentHome.class);
        try {
            home.deleteEnrollment(studentId, courseId);
        } catch (RemoteException e) {
            throw new DataAccessException("Can't update data due to RemoteException", e);
        }
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
        CourseRemote courseRemote;
        Course course;
        try {
            Context initial = new InitialContext();
            Object objref = initial.lookup("CourseEJB");
            CourseHome home = (CourseHome) PortableRemoteObject.narrow(objref, CourseHome.class);
            courseRemote = home.findByPrimaryKey(courseId);
            course = new CourseImpl(courseRemote.getCourseId(), courseRemote.getLocationId(), courseRemote.getName(), courseRemote.getDescription(), courseRemote.getTeacher());
        } catch (Exception e)
        {
            throw new DataAccessException("Can't get student by id", e);
        }
        return course;
    }

    @Override
    public Location getLocationById(int locationId) throws DataAccessException {
        LocationRemote locationRemote;
        Location location;
        try {
            Context initial = new InitialContext();
            Object objref = initial.lookup("LocationEJB");
            LocationHome home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
            if(locationId != 0) {
                locationRemote = home.findByPrimaryKey(locationId);
                location = new LocationImpl(locationRemote.getLocationId(), locationRemote.getName(), locationRemote.getParentId(), locationRemote.isCourse(), locationRemote.getDescription());
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DataAccessException("Can't get student by id", e);
        }
        return location;
    }

    @Override
    public Grade getGradeById(int gradeId) throws DataAccessException {
        GradeRemote gradeRemote;
        Grade grade;
        try {
            Context initial = new InitialContext();
            Object objref = initial.lookup("GradeEJB");
            GradeHome home = (GradeHome) PortableRemoteObject.narrow(objref, GradeHome.class);
            gradeRemote = home.findByPrimaryKey(gradeId);
            grade = new GradeImpl(gradeRemote.getGradeId(), gradeRemote.getStudentId(), gradeRemote.getCourseId(), gradeRemote.getDate(), gradeRemote.getCredits(),  gradeRemote.getDescription());
        } catch (Exception e)
        {
            throw new DataAccessException("Can't get student by id", e);
        }
        return grade;
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId) throws DataAccessException {
        List<Course> lCourse = new ArrayList<Course>();
        ArrayList<CourseRemote> lId;
        CourseRemote courseRemote;
        Course course;
        CourseHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("CourseEJB");
            home = (CourseHome) PortableRemoteObject.narrow(objref, CourseHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<CourseRemote>) home.findAllCoursesByStudentId(studentId);
            System.out.println("Course id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Course was added with id: " + lId.get(i).getCourseId() + "; Course name by id: " + lId.get(i).getName());
                courseRemote = home.findByPrimaryKey(lId.get(i).getCourseId());
                course = new CourseImpl(courseRemote.getCourseId(), courseRemote.getLocationId(), courseRemote.getName(), courseRemote.getDescription(), courseRemote.getTeacher());
                lCourse.add(course);
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }
        return lCourse;
    }

    @Override
    public List<Course> getCoursesByLocationId(int locationId) throws DataAccessException {
        List<Course> lCourse = new ArrayList<Course>();
        ArrayList<CourseRemote> lId;
        CourseRemote courseRemote;
        Course course;
        CourseHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("CourseEJB");
            home = (CourseHome) PortableRemoteObject.narrow(objref, CourseHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<CourseRemote>) home.findAllCoursesByLocationId(locationId);
            System.out.println("Course id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Course was added with id: " + lId.get(i).getCourseId() + "; Course name by id: " + lId.get(i).getName());
                courseRemote = home.findByPrimaryKey(lId.get(i).getCourseId());
                course = new CourseImpl(courseRemote.getCourseId(), courseRemote.getLocationId(), courseRemote.getName(), courseRemote.getDescription(), courseRemote.getTeacher());
                lCourse.add(course);
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }
        return lCourse;
    }

    @Override
    public Map<Integer, String> getLocationHierarchy(int locationId) throws DataAccessException {
        Map<Integer,String> lMap = new HashMap<Integer, String>();
        ArrayList<LocationRemote> lId;
        LocationRemote locationRemote;
        LocationHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("LocationEJB");
            home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<LocationRemote>) home.findAllLocationsForMapping(locationId);
            System.out.println("Location id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Location was added with id: " + lId.get(i).getLocationId() + "; Location name by id: " + lId.get(i).getName());
                locationRemote = home.findByPrimaryKey(lId.get(i).getLocationId());
                lMap.put(locationRemote.getLocationId(), locationRemote.getName());
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lMap;
    }

    @Override
    public Map<Integer, String> getAllLocations() throws DataAccessException {
        Map<Integer,String> lMap = new HashMap<Integer, String>();
        ArrayList<LocationRemote> lId;
        LocationRemote locationRemote;
        LocationHome home = null;
        Context initial = null;

        try {
            initial = new InitialContext();
            Object objref = initial.lookup("LocationEJB");
            home = (LocationHome) PortableRemoteObject.narrow(objref, LocationHome.class);
        } catch (NamingException e) {
            throw new DataAccessException("Can't find object by name", e);
        }

        try {
            lId = (ArrayList<LocationRemote>) home.findAllLocations();
            System.out.println("Location id list: " + lId.size());
            for (int i = 0; i < lId.size(); i++) {
                System.out.println("Location was added with id: " + lId.get(i).getLocationId() + "; Location name by id: " + lId.get(i).getName());
                locationRemote = home.findByPrimaryKey(lId.get(i).getLocationId());
                lMap.put(locationRemote.getLocationId(), locationRemote.getName());
            }
        } catch (RemoteException e) {
            throw new DataAccessException("Can't retrive data via RemoteException", e);
        } catch (FinderException e) {
            throw new DataAccessException("Can't retrive data via FinderException", e);
        }

        return lMap;
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
