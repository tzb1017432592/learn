package com.example.springLearn.newlearn.constom.postprocess;

import com.example.springLearn.newlearn.bean.Hadoop;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

@Component
public class MyImportBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(Hadoop.class);
        registry.registerBeanDefinition("hadoop2", rootBeanDefinition);
        System.out.println("ImportBeanDefinitionRegistrar:postProcessBeanDefinitionRegistry===============");
    }
}
