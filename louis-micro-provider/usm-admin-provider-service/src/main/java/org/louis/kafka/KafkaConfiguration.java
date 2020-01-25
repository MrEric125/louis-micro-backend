package org.louis.kafka;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author John·Louis
 *  create at 2019/12/15
 * description:
 *
 * 获取所有卡夫卡的配置文件，并将其保存为excel
 *
 * 1. 获取kafka配置页的html
 *
 * 2. 获取配置项
 *
 * 3. 通过配置项中的配置类别其实是一个<h3> 标签
 *
 * 4. 解析class为 data-table的标签，其中的信息就是要的配置
 *
 * 5. 将解析出来的配置输出位excel文件
 */
public class KafkaConfiguration {

    public String parseHtml(String content) {
        Document doc = Jsoup.parse(content);
        Elements dataTable = doc.getElementsByClass("data-table");
        return "";
    }


}
