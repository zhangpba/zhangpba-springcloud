//package com.study.city.config;
//
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.ssl.TrustStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//
//import javax.net.ssl.SSLContext;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.List;
//
///**
// * 配置类
// *
// * @author zhangpba
// * @date 2022-04-14
// */
//@Configuration
//public class HttpConfig {
//    // 设置字符集同时保证回传的字符不出现乱码
//    @Bean
//    public RestTemplate restTemplate() {
//        // https
//        CloseableHttpClient httpClient = getHttpsClient();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setHttpClient(httpClient);
//        factory.setConnectionRequestTimeout(300000);
//        factory.setConnectTimeout(300000);
//        factory.setReadTimeout(300000);
//
//        RestTemplate restTemplate = new RestTemplate(factory);
//
//        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
//        for (HttpMessageConverter<?> httpMessageConverter : list) {
//            if (httpMessageConverter instanceof StringHttpMessageConverter) {
//                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
//            }
//        }
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
//
//        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        return restTemplate;
//    }
//
//    // 忽略SSL证书验证
//    public static CloseableHttpClient getHttpsClient() {
//        CloseableHttpClient httpClient;
//        SSLContext sslContext = null;
//        try {
//            sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
//                @Override
//                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//                    return true;
//                }
//            }).build();
//        } catch (NoSuchAlgorithmException e) {
//            e.getStackTrace();
//        } catch (KeyManagementException e) {
//            e.getStackTrace();
//        } catch (KeyStoreException e) {
//            e.getStackTrace();
//        }
//        httpClient = HttpClients.custom().setSSLContext(sslContext).
//                setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//        return httpClient;
//    }
//}
