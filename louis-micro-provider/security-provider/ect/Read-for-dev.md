开发设计
1. gataway 做统一路由  
2. security 做统一认证
    认证流程见图  
![认证访问资源流程](../../../louis-micro-gateway/etc/api请求流程.png)
 说明 本服务所有认证、授权 全部都是以token 形式，没有session  
  所依初次访问网关的时候是没有token的，就会跳转到登录页面 获取 登录token 