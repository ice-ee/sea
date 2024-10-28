package com.ice.sea.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;

/**
 * OKHTTP工具类
 *
 * @author Azem
 * @datetime 2024/10/25 17:03
 */
@Slf4j
public class HttpUtils {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    /**
     * GET请求
     *
     * @param url url
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String get(String url, String tag, String tagDescp) {
        Request request = null;
        Response response = null;
        String result = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .build();
            response = CLIENT.newCall(request).execute();
            result = response.body().string();
            if (response.isSuccessful()) {
                return result;
            } else {
                log.error("{}-{},请求:{},响应:{},响应结果:{}", tag, tagDescp, request, response, result);
                throw new RuntimeException(result);
            }
        } catch (Exception e) {
            log.error("{}-{},请求:{},响应:{},响应结果:{}", tag, tagDescp, request, response, result, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * post
     *
     * @param url url
     * @param body body
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String post(String url, RequestBody body, String tag, String tagDescp) {
        Request request = null;
        Response response = null;
        String result = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            response = CLIENT.newCall(request).execute();
            result = response.body().string();
            if (response.isSuccessful()) {
                return result;
            } else {
                log.error("{}-{},请求:{},响应:{},响应结果:{}", tag, tagDescp, request, response, result);
                throw new RuntimeException(result);
            }
        } catch (Exception e) {
            log.error("{}-{},请求:{},响应:{},响应结果:{}", tag, tagDescp, request, response, result, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * post
     *
     * @param url url
     * @param body body
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @return String
     */
    public static String post(String url, RequestBody body, String tag, String tagDescp, Map<String, Object> extraInfoMap) {
        Request request = null;
        Response response = null;
        String result = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            response = CLIENT.newCall(request).execute();
            result = response.body().string();
            if (response.isSuccessful()) {
                return result;
            } else {
                log.error("{}-{},附加信息:{},请求:{},响应:{},响应结果:{}", tag, tagDescp, extraInfoMap, request, response, result);
                throw new RuntimeException(result);
            }
        } catch (Exception e) {
            log.error("{}-{},附加信息:{},请求:{},响应:{},响应结果:{}", tag, tagDescp, extraInfoMap, request, response, result, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * post
     *
     * @param url url
     * @param body body
     * @param headers headers
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String post(String url, RequestBody body, Headers headers, String tag, String tagDescp) {
        Request request = null;
        Response response = null;
        String result = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .post(body)
                    .build();
            response = CLIENT.newCall(request).execute();
            result = response.body().string();
            if (response.isSuccessful()) {
                return result;
            } else {
                log.error("{}-{},请求:{},响应:{},响应结果:{}", tag, tagDescp, request, response, result);
                throw new RuntimeException(result);
            }
        } catch (Exception e) {
            log.error("{}-{},请求:{},响应:{},响应结果:{}", tag, tagDescp, request, response, result, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * post
     *
     * @param url url
     * @param body body
     * @param headers headers
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @return String
     */
    public static String post(String url, RequestBody body, Headers headers, String tag, String tagDescp, Map<String, Object> extraInfoMap) {
        Request request = null;
        Response response = null;
        String result = null;
        try {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .post(body)
                    .build();
            response = CLIENT.newCall(request).execute();
            result = response.body().string();
            if (response.isSuccessful()) {
                return result;
            } else {
                log.error("{}-{},附加信息:{},请求:{},响应:{},响应结果:{}", tag, tagDescp, extraInfoMap, request, response, result);
                throw new RuntimeException(result);
            }
        } catch (Exception e) {
            log.error("{}-{},附加信息:{},请求:{},响应:{},响应结果:{}", tag, tagDescp, extraInfoMap, request, response, result, e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
