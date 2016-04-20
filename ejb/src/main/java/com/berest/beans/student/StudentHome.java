package com.berest.beans.student;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by Oleg on 04.04.2016.
 */
public interface StudentHome extends EJBHome {
    StudentRemote create(Integer id, String name, String group, String mail, String phoneNo, String address) throws RemoteException, CreateException;
    StudentRemote findByPrimaryKey(Integer key) throws RemoteException, FinderException;
    Collection findAllStudents() throws RemoteException, FinderException;
}
