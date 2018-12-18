package com.bquan.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class XmlUtil {
	/**
	 * 发送短信返回信息
	 * 
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> sendMsgResponse(String xmlDoc) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			map.put("returnCode", root.element("returnCode").getText());
			map.put("msgId", root.element("msgId").getText());
		} catch (DocumentException e) {
			System.out.println(xmlDoc);
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 订购返回信息
	 * 
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> orderResponse(String xmlDoc) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();

			map.put("orderId", root.element("orderId").getText());
			map.put("errorInfo", root.element("errorInfo").getText());
			map.put("returnCode", root.element("returnCode").getText());

		} catch (DocumentException e) {
			System.out.println(xmlDoc);
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 查询订单返回信息
	 * 
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> qeuryResponse(String xmlDoc) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			map.put("returnCode", root.element("returnCode").getText());
			map.put("errorInfo", root.element("errorInfo").getText());

		} catch (DocumentException e) {
			System.out.println(xmlDoc);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 写入短信发送xml
	 * @param id 短信标识
	 * @param accNbr 号码
	 * @param msgText 短信内容
	 * @return xml
	 */
	public static String getSMSXml(String id, String accNbr, String msgText){
		String xml = "";
		try {
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element info = doc.addElement("info");
			XMLWriter xw;
			StringWriter sw = new StringWriter();
			OutputFormat opf;
			
			Element item = info.addElement("item");
			item.addElement("id").setText(id);
			item.addElement("accNbr").setText(accNbr);
			item.addElement("msgText").setText(msgText);
			item.addElement("isVerifyMsg").setText("1");
			
			opf = OutputFormat.createCompactFormat();
			opf.setEncoding("utf-8");
			opf.setIndent(true);
			opf.setIndent("    ");
			opf.setNewlines(true);
			xw = new XMLWriter(sw, opf);
			xw.write(doc);
			xw.close();
			xml = sw.toString();
			sw.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	/**
	 * 获取短信发送返回信息
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> fluxQureySMSResponse(String xmlDoc){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			Element item = root.element("item");
			map.put("id", item.element("id").getText());
			map.put("msgId", item.element("msgId").getText());
			map.put("statusCode", item.element("statusCode").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取短信真实状态
	 * @param xmlDoc 
	 * @return msgId - 短信ID, statusCode - 短信发送状态, errorCode - 错误码
	 */
	public static HashMap<String, String> fluxQureyRealSMSResponse(String xmlDoc){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			map.put("msgId", root.element("msgId").getText());
			map.put("statusCode", root.element("statusCode").getText());
			map.put("errorCode", root.element("errorCode").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	

	/**
	 * 查询流量包订单返回信息
	 * 
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> fluxQureyResponse(String xmlDoc) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			map.put("returnCode", root.element("returnCode").getText());
			map.put("prodCode", root.element("prodCode").getText());
			map.put("errorInfo", root.element("errorInfo").getText());

		} catch (DocumentException e) {
			System.out.println(xmlDoc);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 写入归属地查询接口XML
	 * @param mobile 号码
	 * @param productId 产品ID
	 * @return XML
	 */
	public static String getLocationXml(String transactionId, String mobile){
		String xml = null;
		try {
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element root = doc.addElement("interface_root");
			XMLWriter xw;
			StringWriter sw = new StringWriter();
			OutputFormat opf;
			
			root.addElement("transaction_id").setText(transactionId);
			root.addElement("transaction_business_type").setText("");
			root.addElement("transaction_source").setText("");
			root.addElement("transaction_sign").setText("");
			Element contentList = root.addElement("content_list");
			Element content = contentList.addElement("content");
			content.addElement("content_id").setText("");
			Element infoMessage = content.addElement("info_message");
			infoMessage.addElement("regionId").setText("");
			Element operInfo = infoMessage.addElement("operInfo");
			operInfo.addElement("accNbr").setText(mobile);
			
			opf = OutputFormat.createCompactFormat();
			opf.setEncoding("utf-8");
			opf.setIndent(true);
			opf.setIndent("    ");
			opf.setNewlines(true);
			xw = new XMLWriter(sw, opf);
			xw.write(doc);
			xw.close();
			xml = sw.toString();
			sw.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	/**
	 * 归属地查询接口返回XML解析
	 * @param xmlDoc 返回xml
	 * @return Map
	 */
	public static Map<String, String> fluxQueryLocation(String xmlDoc){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			
			map.put("returnCode", root.element("return_code").getText());
			map.put("codeDescribe", root.element("code_describe").getText());
			map.put("errorCode", root.element("error_code").getText());
			map.put("contentId", root.element("content_id").getText());
			
			map.put("servId", root.element("servId").getText());
			map.put("regionId", root.element("regionId").getText());
			map.put("regionName", root.element("regionName").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 写入流量校验接口XML
	 * @param mobile 号码
	 * @param productId 产品ID
	 * @return XML
	 */
	public static String getFlowVerifyXml(String transactionId, String mobile, String productId){
		String xml = null;
		try {
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element root = doc.addElement("interface_root");
			XMLWriter xw;
			StringWriter sw = new StringWriter();
			OutputFormat opf;
			
			root.addElement("transaction_id").setText(transactionId);
			root.addElement("transaction_business_type").setText("");
			root.addElement("transaction_source").setText("");
			root.addElement("transaction_sign").setText("");
			Element contentList = root.addElement("content_list");
			Element content = contentList.addElement("content");
			content.addElement("content_id").setText("");
			Element infoMessage = content.addElement("info_message");
			infoMessage.addElement("regionId").setText("");
			Element operInfo = infoMessage.addElement("operInfo");
			operInfo.addElement("accNbr").setText(mobile);
			operInfo.addElement("pricePlanId").setText(productId);
			operInfo.addElement("actionType").setText("A");
			
			opf = OutputFormat.createCompactFormat();
			opf.setEncoding("utf-8");
			opf.setIndent(true);
			opf.setIndent("    ");
			opf.setNewlines(true);
			xw = new XMLWriter(sw, opf);
			xw.write(doc);
			xw.close();
			xml = sw.toString();
			sw.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	/**
	 * 流量校验接口XML解析
	 * @param xmlDoc xml
	 * @return Map
	 */
	public static Map<String, String> fluxQueryFlowVerify(String xmlDoc){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			
			map.put("returnCode", root.element("return_code").getText());
			map.put("codeDescribe", root.element("code_describe").getText());
			map.put("errorCode", root.element("error_code").getText());
			map.put("contentId", root.element("content_id").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 生成流量订购接口XML
	 * @param mobile 号码
	 * @param productId 流量包ID
	 * @return xml
	 */
	public static String getFlowOrder(String transactionId, String mobile, String productId){
		String xml = null;
		try {
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element root = doc.addElement("interface_root");
			XMLWriter xw;
			StringWriter sw = new StringWriter();
			OutputFormat opf;
			
			root.addElement("transaction_id").setText(transactionId);
			root.addElement("transaction_business_type").setText("");
			root.addElement("transaction_source").setText("");
			root.addElement("transaction_sign").setText("");
			Element contentList = root.addElement("content_list");
			Element content = contentList.addElement("content");
			content.addElement("content_id").setText("");
			Element infoMessage = content.addElement("info_message");
			infoMessage.addElement("regionId").setText("");
			Element operInfo = infoMessage.addElement("operInfo");
			operInfo.addElement("accNbr").setText(mobile);
			operInfo.addElement("pricePlanId").setText(productId);
			operInfo.addElement("actionType").setText("A");
			
			opf = OutputFormat.createCompactFormat();
			opf.setEncoding("utf-8");
			opf.setIndent(true);
			opf.setIndent("    ");
			opf.setNewlines(true);
			xw = new XMLWriter(sw, opf);
			xw.write(doc);
			xw.close();
			xml = sw.toString();
			sw.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	/**
	 * 流量订购接口XML解析
	 * @param xmlDoc xml
	 * @return Map
	 */
	public static Map<String, String> fluxQueryFlowOrder(String xmlDoc){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			
			map.put("returnCode", root.element("return_code").getText());
			map.put("codeDescribe", root.element("code_describe").getText());
			map.put("errorCode", root.element("error_code").getText());
			map.put("contentId", root.element("content_id").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/** 
     * 转换一个xml格式的字符串到json格式 (传进的参数是字符串)
     *  
     * @param xml 
     *            xml格式的字符串 
     * @return 成功返回json 格式的字符串;失败反回null 
	 * @throws IOException 
	 * @throws JDOMException 
     */  
    public static  JSONObject xml2JSON(String xml) throws Exception {
    	
        JSONObject obj = new JSONObject();  
        InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));  
        SAXBuilder sb = new SAXBuilder();  
        org.jdom.Document doc = sb.build(is);  
        org.jdom.Element root = doc.getRootElement();  
        obj.put(root.getName(), iterateElement(root));  
        return obj;
    }
    /** 
     * 转换一个xml格式的字符串到json格式 (传进的参数是xml文件)
     *  
     * @param file 
     *            java.io.File实例是一个有效的xml文件 
     * @return 成功反回json 格式的字符串;失败反回null 
     * @throws IOException 
     * @throws JDOMException 
     */  
    public static JSONObject xml2JSON(File file) throws JDOMException, IOException {
        JSONObject obj = new JSONObject();  
	    SAXBuilder sb = new SAXBuilder();  
	    org.jdom.Document doc = sb.build(file);  
	    org.jdom.Element root = doc.getRootElement();  
	    obj.put(root.getName(), iterateElement(root));  
	    return obj;  
    }
  
    /** 
     * 一个迭代方法 
     *  
     * @param element 
     *            : org.jdom.Element 
     * @return java.util.Map 实例 
     */  
    @SuppressWarnings("unchecked")  
    private static Map  iterateElement(org.jdom.Element element) {  
        List jiedian = element.getChildren();  
        org.jdom.Element et = null;  
        Map obj = new HashMap();  
        List list = null;  
        for (int i = 0; i < jiedian.size(); i++) {  
            list = new LinkedList();  
            et = (org.jdom.Element)jiedian.get(i);  
            if (et.getTextTrim().equals("")) {  
                if (et.getChildren().size() == 0)  
                    continue;  
                if (obj.containsKey(et.getName())) {  
                    list = (List) obj.get(et.getName());  
                }  
                list.add(iterateElement(et));  
                obj.put(et.getName(), list);  
            } else {  
                if (obj.containsKey(et.getName())) {  
                    list = (List) obj.get(et.getName());  
                }  
                list.add(et.getTextTrim());  
                obj.put(et.getName(), list);  
            }  
        }  
        return obj;  
    }
}