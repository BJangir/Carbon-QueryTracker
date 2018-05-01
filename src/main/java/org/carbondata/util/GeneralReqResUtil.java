package org.carbondata.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 5/1/2018.
 */
public class GeneralReqResUtil {

  public Map<String, String> normlizeRequestMapToNormalMap(Map parameterMap) {
    Map<String, String> map = new HashMap<String, String>();
    Set set1 = parameterMap.keySet();
    Iterator iterator1 = set1.iterator();
    Set set = parameterMap.entrySet();
    Iterator iterator = set.iterator();
    Collections.unmodifiableMap(parameterMap).keySet();
    while (iterator.hasNext()) {
      Object key_key = iterator.next();
     /* Object key =
          ((Collections.UnmodifiableMap))key_key).getKey();
      Object value =
          ((Collections.UnmodifiableMap.UnmodifiableEntrySet.UnmodifiableEntry) key_key).getValue();

      map.put(key.toString(),value.toString());*/
    }
    return map;

  }


  public Map<String, String> normlizeRequestParamterToNormalMap(HttpServletRequest request) {
    Map<String, String> map = new HashMap<String, String>();
    Enumeration parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()){
      String key = (String)parameterNames.nextElement();
      String[] paramValues = request.getParameterValues(key);
      map.put(key,paramValues[0]);
    }
    return map;
  }
}
