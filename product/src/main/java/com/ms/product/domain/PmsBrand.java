package com.ms.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@TableName("pms_brand")
@Data
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * ʒ��id
    */
    private Long brandId;

    /**
    * ʒ����
    */
    private String name;

    /**
    * ʒ��logo��ַ
    */
    private String logo;

    /**
    * ����
    */
    private String descript;

    /**
    * ��ʾ״̬[0-����ʾ��1-��ʾ]
    */
    private int showStatus;

    /**
    * ��������ĸ
    */
    private String firstLetter;

    /**
    * ����
    */
    private Integer sort;

    public PmsBrand() {}
}