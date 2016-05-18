package com.berest.beans.location;

import com.berest.connection.DataSourceConnection;

import javax.ejb.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by Oleg on 08.05.2016.
 */
public class LocationBean implements EntityBean {
    private Integer locationId;
    private String name;
    private String description;
    private int parentId;
    private boolean isCourse;
    private EntityContext context;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isCourse() {
        return isCourse;
    }

    public void setCourse(boolean course) {
        isCourse = course;
    }

    public EntityContext getContext() {
        return context;
    }

    public void setContext(EntityContext context) {
        this.context = context;
    }

    public LocationBean() {
    }

    public void setEntityContext(EntityContext entityContext) throws EJBException {
        System.out.println("Location bean context was set.");
        this.context = entityContext;
    }

    public void unsetEntityContext() throws EJBException {
        System.out.println("Location bean context was unset.");
        this.context = null;
    }

    public void ejbRemove() throws RemoveException, EJBException {
        System.out.println("Location bean method ejbRemove() was called.");
        //logger.info("Student bean method ejbRemove() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.locationId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("DELETE FROM LOCATIONS WHERE LOCATION_ID = ?");
            statement.setInt(1, this.locationId);
            statement.execute();
        } catch (SQLException e) {
            throw new EJBException("Can't delete data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbActivate() throws EJBException {
        System.out.println("Location bean was activated.");
        this.locationId = (Integer) context.getPrimaryKey();
    }

    public void ejbPassivate() throws EJBException {
        System.out.println("Location bean was passivated.");
    }

    public void ejbLoad() throws EJBException {
        System.out.println("Location bean method ejbLoad() was called.");
        //logger.info("Student bean method ejbLoad() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        this.locationId = (Integer) context.getPrimaryKey();
        try {
            statement = connection.prepareStatement("SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?");
            statement.setString(1, String.valueOf(this.locationId));
            result = statement.executeQuery();
            if(result.next()) {
                this.locationId = result.getInt("LOCATION_ID");
                this.name = result.getString("NAME");
                this.description = result.getString("DESCRIPTION");
                this.parentId = result.getInt("PARENT_ID");
                this.isCourse = Boolean.parseBoolean(result.getString("IS_LAST_LEVEL"));
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public void ejbStore() throws EJBException {
        System.out.println("Location bean method ejbStore() was called.");
        //logger.info("Student bean method ejbStore() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            if(this.getParentId() == 0) {
                statement = connection.prepareStatement("UPDATE LOCATIONS SET NAME = ?, PARENT_ID = NULL, IS_LAST_LEVEL = ?, DESCRIPTION = ? WHERE LOCATION_ID = ?");
                statement.setString(2, String.valueOf(this.isCourse()));
                statement.setString(3, this.getDescription());
                statement.setInt(4, this.getLocationId());
            } else {
                statement = connection.prepareStatement("UPDATE LOCATIONS SET NAME = ?, PARENT_ID = ?, IS_LAST_LEVEL = ?, DESCRIPTION = ? WHERE LOCATION_ID = ?");
                statement.setInt(2, this.getParentId());
                statement.setString(3, String.valueOf(this.isCourse()));
                statement.setString(4, this.getDescription());
                statement.setInt(5, this.getLocationId());
            }
            statement.setString(1, this.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Integer ejbFindByPrimaryKey(Integer key) throws FinderException {
        System.out.println("Location bean method ejbFindByPrimaryKey() was called.");
        //logger.info("Student bean method ejbFindByPrimaryKey() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?");
            statement.setInt(1, key);
            result = statement.executeQuery();
            if(result.next()) {
                return key;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new EJBException("Can't load data by id due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Integer ejbCreate(Integer id, String name, int parentId, boolean isCourse, String description) throws CreateException {
        String key[] = {"LOCATION_ID"};
        System.out.println("Location bean method ejbCreate() was called.");
        long k;
        //logger.info("Student bean method ejbCreate() was called.");

        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            if (this.getParentId() == 0) {
                statement = connection.prepareStatement("INSERT INTO LOCATIONS(LOCATION_ID, NAME, PARENT_ID, IS_LAST_LEVEL, DESCRIPTION) VALUES (NULL, ?, NULL, ?, ?)");
                statement.setString(2, String.valueOf(this.isCourse()));
                statement.setString(3, this.getDescription());
            } else {
                statement = connection.prepareStatement("INSERT INTO LOCATIONS(LOCATION_ID, NAME, PARENT_ID, IS_LAST_LEVEL, DESCRIPTION) VALUES (NULL, ?, ?, ?, ?)");
                statement.setInt(2, this.getParentId());
                statement.setString(3, String.valueOf(this.isCourse()));
                statement.setString(4, this.getDescription());
            }
            statement.setString(1, this.getName());
            statement.execute();

            result = statement.getGeneratedKeys();
            System.out.println("Auto Generated Primary Key 1: " + result.toString());
            if (result.next()) {
                k = result.getLong(1);
                System.out.println("Auto Generated Primary Key " + k);

                id = toIntExact(k);
                this.locationId = id;
                this.name = name;
                this.description = description;
                this.parentId = parentId;
                this.isCourse = isCourse;
                System.out.println("Auto Generated Primary Key int " + id);
            }
        } catch (SQLException e) {
            throw new EJBException("Can't create new data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return id;
    }

    public Collection ejbFindAllLocationsByParentId(int locationId) throws FinderException {
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lLocation = new ArrayList<Integer>();
        try {
            if(locationId == 0) {
                statement = connection.prepareStatement("SELECT * FROM LOCATIONS WHERE PARENT_ID IS NULL ORDER BY NAME");
            }
            else {
                statement = connection.prepareStatement("SELECT * FROM LOCATIONS WHERE PARENT_ID = ? ORDER BY NAME");
                statement.setInt(1, locationId);
            }
            result = statement.executeQuery();
            while(result.next()){
                this.locationId = result.getInt("LOCATION_ID");
                lLocation.add(this.locationId);           }
        } catch (Exception e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lLocation;
    }

    public void ejbPostCreate(Integer id, String name, int parentId, boolean isCourse, String description) throws CreateException {
        System.out.println("Location bean method ejbPostCreate() was called.");
    }

    public void ejbHomeUpdateById(Integer id, String name, int parentId, boolean isCourse, String description) {
        System.out.println("Student bean method update() was called.");
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            if(this.getParentId() == 0) {
                statement = connection.prepareStatement("UPDATE LOCATIONS SET NAME = ?, PARENT_ID = NULL, IS_LAST_LEVEL = ?, DESCRIPTION = ? WHERE LOCATION_ID = ?");
                statement.setString(2, String.valueOf(this.isCourse()));
                statement.setString(3, this.getDescription());
                statement.setInt(4, this.getLocationId());
            } else {
                statement = connection.prepareStatement("UPDATE LOCATIONS SET NAME = ?, PARENT_ID = ?, IS_LAST_LEVEL = ?, DESCRIPTION = ? WHERE LOCATION_ID = ?");
                statement.setInt(2, this.getParentId());
                statement.setString(3, String.valueOf(this.isCourse()));
                statement.setString(4, this.getDescription());
                statement.setInt(5, this.getLocationId());
            }
            statement.setString(1, this.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EJBException("Can't store data due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
    }

    public Collection ejbFindAllLocationsForMapping(int locationId) throws FinderException {
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lLocation = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT LOCATION_ID FROM LOCATIONS START WITH LOCATION_ID = ? CONNECT BY LOCATION_ID = PRIOR PARENT_ID");
            statement.setInt(1, locationId);
            result = statement.executeQuery();
            while(result.next()){
                this.locationId = result.getInt("LOCATION_ID");
                lLocation.add(this.locationId);           }
        } catch (Exception e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lLocation;
    }

    public Collection ejbFindAllLocations() throws FinderException {
        Connection connection = DataSourceConnection.getInstance().connect();
        ResultSet result = null;
        PreparedStatement statement = null;
        List<Integer> lLocation = new ArrayList<Integer>();
        try {
            statement = connection.prepareStatement("SELECT LOCATION_ID, NAME FROM LOCATIONS WHERE IS_LAST_LEVEL = 'false' ORDER BY NAME");
            result = statement.executeQuery();
            while(result.next()){
                this.locationId = result.getInt("LOCATION_ID");
                lLocation.add(this.locationId);           }
        } catch (Exception e) {
            throw new EJBException("Can't get data for all items due to SQLException", e);
        }
        finally {
            DataSourceConnection.getInstance().disconnect(connection, result, statement);
        }
        return lLocation;
    }
}
