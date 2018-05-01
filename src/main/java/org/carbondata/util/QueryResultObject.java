package org.carbondata.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/2/2018.
 */
public class QueryResultObject {


  String time1="NA";
  String time2="NA";
  String outputrows="NA";
  String query="NA";
  String queryId="NA";

  public List<String[]> getAllrecords() {
    return allrecords;
  }

  public void setAllrecords(List<String[]> allrecords) {
    this.allrecords = allrecords;
  }

  List<String[]> allrecords=new ArrayList();

  public String getQuery() {
    return query;
  }
  public void setQuery(String query) {
    this.query = query;
  }
  public String getQueryId() {
    return queryId;
  }
  public void setQueryId(String queryId) {
    this.queryId = queryId;
  }
  public String getTime1() {
    return time1;
  }
  public void setTime1(String time1) {
    this.time1 = time1;
  }
  public String getTime2() {
    return time2;
  }
  public void setTime2(String time2) {
    this.time2 = time2;
  }
  public String getOutputrows() {
    return outputrows;
  }
  public void setOutputrows(String outputrows) {
    this.outputrows = outputrows;
  }

public void writeResultToFile(String pathname)
    throws Exception {
StringBuffer buffer=new StringBuffer();
  try {
    File file= new File(pathname);
    PrintWriter writer=new PrintWriter(file);
    for(String[] rows:allrecords){
      for(String cols:rows){
        buffer.append("\"").append(cols).append("\"").append(",");
      }
      String rw = buffer.toString();
      String allcolrecord = rw.substring(0, rw.length() - 1);
      writer.write(allcolrecord);
      writer.flush();
    }
    writer.close();
  } catch (FileNotFoundException e) {
    e.printStackTrace();
    throw e;
  }

}
}
