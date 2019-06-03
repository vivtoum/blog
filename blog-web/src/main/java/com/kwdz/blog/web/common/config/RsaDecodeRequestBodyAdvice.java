package com.kwdz.blog.web.common.config;


import com.kwdz.blog.api.common.util.RSAUtils;
import com.kwdz.blog.api.common.util.RedisUtil;
import com.kwdz.blog.api.common.util.RsaSecurityParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 请求数据解密
 *
 * @author monkey
 * @date 2018/10/29 20:17
 */
@Slf4j
@ControllerAdvice(basePackages = "com.kwdz.blog.web.controller")
public class RsaDecodeRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    private RedisUtil redisUtil;

    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDBEN8iHcL9eD7+aEOJVVkXzW58kTPf83RXB2FaS7fyaXbYQysIinDL2yje6DViutzk6SovfwX8aVH6OEd/Rq3wzPIRoDDSwCJVOIWc/lHj/BWc6vjQfdOjtVUDka+qN2fSAzn818H6mEKxZeM65O1ypO6xGBeosb3ynpkm0XaqbvT/W0kjus1Ao9XSXerbCUU29I9+JaBl2irvlzvkKfaNFYbvlp269VsBEdD5VYNDdhDmBy3ZXWqBU0aHjNnr2FymSkhPBlc8Qo2wKnT5B2fCZpvwfJRjPajZfOGwtEB3EMB5D4j4xB101wjMhHVBuM0wBsndanE5FJdorgMss9HxAgMBAAECggEAdluSf0bBkERUUgC3Toz9FmOBVWpq4+NLMkM7AnEi7sCK9B+RVSYR6leL4cN3XvRxkQAENaPoloo2kHBKHJPG9LWfvQB4jert4VLKTql2qI6U7WL7cE2DHnx/BrYce/yFcDE8Lm5S4/7N/BUbTbKkgsDr97NuZaO5b/CcsTh+SBK0fzS4yUS+RAPT3+UVDOom5jGpeAq0gRed3qsDvPkq4jCLRg4cK+Sb5zicK1+rc56WZ6RHhDzf3U+mQG5p4aBG0GrbfGtFJ0bnyP7BagaDe6xhVUdH2eoJwtcj5Dq2dyG222kKfOpvaBKhCrVzc0wDgqToHgiNXSGfyIBDSrgotQKBgQD/MleH/X36YUAe8XTHb25xL+KKsH3GhH/a6BtGxyyRNF9Veo0heXwc90PW9uBB7L7JJWXnoblgB6IeUr4xCu4dBox1euk4ik4ght5dBpJ408NVbqzoz1itJ7LJBJeHktZK74F+ooRma6TZRfF5LSFBCOCeWbZVxt+G89MeDhrC+wKBgQDBrHWwU+7MLWliz4w84C6atGSERE3OwaO48G6HjNWwDb0CEPWDwwBEv7di1Q9Yf5c/wsD5FvhVNyeEIBt+yuSgNBEDstQ5CgAA6vB24cuCmpt4TGygRdwT1OezlM6A/8DfVyEIPakb5FkAf+ZVUBOBe3ovCdhKR+lMNeA3foxLAwKBgH0toXgIZQozN/O1tvW4+DC3L2WeayO8jMbBQdf3DSpwyS6xvZak87d1pqZEVP6hdXhPSuxTDQ5I0EIVjkuaM/Cy7KUX1Fyyot4bqelxttlj7pXygwhz5OiS54TpJrcO1OTPFPMcHtvqg2M8htVMyNoQ07V5BhKPScf1xCIjAwePAoGBALyRLrE0t1+Co4Y6b99hot6r3uZNHri9HqxVJDjELdZROgLGAlV8ykklpOcMbPmMmPXSqFKeQ8GshsQFAuBLqZg1uY8xUaILk39RQDlvlNcfs1nIh0bxXdrNQ588tmcxHFgYHChK6NkoVmO/I8NSyPsb/xXHbxi7tXGnTxyZEP2DAoGBAKuTY1IZ3v7mPHZm3GMt3KlVHvMcdvCHV+ekARvCTV5V3jc74AYcxpJbcV1D3bQpGOKpdG8mEcNew5KaXO7KaLSJEcoehhdY6z3Zq6ZoLXOHYqRIaXQRx9wBeTZks4mkXiMAWfQwNarGTU5c7db5n5PO5398mA3OhV+uqUy1cd3P";

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        try {
            boolean encode = false;
            if (methodParameter.getMethod().isAnnotationPresent(RsaSecurityParameter.class)) {
                //获取注解配置的包含和去除字段
                RsaSecurityParameter serializedField = methodParameter.getMethodAnnotation(RsaSecurityParameter.class);
                //入参是否需要解密
                encode = serializedField.inDecode();
            }
            if (encode) {
                log.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密");
                return new MyHttpInputMessage(inputMessage);
            } else {
                return inputMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常：" + e.getMessage());
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    class MyHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;

        private InputStream body;

        public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            this.headers = inputMessage.getHeaders();
            String content = easpString(IOUtils.toString(inputMessage.getBody(), "utf-8"));
            this.body = IOUtils.toInputStream(RSAUtils.decryptDataOnJava(content, PRIVATE_KEY));
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        /**
         * @param requestData
         * @return
         */
        public String easpString(String requestData) {
            if (requestData != null && !"".equalsIgnoreCase(requestData)) {
                String s = "{\"requestData\":";
                if (!requestData.startsWith(s)) {
                    throw new RuntimeException("参数【requestData】缺失异常！");
                } else {
                    return StringUtils.substring(requestData, s.length(), requestData.length() - 1);
                }
            }
            return "";
        }
    }
}