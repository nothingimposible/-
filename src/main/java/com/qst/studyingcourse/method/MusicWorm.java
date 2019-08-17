package com.qst.studyingcourse.method;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MusicWorm {

    public void music() throws IOException {
        String url="https://www.kugou.com/yy/html/singer.html";
        Connection conn = Jsoup.connect(url).data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(30000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document lin = conn.get();
        Elements pa=lin.select("img");
        System.out.println("查询");
        for(Element e:pa)
            System.out.println(e.toString());
        System.out.println(pa.toString());
    }

    public void music1() throws IOException {
        Document doc=Jsoup.connect("https://music.163.com/#/discover/playlist/?cat=华语").get();
        Element pa=doc.getElementById("m-disc-pl-c");
        System.out.println(pa);
    }
}
