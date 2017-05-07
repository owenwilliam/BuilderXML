package com.owen.configutil;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 解析XML的文件，将解析的信息存在到Map中，随时都可以获取
 * 
 * @author OwenWilliam
 * @Date 2017-4-19
 *
 */
public class ResolveXML
{

	@SuppressWarnings("unchecked")
	public static HashMap<Object, HashMap<Object, Object>> getXMLInfo(String fileName)
	{

		if (fileName != null)
		{
			try
			{
				SAXBuilder saxbuilder = new SAXBuilder();// 解析器
				Document document = saxbuilder.build(new FileInputStream(fileName));// 处理文件
				Element element = document.getRootElement();// 根节点 </configs>
				List<Object> list = element.getChildren();// 子节点 </config>
				HashMap<Object, HashMap<Object, Object>> resultMap = new HashMap<Object, HashMap<Object, Object>>();// 存放最终结果

				for (int i = 0; i < list.size(); i++)
				{
					Element element2 = (Element) list.get(i);// 将这个</config>子节点当作根节点
					List<Object> list2 = element2.getChildren();// 子节点</item>
					if ((list2 == null) || (list2.size() <= 0))
						continue;
					HashMap<Object, Object> map = new HashMap<Object, Object>();

					for (int j = 0; j < list2.size(); j++)
					{
						Element localElement3 = (Element) list2.get(j);
						String id = localElement3.getAttribute("id").getValue();// 获取id值
						map.put(id, localElement3.getText());// 标签中的内容
						resultMap.put(element2.getAttribute("id").getValue(), map);//对应的</config>标签id
					}

				}
				return resultMap;
			} catch (Exception localException)
			{
				localException.printStackTrace();
			}

		}

		return null;
	}

	public static void main(String[] args)
	{
		String fileName = System.getProperty("user.dir")
				+ "/config/userInfo.xml";
		HashMap<Object, HashMap<Object, Object>> info = getXMLInfo(fileName);

		HashMap<Object, Object> userInfo = (HashMap<Object, Object>) info.get("userInfo");
		System.out.println(userInfo.get("Owen"));
		System.out.println(userInfo.get("Rubby"));
		System.out.println(userInfo.get("Hanks"));

	}
}