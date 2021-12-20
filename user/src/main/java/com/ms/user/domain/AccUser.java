package com.ms.user.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("acc_user1")
public class AccUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 主键id
     */
    @Excel(name = "id", width = 15)
    private Long id;

    /**
     * 登录账号
     */
    @Excel(name = "姓名", width = 30)
    private String username;

    /**
     * 昵称
     */
    @Excel(name = "用户昵称", width = 30)
    private String nickName;

    /**
     * 密码
     */
    @Excel(name = "密码", width = 30)
    private String password;

    /**
     * 加密盐
     */
    @Excel(name = "加密盐", width = 255)
    private String salt;

    /**
     * 头像
     */
    @Excel(name = "头像", width = 255)
    private String avatar;


    /**
     * 性别(0：默认未知，1：男，2：女)
     */
    @Excel(name = "性别", width = 15)
    private Integer sex;

    /**
     * 电子邮件country_code
     */
    @Excel(name = "电子邮件", width = 60)
    private String email;


    /**
     * 账号状态(0：默认启用，1：停用)
     */
    @Excel(name = "账号状态", width = 15)
    private Integer status;

    /**
     * 微信用户openid
     */
    @Excel(name = "微信用户openid", width = 255)
    private String clientToken;

    /**
     * facebook用户id
     */
    @Excel(name = "facebook用户id", width = 30)
    private String facebookId;

    /**
     * google用户id
     */
    @Excel(name = "google用户id", width = 30)
    private String googleId;

    /**
     * apple用户id
     */
    @Excel(name = "apple用户id", width = 30)
    private String appleId;

    /**
     * 账号来源于那个平台(默认0-微信；1-谷歌；2-facebook；3-apple；4-平台直接注册)
     */
    @Excel(name = "平台", width = 15)
    private Integer accountFromPlatform;

    /**
     * 账号类型(0：默认普通用户，1：商家；2-临时账户; 3-运维；4-代理;5-子代理)
     */
    @Excel(name = "账号类型", width = 15)
    private Integer accountType;

    /**
     * 账号来源(android;ios;h5;微信)
     */
    @Excel(name = "账号来源", width = 30)
    private String userSource;


    /**
     * 删除标识(0：默认正常，1：删除)
     */
    @Excel(name = "删除标识", width = 15)
    private Integer delFlag;

    /**
     * 商户id
     */
    @Excel(name = "商户id", width = 30)
    private Long businessId;

    /**
     * 真实姓名
     */
    @Excel(name = "加密盐", width = 30)
    private String realname;

    /**
     * 订单数
     */
    @Excel(name = "订单数", width = 15)
    private Integer orderNums;

    /**
     * 用户当前积分值
     */
    @Excel(name = "用户当前积分值", width = 15)
    private Integer commodityNum;

    /**
     * 消费金额
     */
    @Excel(name = "消费金额", width = 15)
    private Long orderMoney;

    /**
     * 押金缴纳状态：0-未缴纳；1-已缴纳
     */
    @Excel(name = "押金缴纳状态", width = 15)
    private Integer depositStatus;

    /**
     * 会员状态：0-未开通；1-已开通
     */
    @Excel(name = "会员状态", width = 15)
    private Integer memberStatus;


    /**
     * 冻结/停用操作人id
     */
    @Excel(name = "停用操作人id", width = 15)
    private Long operUserId;

    /**
     * 冻结/停用操作人名称
     */
    @Excel(name = "停用操作人名称", width = 30)
    private String operUserName;

    /**
     * 冻结/停用原因
     */
    @Excel(name = "停用原因", width = 30)
    private String operDesc;

    /**
     * 服务类型：0-点餐；1-共享充电宝；2-生活服务；3-金融服务；4-票务服务；5-旅游服务；6-招聘服务；7-二手交易；8-二手车；9-房产交易;10-ipfs
     */
    @Excel(name = "服务类型", width = 15)
    private Integer platformType;

    /**
     * 城市
     */
    @Excel(name = "城市", width = 30)
    private String city;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址", width = 30)
    private String address;

    /**
     * 充电宝服务--极光推送用户id
     */
    @Excel(name = "极光推送用户id", width = 30)
    private String jpushId;

    /**
     * 0:没有新手礼包，1：有新手礼包
     */
    @Excel(name = "新手礼包", width = 15)
    private Integer tyro;

    /**
     * 更新新手礼包标志: 0-未更新，1-已更新
     */
    @Excel(name = "更新新手礼包标志", width = 15)
    private Integer updTyroFlag;

    public AccUser() {
    }
}