package com.lvdreamer.excel;

import java.util.List;

public class InsertSqlPrinter {

    public static void printClassTypeInsertSql(List<LableClassType> datas) {

    }

    public static void printLabelDefineInsertSql(List<LabelDefine> datas) {
        StringBuilder prefixSql = new StringBuilder("INSERT INTO label_define(`COLUMN_NUM`, `COLUMN_NAME`, `VALUE_TYPE`, `CLASS_LEVEL1`, `CLASS_LEVEL2`, `DATA_SOURCE`, `STATUS`, `REMARK`, `EXPIRED_TIME`, `CREATOR_ID`, `CREATOR_NAME`, `CREATE_TIME`, `LAST_UPDATE_TIME`, `LAST_UPDATOR_ID`, `LAST_UPDATOR_NAME`, `IS_DELETED`, `IS_IFLABEL`) VALUES ");
        StringBuilder sqlBody = new StringBuilder();
        for (LabelDefine labelDefine : datas) {
            sqlBody.append(",('").append(labelDefine.getColumnNum()).append("',");
            sqlBody.append("'").append(labelDefine.getColumnName()).append("',");
            sqlBody.append("'").append(labelDefine.getValueType()).append("',");
            sqlBody.append("'").append(labelDefine.getClassLevel1()).append("',");
            sqlBody.append("'").append(labelDefine.getClassLevel2()).append("',");
            sqlBody.append("'").append(labelDefine.getDataSource()).append("',");
            sqlBody.append("'").append(labelDefine.getStatus()).append("',");
            sqlBody.append("'").append(labelDefine.getRemark()).append("',");
            sqlBody.append("'").append(labelDefine.getExpiredTime()).append("',");
            sqlBody.append("'").append(labelDefine.getCreatorId()).append("',");
            sqlBody.append("'").append(labelDefine.getCreatorName()).append("',");
            sqlBody.append("'").append(labelDefine.getCreateTime()).append("',");
            sqlBody.append("'").append(labelDefine.getLastUpdatorId()).append("',");
            sqlBody.append("'").append(labelDefine.getLastUpdatorName()).append("',");
            sqlBody.append("'").append(labelDefine.getLastUpdateTime()).append("',");
            sqlBody.append("'").append(labelDefine.getIsDeleted()).append("',");
            sqlBody.append("'").append(labelDefine.getIsIflabel()).append("'");
            sqlBody.append(")\n");
        }
        sqlBody.deleteCharAt(0);
        System.out.println(prefixSql.append(sqlBody).toString().replaceAll("'null'", "null"));
    }

    public static void printLabelValueInsertSql(List<LabelValueMap> datas) {
        StringBuilder prefixSql = new StringBuilder("INSERT INTO `label_value_map`(`COLUMN_NUM`, `CONDITIONS`, `NUM_VAL1`, `NUM_VAL2`, `STRING_VAL`, `RESULT`, `USER_COUNT`, `REMARK`, `IS_DELETED`) VALUES \n");
        StringBuilder sqlBody = new StringBuilder();
        for (LabelValueMap valueMap : datas) {
            sqlBody.append(",('").append(valueMap.getColumnNum()).append("',");
            sqlBody.append("'").append(valueMap.getConditions()).append("',");
            sqlBody.append("'").append(valueMap.getNumVal1()).append("',");
            sqlBody.append("'").append(valueMap.getNumVal2()).append("',");
            sqlBody.append("'").append(valueMap.getStringVal()).append("',");
            sqlBody.append("'").append(valueMap.getResult()).append("',");
            sqlBody.append("'").append(valueMap.getUserCount()).append("',");
            sqlBody.append("'").append(valueMap.getRemark()).append("',");
            sqlBody.append("'0')\n");
        }
        sqlBody.deleteCharAt(0);
        System.out.println(prefixSql.append(sqlBody).toString().replaceAll("'null'", "null"));

    }
}