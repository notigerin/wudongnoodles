package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("commen")
public class Commen extends Model<DDCRole>
{
    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    /**
     * username
     */
    @TableField("username")
    private String username;

    /**
     * sex
     */
    @TableField("sex")
    private Integer sex;

    /**
     * mobile
     */
    @TableField("mobile")
    private String mobile;

    /**
     * email
     */
    @TableField("email")
    private String email;

    /**
     * path
     */
    @TableField("path")
    private String path;

    /**
     * city
     */
    @TableField("city")
    private long city;

    /**
     * remark
     */
    @TableField("remark")
    private String remark;

    /**
     * create_by
     */
    @TableField("create_By")
    private Long create_By;

    /**
     * create_time
     */
    @TableField("create_Time")
    private Long create_Time;

    /**
     * update_by
     */
    @TableField("update_By")
    private Long update_By;

    /**
     * update_time
     */
    @TableField("update_Time")
    private Long update_Time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateBy() {
        return create_By;
    }

    public void setCreateBy(Long create_By) {
        this.create_By = create_By;
    }

    public Long getCreateTime() {
        return create_Time;
    }

    public void setCreateTime(Long create_Time) {
        this.create_Time = create_Time;
    }

    public Long getUpdateBy() {
        return update_By;
    }

    public void setUpdateBy(Long update_By) {
        this.update_By = update_By;
    }

    public Long getUpdateTime() {
        return update_Time;
    }

    public void setUpdateTime(Long update_Time) {
        this.update_Time = update_Time;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
