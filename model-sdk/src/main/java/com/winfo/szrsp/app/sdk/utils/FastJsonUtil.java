package com.winfo.szrsp.app.sdk.utils;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.winfo.szrsp.app.sdk.http.ResponseResult;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: SZRegulatoryServicePlatformApp
 * PackageNmae: com.winfo.szrsp.app.sdk.utils
 * Author: wenjie
 * FileName: com.winfo.szrsp.app.sdk.utils.FastJsonUtil.java
 * Date: 2017/11/27 17:32
 * Description: 阿里巴巴json解析工具类
 */

public class FastJsonUtil {

    private static final SerializeConfig config = new SerializeConfig();

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, //Boolean字段如果为null,输出为false,而非null
            SerializerFeature.PrettyFormat //结果是否格式化,默认为false
    };

    /**
     * 获取服务器的code值
     *
     * @param jsonStr 服务器返回的josn
     * @return code
     */
    public static int getCode(String jsonStr) {
        return jsonStrToJSONObject(jsonStr).getIntValue("code");
    }

    /**
     * 获取msg
     *
     * @param jsonStr 服务返回的json
     * @return msg
     */
    public static String getMsg(String jsonStr) {
        return jsonStrToJSONObject(jsonStr).getString("msg");
    }

    /**
     * 将一个json字符串转换成JSONObject
     *
     * @param jsonStr 字符串
     * @return JSONObject
     */
    public static JSONObject jsonStrToJSONObject(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }


    /**
     * 将服务器返回的datas的json字符串转换成实体类
     *
     * @param responseResult datas的json字符串
     * @param clazz          对象
     * @param <T>            泛型
     * @return 实体类
     */
    public static <T> T jsonDatasStrToBean(String responseResult, Class<T> clazz) {
        return JSONObject.parseObject(getDatas(responseResult), clazz);
    }

    /**
     * 将datas的json字符串转换成集合
     *
     * @param responseResult 服务器返回的所有的json
     * @param clazz          对象
     * @param <T>            泛型
     * @return 集合
     */
    public static <T> List<T> jsonDatasToListBean(String responseResult, Class<T> clazz) {
        return JSONObject.parseArray(getDatas(responseResult), clazz);
    }

    /**
     * 将服务器返回的josn集合字符串 转换成一个list的实体类集合
     *
     * @param jsonStr 服务返回的json
     * @param clazz   list包裹的实体类类型
     * @param <T>     泛型
     * @return 实体类集合数据
     */
    public static <T> List<T> jsonStrToListBean(String jsonStr, Class<T> clazz) {
        return JSONObject.parseArray(jsonStr, clazz);
    }

    /**
     * 将服务器返回的json数据 转换成一个实体类
     *
     * @param jsonStr json字符串
     * @param clazz   要转换的实体类的class
     * @param <T>     泛型
     * @return 实体数据
     */
    public static <T> T jsonStrToBean(String jsonStr, Class<T> clazz) {
        return JSONObject.parseObject(jsonStr, clazz);
    }


    /**
     * 解析泛型嵌套的json字符串
     */
    public static <T> T jsonStrToBean(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<T>() {
        });
    }


    /**
     * lsit集合转换成json
     *
     * @param tList 集合
     * @param <T>   泛型对象
     * @return jsonstring
     */
    public static <T> String listToJsonStr(List<T> tList) {
        return JSONObject.toJSONString(tList, config, features);
    }

    /**
     * map转json格式字符  prettyFormat是否格式化 false：不格式化
     */
    public static String mapToJsonStr(Map<?, ?> map) {
        assert map != null;
        return JSONObject.toJSONString(map, false);
    }

    /**
     * 将josn字符串转换成map对象
     *
     * @param jsonStr json
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonStrToMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, Map.class);
    }

    /**
     * 将JSON字符串转换为String 的List对象
     **/
    public static List<String> jsonStrToStringList(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<List<String>>() {
        });
    }

    /**
     * 将JSON字符串转换为map 的List对象
     **/
    public static List<Map<String, Object>> jsonStrToMapList(String jsonStr) {
        return JSON.parseObject(jsonStr, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 将一个java实体类转换成json字符串
     *
     * @param t   实体类对象
     * @param <T> 泛型
     * @return jsonstr
     */
    public static <T> String beanToJsonStr(T t) {
        return JSONObject.toJSONString(t,features);
    }

    /**
     * 将map转化为string
     *
     * @param map
     * @return string
     */
    public static String collectToJsonStr(Map map) {
        return JSONObject.toJSONString(map);
    }

    /**
     * 获取服务返回的datas的String类型json字符串 当code为1时才会有数据
     *
     * @param jsonStr 数
     * @return datas
     */
    private static String getDatas(String jsonStr) {
        return jsonStrToJSONObject(jsonStr).getString("datas");
    }


    public static void main(String args[]) {

        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("小明");
        userInfo.setAge(18);
        userInfo.setAddress("深圳");
        userInfo.setEmail("123@qq.com");
        userInfo.setPhoneNumber("11111111111");
        userInfo.setSex("男");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setNickName("小红");
        userInfo2.setAge(21);
        userInfo2.setAddress("广州");
        userInfo2.setEmail("234@qq.com");
        userInfo2.setPhoneNumber("22222222222");
        userInfo2.setSex("女");

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setNickName("小黑");
        userInfo3.setAge(19);
        userInfo3.setAddress("北京");
        userInfo3.setEmail("453@qq.com");
        userInfo3.setPhoneNumber("33333333333");
        userInfo3.setSex("男");

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setNickName("小强");
        userInfo4.setAge(null);
        userInfo4.setAddress("杭州");
        userInfo4.setEmail(null);
        userInfo4.setPhoneNumber("44444444444");
        userInfo4.setSex(null);

//        String userInfo4str = beanToJsonStr(userInfo4);
//        String userInfo4str = JSON.toJSONString(userInfo4);
//        System.out.print(userInfo4str);


        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(userInfo);
        userInfos.add(userInfo2);
        userInfos.add(userInfo3);
        userInfos.add(userInfo4);

        ResponseResult<List<UserInfo>> responseResult = new ResponseResult<>();
        responseResult.setCode(1);
        responseResult.setMsg("成功");
        responseResult.setDatas(userInfos);

        //将包装对象转换成josn字符串
        String str = beanToJsonStr(responseResult);
        /*
         * {
         "code": 1,
         "datas": "",
         "msg": "成功"
         }
         */
        System.out.print(str);


        /*
        fastJson 方式
         */
        String str1 = "{\"code\":0,\"msg\":\"没有查到数据！\",\"datas\":\"\"}";
        ResponseResult<List<UserInfo>> responseResult1 = JSON.parseObject(str1, new TypeReference<ResponseResult<List<UserInfo>>>() {
        });

        System.out.print(responseResult1);

        String aa = beanToJsonStr(responseResult1);
        System.out.print(aa);

        /*
        Gson方式
         */
        Gson gson = new Gson();
        ResponseResult<List<UserInfo>> responseResult2 = gson.fromJson(aa, new TypeToken<ResponseResult<List<UserInfo>>>() {
        }.getType());
        System.out.print(responseResult2);
    }


    class Stu {
        String name;
    }

//    public static class UserInfoTest {
//        BigDecimal id;
//        int age;
//        String name;
//        Integer count;
//        String address;
//        Date date;
//
//        public BigDecimal getId() {
//            return id;
//        }
//
//        public void setId(BigDecimal id) {
//            this.id = id;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Integer getCount() {
//            return count;
//        }
//
//        public void setCount(Integer count) {
//            this.count = count;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public Date getDate() {
//            return date;
//        }
//
//        public void setDate(Date date) {
//            this.date = date;
//        }
//
//    }


}
