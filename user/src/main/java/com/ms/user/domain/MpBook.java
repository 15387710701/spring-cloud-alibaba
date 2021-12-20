package com.ms.user.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Bboy_smartv
 * @description mp_book
 * @date 2021-10-11
 */
@Data
@TableName("mp_book")
public class MpBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    @ExcelProperty(value = "id")
    @Excel(name = "id", width = 15)
    private Integer id;

    /**
     * book_name
     */
    @ExcelProperty(value = "姓名")
    @Excel(name = "姓名", width = 30)
    private String bookName;

    /**
     * author
     */
    @ExcelProperty(value = "作者")
    @Excel(name = "作者", width = 30)
    private String author;

    /**
     * publish
     */
    @ExcelProperty(value = "出版社")
    @Excel(name = "出版社", width = 30)
    private String publish;

    /**
     * pages
     */
    @ExcelProperty(value = "页数")
    @Excel(name = "页数", width = 15)
    private Integer pages;

    /**
     * price
     */
    @ExcelProperty(value = "价格")
    @Excel(name = "价格", width = 30)
    private Float price;
    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    @Excel(name = "经度", width = 30)
    private String longitude;

    /**
     * 维度
     * 忽略这个字段不导出
     */
    @ExcelIgnore  //忽略这个字段不导出
    private String latitude;

    public MpBook() {
    }
}