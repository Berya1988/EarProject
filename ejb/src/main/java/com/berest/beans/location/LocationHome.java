package com.berest.beans.location;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by Oleg on 08.05.2016.
 */
public interface LocationHome extends EJBHome {
    LocationRemote create(Integer id, String name, int parentId, boolean isCourse, String description) throws RemoteException, CreateException;
    LocationRemote findByPrimaryKey(Integer key) throws RemoteException, FinderException;
    Collection findAllLocationsByParentId(int locationId) throws RemoteException, FinderException;
    Collection findAllLocationsForMapping(int locationId) throws RemoteException, FinderException;
    Collection findAllLocations() throws RemoteException, FinderException;
    void updateById(Integer id, String name, int parentId, boolean isCourse, String description) throws RemoteException;
}
