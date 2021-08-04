package com.xihai.controller;

/*
@author XiHai ShengGe
*/

import com.xihai.domain.Student;
import com.xihai.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Reference(version = "1.0.0",check = false)
    private StudentService studentService;

    @RequestMapping("/count")
    @ResponseBody
    public Object studentCount(){
        Integer allStudentCount = studentService.queryAllStudentCount();
        return "学生的总人数为：" + allStudentCount;
    }

    @RequestMapping(value = "/detail/{id}")
    public ModelAndView studentDetail(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Student student = studentService.queryStudentById(id);
        modelAndView.addObject("student",student);
        modelAndView.setViewName("/detail");
        return modelAndView;
    }

    @GetMapping("/all/count")
    @ResponseBody
    public Object allStudent(){
        Integer allStudentCount = studentService.queryAllStudentCount();
        return "学生总人数为：" + allStudentCount;
    }
}
