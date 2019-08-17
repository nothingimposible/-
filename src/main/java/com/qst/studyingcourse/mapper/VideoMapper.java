package com.qst.studyingcourse.mapper;

import com.qst.studyingcourse.pojo.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface VideoMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into videotable (id,videodesc,courseid,videodescribe,coverimg) values(#{id},#{videodesc},#{courseid},#{videodescribe},#{coverimg})")
    public void uploadvideo(Video video);

    @Select("select * from videotable where courseid=#{videoid}")
    public ArrayList<Video> selectvideo(int videoid);

    @Delete("delete from videotable where courseid=#{id}")
    public void deleteByCourseid(int id);

    @Delete("delete from videotable where id=#{id}")
    public void deleteById(int id);

    @Select("select * from videotable where id=#{id}")
    public Video selectone(int id);
}
