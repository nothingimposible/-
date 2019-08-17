package com.qst.studyingcourse.controller;

import com.qst.studyingcourse.mapper.CourseMapper;
import com.qst.studyingcourse.mapper.VideoMapper;
import com.qst.studyingcourse.pojo.Course;
import com.qst.studyingcourse.pojo.FirstCourse;
import com.qst.studyingcourse.pojo.SecondCourse;
import com.qst.studyingcourse.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    VideoMapper videoMapper;

    @RequestMapping(value = "/setcourse")
    public String setCourse(HttpSession session, @RequestParam("fileimg") MultipartFile file, Model m, Course course){
        System.out.println("进入控制层");
        String imgname;
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = "F://course/courseimg/" + File.separator + fileName;
            imgname="/coursefile/courseimg/"+File.separator.substring(1,File.separator.length()) + fileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            System.out.println(destFile);
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
            m.addAttribute("fileName", fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
        Course cs=course;
        cs.setUpID((String) session.getAttribute("user"));
        cs.setCourseimg(imgname);
        System.out.println(cs.toString());
        courseMapper.insertCourse(cs);
        System.out.println(course.toString());
        return "redirect:/course/mycourse";
    }

    @RequestMapping(value = "/opencourse")
    public String opencourse(Model model){
        ArrayList<SecondCourse> secondCourses=courseMapper.secondcourses();
        model.addAttribute("secondcourselist",secondCourses);
        return "opencourse";
    }

    @RequestMapping(value = "/main")
    public String main(Model model){
        //随机抽取6条数据
        ArrayList<Course> courses=courseMapper.randcourse(6);
        model.addAttribute("courses",courses);
        return "main";
    }

    public void loadcourse(Model model){
        ArrayList<FirstCourse> fc=courseMapper.firstcourses();
        ArrayList<SecondCourse> sc=courseMapper.secondcourses();
        model.addAttribute("fc",fc);
        model.addAttribute("sc",sc);
    }

    @RequestMapping(value = "/courselist")
    public String course(Model model){
       loadcourse(model);
        ArrayList<Course> courses=courseMapper.randcourse(6);
        model.addAttribute("courses",courses);
        return "course";
    }

    @RequestMapping(value = "/searchfirstcourse")
    public String searchfirstcourse(Model model,@RequestParam(value = "firstid")int id){
        loadcourse(model);
        ArrayList<Course> courses=courseMapper.selectByFirstid(id);
        model.addAttribute("courses",courses);
        return "course";
    }

    @RequestMapping(value = "/searchsecondcourse")
    public String searchsecondcourse(Model model,@RequestParam(value = "secondid")int id){
        loadcourse(model);
        ArrayList<Course> courses=courseMapper.selectBySecondid(id);
        model.addAttribute("courses",courses);
        return "course";
    }

    @RequestMapping(value = "/searchcoursename")
    public String searchcoursename(Model model,@RequestParam(value = "searchname")String coursename){
        loadcourse(model);
        ArrayList<Course> courses=courseMapper.selectByName(coursename);
        model.addAttribute("courses",courses);
        return "course";
    }

    @RequestMapping(value = "/mycourse")
    public String mycourse(HttpSession session,Model model){
        ArrayList<Course> mycourse=courseMapper.mycourse((String) session.getAttribute("user"));
        for(Course e:mycourse){
            System.out.println(e.toString());
        }
        model.addAttribute("mycourse",mycourse);
        return "mycourse";
    }

    @RequestMapping(value = "/coursevideo")
    public String coursevideo(@RequestParam(value = "id") int id,Model model){
        ArrayList<Video> videos=videoMapper.selectvideo(id);
        model.addAttribute("videos",videos);
        model.addAttribute("courseid",id);
        return "coursevideo";
    }

    @RequestMapping(value = "/destroy")
    public String destroy(@RequestParam(value = "id") int id){
        videoMapper.deleteByCourseid(id);
        courseMapper.deleteById(id);
        return "redirect:/course/mycourse";
    }
}
