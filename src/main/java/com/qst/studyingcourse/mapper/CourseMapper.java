package com.qst.studyingcourse.mapper;

import com.qst.studyingcourse.pojo.Course;
import com.qst.studyingcourse.pojo.FirstCourse;
import com.qst.studyingcourse.pojo.SecondCourse;
import com.qst.studyingcourse.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface CourseMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into coursetable (id,coursename,coursedesc,courseimg,courseteacher,coursetype,secondid,upID) values (#{id},#{coursename},#{coursedesc},#{courseimg},#{courseteacher},#{coursetype},#{secondid},#{upID})")
    public void insertCourse(Course course);

    @Select("select * from coursetable where id=#{id}")
    public Course selectOne(Course course);

    @Select("select * from firsttable")
    public ArrayList<FirstCourse> firstcourses();

    @Select("select * from secondtable")
    public ArrayList<SecondCourse> secondcourses();

    @Select("select * from coursetable where upID=#{userid}")
    public ArrayList<Course> mycourse(String userid);

    @Delete("delete from coursetable where id=#{id}")
    public void deleteById(int id);

    @Select("SELECT * FROM coursetable ORDER BY RAND() LIMIT #{num}")
    public ArrayList<Course> randcourse(int num);

    @Select("select * from coursetable where secondid=#{secondid}")
    public ArrayList<Course> selectBySecondid(int secondid);

    @Select("SELECT * FROM coursetable,secondtable WHERE coursetable.secondid=secondtable.ID AND secondtable.FIRSTID=#{firstid}")
    public ArrayList<Course> selectByFirstid(int firstid);

    @Select("SELECT * FROM coursetable WHERE coursename LIKE '%${coursename}%'")
    public ArrayList<Course> selectByName(@Param("coursename")String coursename);

}
