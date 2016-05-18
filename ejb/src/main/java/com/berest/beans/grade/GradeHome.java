package com.berest.beans.grade;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Oleg on 16.05.2016.
 */
public interface GradeHome extends EJBHome {
    GradeRemote findByPrimaryKey(Integer key) throws RemoteException, FinderException;
    GradeRemote create(Integer gradeId, Integer studentId, Integer courseId, Date date, int credits, String description) throws RemoteException, CreateException;
    void updateById(Integer gradeId, Integer studentId, Integer courseId, Date date, int credits, String description) throws RemoteException;
    Collection findAllGradesByCourseAndStudent(int courseId, int studentId) throws RemoteException, FinderException;
}
