/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author maoyz0621 on 19-10-9
 * @version: v1.0
 */
public class MyFilterType implements TypeFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilterType.class);

    /**
     * 定制过滤规则
     *
     * @param metadataReader
     * @param metadataReaderFactory
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String superClassName = classMetadata.getSuperClassName();

        LOGGER.info("superClassName = {}", superClassName);

        Resource resource = metadataReader.getResource();

        return true;
    }
}
