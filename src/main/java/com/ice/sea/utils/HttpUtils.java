package com.ice.sea.utils;

import com.moczul.ok2curl.CurlInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OKHTTP工具类
 *
 * @author Azem
 * @datetime 2024/10/25 17:03
 */
@Slf4j
public class HttpUtils {

    private static final ConnectionPool cp = new ConnectionPool(10, 5, TimeUnit.MINUTES);

    private static final OkHttpClient CLIENT = new OkHttpClient()
            .newBuilder()
            .connectionPool(cp)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .addNetworkInterceptor(new CurlInterceptor(System.out::println))
            .build();

    private static final String LOG_FORMAT_EXCLUDE_RESULT = "{}-{}-附加信息:{}=>请求:{}";
    private static final String LOG_FORMAT = "{}-{}-附加信息:{}=>请求:{},响应:{},响应结果:{}";

    /**
     * get请求
     *
     * @param url url
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String get(HttpUrl url, String tag, String tagDescp) {
        return get(url, null, tag, tagDescp, null);
    }

    /**
     * get请求
     *
     * @param url url
     * @param headers headers
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String get(HttpUrl url, Headers headers, String tag, String tagDescp) {
        return get(url, headers, tag, tagDescp, null);
    }

    /**
     * post请求
     *
     * @param url url
     * @param body body
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String post(HttpUrl url, RequestBody body, String tag, String tagDescp) {
        return post(url, body, null, tag, tagDescp, null);
    }

    /**
     * post请求
     *
     * @param url url
     * @param body body
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @return String
     */
    public static String post(HttpUrl url, RequestBody body, String tag, String tagDescp, Map<String, Object> extraInfoMap) {
        return post(url, body, null, tag, tagDescp, extraInfoMap);
    }

    /**
     * post请求
     *
     * @param url url
     * @param body body
     * @param headers headers
     * @param tag tag
     * @param tagDescp tagDescp
     * @return String
     */
    public static String post(HttpUrl url, RequestBody body, Headers headers, String tag, String tagDescp) {
        return post(url, body, headers, tag, tagDescp, null);
    }

    /**
     * GET请求
     *
     * @param url url
     * @param headers headers
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @return String
     */
    public static String get(HttpUrl url, Headers headers, String tag, String tagDescp, Map<String, Object> extraInfoMap) {
        Request request = createRequest("GET", url, headers, null);
        return getResult(request, tag, tagDescp, extraInfoMap, false);
    }

    /**
     * post请求
     *
     * @param url url
     * @param body body
     * @param headers headers
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @return String
     */
    public static String post(HttpUrl url, RequestBody body, Headers headers, String tag, String tagDescp, Map<String, Object> extraInfoMap) {
        Request request = createRequest("POST", url, headers, body);
        return getResult(request, tag, tagDescp, extraInfoMap, true);
    }

    /**
     * getResult
     *
     * @param request request
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @param showResult showResult
     * @return String
     */
    private static String getResult(Request request, String tag, String tagDescp, Map<String, Object> extraInfoMap, boolean showResult) {
        Response response = null;
        String result = null;
        try {
            response = CLIENT.newCall(request).execute();
            result = response.body().string();
            if (response.isSuccessful()) {
                if (showResult) {
                    printInfoLog(tag, tagDescp, extraInfoMap, request, response, result);
                } else {
                    log.info(LOG_FORMAT_EXCLUDE_RESULT, tag, tagDescp, extraInfoMap, request);
                }
            } else {
                printErrorLog(tag, tagDescp, extraInfoMap, request, response, result);
            }
        } catch (Exception e) {
            printExceptionLog(tag, tagDescp, extraInfoMap, request, response, result, e);
        }
        return result;
    }

    /**
     * createRequest
     *
     * @param requestMethod requestMethod
     * @param url url
     * @param headers headers
     * @param body body
     * @return Request
     */
    private static Request createRequest(String requestMethod, HttpUrl url, Headers headers, RequestBody body) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (headers != null) {
            builder.headers(headers);
        }
        switch (requestMethod) {
            case "GET" : builder.get(); break;
            case "POST" : builder.post(body); break;
            default :
                // ignore
        }
        return builder.build();
    }

    /**
     * print info log
     *
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @param request request
     * @param response response
     * @param result result
     */
    private static void printInfoLog(String tag, String tagDescp, Map<String, Object> extraInfoMap, Request request, Response response, String result) {
        log.info(LOG_FORMAT, tag, tagDescp, extraInfoMap, request, response, result);
    }

    /**
     * print error log
     *
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @param request request
     * @param response response
     * @param result result
     */
    private static void printErrorLog(String tag, String tagDescp, Map<String, Object> extraInfoMap, Request request, Response response, String result) {
        log.error(LOG_FORMAT, tag, tagDescp, extraInfoMap, request, response, result);
    }

    /**
     * print exception log
     *
     * @param tag tag
     * @param tagDescp tagDescp
     * @param extraInfoMap extraInfoMap
     * @param request request
     * @param response response
     * @param result result
     * @param e e
     */
    private static void printExceptionLog(String tag, String tagDescp, Map<String, Object> extraInfoMap, Request request, Response response, String result, Exception e) {
        log.error(LOG_FORMAT, tag, tagDescp, extraInfoMap, request, response, result, e);
    }
}
