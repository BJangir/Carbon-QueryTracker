package org.carbondata.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 5/2/2018.
 */
public class ResponseWriterUtil {
  static StringBuffer responseResult=new StringBuffer("<html xmlns=\"http://www.w3.org/1999/html\">\n"
      + "<head>\n" + "    <link rel=\"stylesheet\" href=\"resources/style/styles.css\">\n"
      + "    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n"
      + "    <link href=\"css/dataTables.bootstrap.css\" rel=\"stylesheet\">\n" + "</head>\n"
      + "<body>\n" + "\n" + "<progress value=\"inprogressValue\" max=\"100\"></progress>\n"
      + "<div> Time Taken:- time_value (Sec) ,Output Rows:-outputRows</div>\n" + "\n"
      + "<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n"
      + "<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\n"
      + "<script type=\"text/javascript\" src=\"js/jquery.csv.min.js\"></script>\n"
      + "<script type=\"text/javascript\" src=\"js/jquery.dataTables.min.js\"></script>\n"
      + "<script type=\"text/javascript\" src=\"js/dataTables.bootstrap.js\"></script>\n"
      + "<script type=\"text/javascript\" src=\"js/csv_to_html_table.js\"></script>\n" + "\n" + "\n"
      + "<script type=\"text/javascript\">\n" + "      function format_link(link){\n"
      + "        if (link)\n"
      + "          return \"<a href='\" + link + \"' target='_blank'>\" + link + \"</a>\";\n"
      + "        else\n" + "          return \"\";\n" + "      }\n" + "\n"
      + "      CsvToHtmlTable.init({\n" + "        csv_path: 'queryResult.csv',\n"
      + "        element: 'table-container',\n" + "        allow_download: true,\n"
      + "        csv_options: {separator: ',', delimiter: '\"'},\n"
      + "        datatables_options: {\"paging\": false},\n"
      + "        custom_formatting: [[4, format_link]]\n" + "      });\n" + "    </script>\n"
      + "</body>\n" + "</html>\n");



  public static void main(String[] args) throws IOException {
   File file =new File("./src/main/webapp/index_queryresult.html.template");
    BufferedReader reader=new BufferedReader(new FileReader(file));

    String line="";
    while( (line=reader.readLine()) != null) {
      responseResult.append(line).append("\n");
    }
    System.out.println(responseResult.toString());
  }

  public  String writerQueryResult(String responseStatus,String resultCSVPath,String time,String outputrows ){
    String responsehtml = responseResult.toString().replace("inprogressValue", responseStatus);
    responsehtml=responsehtml.replace("queryResult.csv",resultCSVPath);
    responsehtml=responsehtml.replace("time_value",time);
    responsehtml=responsehtml.replace("outputRows",outputrows);
    return responsehtml;
  }

}
