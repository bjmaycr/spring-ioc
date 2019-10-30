package com.ioc;

import com.ioc.ClassPathXml.ClassPathXmlApplicationContext;
import com.ioc.service.IocDaoService;

/**
 * @author zcl
 * @Description
 * @date 2019/10/29 9:56
 */
public class Ioc {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext  applicationContext = new ClassPathXmlApplicationContext("src/bean.xml");
        IocDaoService ids = (IocDaoService) applicationContext.getBean("iocService");
        ids.sayhello();
    }
}
