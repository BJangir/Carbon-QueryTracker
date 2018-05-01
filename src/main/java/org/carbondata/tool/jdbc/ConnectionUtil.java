package org.carbondata.tool.jdbc;

/**
 * Created by Administrator on 5/1/2018.
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.carbondata.util.QueryResultObject;

public class ConnectionUtil {
  private final static Logger LOGGER = Logger.getLogger(ConnectionUtil.class.getName());

  Connection con = null;
  public static  Map<String, Connection> connectionMap = new HashMap<String, Connection>();

  Connection getConnection() {
    return con;
  }

  public boolean prepareConnection(String name, String jdbc_url, String driverclass,
      String username, String pass) {
    try {
      Class.forName(driverclass);
    } catch (ClassNotFoundException e) {
      LOGGER.info("Driver Class is not found" + e.getMessage());
      e.printStackTrace();
      return false;
    }

    try {
      if (null != username && null != pass) {
        con = DriverManager.getConnection(jdbc_url, username, pass);
      } else {
        con = DriverManager.getConnection(jdbc_url);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    connectionMap.put(name, con);
    LOGGER.info("Add Connection"+name+"Success!!!");
    return true;
  }

  public QueryResultObject executeQuery(String connectioname,String queryID,String query)
      throws Exception {
    QueryResultObject resultObject=new QueryResultObject();
    try{
      List<String[]> allrecords=new ArrayList();
      Statement stmt = null;
      ResultSet result = null;
      Connection connection = connectionMap.get(connectioname);
      if(null == connection) {
        return resultObject;
      }
      stmt = connection.createStatement();
      long starteachQuery = System.currentTimeMillis();
      result = stmt.executeQuery(query);
      //long endEachQuery = System.currentTimeMillis();
      int count=0;
      ResultSetMetaData metaData = result.getMetaData();
      int columnCount = metaData.getColumnCount();
      String[] coloums = new String[columnCount];

      for (int i=0; i <columnCount ; i++)
      {
        coloums[i] = metaData.getColumnName(i+1);
      }

      allrecords.add(coloums);
      while (result.next())
      {
        String[] row = new String[columnCount];
        for (int i=0; i <columnCount ; i++)
        {
          row[i] = result.getString(i + 1);
        }
        allrecords.add(row);
        count++;
      }
      long endEachQuery = System.currentTimeMillis();
      String replaceAll = query.replaceAll("\"", "\"\"");
      resultObject.setQueryId(queryID);
      resultObject.setQuery(replaceAll);
      resultObject.setTime1(""+(endEachQuery-starteachQuery));
      resultObject.setOutputrows(count+"");
      resultObject.setAllrecords(allrecords);
      System.out.println("=== Query===,"+queryID+",\""+replaceAll+"\",Total count,"+count+","+(endEachQuery-starteachQuery)+" Milli");
      if(count==0){
        System.out.println("======%%%%%%====%%%No Row retuned for query=="+query);
      }

    }catch(Exception e){
      e.printStackTrace();
      throw e;
    }

    return resultObject;

  }
}


