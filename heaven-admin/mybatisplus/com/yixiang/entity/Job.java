package com.yixiang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 岗位
 * </p>
 *
 * @author Bean
 * @since 2020-04-21
 */
public class Job extends Model<Job> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位状态
     */
    private Boolean enabled;

    /**
     * 岗位排序
     */
    private Long sort;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 创建日期
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Job{" +
        "id=" + id +
        ", name=" + name +
        ", enabled=" + enabled +
        ", sort=" + sort +
        ", deptId=" + deptId +
        ", createTime=" + createTime +
        "}";
    }
}
