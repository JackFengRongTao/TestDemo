package com.fengcase3.utils.feng;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <p>xml�ַ���ת����map or json</p>
 * @author fengrongtao
 *
 */
public class XmlToMap {
	
	/**
	 * <p>xml�ַ���ת����map</p>
	 * @param xmlStr
	 * @return
	 * @throws Exception 
	 */
	public static Map getXmlToMap(String xmlStr) throws Exception {
		Map map = new HashMap();
		Map fristMap = new HashMap();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();//dom��������
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//dom������
		InputStream inputStream = new  ByteArrayInputStream(xmlStr.getBytes("UTF-8"));//xml��ת�����ֽ���
		Document  document = documentBuilder.parse(inputStream);//������dom
		String ec = document.getInputEncoding();
		System.out.println(ec);
		Node fristNode= document.getFirstChild();//���ڵ�
		String fristNodeName = fristNode.getNodeName();
		System.out.println(fristNodeName);
		//��ȡ��һ���ȫ���ڵ�
		NodeList nodeList = document.getFirstChild().getChildNodes();
		recursion(nodeList,fristMap);
		map.put(fristNodeName, fristMap);
		return map;
	}
	
	/**
	 * xml�ڵ�ݹ��ȡ
	 * @param nodeList
	 */
	public static void  recursion(NodeList nodeList,Map map){
		Node node;
		int i = 0;
		while(i < nodeList.getLength()){
			node = nodeList.item(i);			
			System.out.println(node.getNodeName());				
			NodeList childNodesList =  node.getChildNodes();
			
			
			if(childNodesList.getLength()>0){	
				if(childNodesList.item(0).hasChildNodes()){//�ڵ��»��нڵ�
										
					Map nextMap = new HashMap();				
					recursion(childNodesList,nextMap);
					if(map.containsKey(node.getNodeName())){
						map.put(node.getNodeName()+(int)(Math.random()*10000), nextMap);	
					}else{
						map.put(node.getNodeName(), nextMap);
					}					
					
				}else{//�ڵ�����Ϊ�ַ���
					map.put(node.getNodeName(), node.getTextContent());
				}	
				
			}else{//�ڵ�����Ϊ��
				map.put(node.getNodeName(), node.getTextContent());
			}			
			i++;
		}		
	}
	
	
	/**
	 * <p>xml�ַ���ת����map</p>
	 * @param xmlStr
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject getXmlToJson(String xmlStr) throws Exception {
		JSONObject jsonObject = new JSONObject();
		JSONObject FfristJsonObject = new JSONObject();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();//dom��������
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//dom������
		InputStream inputStream = new  ByteArrayInputStream(xmlStr.getBytes("UTF-8"));//xml��ת�����ֽ���
		Document  document = documentBuilder.parse(inputStream);//������dom
		String ec = document.getInputEncoding();
		System.out.println(ec);
		Node fristNode= document.getFirstChild();//���ڵ�
		String fristNodeName = fristNode.getNodeName();
		System.out.println(fristNodeName);
		//��ȡ��һ���ȫ���ڵ�
		NodeList nodeList = document.getFirstChild().getChildNodes();
		recursionJson(nodeList,FfristJsonObject);
		jsonObject.put(fristNodeName, FfristJsonObject);
		return jsonObject;
	}
	
	/**
	 * xml�ڵ�ݹ��ȡ
	 * @param nodeList
	 * @throws Exception 
	 */
	public static void  recursionJson(NodeList nodeList,JSONObject jsonObject) throws Exception{
		Node node;
		int i = 0;
		while(i < nodeList.getLength()){
			node = nodeList.item(i);			
			System.out.println(node.getNodeName());				
			NodeList childNodesList =  node.getChildNodes();
			
			
			if(childNodesList.getLength()>0){	
				if(childNodesList.item(0).hasChildNodes()){//�ڵ��»��нڵ�
										
					Map nextMap = new HashMap();				
					recursion(childNodesList,nextMap);
					if(jsonObject.has(node.getNodeName())){
						jsonObject.put(node.getNodeName()+(int)(Math.random()*10000), nextMap);	
					}else{
						jsonObject.put(node.getNodeName(), nextMap);
					}					
					
				}else{//�ڵ�����Ϊ�ַ���
					jsonObject.put(node.getNodeName(), node.getTextContent());
				}	
				
			}else{//�ڵ�����Ϊ��
				jsonObject.put(node.getNodeName(), node.getTextContent());
			}			
			i++;
		}		
	}
	
	
	
	
	
	
	
	
	/**
	 * ����
	 * @param args
	 * @throws Exception
	 */	
	public static void main(String[] args) throws Exception {
		//InputStream is = new  ByteArrayInputStream()
		//System.out.println();
		String xmlstr = 
				"<?xml version='1.0' encoding='UTF-8'?>" +
				"<databiz>" +
				"<serviceId>0001</serviceId>" +
				"<data>" +
					"<rtMsg>��ѯʧ��</rtMsg>" +
					"<rtnvalue>02</rtnvalue>" +
					"<prod>" +
						"<idWltCloudKind>��Ŀ</idWltCloudKind>" +
						"<prodName>������</prodName>" +
					"</prod>" +
					"<prodkindList>" +
						"<prodkind>" +
							"<idWltCloudProdKind>��Ŀ</idWltCloudProdKind>" +
							"<prodKindName>������</prodKindName>" +
						"</prodkind>" +
						"<prodkind>" +
							"<idWltCloudProdKind>��Ŀ</idWltCloudProdKind>" +
							"<prodKindName>������</prodKindName>" +
						"</prodkind>" +
					"</prodkindList>" +
				"</data>" +
				"</databiz>";		
		 JSONObject jsonObject = getXmlToJson(xmlstr);
		  System.out.println(jsonObject.toString());		
	}
	
	
}
