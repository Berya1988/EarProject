package com.berest.beans.grade;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.sql.Date;

/**
 * Created by Oleg on 16.05.2016.
 */
public interface GradeRemote extends EJBObject {
    Integer getGradeId() throws RemoteException;
    Integer getStudentId() throws RemoteException;
    Integer getCourseId() throws RemoteException;
    Date getDate() throws RemoteException;
    int getCredits() throws RemoteException;
    String getDescription() throws RemoteException;
}
