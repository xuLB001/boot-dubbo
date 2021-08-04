package com.xihai.service;

/*
@author XiHai ShengGe
*/

import com.xihai.domain.Student;
import com.xihai.mapper.StudentMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Service(version = "1.0.0")
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public Integer queryAllStudentCount() {
        //提升系统性能
        //首先去redis缓存中查询；如果有直接使用，如果没有则去数据库查询并存放到redis缓存中
        Integer allStudentCount = (Integer) redisTemplate.opsForValue().get("allStudentCount");
        //判断是否有值
        if (null == allStudentCount){
            //去数据库查询，并存入到redis缓存中
            allStudentCount = studentMapper.selectAllStudentCount();
            //并存放到redis缓存中
            redisTemplate.opsForValue().set("allStudentCount",allStudentCount,30, TimeUnit.SECONDS);
        }
        return allStudentCount;
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
