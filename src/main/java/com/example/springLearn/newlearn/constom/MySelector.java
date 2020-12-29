package com.example.springLearn.newlearn.constom;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MySelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.example.springLearn.newlearn.bean.Hive", "com.example.springLearn.newlearn.bean.Spark"};
    }
}
