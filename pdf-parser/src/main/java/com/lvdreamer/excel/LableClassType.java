package com.lvdreamer.excel;

public class LableClassType {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 标签大类名称
     */
    private String name;

    /**
     * 标签小类对应的标签大类ID
     */
    private Integer parentId;

    /**
     * 0表示标签大类  1表示标签小类
     */
    private String type;

    /**
     * 创建时间  时间戳
     */
    private String createTime;

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
     * 获取标签大类名称
     *
     * @return name - 标签大类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签大类名称
     *
     * @param name 标签大类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取标签小类对应的标签大类ID
     *
     * @return parent_id - 标签小类对应的标签大类ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置标签小类对应的标签大类ID
     *
     * @param parentId 标签小类对应的标签大类ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取0表示标签大类  1表示标签小类
     *
     * @return type - 0表示标签大类  1表示标签小类
     */
    public String getType() {
        return type;
    }

    /**
     * 设置0表示标签大类  1表示标签小类
     *
     * @param type 0表示标签大类  1表示标签小类
     */
    public void setType(String type) {
        this.type = type;
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
}