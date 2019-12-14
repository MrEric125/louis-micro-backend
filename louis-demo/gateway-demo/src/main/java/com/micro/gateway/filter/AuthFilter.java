//package com.micro.gateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author Administrator
// * @version 1.0
// * 资源过滤器
// * 所有的资源请求在路由之前进行前置过滤
// * 如果请求头不包含 Authorization参数值，直接拦截不再路由
// *
// **/
//@Slf4j
//@Component
//public class AuthFilter extends ZuulFilter {
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public Object run() throws ZuulException {

//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//
//        log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
//
//        Object accessToken = request.getHeader("Authorization");
//        if (accessToken==null){
//            log.warn("Authorization token is empty");
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(401);
//            requestContext.setResponseBody("Authorization token is empty");
//            return null;
//        }
//        log.info("Authorization token is ok");
//        return null;
//    }
//}
