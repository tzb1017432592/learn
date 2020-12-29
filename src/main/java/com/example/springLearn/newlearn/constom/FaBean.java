package com.example.springLearn.newlearn.constom;

import com.example.springLearn.newlearn.bean.Php;
import org.springframework.beans.factory.FactoryBean;

public class FaBean implements FactoryBean<Php> {
    //注册的bean实例
    @Override
    public Php getObject() throws Exception {
        return new Php(1, "php");
    }

    //注册的bean的类型
    @Override
    public Class<?> getObjectType() {
        return Php.class;
    }

    //定义是是否为单例，true是，false否
    @Override
    public boolean isSingleton() {
        return true;
    }
}
