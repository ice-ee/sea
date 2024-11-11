package com.ice.sea;

import com.ice.sea.utils.HttpUtils;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Azem
 * @datetime 2024/11/11 20:44
 */
@SpringBootTest

public class HttpUtilsTest {

    @Test
    public void test() {
        HttpUrl url = HttpUrl.parse("http://www.baidu.com");
        HttpUtils.get(url, "测试", "GET请求");
    }
}
