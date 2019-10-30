package com.ioc.ClassPathXml;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcl
 * @Description
 * @date 2019/10/29 9:40
 */
public class ClassPathXmlApplicationContext implements BeanFactory{

   Map<String,Object> beans = new HashMap<String,Object>();
    public ClassPathXmlApplicationContext(String xmlPath){
        try {
            //创建SAXBuilder对象解析文档
            SAXBuilder saxBuilder = new SAXBuilder();
            //解析build里的参数是一个文件路径.
            Document document = saxBuilder.build(xmlPath);
            //document.getRootElement().getChildren("bean")获取所有<bean>标签内容
            List elements = document.getRootElement().getChildren("bean");
            //遍历<bean>对象
            for (int i = 0; i < elements.size(); i++) {
                 //获取第一个<bean标签的elements.get(0)
                Element element = (Element) elements.get(i);

                String beanName = element.getAttributeValue("id");

                String clazz = element.getAttributeValue("class");

                Object object = Class.forName(clazz).newInstance();

                beans.put(beanName,object);

                List elements2 = element.getChildren("property");
                for (int j = 0; j < elements2.size(); j++) {

                    Element element2 = (Element) elements2.get(j);

                    String propertyName = element2.getAttributeValue("name");

                    String refBean = element2.getAttributeValue("ref");

                    propertyName=  propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);

                    String methodName = "set" + propertyName;

                    Object object2 = beans.get(refBean);

                    Method method = object.getClass().getDeclaredMethod(methodName,object2.getClass().getInterfaces());
                    method.invoke(object,object2);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    @Override
    public Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
