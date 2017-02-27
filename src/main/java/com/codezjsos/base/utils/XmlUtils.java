package com.codezjsos.base.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

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
}
