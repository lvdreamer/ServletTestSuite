package com.lvdreamer.basic;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonTreeUtil {

    /**
     * * 生成树形结构(根节点不确定的情况)
     *
     * @param list        源数据
     * @param nodeList    跟节点数据
     * @param childName   子节点名称
     * @param key         节点key
     * @param up_key      上级节点key
     * @param child_level 节点级别 1,2,3...
     * @return
     */
    public static JsonArray recursionCreateArrayNotRoot(JsonArray list, JsonArray nodeList, String childName, String key, String up_key, int child_level) {
        if (nodeList == null) {
            //跟节点为空的情况下 ，遍历查询跟节点
            nodeList = new JsonArray();//作为跟节点的存在
            for (int i = 0; i < list.size(); i++) {
                boolean mark = false;
                JsonObject n = list.get(i).getAsJsonObject();
                for (int j = 0; j < list.size(); j++) {
                    JsonObject m = list.get(j).getAsJsonObject();
                    if (n.get(up_key).getAsString().equals(m.get(key).getAsString())) {
                        mark = true;
                        break;
                    }
                }
                if (!mark) {
                    n.addProperty("child_level", child_level);
                    nodeList.add(n);//添加的是没有上级的
                }
            }
            list.remove(nodeList);//将根节点移除出列表
        }
        //为根节点寻找下级
        for (int i = 0; i < nodeList.size(); i++) {
            JsonObject n = nodeList.get(i).getAsJsonObject();
            JsonArray child = (JsonArray)n.get(childName);
            if (child == null) {
                child = new JsonArray();
            }
            for (int j = 0; j < list.size(); j++) {
                JsonObject m = list.get(j).getAsJsonObject();
                if (n.get(key).getAsString().equals(m.get(up_key).getAsString())) {
                    m.addProperty("child_level", child_level + 1);
                    child.add(m);
                }
            }
            n.add(childName, child);
            list.remove(child);
            recursionCreateArrayNotRoot(list, n.get(childName).getAsJsonArray(), childName, key, up_key, child_level + 1);//不断的寻找下级
        }
        return nodeList;
    }

    public static void main(String[] args) {
        String testData = "[{\"id\":\"2001\",\"parentId\":\"20\",\"usr_org_name\":\"1级\",\"org_level\":\"1\"},{\"id\":\"2002\",\"parentId\":\"2001\",\"usr_org_name\":\"2级\",\"org_level\":\"1\"},{\"id\":\"2003\",\"parentId\":\"2002\",\"usr_org_name\":\"3级\",\"org_level\":\"1\"}]";
        JsonArray jsonArray = new JsonParser().parse(testData).getAsJsonArray();
        JsonArray result = JsonTreeUtil.recursionCreateArrayNotRoot(jsonArray, null, "child", "id", "parentId", 1);
        System.out.println(result);

    }
}