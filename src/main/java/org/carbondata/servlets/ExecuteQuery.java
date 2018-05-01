package org.carbondata.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.carbondata.tool.jdbc.ConnectionUtil;
import org.carbondata.util.GeneralReqResUtil;
import org.carbondata.util.QueryResultObject;

/**
 * Created by Administrator on 5/1/2018.
 */
public class ExecuteQuery extends HttpServlet {
  static int queryID=0;
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException
  {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    GeneralReqResUtil generalReqResUtil=new GeneralReqResUtil();
    Map<String, String> requestMap = generalReqResUtil.normlizeRequestParamterToNormalMap(request);
    String query=requestMap.get("query").replaceAll("\r\n"," ");
    String connectionname=requestMap.get("connectionname");
    ConnectionUtil util=new ConnectionUtil();
    Connection connection = util.connectionMap.get(connectionname);
    String result = "";
    if(null != connection) {
      try {
        QueryResultObject queryResultObject =
            util.executeQuery(connectionname, (queryID++) + "", query);
        if (null != queryResultObject) {
          String resultfilePath=request.getRealPath("queryResult.csv");
          queryResultObject.writeResultToFile(resultfilePath);
          ResponseWriterUtil writerUtil = new ResponseWriterUtil();
          result = writerUtil.writerQueryResult("100", resultfilePath, queryResultObject.getTime1(),queryResultObject.getOutputrows());
        }
      }
      catch (Exception e) {
        result="Error in Query,"+e.getMessage();
        e.printStackTrace();
      }

    } else {
    result="Connectoon Name"+connectionname+" Not found";
    }

      response.getWriter().println(result);
    }



}
