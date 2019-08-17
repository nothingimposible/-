package com.qst.studyingcourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.qst.studyingcourse.mapper")
@SpringBootApplication
public class StudyingcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyingcourseApplication.class, args);
    }

}
