package com.qst.studyingcourse.controller;

import com.qst.studyingcourse.method.JsoupWorm;
import com.qst.studyingcourse.method.MusicWorm;
import com.qst.studyingcourse.pojo.Comic;
import com.qst.studyingcourse.pojo.Course;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class JsoupController {

    @RequestMapping(value = "/jsoup")
    @ResponseBody
    public String jsoup() throws IOException {

        new MusicWorm().music();
        return "查询成功";
    }

    @RequestMapping(value = "/search")
    public String worm(Model model,@RequestParam(value = "comicname") String comicname) throws IOException {
        ArrayList<Comic> comics= new JsoupWorm().connectsearch(comicname);
        model.addAttribute("comics",comics);
        return "comic";
    }

    @RequestMapping(value = "/comic")
    public String comic(Model model) throws IOException {
        ArrayList<Comic> comics= new JsoupWorm().main();
        model.addAttribute("comics",comics);
        return "comic";
    }

    @RequestMapping(value = "/comicplayer")
    public String comicplayer(@RequestParam(value = "link") String link,Model model) throws IOException {
        ArrayList<String> list=new JsoupWorm().playerlist(link);
        String playerlink=new JsoupWorm().player(link);
        model.addAttribute("list",list);
        model.addAttribute("playerlink",playerlink);
        return "comicplayer";
    }

    @RequestMapping(value = "/comicajax")
    public void comicajax(@RequestParam(value = "link") String link, Model model, HttpServletResponse response) throws IOException {
        String playerlink=new JsoupWorm().player(link);
        JSONObject jb=new JSONObject().fromObject(playerlink);
        System.out.println(jb.toString());
        System.out.println("请求结束");
        response.getWriter().write(jb.toString());
    }
}
