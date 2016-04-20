package ua.berest.lab3.controller;

import ua.berest.lab3.exception.DataAccessException;
import ua.berest.lab3.model.Course;
import ua.berest.lab3.model.Grade;
import ua.berest.lab3.model.Location;
import ua.berest.lab3.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by Oleg on 24.01.2016.
 */
public interface ModelDataAccess {
    List<Student> getAllStudents() throws DataAccessException;
    List<Student> getAllStudentsInCourse(int courseId) throws DataAccessException;
    List<Student> getAllStudentsOutOfCourse(int courseId) throws DataAccessException;
    List<Location> getAllLocationsByParentId(int locationId) throws DataAccessException;
    List<Grade> getAllGradesByCourseAndStudent(int courseId, int studentId) throws DataAccessException;
    void addStudent(Student student) throws DataAccessException;
    void addLocation(Location location) throws DataAccessException;
    void addCourse(Course course) throws DataAccessException;
    void addGrade(Grade grade) throws DataAccessException;
    void removeStudent(int studentId) throws DataAccessException;
    void removeLocation(int locationId) throws DataAccessException;
    void removeCourse(int courseId) throws DataAccessException;
    void removeGrade(int gradeId) throws DataAccessException;
    void updateStudent(Student student) throws DataAccessException;
    void updateLocation(Location location) throws DataAccessException;
    void updateCourse(Course course) throws DataAccessException;
    void updateGrade(Grade grade) throws DataAccessException;
    void enrollStudent(int studentId, int courseId) throws DataAccessException;
    void removeEnrollment(int studentId, int courseId) throws DataAccessException;
    Student getStudentById(int studentId) throws DataAccessException;
    Course getCourseById(int courseId) throws DataAccessException;
    Location getLocationById(int locationId) throws DataAccessException;
    Grade getGradeById(int gradeId) throws DataAccessException;
    List<Course> getCoursesByStudentId(int studentId) throws DataAccessException;
    List<Course> getCoursesByLocationId(int locationId) throws DataAccessException;
    Map<Integer,String> getLocationHierarchy(int locationId) throws DataAccessException;
    Map<Integer,String> getAllLocations() throws DataAccessException;

    int getTotalCountOfStudents() throws DataAccessException;
    List<Student> getAllStudentsByPage(int startIndex, int range) throws DataAccessException;
}
