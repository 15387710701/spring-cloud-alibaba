package com.ms.user.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ms.commons.utils.Result;
import com.ms.user.domain.AccUser;
import com.ms.user.domain.MpBook;
import com.ms.user.mapper.AccUserMapper;
import com.ms.user.service.IMpBook;
import com.ms.user.utils.ExcelUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IMpBook bookService;
    @Autowired
    private AccUserMapper accUserMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/list")
    public List<MpBook> findAll() {
        List<MpBook> list = bookService.list();
        for (MpBook mpBook : list) {
            String s = JSON.toJSONString(mpBook);
            String id = String.valueOf(mpBook.getId());
            double longitude = Double.parseDouble(mpBook.getLongitude());
            double latitude = Double.parseDouble(mpBook.getLatitude());
            redisTemplate.opsForGeo().add("book", new Point(longitude, latitude), mpBook.getBookName());
        }
        return list;
    }

    @PostMapping("/updateOrSave")
    public Result findAll(@RequestBody MpBook mpBook) {
        return Result.ok(bookService.saveOrUpdate(mpBook));
    }

    @GetMapping("/getMpBook")
    public String findAll(Long id) {
        return bookService.getById(id).getAuthor();
    }

    @GetMapping("/delete")
    public Result delete(Long id) {
        return Result.ok(bookService.removeById(id));
    }

    /**
     * @param longitude ??????
     * @param latitude  ??????
     * @param distance  ????????????
     * @return
     */
    @GetMapping("/getNear")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> getNear(double longitude, double latitude, Integer distance) {
        //distance ????????????????????????
        // includeDistance ????????????
        // includeCoordinates ???????????????
        // sortAscending ????????????
        // limit ????????????????????????
        // arg  ????????????????????????
        // longitude ??????
        // latitude ??????
        Distance distance1 = new Distance(distance, Metrics.KILOMETERS);//??????5km
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending();
        /*GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = geoNearByPlace();*/
        return redisTemplate.opsForGeo().radius("book", new Circle(new Point(longitude, latitude), distance1), args);
    }


    @GetMapping("/distance")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearByPlace(String key, String member, Distance distance, long count) {
        // includeDistance ????????????
        // includeCoordinates ???????????????
        // sortAscending ????????????
        // limit ????????????????????????
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending().limit(count);
        return redisTemplate.opsForGeo().radius("key", member, distance, args);
    }

    @GetMapping("/distance2")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> geoNearByPlace(String key, Double lo, Double la, Integer distance) {
        Distance distances = new Distance(distance, Metrics.KILOMETERS);
        // includeDistance ????????????
        // includeCoordinates ???????????????
        // sortAscending ????????????
        // limit ????????????????????????
        // arg  ????????????????????????
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance().includeCoordinates().sortAscending();
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius("china:city", new Circle(new Point(lo, la), distances), args);//params: key, ????????????, Circle, GeoRadiusCommandArgs
        return results;

    }

    @GetMapping("/yanzhengma")
    public void yanzhengma(HttpServletResponse response) throws IOException {
        //????????????????????????????????????????????????????????????????????????
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
//ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
//?????????????????????????????????????????????????????????????????????
        captcha.write("d:/shear.png");
//??????????????????????????????????????????boolean???
        captcha.verify("1234");
        captcha.write(response.getOutputStream());

//Servlet???OutputStream????????????????????????
    }

    /**
     * @param response
     * @return void
     * @Title: expUser
     * @Description: ??????excel
     */
    @GetMapping("/expUser")
    public void expUser(HttpServletResponse response) {
        List<AccUser> users = accUserMapper.selectList(new LambdaQueryWrapper<AccUser>().last("LIMIT 99000,500 "));
        if (users != null && users.size() > 0) {
            ExcelUtils.exportExcel(users, "?????????", "????????????", MpBook.class, "??????20181118.xlsx", response);
        }
    }

    @GetMapping("/expUser1")
    public void expUser1(HttpServletResponse response) {
        List<MpBook> users = bookService.list(null);
        if (users != null && users.size() > 0) {
            ExcelUtils.exportExcel(users, "?????????", "????????????", MpBook.class, "hhh.xlsx", response);
        }
    }

    @GetMapping("/save")
    public void save(HttpServletResponse response) {
        for (int i = 0; i < 10000; i++) {
            MpBook mpBook = new MpBook();
            mpBook.setAuthor(UUID.randomUUID() + "");
            mpBook.setId(1020 + i);
            mpBook.setBookName("?????????" + UUID.randomUUID());
            mpBook.setLatitude(new Random().toString());
            mpBook.setLongitude(UUID.randomUUID() + "");
            mpBook.setPages(10);
            mpBook.setPublish(UUID.randomUUID() + "");
            bookService.save(mpBook);
        }

    }

 /*   @GetMapping("/list111")
    public void list111(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        String time = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        //?????????????????????
        response.setHeader("Content-disposition", "attachment; filename=" + "contract"+time+".xlsx");
        //????????????
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //?????????
        response.setHeader("Pragma", "No-cache");
        //?????????
        response.setHeader("Cache-Control", "no-cache");
        //???????????????
        response.setDateHeader("Expires", 0);
            //???????????????
            //??????????????????????????????????????????
            ExcelWriter excelWriter= EasyExcel.write(outputStream).build();;
            for (int i=0;i<(list.size())+1;i++){
                List<MpBook> list = bookService.list(new QueryWrapper<MpBook>().last("limit " + (i++)*200 + ",200"));
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "??????" + (i + 1)).head(MpBook.class)
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
                excelWriter.write(list,writeSheet);
            }

            //?????????
            excelWriter.finish();

    }*/

    @GetMapping("/list111")
    public void list111(HttpServletResponse response, String name, List list, Class entityClass) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        String time = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        //??????????????????
        //?????????????????????
        response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes(StandardCharsets.UTF_8), "ISO8859-1") + ".xlsx");
        //????????????
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");

        //?????????
        response.setHeader("Pragma", "No-cache");
        //?????????
        response.setHeader("Cache-Control", "no-cache");
        //???????????????
        response.setDateHeader("Expires", 0);

        //???????????????
        //??????????????????????????????????????????
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, name).head(entityClass)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
        /*List<MpBook> list = bookService.list();*/
        if (list != null) {
          /*  int i =0;
            while(true){
                *//*List<MpBook> list = bookService.list(new QueryWrapper<MpBook>().last("limit " + (i++)*1000 + ",1000"));*//*
                if (list.isEmpty()) {
                    break;
                }*/
            excelWriter.write(list, writeSheet);
            System.out.println("raw-------------------------------------------------------------------------");
            // }

            //?????????
            excelWriter.finish();
        }

    }

    @GetMapping("/save1")
    public void save1(HttpServletResponse response) {
        MpBook mpBook = new MpBook();
        mpBook.setAuthor("aaa");
        boolean insert = bookService.save(mpBook);
        System.out.println(mpBook.getId());
    }

    @GetMapping("/ex")
    public void ex(HttpServletResponse response) throws IOException {
        List<MpBook> insert = bookService.list();
        list111(response, "??????", insert, MpBook.class);
    }
}
