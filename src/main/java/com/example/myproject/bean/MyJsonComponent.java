/**
 * @(#)jsonComponent.java V1.2.0 16-8-16 
 * Copyright (c) 2014-2016 The inspur Software Foundation.All rights reserved 
 */
package com.example.myproject.bean;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Chen Hao
 * @version V1.2.0 16-8-16
 */

//@JsonComponent
public class MyJsonComponent {

    public static class Serializer extends JsonSerializer {

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        }
    }

//    public static class Deserializer extends JsonDeserializer {
//
//        @Override
//        public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//            return null;
//        }
//    }
}
