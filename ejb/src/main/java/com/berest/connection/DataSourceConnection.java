package com.berest.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

/**
 * Created by Oleg on 25.04.2016.
 */
public class DataSourceConnection {
    //static final Logger logger = Logger.getLogger(DataSourceConnection.class);
    private static final DataSourceConnection instance = new DataSourceConnection();

    private DataSource ds;
    private Context ctx;
    private Hashtable ht = new Hashtable();

    private DataSourceConnection() {
        System.out.println("In constructor!");
        ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL, "t3://localhost:7001");
        try {
            ctx = new InitialContext(ht);
            ds = (javax.sql.DataSource) ctx.lookup("JNDI_Name0");
        } catch (NamingException e) {
            //logger.error("Threw a NamingException in DataSourceConnection class::" + e.getMessage(), e);
        }
    }

    public static DataSourceConnection getInstance() {
        return instance;
    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            //logger.error("Threw a SQLException in DataSourceConnection class::" + e.getMessage(), e);
        }
        return connection;
    }

    public void disconnect(Connection connection, ResultSet result, Statement statement) {
        try {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
            if(result != null)
                result.close();
        } catch (SQLException e) {
            //logger.error("Threw a SQLException in DataSourceConnection class::" + e.getMessage(), e);
        }
    }

}
