package com.berest.beans.course;

import com.berest.beans.student.StudentRemote;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by Oleg on 16.05.2016.
 */
public interface CourseHome extends EJBHome {
    CourseRemote findByPrimaryKey(Integer key) throws RemoteException, FinderException;
    CourseRemote create(Integer courseId, String name, String description, String teacher, Integer locationId) throws RemoteException, CreateException;
    void updateById(Integer courseId, String name, String description, String teacher, Integer locationId) throws RemoteException;
    Collection findAllCoursesByStudentId(int studentId) throws RemoteException, FinderException;
    Collection findAllCoursesByLocationId(int locationId) throws RemoteException, FinderException;
}
