安装ik分词器 
如果网络好，直接在dockerFile中使用一下方式
```consolg

RUN elasticsearch-plugin install  https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.4.3/elasticsearch-analysis-ik-6.4.3.zip

```
需要注意的是，如果定制的镜像有一些问题，我们只需要使用

```console
定制，并删除容器
docker-compose down -v
重新编译并更新的镜像
docker-compose up --build 

```









