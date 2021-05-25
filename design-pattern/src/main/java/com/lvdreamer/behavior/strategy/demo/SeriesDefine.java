package com.lvdreamer.behavior.strategy.demo;

public class SeriesDefine {


    /**
     * 标签位
     */
    private Long labelNum;

    /**
     * 映射值
     */
    private String labelMap;

    /**
     * 用户数
     */
    private Long userCount;

    /**
     * 描述
     */
    private String remark;

    public Long getLabelNum() {
        return labelNum;
    }

    public void setLabelNum(Long labelNum) {
        this.labelNum = labelNum;
    }

    public String getLabelMap() {
        return labelMap;
    }

    public void setLabelMap(String labelMap) {
        this.labelMap = labelMap;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
