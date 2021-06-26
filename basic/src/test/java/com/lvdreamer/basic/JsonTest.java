package com.lvdreamer.basic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

public class JsonTest {

    @Test
    public void nullJson() {
        String response = "{}";
        Math respResult = new Gson().fromJson(response, new TypeToken<Math>() {
        }.getType());
        System.out.println(respResult);
    }

}
