package com.qst.studyingcourse.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.qst.studyingcourse.mapper.VideoMapper;
import com.qst.studyingcourse.method.UploadVideo;
import com.qst.studyingcourse.pojo.Video;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    VideoMapper videoMapper;
    private MultipartFile file;

/*    @RequestMapping(value = "/uploadvideo")
    public String upVideo(@RequestParam("filedesc") MultipartFile file,@RequestParam("fileimg") MultipartFile fileimg, Model m, Video video){
        Video vd=video;
        String imgname;
        String viewname;
        if(vd.getCourseid()!=0){
            System.out.println("进入控制层");
            try {
                //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
                String fileName = System.currentTimeMillis() + file.getOriginalFilename();
                String fileNameimg = System.currentTimeMillis() + fileimg.getOriginalFilename();
                //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
                String destFileName = "F://course/courseview/" + File.separator + fileName;
                String destFileNameimg = "F://course/courseimg/" + File.separator + fileNameimg;
                imgname= "/coursefile/courseimg/"+ File.separator.substring(1,File.separator.length()) + fileNameimg;
                viewname="/coursefile/courseview/"+File.separator.substring(1,File.separator.length()) + fileName;
                //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
                File destFile = new File(destFileName);
                File destFileimg = new File(destFileNameimg);
                destFile.getParentFile().mkdirs();
                destFileimg.getParentFile().mkdirs();
                //5.把浏览器上传的文件复制到希望的位置
                System.out.println(destFile);
                file.transferTo(destFile);
                fileimg.transferTo(destFileimg);
                //6.把文件名放在model里，以便后续显示用
                m.addAttribute("fileName", fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            Video vo=video;
            vo.setCoverimg(imgname);
            vo.setVideodesc(viewname);
            videoMapper.uploadvideo(vo);
            System.out.println(vd.toString());
            return "redirect:/course/coursevideo?id="+vo.getCourseid();
        }else {

            return "upview";
        }
    }*/
/*初始化客户端*/
public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
    //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
    String regionId = "cn-shanghai";
    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
    DefaultAcsClient client = new DefaultAcsClient(profile);
    return client;
}

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,String s) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(s);
        return client.getAcsResponse(request);
    }

    @RequestMapping(value = "/uploadvideo")
    public String upVideo(@RequestParam("filedesc") MultipartFile file,@RequestParam("fileimg") MultipartFile fileimg, Model m, Video video){
        Video vd=video;
        String imgname;
        String viewname;
        String aliyunid=null;
        System.out.println(file.getName());
        System.out.println(file.getClass().getResource("").getPath());
        System.out.println(file);
        if(vd.getCourseid()!=0){
            System.out.println("进入控制层");
            try {
                //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
                String fileName = System.currentTimeMillis() + file.getOriginalFilename();
                String fileNameimg = System.currentTimeMillis() + fileimg.getOriginalFilename();
                //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
                String destFileName = "F:\\course\\courseupload\\" +  File.separator.substring(1,File.separator.length()) + fileName;
                String destFileNameimg = "F://course/courseimg/" + File.separator + fileNameimg;
                imgname= "/coursefile/courseimg/"+ File.separator.substring(1,File.separator.length()) + fileNameimg;
                viewname="F://course/courseupload/"+File.separator.substring(1,File.separator.length()) + fileName;
                //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
                File destFile = new File(destFileName);
                File destFileimg = new File(destFileNameimg);
                System.out.println(destFileName);
                destFile.getParentFile().mkdirs();
                destFileimg.getParentFile().mkdirs();
                //5.把浏览器上传的文件复制到希望的位置
                System.out.println(destFile);
                file.transferTo(destFile);
                fileimg.transferTo(destFileimg);
               aliyunid = new UploadVideo().upload(video.getVideodescribe(),destFileName);
                //6.把文件名放在model里，以便后续显示用
                m.addAttribute("fileName", fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            DefaultAcsClient client = initVodClient("LTAIbOlg7PmBMn0D", "aIx1dbs9FqX7eUkVQST0Tpmq0J6v88");
            GetPlayInfoResponse response = new GetPlayInfoResponse();
            String PlayURL = null;
            try {
                response = getPlayInfo(client,aliyunid);
                List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
                //播放地址
                for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                    PlayURL = playInfo.getPlayURL();
                }
                //Base信息
                System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
            } catch (Exception e) {
                System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            }
            Video vo=video;
            vo.setCoverimg(imgname);
            System.out.println(PlayURL);
            vo.setVideodesc(PlayURL);
            videoMapper.uploadvideo(vo);
            System.out.println(vd.toString());
            return "redirect:/course/coursevideo?id="+vo.getCourseid();
        }else {

            return "upview";
        }
    }

    @RequestMapping(value = "/upload")
    public String upload(@RequestParam(value = "id") int id,Model model){
        model.addAttribute("courseid",id);
        return "upview";
    }

    @RequestMapping(value = "/video")
    public String video(@RequestParam(value = "id") int id,Model model){
        ArrayList<Video> videos=videoMapper.selectvideo(id);
        Video onevideo=null;
        if(videos.size()>0)
        onevideo=videos.get(0);
        model.addAttribute("onevideo",onevideo);
        model.addAttribute("videos",videos);
        return "video";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") int id){
        System.out.println("进入删除,id="+id);
        videoMapper.deleteById(id);
        System.out.println("成功");
        return "{}";
    }

    @RequestMapping(value = "/returnvideo")
    public void returnvideo(HttpServletResponse response,@RequestParam(value = "id")int id)throws IOException{
        response.setCharacterEncoding( "UTF-8" );
        response.setContentType( "text/xml" );
        System.out.println("进入控制层"+id);
        Video vd=videoMapper.selectone(id);
        System.out.println(vd.toString());
        JSONObject jb=new JSONObject().fromObject(vd);
        System.out.println(jb.toString());
        System.out.println("请求结束");
        response.getWriter().write(jb.toString());

    }
}
