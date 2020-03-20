ARG ELK_VERSION

# https://github.com/elastic/elasticsearch-docker
FROM elasticsearch:${ELK_VERSION}



#es插件目录
ENV ES_PLUGINS_PATH /usr/share/elasticsearch/plugins

COPY elasticsearch-analysis-ik-6.4.3.zip $ES_PLUGINS_PATH

#定义存放ik分词器文件的目录
ENV ik_SRC_COMPILE_PATH /usr/share/elasticsearch/plugins/ik

#创建存放ik分词器文件的目录
RUN mkdir $ik_SRC_COMPILE_PATH && \

#进入编译ik分词器文件的目录
cd $ES_PLUGINS_PATH && \

mv elasticsearch-analysis-ik-6.4.3.zip $ik_SRC_COMPILE_PATH &&\


cd $ik_SRC_COMPILE_PATH && \

#下载ik包
#wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/${ELK_VERSION}/elasticsearch-analysis-ik-${ELK_VERSION}.zip && \

#解压zip包
unzip elasticsearch-analysis-ik-6.4.3.zip

#构建成功后，将文件移动到插件目录
#mv analysis-ik $ES_PLUGINS_PATH/

# Add your elasticsearch plugins setup here
# Example: RUN elasticsearch-plugin install analysis-icu