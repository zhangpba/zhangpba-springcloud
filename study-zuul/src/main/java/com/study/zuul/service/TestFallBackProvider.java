//package com.study.zuul.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 如果需要在Zuul网关服务中增加容错处理fallback，需要实现接口ZuulFallbackProvider
// * spring-cloud框架，在Edgware版本(包括)之后，声明接口ZuulFallbackProvider过期失效，
// * 提供了新的ZuulFallbackProvider的子接口 - FallbackProvider
// * 在老版本中提供的ZuulFallbackProvider中，定义了两个方法。
// * - String getRoute()
// * 当前的fallback容错处理逻辑处理的是哪一个服务。可以使用通配符‘*’代表为全部的服务提供容错处理。
// * 如果只为某一个服务提供容错，返回对应服务的spring.application.name值。
// * - ClientHttpResponse fallbackResponse()
// * 当服务发生错误的时候，如何容错。
// * 新版本中提供的FallbackProvider提供了新的方法。
// * - ClientHttpResponse fallbackResponse(Throwable cause)
// * 如果使用新版本中定义的接口来做容错处理，容错处理逻辑，只运行子接口中定义的新方法。也就是有参方法。
// * 是为远程服务发生异常的时候，通过异常的类型来运行不同的容错逻辑。
// *
// * @author zhangpba
// * @date 2021-06-07
// */
//@Component
//public class TestFallBackProvider implements ZuulFallbackProvider {
//
//    /**
//     * 返回fallback处理哪一个服务。返回的是服务的名称
//     * <p>
//     * 推荐 - 为指定的服务定义特性化的fallback逻辑。
//     * 推荐 - 提供一个处理所有服务的fallback逻辑。
//     * 好处 - 服务某个服务发生超时，那么指定的fallback逻辑执行。如果有新服务上线，未提供fallback逻辑，有一个通用的。
//     *
//     * @return 返回的是服务的名称
//     */
//    @Override
//    public String getRoute() {
//        return "study-user";
//    }
//
//    /**
//     * fallback逻辑。在早期版本中使用。(此版本就是早期版本)
//     * Edgware版本之后，ZuulFallbackProvider接口过期，提供了新的子接口FallbackProvider
//     * 子接口中提供了方法ClientHttpResponse fallbackResponse(Throwable cause)。
//     * 优先调用子接口新定义的fallback处理逻辑。
//     *
//     * @return
//     */
//    @Override
//    public ClientHttpResponse fallbackResponse() {
//        System.out.println(" ClientHttpResponse fallbackResponse()");
//        List<Map<String, Object>> result = new ArrayList<>();
//        Map<String, Object> data = new HashMap<>();
//        data.put("message", "服务器正忙，请稍后重试！");
//        result.add(data);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        String msg = "";
//        try {
//            msg = mapper.writeValueAsString(result);
//        } catch (JsonProcessingException e) {
//            msg = "";
//        }
//
//        return this.executeFallback(HttpStatus.OK, msg, "application", "json", "utf-8");
//    }
//
//    /**
//     * 具体处理过程。
//     *
//     * @param status       容错处理后的返回状态，如200正常GET请求结果，201正常POST请求结果，404资源找不到错误等。使用spring提供的枚举类型对象实现。HttpStatus
//     * @param contentMsg   自定义的响应内容。就是反馈给客户端的数据。
//     * @param mediaType    响应类型，是响应的主类型， 如： application、text、media。
//     * @param subMediaType 响应类型，是响应的子类型， 如： json、stream、html、plain、jpeg、png等。
//     * @param charsetName  响应结果的字符集。这里只传递字符集名称，如： utf-8、gbk、big5等。
//     * @return ClientHttpResponse 就是响应的具体内容。
//     * 相当于一个HttpServletResponse。
//     */
//    private final ClientHttpResponse executeFallback(final HttpStatus status, String
//            contentMsg, String mediaType, String subMediaType, String charsetName) {
//        return new ClientHttpResponse() {
//            /**
//             * 设置响应头信息
//             *
//             * @return
//             */
//            @Override
//            public HttpHeaders getHeaders() {
//                HttpHeaders headers = new HttpHeaders();
//                MediaType mt = new MediaType(mediaType, subMediaType, Charset.forName(charsetName));
//                headers.setContentType(mt);
//                return headers;
//            }
//
//            /**
//             * 设置响应体
//             *
//             * @return 响应体的流
//             * @throws IOException
//             */
//            @Override
//            public InputStream getBody() throws IOException {
//                String content = contentMsg;
//                return new ByteArrayInputStream(content.getBytes());
//            }
//
//            /**
//             * 设置响应体的状态码
//             *
//             * @return 状态码 httpStatus
//             * @throws IOException
//             */
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                return status;
//            }
//
//            /**
//             * 设置响应体的状态码 int
//             * @return int
//             * @throws IOException
//             */
//            @Override
//            public int getRawStatusCode() throws IOException {
//                return 0;
//            }
//
//            /**
//             * 设置响应体的状态码 String
//             * @return
//             * @throws IOException
//             */
//            @Override
//            public String getStatusText() throws IOException {
//                return this.getStatusCode().getReasonPhrase();
//            }
//
//
//            /**
//             * 回收资源
//             *
//             * 用于回收当前fallback逻辑开启的资源对象的。
//             * 不要关闭getBody方法返回的那个输入流对象。
//             */
//            @Override
//            public void close() {
//
//            }
//
//        };
//    }
//
//    /**
//     * fallback逻辑。优先调用。可以根据异常类型动态决定处理方式。
//     */
////    @Override
////    public ClientHttpResponse fallbackResponse(Throwable cause) {
////        System.out.println("ClientHttpResponse fallbackResponse(Throwable cause)");
////        if(cause instanceof NullPointerException){
////
////            List<Map<String, Object>> result = new ArrayList<>();
////            Map<String, Object> data = new HashMap<>();
////            data.put("message", "网关超时，请稍后重试");
////            result.add(data);
////
////            ObjectMapper mapper = new ObjectMapper();
////
////            String msg = "";
////            try {
////                msg = mapper.writeValueAsString(result);
////            } catch (JsonProcessingException e) {
////                msg = "";
////            }
////
////            return this.executeFallback(HttpStatus.GATEWAY_TIMEOUT,
////                    msg, "application", "json", "utf-8");
////        }else{
////            return this.fallbackResponse();
////        }
////    }
//}
