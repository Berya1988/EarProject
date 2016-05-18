package com.berest.beans.course;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by Oleg on 16.05.2016.
 */
public interface CourseRemote extends EJBObject {
    Integer getCourseId() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    String getTeacher() throws RemoteException;
    Integer getLocationId() throws RemoteException;
}
