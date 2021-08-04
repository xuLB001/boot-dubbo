package com.xihai.service;

import com.xihai.domain.Student;

/*
@author XiHai ShengGe
*/
public interface StudentService {

    //获取学生总人数
    public Integer queryAllStudentCount();

    Student queryStudentById(Integer id);
}
