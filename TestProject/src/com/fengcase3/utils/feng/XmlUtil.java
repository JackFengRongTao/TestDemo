package com.fengcase3.utils.feng;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * xml������
 * @author fengrongtao
 *
 */
public class XmlUtil {
	private Document xmlDoc = null;//xml

	public XmlUtil(String xmlString) throws DocumentException {
		this.xmlDoc = DocumentHelper.parseText(xmlString);
	}

	public XmlUtil(Document doc) {
		this.xmlDoc = doc;
	}

	public XmlUtil(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		this.xmlDoc = reader.read(file);
	}
	
	/**
	 * ????
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public Node getNode(String nodePath) throws Exception{
		Node node = null;
		
		try {
			node = this.xmlDoc.selectSingleNode(nodePath);
		} catch (Exception e) {
			throw new Exception("��ȡ�ڵ���Ϣ����"+e.getMessage());
		}
				
		return node;
	}
	
	/**
	 * ��ȡXML��ʽ�ַ����Ľڵ�ֵ
	 * @param nodePath,�ڵ��������λ
	 * @return String
	 * @throws Exception
	 */
	public String getNodeValue(String nodePath) throws Exception{
		String result;
		try {
			Node node = this.xmlDoc.selectSingleNode(nodePath);
			if(node == null){
				throw new Exception("û�����ô˽ڵ�·����������");
			}
			result = node.getStringValue();			
		} catch (Exception e) {
			throw new Exception("��ȡ�ڵ���Ϣ����"+e.getMessage());
		}		
		return result;	 
	}
	
	
	/**
	 * ���ض���ӽڵ��ֵ��������List��
	 * @param nodePath String
	 * @return List
	 * @throws Exception 
	 */
	public List<Object> getNodeValues(String nodePath) throws Exception{
		ArrayList<Object> list = new ArrayList<Object>();
		Iterator<?> nodes = xmlDoc.selectNodes(nodePath).iterator();
		try {
			if(nodes == null){			
				throw new Exception("û�����ô˽ڵ�·���������ã�");
			}else{
				while(nodes.hasNext()){
					Element nodeEle= (Element)nodes.next();
					list.add(nodeEle.getData());
				}
			}			
		} catch (Exception e) {
			throw new Exception("��ȡ�ڵ���Ϣ����"+e.getMessage()); 
		}
		return list;
	}
	
	/**
	 * ����ָ���ڵ�Ķ���ӽڵ��ֵ��������List��
	 * @param elem
	 * @param elemName
	 * @return List
	 * @throws Exception
	 */
	public List<Object> getNodeValues(Element elem,String elemName) throws Exception{
		ArrayList<Object> list = new ArrayList<Object>();
		Iterator<?> nodes = elem.elementIterator(elemName);
		try {
			if(nodes == null){
				throw new Exception("û�����ô˽ڵ�·���������ã�");
			}else{
				while(nodes.hasNext()){
					Element nodeElement = (Element) nodes.next();
					list.add(nodeElement.getData());
				}
			}			
		} catch (Exception e) {
			throw new Exception("��ȡ��Ϣ����"+e.getMessage());
		}
		
		return list;		
	}
	
	/**
	 * ��ȡ����
	 * @param nodePath,�ڵ�·��
	 * @param attrName,��������
	 * @return String 
	 * @throws Exception
	 */
	public String getAttrValue(String nodePath,String attrName) throws Exception{
		String result = "";
		Iterator<?> nodes = xmlDoc.selectNodes(nodePath).iterator();
		try {
			if(nodes == null){
				throw new Exception("û�����ô˽ڵ�·���������ã�");
			}
			while(nodes.hasNext()){
					Element nodeElement = (Element) nodes.next();
					result = nodeElement.attributeValue(attrName);
			}						
		} catch (Exception e) {
			throw new Exception("��ȡ��Ϣ����"+e.getMessage());
		}		
		return result;		
	}
	
	/**
	 * ��ָ��XML�ַ�����ָ��λ�ò���ָ����Ԫ��
	 * @param path,λ��
	 * @param ele,Ҫ�����Ԫ��
	 * @return String
	 */
	public String writeNode(String path,Element ele) throws Exception{
		List<?> list = xmlDoc.selectNodes(path);
		Iterator<?> iter = list.iterator();
		if(iter.hasNext()){
			Element valueElement = (Element)iter.next();
			valueElement.add(ele);
		}		
		return xmlDoc.asXML();
	}
	
	/**
	 * ���ظ��ڵ�
	 * @return Element
	 */
	public Element getRootElement() throws Exception{
		return xmlDoc.getRootElement();
	}
	
	/**
	 * ö�������ӽڵ㣬�����뵽list
	 * @param element
	 * @return List
	 */
	public List<Element> getSubElems(Element element) throws Exception{
		ArrayList<Element> list  = new ArrayList<Element>();
		for(Iterator<?> i = element.elementIterator();i.hasNext();){
			Element ele = (Element)i.next();
			list.add(ele);
		}		
		return list;
	}
	
	/**
	 * �ڵ�ö�����ԣ������뵽list
	 * @param element
	 * @return List
	 * @throws Exception
	 */
	public List<Attribute> getAttrs(Element element) throws Exception{
		ArrayList<Attribute> list  = new ArrayList<Attribute>();
		//ö������
		for(Iterator<?> i = element.attributeIterator();i.hasNext();){
			Attribute attribute = (Attribute) i.next();
			list.add(attribute);
		}		
		return list;
	}
	
	public void recursionElem(Element element) throws Exception {
		for(int i = 0,size = element.nodeCount();i < size;i++){
			Node node = element.node(i);
			
		}
	}
	
	
	
	
	/**
	 * ����
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
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
		XmlUtil xmlUtil = new XmlUtil(xmlstr);		
		//String result =   xmlUtil.getNodeValue("databiz");
	}
	
}
