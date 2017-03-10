package com.codezjsos.base.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhufang on 2017/2/27.
 */
public class XmlUtils {
    public XmlUtils() {
        super();
    }
    private static Document instance(String filename) throws DocumentException {
        SAXReader sr=new SAXReader();
        return sr.read(XmlUtils.class.getClassLoader().getResourceAsStream(filename));
    }

    private static Element getById(String filename,String id) throws DocumentException {
        Element element= (Element) instance(filename).selectSingleNode("//*[@id='"+id+"']");
        return element;
    }
    public static String readValueById(String filename,String id,String attrName) throws DocumentException {
            return getById(filename,id).attributeValue(attrName);
    }

    public static String readVauleRefAttr(String filename,String id,String attrName) throws DocumentException {
        String refid=readValueById(filename,id,"ref");
        return readValueById(filename,refid,attrName);
    }

    private static Map<String,Object> readFieldMap(Element element ){
        Map<String,Object> map=new HashMap<String,Object>();
        for(Object obj:element.attributes()){
            Attribute attr= (Attribute) obj;
            map.put(attr.getName(),attr.getValue());
        }
        return map;
    }
    public static Map<String,Object> readAttrForMap(String filename,String id) throws DocumentException {

        return readFieldMap(getById(filename,id));
    }

    private static List<Map<String,Object>> readElements(List<Element> list){

        List<Map<String,Object>> fields=new ArrayList<Map<String, Object>>();
        for(Element e:list){
            fields.add(readFieldMap(e));
        }
        return fields;
    }
    public static List<Map<String,Object>> readElements(String filename,String id) throws DocumentException {
        Element element=getById(filename, id);
        List<Element> list=element.elements();
        return readElements(list);
    }
    public static List<Map<String,Object>> readElements(String filename,String id,String tag) throws DocumentException {
        Element element=getById(filename, id);
        List<Element> list=element.selectNodes(tag);
        return readElements(list);
    }

    public static List<Map<String,Object>> readElementsByTag(String filename,String tag) throws DocumentException {
        Element element= (Element) instance(filename).selectSingleNode("//"+tag);
        return readElements(element.elements());
    }

    public static Object attrConverObject (Class<?> cls,Map<String,Object> values) throws Exception {
        Object obj=cls.newInstance();
        Field[] fields=cls.getFields();
        for(Field f:fields){
            String fieldname=f.getName();
            if(values.containsKey(fieldname)){
                BeanUtils.setProperty(obj,fieldname,values.get(fieldname));
            }
        }
        return obj;
    }

    public static List<String> readTexts(String filename,String select) throws DocumentException {
        List list = instance(filename).selectNodes(select);
        List<String> list2=new ArrayList<String>();
        for(Object obj:list){
            Element e=(Element)obj;
            list2.add(e.getTextTrim());
        }
        return list2;
    }
    public static String  readText(String filename,String id) throws DocumentException {
        return getById(filename,id).getTextTrim();

    }
}
