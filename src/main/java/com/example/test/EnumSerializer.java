package com.example.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: zhangyupeng
 * @date: 2019-10-30 12:01
 * @description: custom enum serializer when json serializer
 **/
public class EnumSerializer extends JsonSerializer<EnumZoo.EnumInterface> {

  private static final Logger logger = LoggerFactory.getLogger(EnumSerializer.class);

  @Override
  public void serialize(EnumZoo.EnumInterface enumInterface, JsonGenerator jsonGenerator,
                        SerializerProvider serializerProvider) throws IOException {
      jsonGenerator.writeObject(enumInterface.getName());
  }
}
