package com.example.springLearn.newlearn.config;

import com.example.springLearn.newlearn.bean.*;
import com.example.springLearn.newlearn.constom.ConditionConstom;
import com.example.springLearn.newlearn.constom.FaBean;
import com.example.springLearn.newlearn.constom.MySelector;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(excludeFilters = {
        //@ComponentScan.Filter(type= FilterType.ANNOTATION,classes = {Controller.class}),
        // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {ServiceTest.class})
        // @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyFilter.class})
}, value = "com.example.springLearn.newlearn")
@Import({Cat.class, MySelector.class})
public class SpringConfig {
    //@Scope("prototype")  //多例单例设置
    @Bean
    @Lazy
    public User getuser() {
        System.out.println("getuser:我被创建了");
        return new User(1, "james");
    }

    @Conditional(ConditionConstom.class)//按照条件进行注册 springboot大量使用的注解
    @Bean
    public User getuser2() {
        System.out.println("getuser2:我被创建了");
        return new User(1, "kobe");
    }

    @Bean
    public FaBean gatFabean() {
        System.out.println("gatFabean:我被创建了");
        return new FaBean();
    }

    @Bean(initMethod = "init", destroyMethod = "die")
    public Scala gatScala() {
        System.out.println("gatFabean:我被创建了");
        return new Scala();
    }

    @Primary
    @Bean("kafka2")
    public Kafka gatKafka() {
        Kafka kafka = new Kafka();
        kafka.setName("kafka2");
        return kafka;
    }

    @Bean("mySql2")
    public MySql gatMySql() {
        MySql mySql = new MySql();
        mySql.setName("mySql2");
        return mySql;
    }

}
