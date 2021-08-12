package com.study.zuul.service;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author zhangpba
 * @date 2021-08-12
 */
@Component
public class TestFallBackProvider1 extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(TestFallBackProvider1.class);


    /**
     * 表示是否使用该过滤器
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的过滤执行逻辑
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String host = request.getRemoteHost();
        logger.info("请求的host:{}", host);
        return host;
    }

    /**
     * 过滤器类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序。返回值越小，执行顺序越优先。
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 具体处理过程。
     *
     * @param status       容错处理后的返回状态，如200正常GET请求结果，201正常POST请求结果，404资源找不到错误等。使用spring提供的枚举类型对象实现。HttpStatus
     * @param contentMsg   自定义的响应内容。就是反馈给客户端的数据。
     * @param mediaType    响应类型，是响应的主类型， 如： application、text、media。
     * @param subMediaType 响应类型，是响应的子类型， 如： json、stream、html、plain、jpeg、png等。
     * @param charsetName  响应结果的字符集。这里只传递字符集名称，如： utf-8、gbk、big5等。
     * @return ClientHttpResponse 就是响应的具体内容。
     * 相当于一个HttpServletResponse。
     */
    private final ClientHttpResponse executeFallback(final HttpStatus status, String
            contentMsg, String mediaType, String subMediaType, String charsetName) {
        return new ClientHttpResponse() {
            /**
             * 设置响应头信息
             *
             * @return
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType(mediaType, subMediaType, Charset.forName(charsetName));
                headers.setContentType(mt);
                return headers;
            }

            /**
             * 设置响应体
             *
             * @return 响应体的流
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                String content = contentMsg;
                return new ByteArrayInputStream(content.getBytes());
            }

            /**
             * 设置响应体的状态码
             *
             * @return 状态码 httpStatus
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            /**
             * 设置响应体的状态码 int
             * @return int
             * @throws IOException
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return 0;
            }

            /**
             * 设置响应体的状态码 String
             * @return
             * @throws IOException
             */
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }


            /**
             * 回收资源
             *
             * 用于回收当前fallback逻辑开启的资源对象的。
             * 不要关闭getBody方法返回的那个输入流对象。
             */
            @Override
            public void close() {

            }

        };
    }

}
