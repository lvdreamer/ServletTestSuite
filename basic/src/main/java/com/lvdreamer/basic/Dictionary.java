package com.lvdreamer.basic;

import com.google.common.collect.ImmutableTable;

public enum Dictionary {

    DATASOURCE, HANDLE_TYPE, HANDLE_STATUS;

    private static final ImmutableTable<Dictionary, String, String> dict = ImmutableTable.<Dictionary, String, String>builder()
            .put(DATASOURCE, "1", "数据源1")
            .put(DATASOURCE, "2", "数据源2")
            .put(DATASOURCE, "3", "数据源3")
            .put(DATASOURCE, "5", "数据源4")
            .put(DATASOURCE, "10", "数据源5")
            .put(DATASOURCE, "11", "数据源6")
            .put(HANDLE_STATUS, "1", "处理中")
            .put(HANDLE_STATUS, "2", "处理完成")
            .put(HANDLE_TYPE, "1", "替换")
            .put(HANDLE_TYPE, "2", "追加")
            .build();

    public String getNameByType(String type) {
        return dict.get(this, type);
    }
}
