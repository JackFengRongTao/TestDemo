package com.fengcase3.utils.feng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapToXml {
	public static void main(String[] args) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap = returnMap();
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<databiz>");		
		 map2Xml(dataMap,sb);
		sb.append("</databiz>");
		System.out.println(sb.toString());
		String xmlstr = 
				"<?xml version='1.0' encoding='UTF-8'?>" +
				"<databiz>" +
				"<serviceId>0001</serviceId>" +
				"<data>" +
					"<rtMsg>??????</rtMsg>" +
					"<rtnvalue>02</rtnvalue>" +
					"<prodkindList>" +
						"<prodkind>" +
							"<idWltCloudProdKind>???</idWltCloudProdKind>" +
							"<prodKindName>??????</prodKindName>" +
						"</prodkind>" +
						"<prodkind>" +
							"<idWltCloudProdKind>???</idWltCloudProdKind>" +
							"<prodKindName>??????</prodKindName>" +
						"</prodkind>" +
					"</prodkindList>" +
				"</data>" +
				"</databiz>";

				
		}
	
	/**
	 * map2xml
	 */
	public static String   map2Xml(Map<String, Object> dataMap,StringBuffer sb){
		/*£¿¡°£»*/
		Set set = dataMap.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			String key = it.next();
			Object obj =dataMap.get(key);
			if(null == obj ){
				obj = "";
				sb.append("<"+key+">");
				sb.append(obj);
				sb.append("</"+key+">");
			}else if(obj instanceof String){
				sb.append("<"+key+">");
				sb.append(obj);
				sb.append("</"+key+">");
			}else if(obj instanceof Map){
				sb.append("<"+key+">");				
				map2Xml((Map)obj,sb);				
				sb.append("</"+key+">");				
				
			}else if(obj instanceof List){
				List objList = (List)obj;
				sb.append("<"+key+">");
				for(int i = 0;i < objList.size();i++){
					Object  object = objList.get(i);					
					map2Xml((Map)object,sb);					
				}
				sb.append("</"+key+">");
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * ??map??????????
	 * @return
	 */
	public static  Map<String, Object> returnMap(){
		Map<String, Object> bizdata = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> prodkind = new HashMap<String, Object>();
		Map<String, Object> prodkind2 = new HashMap<String, Object>();
		List prodkindList = new ArrayList();
			
		bizdata.put("serviceId", "0001");		
		data.put("rtnvalue", "02");
		data.put("rtMsg", "??????");			
		prodkind.put("idWltCloudProdKind", "???");
		prodkind.put("prodKindName", "??????");	
		prodkind2.put("prodkind", prodkind);
		prodkindList.add(prodkind2);		
		prodkindList.add(prodkind2);		
		data.put("prodkindList", prodkindList);
		bizdata.put("data", data);
		
		
		return bizdata;
	}
	
	
	
	
}
