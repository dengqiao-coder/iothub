package com.lemon.iothubserver.entity;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author dengqiao
 * @date 2020-01-03 11:07
 */
@Data
public abstract class MongoAbsEntity {
    @Id
    @Indexed
    private String id;
    @Field("create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date createTime;
    @Field("update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date updateTime;
    @Field("remarks")
    protected String remarks;
    @Field("create_user")
    protected String createUser;
    @Field("update_user")
    protected String updateUser;
    @Field("delete_flag")
    protected int deleteFlag;

    public void preSave() {
        if (StrUtil.isBlank(id)) {
            this.createTime = new Date();
        }
        this.updateTime = new Date();
    }
}
