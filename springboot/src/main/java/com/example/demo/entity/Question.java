package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author JHJ
 * @since 2022-08-01
 */
@Getter
@Setter
  @ApiModel(value = "Question对象", description = "")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("状态")
      private String submitStatus;

      @ApiModelProperty("序号")
      private String number;

      @ApiModelProperty("难度")
      private String difficulty;

      @ApiModelProperty("题目")
      private String name;

      @ApiModelProperty("通过率")
      private Double passrate;
  @ApiModelProperty("题目详情")
  private String details;
  @ApiModelProperty("题目图片")
  private String src;
  @ApiModelProperty("输入样例")
  private String einput;
  @ApiModelProperty("输入样例")
  private String eoutput;
  @ApiModelProperty("输入格式")
  private String input;
  @ApiModelProperty("输出格式")
  private String output;
  @ApiModelProperty("提示")
  private String tips;
  @ApiModelProperty("评分")
  private BigDecimal rate;
  @ApiModelProperty("评分点击次数")
  private Integer rClick;
  @ApiModelProperty("题解")
  private String solve;


}
