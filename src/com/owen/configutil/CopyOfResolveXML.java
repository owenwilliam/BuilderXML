package com.owen.configutil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class CopyOfResolveXML
{

private  static HashMap configMap;
  
@SuppressWarnings({ "unchecked", "null" })
public static List<HashMap<Object,Object>> getXMLInfo(String fileName)
  {
    if (fileName != null)
    {
      try
      {
        SAXBuilder saxbuilder = new SAXBuilder();//解析器
        Document document = saxbuilder.build(new FileInputStream(fileName));
        Element element = document.getRootElement();// 根节点  </configs>
        List<Object> list = element.getChildren();//子节点 </config>
    //    HashMap<Object,List<HashMap<Object,Object>>> map = new HashMap<Object,List<HashMap<Object,Object>>>();
        List<HashMap<Object,Object>> mapList = new ArrayList<HashMap<Object,Object>>();
        for (int i = 0; i < list.size(); i++)
        {
          Element element2 = (Element)list.get(i);//将这个</config>子节点当作根节点
          List<Object> list2 = element2.getChildren();//子节点</item>
          if ((list2 == null) || (list2.size() <= 0))
            continue;
          HashMap<Object,Object> map3 = new HashMap<Object,Object>();
         // HashMap<Object,Object> map4 = new HashMap<Object,Object>();
         
          for (int j = 0; j < list2.size(); j++)
          {
            Element localElement3 = (Element)list2.get(j);
            String id = localElement3.getAttribute("id").getValue();//获取id值
            map3.put(id, localElement3.getText());//localElement3.getText()标签中的内容。 标签id对应内容
          /*  
            Attribute userName = localElement3.getAttribute("name");
            if ((userName != null) && (userName.getValue().trim().length() > 0))
              map3.put("name", userName.getValue());
            
            Attribute useAge = localElement3.getAttribute("age");
            if ((useAge == null) && (useAge.getValue().trim().length() <= 0))
             map3.put("age", useAge.getValue());
            
            Attribute useAddress = localElement3.getAttribute("address");
            if ((useAddress == null) && (useAddress.getValue().trim().length() <= 0))
             map3.put("address", useAddress.getValue());
        //    map4.put(id, map3);
*/            mapList.add(map3);
          }
          
        //  map.put("result", mapList);
        }
       // setConfig(map);
        return mapList;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    
    }
    
    return null;
  }


	public static void main(String[] args)
	{
		
		File file = new File("config\\base.xml");
		String abspath =file.getAbsolutePath();
		List<HashMap<Object,Object>> info = getXMLInfo("E:\\workspace\\myLearning\\ConfigUtil\\config\\userInfo.xml");
		HashMap<Object,Object> userInfo = (HashMap<Object, Object>) info.get(1);
		//for(int i = 0; i< info.size(); i++)
		//{
			
			for(int j = 0; j <userInfo.size(); j++)
			{
				System.out.println(userInfo.get("name"));
			}
		//}
	}
}