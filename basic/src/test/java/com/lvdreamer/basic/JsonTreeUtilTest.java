package com.lvdreamer.basic;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.Test;

public class JsonTreeUtilTest {

    @Test
    public void recursionCreateArrayNotRoot() {

        String testData = "[{\"orgId\":\"688733de44fbf0fb071106146b503560\",\"orgName\":\"运营中心\",\"parentOrgId\":\"DQ\"},{\"orgId\":\"6909577c9e482d5e004a6d2cabdaeaa4\",\"orgName\":\"监控中心\",\"parentOrgId\":\"DF\"},";
        JsonArray jsonArray = new JsonParser().parse(testData).getAsJsonArray();
        JsonArray result = JsonTreeUtil.recursionCreateArrayNotRoot(jsonArray, null, "child", "orgId", "parentOrgId", 1);
        System.out.println(result);
    }
}