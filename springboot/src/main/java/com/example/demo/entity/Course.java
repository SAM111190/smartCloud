package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("course")
@Data
public class Course {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String time;
    private Integer total;
}
