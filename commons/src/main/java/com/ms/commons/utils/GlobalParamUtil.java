/*
package com.ms.commons.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GlobalParamUtil {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static final String key_page = "pageNo";//第几页

    private static final String key_limit = "pageSize";//每页查几条

    private static final String key_user = "user_info";//

    public static final String thread_lock = "thread_lock";

    private static final int default_limit = 15;

    private static final int default_page = 1;

    private static final String LANG = "lang";

    private static final int default_lang = 0;

    public static void initParam(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //设置语言方式
        String lang = request.getHeader(LANG);
        setLang(lang);
        //拦截分页
        String page = request.getParameter(key_page);
        String limit = request.getParameter(key_limit);
        if (StrUtil.isEmpty(page)) {
            getByRequestBody(request, map);
            return;
        }
        setPage(page, limit);
    }

    private static void getByRequestBody(HttpServletRequest request, Map<String, Object> map) {
    */
/*    try {
            String bodyString = HttpHelper.getBodyString(request);
            if (JSONUtil.isJsonObj(bodyString)) {
                JSONObject json = JSONUtil.parseObj(bodyString);
                if (json != null && !json.isEmpty()) {
                    String page = json.getStr(key_page);
                    String limit = json.getStr(key_limit);
                    setPage(page, limit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*//*

    }

    public static void setUser(UmsMember userDTO) {
        if (userDTO == null)
            return;
        Map<String, Object> map = threadLocal.get();
        if (map == null)
            map = new HashMap<>();
        map.put(key_user, userDTO);
        threadLocal.set(map);
    }

    */
/**
     * 设置语言方式
     *
     * @param lang
     * @date 2021/4/28
     * @author Gyl
     * @Version 2.0
     *//*

    public static void setLang(String lang) {
        Map<String, Object> map = threadLocal.get();
        if (map == null)
            map = new HashMap<>();
        if ("zh_CN".equals(lang)) {
            //简体-0
            map.put(LANG, 0);
        } else if ("zh_HK".equals(lang)) {
            //繁体-1
            map.put(LANG, 1);
        } else if ("en_US".equals(lang)) {
            //英语-2
            map.put(LANG, 2);
        } else {
            //默认简体-0
            map.put(LANG, default_lang);
        }
        if (!StringUtils.isBlank(lang)) {
            map.put(LANG + "-string", lang);
        }
        threadLocal.set(map);
    }


    */
/**
     * 获取语言方式
     *
     * @return int
     * @date 2021/4/28
     * @author Gyl
     * @Version 2.0
     *//*

    public static int getLang() {
        Map<String, Object> map = threadLocal.get();
        if (map != null) {
            Object lang = map.get(LANG);
            return lang == null ? default_lang : (int) lang;
        }
        return default_page;
    }

    */
/**
     * 获取语言
     *
     * @return zh_CN, en_US, zh_HK
     *//*

    public static String getLangString() {
        Map<String, Object> map = threadLocal.get();
        if (map.containsKey(LANG + "-string")) {
            return (String) map.get(LANG + "-string");
        }
        return "zh_CN";
    }
*/
/*
    public static UserDTO getUser() {
        return getUser(true);
    }

    *//*
*/
/**
     * 获取管理后台登录用户
     *
     * @return
     *//*
*/
/*
    public static LoginUser getAdminUser() {
        return (LoginUser) getUser(true);
    }

    *//*
*/
/**
     * 获取前端登录用户
     *
     * @return
     *//*
*/
/*
    public static AccUserDTO getAppUser() {
        return ((AccUserDTO) getUser(true));
    }


    *//*
*/
/**
     * 是否需要抛异常
     *
     * @return
     *//*

    public static UmsMember getUser() {
        Map<String, Object> map = threadLocal.get();
     */
/*   if (map == null) {
            if (throwException)
                throw new MsException(401, "未登录");
            return null;
        }
        Object o = map.get(key_user);
        if (o == null) {
            throw new MsException(401, "未登录");
        }*//*

        return ((UmsMember)map.get(key_user));
    }

    public static int getPage() {
        Map<String, Object> map = threadLocal.get();
        if (map != null) {
            Object page = map.get(key_page);
            return page == null || StringUtils.isBlank(page.toString()) ? default_page : Integer.valueOf(page.toString());
        }
        return default_page;
    }

    public static void setPage(String page, String limit) {
        Map<String, Object> map = threadLocal.get();
        if (map == null)
            map = new HashMap<>();
        map.put(key_page, page);
        map.put(key_limit, limit);
        threadLocal.set(map);
    }

    public static void main(String[] args) {
        Double a = -2.0;
        BigDecimal bigDecimal = new BigDecimal(a);
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal add = bigDecimal.add(bigDecimal1);
        System.out.println(add);
        Object page = "2";
        System.out.println(page == null || StringUtils.isBlank(page.toString()) ? default_page : Integer.valueOf(page.toString()));
    }

    public static int getStart() {
        int page = getPage();
        int limit = getLimit();
        return (page - 1) * limit;
    }

    public static int getLimit() {
        Map<String, Object> map = threadLocal.get();
        if (map != null) {
            Object limit = map.get(key_limit);
            return limit == null || StringUtils.isBlank(limit.toString()) ? default_limit : Integer.valueOf(limit.toString());
        }
        return default_limit;
    }

    public static void bindKey(String key, Object val) {
        Map<String, Object> map = threadLocal.get();
        if (map == null)
            map = new HashMap<>();
        threadLocal.set(map);
        map.put(key, String.valueOf(val));
    }

    public static void bindKey(Object val) {
        Map<String, Object> map = threadLocal.get();
        if (map == null)
            map = new HashMap<>();
        threadLocal.set(map);
        map.put(thread_lock, String.valueOf(val));
    }

    public static String getLockKey(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null)
            return "";
        Object obj = map.get(key);
        return obj == null ? "" : String.valueOf(obj);
    }

    public static String getLockKey() {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            return "";
        }
        Object obj = map.get(thread_lock);
        return obj == null ? "" : String.valueOf(obj);
    }

    public static void removeKey() {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            return;
        }
        map.remove(thread_lock);
    }

    public static void removeKey(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            return;
        }
        map.remove(key);
    }

    public static void removeAll() {
        threadLocal.remove();
    }

}
*/
