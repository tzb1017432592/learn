package com.example.springLearn.config;

import com.example.springLearn.bean.Cat;
import com.example.springLearn.bean.Mks;
import com.example.springLearn.bean.Person;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value = "com.example.springLearn.config", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TestConfig.class)
}, useDefaultFilters = false)
@Import({Person.class})
public class TestConfig {

    /**
     * 多例模式是懒加载，多例时不会调用销毁方法由系统进行销毁，
     * 只有获取时才会执行以下方法创建对象，单例模式是饿汉式加载，容器启动时就创建了对象
     *
     * @return
     */
    @Scope("prototype")
    @Bean(initMethod = "live", destroyMethod = "die")
    public Cat cat() {
        Cat cat = new Cat();
        cat.setAge(10);
        cat.setName("keli");
        System.out.println("我被创建了------------------------Cat");
        return cat;
    }

    @Bean(initMethod = "live", destroyMethod = "die")
    public Cat cat2() {
        Cat cat = new Cat();
        cat.setAge(10);
        cat.setName("keli");
        System.out.println("我被创建了------------------------Cat");
        return cat;
    }

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        Cat cat = (Cat) applicationContext.getBean("cat2");
        //  ConfigurableListableBeanFactory beanFactory=ConditionContext()
        System.out.println("--");
        applicationContext.close();
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //  ConfigurableListableBeanFactory beanFactory=ConditionContext()
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("--");
    }

    @Test
    public void test3() {
        // ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        BeanDefinitionRegistry beanDefinitionRegistry = new AnnotationConfigApplicationContext();
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Mks.class);
        beanDefinitionRegistry.registerBeanDefinition("good", rootBeanDefinition);
        String[] beanDefinitionNames = beanDefinitionRegistry.getBeanDefinitionNames();
        BeanDefinition good = beanDefinitionRegistry.getBeanDefinition("good");
        String beanClassName = good.getBeanClassName();
        Class<? extends BeanDefinition> aClass = good.getClass();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
