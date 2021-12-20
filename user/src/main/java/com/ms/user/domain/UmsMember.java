
package com.ms.user.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("ums_member")
@Data
public class UmsMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)

    @Excel(name = "id", width = 15)
    private Long id;


    @Excel(name = "级别", width = 2)
    private Long levelId;


    @Excel(name = "用户名", width = 30)
    private String username;


    @Excel(name = "密码", width = 30)
    private String password;


    @Excel(name = "用户昵称", width = 30)
    private String nickname;


    @Excel(name = "手机号码", width = 30)
    private String mobile;


    @Excel(name = "邮箱", width = 30)
    private String email;


    @Excel(name = "头像", width = 30)
    private String header;


    @Excel(name = "性别", width = 2)
    private int gender;


    @Excel(name = "出生日期", width = 30)
    private Date birth;


    @Excel(name = "城市", width = 30)
    private String city;


    @Excel(name = "工作", width = 30)
    private String job;


    @Excel(name = "标识", width = 30)
    private String sign;


    @Excel(name = "类型", width = 30)
    private int sourceType;


    @Excel(name = "结束", width = 30)
    private Integer integration;

    @Excel(name = "等级", width = 30)

    private Integer growth;


    @Excel(name = "禁用状态", width = 30)
    private int status;

    @Excel(name = "创建时间", width = 30)
    private Date createTime;

    public UmsMember() {
    }
}
