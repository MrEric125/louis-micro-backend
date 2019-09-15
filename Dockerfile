#可以通过这种方式定制镜像
FROM tomcat
#分层架构
COPY docker/common/webapps/ROOT/index.jsp /usr/local/tomcat/webapps/ROOT
#build
#docker build -t imagename .
# . 的目的
#1.在当前目录中找到dockerfile 配置文件
#2.指定我的Dockerfile文件的上下文环境，并打包发送到server上，真正的构建是在宿主机上面构建的
# run
#docker run -p 8080:8080 -d --name myplus myplus
#指定工作目录
#写Dockerfile的过程就是一个在linux上装软件的过程，
WORKDIR /usr/local/tomcat/webapps/ROOT

EXPOSE 8080