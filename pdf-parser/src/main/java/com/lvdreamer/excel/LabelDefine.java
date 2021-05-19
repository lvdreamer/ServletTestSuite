package com.lvdreamer.excel;

import java.math.BigDecimal;
import java.util.Date;

public class LabelDefine {
    /**
     * 主键
     */
    private Integer id;

    /**
     * DNA位
     */
    private Integer columnNum;

    /**
     * DNA位含义
     */
    private String columnName;

    /**
     * 值类型 1 STR：字符型   2 NUM：数字型  3 DATE：日期型
     */
    private Integer valueType;

    /**
     * 标签大类
     */
    private String classLevel1;

    /**
     * 标签小类
     */
    private String classLevel2;

    /**
     * 数据来源 1手工导入 2日标签数据 3月标签数据 4客户调研 5实时DNA 6用户画像DNA 7衍生DNA 9程序自动生成,10转换标签 11手工批量标签
     */
    private String dataSource;

    /**
     * 标签位状态 1未使用 2使用中 3已到期 4已删除
     */
    private String status;

    /**
     * 标签描述
     */
    private String remark;

    /**
     * 过期时间
     */
    private String expiredTime;

    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 创建时间，时间戳
     */
    private String createTime;

    /**
     * 最后一次更新时间，时间戳
     */
    private String lastUpdateTime;

    /**
     * 最后一次更新人的id
     */
    private String lastUpdatorId;

    /**
     * 最后一次更新人的名称
     */
    private String lastUpdatorName;

    /**
     * 0 表示未删除  1 表示删除
     */
    private String isDeleted;

    /**
     * 是否是是否标签 0是 1否
     */
    private String isIflabel;

    /**
     * 标签最小值
     */
    private BigDecimal minValue;

    /**
     * 标签最大值
     */
    private BigDecimal maxValue;

    /**
     * 关联树id
     */
    private Integer dnaTreeId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取DNA位
     *
     * @return column_num - DNA位
     */
    public Integer getColumnNum() {
        return columnNum;
    }

    /**
     * 设置DNA位
     *
     * @param columnNum DNA位
     */
    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    /**
     * 获取DNA位含义
     *
     * @return column_name - DNA位含义
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置DNA位含义
     *
     * @param columnName DNA位含义
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * 获取值类型 1 STR：字符型   2 NUM：数字型  3 DATE：日期型
     *
     * @return value_type - 值类型 1 STR：字符型   2 NUM：数字型  3 DATE：日期型
     */
    public Integer getValueType() {
        return valueType;
    }

    /**
     * 设置值类型 1 STR：字符型   2 NUM：数字型  3 DATE：日期型
     *
     * @param valueType 值类型 1 STR：字符型   2 NUM：数字型  3 DATE：日期型
     */
    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    /**
     * 获取标签大类
     *
     * @return class_level1 - 标签大类
     */
    public String getClassLevel1() {
        return classLevel1;
    }

    /**
     * 设置标签大类
     *
     * @param classLevel1 标签大类
     */
    public void setClassLevel1(String classLevel1) {
        this.classLevel1 = classLevel1;
    }

    /**
     * 获取标签小类
     *
     * @return class_level2 - 标签小类
     */
    public String getClassLevel2() {
        return classLevel2;
    }

    /**
     * 设置标签小类
     *
     * @param classLevel2 标签小类
     */
    public void setClassLevel2(String classLevel2) {
        this.classLevel2 = classLevel2;
    }

    /**
     * 获取数据来源 1手工导入 2日标签数据 3月标签数据 4客户调研 5实时DNA 6用户画像DNA 7衍生DNA 9程序自动生成,10转换标签 11手工批量标签
     *
     * @return data_source - 数据来源 1手工导入 2日标签数据 3月标签数据 4客户调研 5实时DNA 6用户画像DNA 7衍生DNA 9程序自动生成,10转换标签 11手工批量标签
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置数据来源 1手工导入 2日标签数据 3月标签数据 4客户调研 5实时DNA 6用户画像DNA 7衍生DNA 9程序自动生成,10转换标签 11手工批量标签
     *
     * @param dataSource 数据来源 1手工导入 2日标签数据 3月标签数据 4客户调研 5实时DNA 6用户画像DNA 7衍生DNA 9程序自动生成,10转换标签 11手工批量标签
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取标签位状态 1未使用 2使用中 3已到期 4已删除
     *
     * @return status - 标签位状态 1未使用 2使用中 3已到期 4已删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置标签位状态 1未使用 2使用中 3已到期 4已删除
     *
     * @param status 标签位状态 1未使用 2使用中 3已到期 4已删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取标签描述
     *
     * @return remark - 标签描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置标签描述
     *
     * @param remark 标签描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * 获取创建人ID
     *
     * @return creator_id - 创建人ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人ID
     *
     * @param creatorId 创建人ID
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建人名称
     *
     * @return creator_name - 创建人名称
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置创建人名称
     *
     * @param creatorName 创建人名称
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取最后一次更新人的id
     *
     * @return last_updator_id - 最后一次更新人的id
     */
    public String getLastUpdatorId() {
        return lastUpdatorId;
    }

    /**
     * 设置最后一次更新人的id
     *
     * @param lastUpdatorId 最后一次更新人的id
     */
    public void setLastUpdatorId(String lastUpdatorId) {
        this.lastUpdatorId = lastUpdatorId;
    }

    /**
     * 获取最后一次更新人的名称
     *
     * @return last_updator_name - 最后一次更新人的名称
     */
    public String getLastUpdatorName() {
        return lastUpdatorName;
    }

    /**
     * 设置最后一次更新人的名称
     *
     * @param lastUpdatorName 最后一次更新人的名称
     */
    public void setLastUpdatorName(String lastUpdatorName) {
        this.lastUpdatorName = lastUpdatorName;
    }

    /**
     * 获取0 表示未删除  1 表示删除
     *
     * @return is_deleted - 0 表示未删除  1 表示删除
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置0 表示未删除  1 表示删除
     *
     * @param isDeleted 0 表示未删除  1 表示删除
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取是否是是否标签 0是 1否
     *
     * @return is_iflabel - 是否是是否标签 0是 1否
     */
    public String getIsIflabel() {
        return isIflabel;
    }

    /**
     * 设置是否是是否标签 0是 1否
     *
     * @param isIflabel 是否是是否标签 0是 1否
     */
    public void setIsIflabel(String isIflabel) {
        this.isIflabel = isIflabel;
    }

    /**
     * 获取标签最小值
     *
     * @return min_value - 标签最小值
     */
    public BigDecimal getMinValue() {
        return minValue;
    }

    /**
     * 设置标签最小值
     *
     * @param minValue 标签最小值
     */
    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    /**
     * 获取标签最大值
     *
     * @return max_value - 标签最大值
     */
    public BigDecimal getMaxValue() {
        return maxValue;
    }

    /**
     * 设置标签最大值
     *
     * @param maxValue 标签最大值
     */
    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 获取关联树id
     *
     * @return dna_tree_id - 关联树id
     */
    public Integer getDnaTreeId() {
        return dnaTreeId;
    }

    /**
     * 设置关联树id
     *
     * @param dnaTreeId 关联树id
     */
    public void setDnaTreeId(Integer dnaTreeId) {
        this.dnaTreeId = dnaTreeId;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}