/*
 * 文 件 名:  PostController
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-07-26

 */
package cn.acyou.iblogdata.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-26 下午 06:01]
 **/
@Slf4j
@Component
public class PostHelper {

    @Autowired
    public RestTemplate httpClient;


    public <T> ResponseEntity<ResultInfo> post(HttpServletRequest request, String url, T params){
        HttpEntity<T> entity = getHttpEntity(request,params);
        return post(url,entity);
    }

    public <T> ResponseEntity<ResultInfo> post(String url, HttpEntity<T> entity){
        long start = System.currentTimeMillis();
        ResponseEntity<ResultInfo> response = httpClient.exchange(url, HttpMethod.POST,entity, ResultInfo.class);
        log.info("interface 耗时 --> "+(System.currentTimeMillis()-start));
        return response;
    }

    public <T> HttpEntity<T> getHttpEntity(HttpServletRequest request,T t){
        return new HttpEntity<T>(t, getHttpHeader(request));
    }

    public HttpHeaders getHttpHeader(HttpServletRequest request){
        return new HttpHeaders();
    }



}
