package com.berest.beans.location;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Created by Oleg on 08.05.2016.
 */
public interface LocationRemote extends EJBObject {
    Integer getLocationId() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    int getParentId() throws RemoteException;
    boolean isCourse() throws RemoteException;
}
