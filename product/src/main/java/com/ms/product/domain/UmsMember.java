package com.ms.product.domain;

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
    /**
     * id
     */
    private Long id;

    /**
     * ��ա�ȼ�id
     */
    private Long levelId;

    /**
     * �û���
     */
    private String username;

    /**
     * ����
     */
    private String password;

    /**
     * �ǳ�
     */
    private String nickname;

    /**
     * �ֻ�����
     */
    private String mobile;

    /**
     * ����
     */
    private String email;

    /**
     * ͷ��
     */
    private String header;

    /**
     * �ա�
     */
    private int gender;

    /**
     * ����
     */
    private Date birth;

    /**
     * ���ڳ���
     */
    private String city;

    /**
     * ְҵ
     */
    private String job;

    /**
     * ����ǩ��
     */
    private String sign;

    /**
     * �û���դ
     */
    private int sourceType;

    /**
     * ����
     */
    private Integer integration;

    /**
     * �ɳ�ֵ
     */
    private Integer growth;

    /**
     * ����״̬
     */
    private int status;

    /**
     * ע��ʱ��
     */
    private Date createTime;

    public UmsMember() {
    }
}