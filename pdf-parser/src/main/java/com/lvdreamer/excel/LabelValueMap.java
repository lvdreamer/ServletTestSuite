package com.lvdreamer.excel;

import java.math.BigDecimal;
import java.util.Date;

public class LabelValueMap {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 标签位
     */
    private Integer columnNum;

    /**
     * 条件 =：等于 LK：like
     */
    private String conditions;

    /**
     * 数值上限
     */
    private BigDecimal numVal1;

    /**
     * 数值下限
     */
    private BigDecimal numVal2;

    /**
     * 字符型的数值
     */
    private String stringVal;

    private String result;

    /**
     * 用户数
     */
    private Integer userCount;

    /**
     * 描述
     */
    private String remark;

    /**
     * 0 表示未删除  1 表示删除
     */
    private String isDeleted;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 排序字段
     */
    private Integer sort;

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
     * 获取标签位
     *
     * @return column_num - 标签位
     */
    public Integer getColumnNum() {
        return columnNum;
    }

    /**
     * 设置标签位
     *
     * @param columnNum 标签位
     */
    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    /**
     * 获取条件 =：等于 LK：like
     *
     * @return conditions - 条件 =：等于 LK：like
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * 设置条件 =：等于 LK：like
     *
     * @param conditions 条件 =：等于 LK：like
     */
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    /**
     * 获取数值上限
     *
     * @return num_val1 - 数值上限
     */
    public BigDecimal getNumVal1() {
        return numVal1;
    }

    /**
     * 设置数值上限
     *
     * @param numVal1 数值上限
     */
    public void setNumVal1(BigDecimal numVal1) {
        this.numVal1 = numVal1;
    }

    /**
     * 获取数值下限
     *
     * @return num_val2 - 数值下限
     */
    public BigDecimal getNumVal2() {
        return numVal2;
    }

    /**
     * 设置数值下限
     *
     * @param numVal2 数值下限
     */
    public void setNumVal2(BigDecimal numVal2) {
        this.numVal2 = numVal2;
    }

    /**
     * 获取字符型的数值
     *
     * @return string_val - 字符型的数值
     */
    public String getStringVal() {
        return stringVal;
    }

    /**
     * 设置字符型的数值
     *
     * @param stringVal 字符型的数值
     */
    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

    /**
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取用户数
     *
     * @return user_count - 用户数
     */
    public Integer getUserCount() {
        return userCount;
    }

    /**
     * 设置用户数
     *
     * @param userCount 用户数
     */
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 获取排序字段
     *
     * @return sort - 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序字段
     *
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}