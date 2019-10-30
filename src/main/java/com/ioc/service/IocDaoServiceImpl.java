package com.ioc.service;

import com.ioc.bean.IocDao;

/**
 * @author zcl
 * @Description
 * @date 2019/10/29 9:38
 */
public class IocDaoServiceImpl implements IocDaoService{
   //创建一个接口
    private IocDao iocDao;

    public void setIocDao(IocDao iocDao) {
        this.iocDao = iocDao;
    }

    public IocDao getIocDao() {
        return iocDao;
    }

    @Override
    public void sayhello() {
        //TODO Auto-generated method stub
        iocDao.sayhello();//调用接口方法
    }
}
