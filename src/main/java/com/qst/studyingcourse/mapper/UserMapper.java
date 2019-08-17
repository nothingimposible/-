package com.qst.studyingcourse.mapper;

import com.qst.studyingcourse.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("insert into usertable (ID,firstname,mail,password,lastname,privilege) values (#{ID},#{firstname},#{mail},#{password},#{lastname},#{privilege})")
    public void insertUser(User user);

    @Select("select count(*) from usertable where ID=#{ID}")
    public int searchNum(User user);

    @Select("select * from usertable where ID=#{ID} and password=#{password}")
    public User login(User user);

}
