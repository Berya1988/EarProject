package com.berest.beans.student;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by Oleg on 04.04.2016.
 */
public interface LocalStudentHome extends EJBLocalHome {
    LocalStudent create(Integer id, String name, String group, String mail, String phoneNo, String address) throws CreateException;;
    LocalStudent findByPrimaryKey(Integer key) throws FinderException;
    Collection findAllStudents() throws FinderException;
}
