package com.fengcase3.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.Map;

public class TestGsonUtil {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("key1","1");
        jsonObject.addProperty("key2","2");
        Iterator<Map.Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next().getKey();
            System.out.println(key);
        }
    }
}
