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

/**
 * Created by Administrator on 5/1/2018.
 */
  public class AddJDBCConnection extends HttpServlet
  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException
    {
      GeneralReqResUtil generalReqResUtil=new GeneralReqResUtil();
      Map<String, String> requestMap = generalReqResUtil.normlizeRequestParamterToNormalMap(request);
      String responseMessage = prepareConnection(requestMap);
      response.setContentType("text/html");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println("<script>alert('" +responseMessage+"');window.location.href = \"index.html\";</script>");
    }

    private String prepareConnection(Map reqMap) {
      String returnMessage;
      ConnectionUtil connectionUtil = new ConnectionUtil();
      String jdbc_name = (String) reqMap.get("jdbc_name");
      Connection connection = connectionUtil.connectionMap.get(jdbc_name);
      if (null != connection) {
        returnMessage= "same Name Connection "+jdbc_name+" Already Registred";
      } else {
        String jdbc_url = (String) reqMap.get("jdbc_url");
        String jdbc_class = (String) reqMap.get("jdbc_class");
        String jdbc_user = (String) reqMap.get("jdbc_user");
        String jdbc_password = (String) reqMap.get("jdbc_password");

        boolean b = connectionUtil
            .prepareConnection(jdbc_name, jdbc_url, jdbc_class, jdbc_user, jdbc_password);
        returnMessage = b ?
            " Connection " + jdbc_name + " is added successfully" :
            " Something Wrong Please check logs \n Either given values or wrong or "
                + "\n Jdbc Server not running.";

      }
      return returnMessage;

    }


  }
