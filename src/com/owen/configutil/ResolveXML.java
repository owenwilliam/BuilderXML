package com.owen.configutil;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * ����XML���ļ�������������Ϣ���ڵ�Map�У���ʱ�����Ի�ȡ
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
				SAXBuilder saxbuilder = new SAXBuilder();// ������
				Document document = saxbuilder.build(new FileInputStream(fileName));// �����ļ�
				Element element = document.getRootElement();// ���ڵ� </configs>
				List<Object> list = element.getChildren();// �ӽڵ� </config>
				HashMap<Object, HashMap<Object, Object>> resultMap = new HashMap<Object, HashMap<Object, Object>>();// ������ս��

				for (int i = 0; i < list.size(); i++)
				{
					Element element2 = (Element) list.get(i);// �����</config>�ӽڵ㵱�����ڵ�
					List<Object> list2 = element2.getChildren();// �ӽڵ�</item>
					if ((list2 == null) || (list2.size() <= 0))
						continue;
					HashMap<Object, Object> map = new HashMap<Object, Object>();

					for (int j = 0; j < list2.size(); j++)
					{
						Element localElement3 = (Element) list2.get(j);
						String id = localElement3.getAttribute("id").getValue();// ��ȡidֵ
						map.put(id, localElement3.getText());// ��ǩ�е�����
						resultMap.put(element2.getAttribute("id").getValue(), map);//��Ӧ��</config>��ǩid
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