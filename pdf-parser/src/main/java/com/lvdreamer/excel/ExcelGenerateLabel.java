package com.lvdreamer.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelGenerateLabel {
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public void read(String excelFilePath) throws IOException {
        // 设定Excel文件所在路径
        // 读取Excel文件内容
        List<String[]> readResult = ExcelReader.readExcel(excelFilePath);
        Map<String, LableClassType> defineTypeMap = new LinkedHashMap<>();
        Map<String, LabelDefine> columnDefineMap = new LinkedHashMap<>();
        List<LabelValueMap> columnValueMap = new ArrayList<>();
        Map<String, String> dataSourceMap = ImmutableMap.of(
                "月", "3",
                "日", "2",
                "日累计", "2",
                "实时", "5"
        );
        AtomicInteger defineId = new AtomicInteger(2);
        AtomicInteger bigTypeId = new AtomicInteger(1);
        AtomicInteger smalTypeId = new AtomicInteger(10);
        AtomicInteger valueId = new AtomicInteger(2);
        String date = LocalDate.now().atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String expDate = LocalDate.now().plusMonths(3).atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LabelDefine allColumnDefine = new LabelDefine();
        allColumnDefine.setId(1);
        allColumnDefine.setColumnNum(0);
        allColumnDefine.setColumnName("全量标签");
        allColumnDefine.setValueType(1);
        allColumnDefine.setClassLevel1("0");
        allColumnDefine.setClassLevel2("0");
        allColumnDefine.setDataSource("0");
        allColumnDefine.setStatus("2");
        allColumnDefine.setRemark("全量标签");
        allColumnDefine.setExpiredTime(expDate);
        allColumnDefine.setCreatorId("1");
        allColumnDefine.setCreatorName("admin");
        allColumnDefine.setCreateTime(date);
        allColumnDefine.setLastUpdateTime(date);
        allColumnDefine.setLastUpdatorId("1");
        allColumnDefine.setLastUpdatorName("admin");
        allColumnDefine.setIsDeleted("0");
        allColumnDefine.setIsIflabel("1");
        allColumnDefine.setMinValue(null);
        allColumnDefine.setMaxValue(null);
        allColumnDefine.setDnaTreeId(null);
        columnDefineMap.put("全量标签", allColumnDefine);
        LabelValueMap columVale = new LabelValueMap();
        columVale.setId(1);
        columVale.setColumnNum(0);
        columVale.setConditions("=");
        columVale.setStringVal("Y");
        columVale.setResult("Y");
        columVale.setUserCount(0);
        columVale.setRemark("全量标签");
        columVale.setIsDeleted("0");
        columVale.setSort(1);
        columVale.setCreateTime(date);
        columVale.setUpdateTime(date);
        columnValueMap.add(columVale);
        for (String[] row : readResult) {
            String bigLabel = row[1];
            String smallLabel = row[2];
            String labelName = row[3];
            String fren = row[6];
            String labelDesc = row[7];
            String type = row[8];
            String labelNum = row[10];
            String newLabelDesc = null;// row[13];
            LableClassType bigType = getColumnDefineType(defineTypeMap, bigTypeId, date, bigLabel, null, "0");
            LableClassType smallType = getColumnDefineType(defineTypeMap, smalTypeId, date, smallLabel, bigType.getId(), "1");
            LabelDefine columnDefine = getColumnDefine(dataSourceMap, defineId, date, expDate, labelName, fren, labelDesc, type, labelNum, bigType, smallType);
            columnDefineMap.put(labelName, columnDefine);
            getValueMap(columnValueMap, valueId, date, columnDefine, labelDesc, newLabelDesc);
            //System.out.println(JSON.toJSONString(columnDefine));
        }

        writeDefineType(defineTypeMap);
        writeDefineColumn(columnDefineMap);
        writeColumnValue(columnValueMap);

        return;
    }

    private void writeColumnValue(List<LabelValueMap> columnValueMap) throws IOException {
        OutputStreamWriter fwriter = new OutputStreamWriter(
                new FileOutputStream(new File("D:\\00-temp\\column_value_map.csv")), "GB2312");
        ICsvBeanWriter writer = new CsvBeanWriter(fwriter,
                CsvPreference.STANDARD_PREFERENCE);
        //输出头部
        String headers = "id,column_num, conditions, num_val1, num_val2, string_val, result, user_count, remark, is_deleted, sort, create_time, update_time".replaceAll(" ", "");
        String[] headerArr = headers.split(",", -1);
        String[] matchHeader = new String[headerArr.length];
        for (int i = 0; i < headerArr.length; i++) {
            matchHeader[i] = lineToHump(headerArr[i]);
        }
        writer.writeHeader(headerArr);
        for (LabelValueMap defineType : columnValueMap) {
//按顺序输出数据
            writer.write(defineType, matchHeader);
        }
        writer.close();
    }

    private void writeDefineType(Map<String, LableClassType> defineTypeMap) throws IOException {
        OutputStreamWriter fwriter = new OutputStreamWriter(
                new FileOutputStream(new File("D:\\00-temp\\column_define_type.csv")), "GB2312");
        ICsvBeanWriter writer = new CsvBeanWriter(fwriter,
                CsvPreference.STANDARD_PREFERENCE);
        //输出头部
        String headers = "id,name, parent_id, type, create_time, sort".replaceAll(" ", "");
        String[] headerArr = headers.split(",", -1);
        String[] matchHeader = new String[headerArr.length];
        for (int i = 0; i < headerArr.length; i++) {
            matchHeader[i] = lineToHump(headerArr[i]);
        }
        writer.writeHeader(headerArr);
        for (LableClassType defineType : defineTypeMap.values()) {
//按顺序输出数据
            writer.write(defineType, matchHeader);
        }
        writer.close();
    }

    private void writeDefineColumn(Map<String, LabelDefine> columnDefineMap) throws IOException {
        OutputStreamWriter fwriter = new OutputStreamWriter(
                new FileOutputStream(new File("D:\\00-temp\\column_define.csv")), "GB2312");
        ICsvBeanWriter writer = new CsvBeanWriter(fwriter,
                CsvPreference.STANDARD_PREFERENCE);
        //输出头部
        String headers = "id,column_num, column_name, value_type, class_level1, class_level2, data_source, status, remark, expired_time, creator_id, creator_name, create_time, last_update_time, last_updator_id, last_updator_name, is_deleted, is_iflabel, min_value, max_value, dna_tree_id".replaceAll(" ", "");
        String[] headerArr = headers.split(",", -1);
        String[] matchHeader = new String[headerArr.length];
        for (int i = 0; i < headerArr.length; i++) {
            matchHeader[i] = lineToHump(headerArr[i]);
        }
        writer.writeHeader(headerArr);

        for (LabelDefine define : columnDefineMap.values()) {
//按顺序输出数据
            writer.write(define, matchHeader);
        }
        writer.close();
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private void getValueMap(List<LabelValueMap> list, AtomicInteger valueId, String date, LabelDefine columnDefine, String labelDesc, String newLabelDesc) {
        String labelInfo = StringUtils.isEmpty(newLabelDesc) ? labelDesc : newLabelDesc;
        if (StringUtils.isEmpty(labelInfo) || labelInfo.contains("自动识别") || labelInfo.contains("XXXX年XX月") || labelInfo.contains("yyyymm") || labelInfo.contains("例：")) {
            return;
        }

        if (columnDefine.getValueType() == 1) {
            BigRadixIdGen bigRadixIdGen = new BigRadixIdGen("0");

            String[] labArr = labelInfo.split("[;、]");
            for (String lab : labArr) {
                LabelValueMap columnValueMap = new LabelValueMap();
                columnValueMap.setId(valueId.getAndIncrement());
                columnValueMap.setColumnNum(columnDefine.getColumnNum());
                columnValueMap.setConditions("=");
                columnValueMap.setStringVal(lab);
                columnValueMap.setResult(bigRadixIdGen.getAndIncre());
                columnValueMap.setUserCount(0);
                columnValueMap.setRemark(lab);
                columnValueMap.setIsDeleted("0");
                columnValueMap.setSort(columnValueMap.getId());
                columnValueMap.setCreateTime(date);
                columnValueMap.setUpdateTime(date);
                System.out.println(JSON.toJSONString(columnValueMap));
                list.add(columnValueMap);
            }
        } else {

            labelInfo = labelInfo.replaceAll(" ", "").replaceAll("-", ",");
            System.out.println(labelInfo);
            char[] charArr = labelInfo.toCharArray();
            BigRadixIdGen bigRadixIdGen = new BigRadixIdGen("0");
            StringBuilder sb = new StringBuilder();
            LabelValueMap columnValueMap = null;
            List<LabelValueMap> currValueMap = new ArrayList<>();
            /**
             * 单位转换
             */

            long convertRadio = 1;
            switch (columnDefine.getColumnNum()) {
                case 1137:
                case 1138:
                    convertRadio = 1024 * 1024;
                    break;
                case 1130:
                    convertRadio = 1000;
                    break;
            }
            for (int i = 0; i < charArr.length; ) {
                char c = charArr[i];
                if (c == '(' || c == '[') {
                    char start = c;
                    StringBuilder bNum = new StringBuilder();
                    StringBuilder endNum = new StringBuilder();
                    StringBuilder condition = new StringBuilder().append(c);
                    i++;
                    char nextC;
                    do {
                        nextC = charArr[i++];
                        if (Character.isDigit(nextC) || '.' == nextC) {
                            bNum.append(nextC);
                        }
                    } while (nextC != ',' && i < charArr.length);
                    do {
                        nextC = charArr[i++];
                        if (Character.isDigit(nextC) || '.' == nextC) {
                            endNum.append(nextC);
                        }
                    }

                    while (!"])".contains(nextC + "") && i < charArr.length);
                    char end = charArr[i - 1];
                    condition.append(end);
                    columnValueMap = new LabelValueMap();
                    columnValueMap.setId(valueId.getAndIncrement());
                    columnValueMap.setColumnNum(columnDefine.getColumnNum());
                    columnValueMap.setConditions(condition.toString());
                    columnValueMap.setNumVal1(new BigDecimal(bNum.toString()).multiply(new BigDecimal(convertRadio)));
                    if (!StringUtils.isEmpty(endNum.toString())) {
                        columnValueMap.setNumVal2(new BigDecimal(endNum.toString()).multiply(new BigDecimal(convertRadio)));
                    }
                    columnValueMap.setResult(bigRadixIdGen.getAndIncre());
                    columnValueMap.setUserCount(0);
                    columnValueMap.setRemark(start + bNum.toString() + "," + endNum.toString() + end);
                    columnValueMap.setIsDeleted("0");
                    columnValueMap.setSort(columnValueMap.getId());
                    columnValueMap.setCreateTime(date);
                    columnValueMap.setUpdateTime(date);
                    list.add(columnValueMap);
                    currValueMap.add(columnValueMap);
                    System.out.println(JSON.toJSONString(columnValueMap));
                } else if (Character.isDigit(c)) {
                    StringBuilder bNum = new StringBuilder().append(c);
                    i++;
                    char nextC;
                    do {
                        nextC = charArr[i++];
                        if (Character.isDigit(nextC) || '.' == nextC) {
                            bNum.append(nextC);
                        }

                    }
                    while ((Character.isDigit(nextC) || '.' == nextC) && i < charArr.length - 1);

                    StringBuilder remark = new StringBuilder().append(bNum);
                    if (nextC != ',' && nextC != ';') {
                        remark.append(nextC);
                        while (i < charArr.length && (nextC = charArr[i++]) != ',' && nextC != ';') {
                            remark.append(nextC);
                        }
                    }
                    columnValueMap = new LabelValueMap();
                    columnValueMap.setId(valueId.getAndIncrement());
                    columnValueMap.setColumnNum(columnDefine.getColumnNum());
                    columnValueMap.setConditions("=");
                    columnValueMap.setNumVal1(new BigDecimal(bNum.toString()).multiply(new BigDecimal(convertRadio)));
                    columnValueMap.setResult(bigRadixIdGen.getAndIncre());
                    columnValueMap.setUserCount(0);
                    columnValueMap.setRemark(remark.toString());
                    columnValueMap.setIsDeleted("0");
                    columnValueMap.setSort(columnValueMap.getId());
                    columnValueMap.setCreateTime(date);
                    columnValueMap.setUpdateTime(date);
                    list.add(columnValueMap);
                    currValueMap.add(columnValueMap);
                    System.out.println(JSON.toJSONString(columnValueMap));
                } else {
                    i++;
                }
            }
            if (!currValueMap.isEmpty() && currValueMap.size() > 1) {
                int size = currValueMap.size();
                LabelValueMap lastValue = currValueMap.get(size - 1);
                LabelValueMap last2Value = currValueMap.get(size - 2);
                if (null == lastValue.getNumVal2()) {
                    char last2con = last2Value.getConditions().charAt(1);
                    if (last2Value.getNumVal2().compareTo(last2Value.getNumVal1()) > 0) {
                        lastValue.setConditions(last2con == ']' ? ">" : ">=");
                    } else {
                        lastValue.setConditions(last2con == ']' ? "<" : "<=");
                    }
                }
            }
        }

    }

    private LabelDefine getColumnDefine(Map<String, String> dataSourceMap, AtomicInteger defineId, String date, String expDate, String labelName, String fren, String labelDesc, String type, String labelNum, LableClassType bigType, LableClassType smallType) {
        LabelDefine labelDefine = new LabelDefine();
        labelDefine.setId(defineId.getAndIncrement());
        labelDefine.setColumnNum(Integer.parseInt(labelNum));
        labelDefine.setColumnName(labelName);
        labelDefine.setValueType(type.equals("字符") ? 1 : 2);
        labelDefine.setClassLevel1("" + bigType.getId());
        labelDefine.setClassLevel2("" + smallType.getId());
        labelDefine.setDataSource(dataSourceMap.get(fren));
        labelDefine.setStatus("1");
        labelDefine.setRemark(labelName);
        labelDefine.setExpiredTime(expDate);
        labelDefine.setCreatorId("1");
        labelDefine.setCreatorName("admin");
        labelDefine.setCreateTime(date);
        labelDefine.setLastUpdateTime(date);
        labelDefine.setLastUpdatorId("1");
        labelDefine.setLastUpdatorName("admin");
        labelDefine.setIsDeleted("0");
        labelDefine.setIsIflabel("是;否".equals(labelDesc) ? "1" : "0");
        labelDefine.setMinValue(null);
        labelDefine.setMaxValue(null);
        labelDefine.setDnaTreeId(null);
        return labelDefine;
    }

    private LableClassType getColumnDefineType(Map<String, LableClassType> defineTypeMap, AtomicInteger bigTypeId, String date, String bigLabel, Integer o, String s) {
        LableClassType bigType = defineTypeMap.get(bigLabel);
        if (null == bigType) {
            bigType = new LableClassType();
            bigType.setId(bigTypeId.getAndIncrement());
            bigType.setName(bigLabel);
            bigType.setParentId(o);
            bigType.setType(s);
            bigType.setCreateTime(date);
            bigType.setSort(0);
            defineTypeMap.put(bigLabel, bigType);
        }
        return bigType;
    }


}
