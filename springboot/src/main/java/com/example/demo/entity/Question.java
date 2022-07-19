package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("question")
@Data
public class Question {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String submitStatus;
    private String number;
    private String difficulty;
    private String name;
    private String passrate;
}
