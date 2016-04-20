package com.berest.beans.student;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by Oleg on 04.04.2016.
 */
public interface StudentRemote extends EJBObject {
    Integer getStudentId() throws RemoteException;
    String getName() throws RemoteException;
    String getGroup() throws RemoteException;
    String getMail() throws RemoteException;
    String getPhone() throws RemoteException;
    String getAddress() throws RemoteException;
}
