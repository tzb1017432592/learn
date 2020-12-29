package com.example.springLearn.custom;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {
    /**
     * @param metadataReader        :读取正在扫描的信息
     * @param metadataReaderFactory 获取到其他任何类信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类路径
        Resource resource = metadataReader.getResource();
        System.out.println(resource.getURI());
        System.out.println(classMetadata.getClassName());
        if (classMetadata.getClassName().contains("Service")) {
            return false;
        }
        return true;
    }
}
