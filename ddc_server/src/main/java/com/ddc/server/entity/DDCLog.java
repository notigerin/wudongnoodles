package com.ddc.server.entity;
import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.util.Date;
import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("sdk_back_operation_log")
public class DDCLog extends Model<DDCLog> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    /**
     * logdescription
     */
    @TableField("logdescription")
    private String logdescription;

    /**
     * userno
     */
    @TableField("userno")
    private String userno;

    /**
     * classname
     */
    @TableField("classname")
    private String classname;

    /**
     * methodname
     */
    @TableField("methodname")
    private String methodname;

    /**
     * ip
     */
    @TableField("ip")
    private String ip;

    /**
     * createtime
     */
    @TableField("createtime")
    private String createtime;

    /**
     * succeed
     */
    @TableField("succeed")
    private String succeed;

    /**
     * modelname
     */
    @TableField("modelname")
    private String modelname;

    /**
     * action
     */
    @TableField("action")
    private String action;
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

    public DDCLog(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getLogdescription() {
        return logdescription;
    }

    public void setLogdescription(String logdescription) {
        this.logdescription = logdescription;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DDCLog(String logdescription, String userno, String classname, String methodname, String ip, String createtime, String succeed, String modelname, String action) {
        this.logdescription = logdescription;
        this.userno = userno;
        this.classname = classname;
        this.methodname = methodname;
        this.ip = ip;
        this.createtime = createtime;
        this.succeed = succeed;
        this.modelname = modelname;
        this.action = action;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
