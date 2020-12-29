package com.example.springLearn.newlearn.controll;

import com.example.springLearn.newlearn.bean.Kafka;
import com.example.springLearn.newlearn.bean.MySql;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller("testcontroller")
public class TestController {
    //@Qualifier("kafka")
    @Autowired
    private Kafka kafka;

    public void test() {
        kafka.say();
    }

    @Resource
    private MySql mySql;


    public void test2() {
        mySql.say();
    }

}
