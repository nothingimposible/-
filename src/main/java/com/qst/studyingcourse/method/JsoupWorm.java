package com.qst.studyingcourse.method;

import com.qst.studyingcourse.pojo.Comic;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.helper.HttpConnection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupWorm {
    public String link;

    public void setLink(String link) {
        this.link = link;
    }

    public boolean judge(String s){
        String s1=s.substring(19,22);
        if("end".equals(s1))
            return true;
        else
            return false;

    }
    public String player(String url) throws IOException {
        System.out.println("进入jsoup");
        Connection conn = Jsoup.connect(url).data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(60000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document lin = conn.get();
        String byclass =lin.select("iframe").attr("src");

        System.out.println(byclass);
        return byclass;
    }

    public ArrayList<String> playerlist(String url) throws IOException {
        System.out.println("进入jsoup");
        ArrayList<String> link=new ArrayList<String>();
        Connection conn = Jsoup.connect(url).data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(30000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document lin = conn.get();
        Elements byclass =lin.getElementsByClass("multilink-table-wrap");
        Elements a = byclass.select("a");
        int i=0;
        for (Element element:a) {
            link.add(element.attr("href"));
            System.out.println(element.attr("href"));
        }
        return link;
    }

    public ArrayList<Comic> main() throws IOException {
        System.out.println("进入jsoup");
        ArrayList<Comic> comics=new ArrayList<Comic>();
        String url="https://www.5dm.tv/bgm";
        Connection conn = Jsoup.connect(url).data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(30000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document lin = conn.get();
        Elements byclass =lin.getElementsByClass("smart-box-content");
        Elements img = byclass.select("img");
        int i=0;
        for (Element element:img) {
            Comic comic=new Comic();
            Element link=img.parents().select("a").get(i);
            if(i%2==0) {
                System.out.println(element.attr("data-original"));
                System.out.println(link.attr("href"));
                System.out.println(element.attr("alt"));
                comic.setImg("https://www.5dm.tv/" + element.attr("data-original"));
                comic.setLink(link.attr("href"));
                comic.setTitle(element.attr("alt"));
                System.out.println("======================");
                comics.add(comic);
            }
            i+=2;
        }
        return comics;
    }

    public ArrayList<Comic> connectsearch(String searchtitle) throws IOException {
        System.out.println("进入爬虫");
        ArrayList<Comic> comics=new ArrayList<Comic>();
        String url="https://www.5dm.tv/search/"+searchtitle;
        Connection conn = Jsoup.connect(url).data("query", "Java")   // 请求参数
                .userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)") // 设置 User-Agent
                .timeout(30000); // 设置连接超时时间
        Connection.Response resp = conn.execute();
        Document lin = conn.get();

        System.out.println("获取链接");
        //			System.out.println(doc.title()); //CSDN-专业IT技术社区
        //把文章标题和连接写入txt文件
        Elements byclass =lin.getElementsByClass("video-listing-content");
       // Elements lin=doc.getElementsByClass("post_ajax_tm");

       // System.out.println("filter"+lin.toString());
        Elements img = byclass.select("img");
     //   Elements link=img.select("a");

       // Elements a = feedlist_id.select("img");
        int i=0;
        for (Element element:img) {
            Comic comic=new Comic();

                Element link=img.parents().select("a").get(i);
                if(link.attr("title")!=""&&judge(link.attr("href"))){
                    System.out.println(element.attr("data-original"));

                    System.out.println(link.attr("href"));

                    System.out.println(link.attr("title"));
                    comic.setImg("https://www.5dm.tv/"+element.attr("data-original"));
                    comic.setLink(link.attr("href"));
                    comic.setTitle(link.attr("title"));
                    System.out.println("======================");
                    comics.add(comic);
                }

           i++;
        }
      return comics;
    }

}
