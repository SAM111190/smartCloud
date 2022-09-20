package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author JHJ
 * @since 2022-09-19
 */
@Getter
@Setter
  @ApiModel(value = "Questionrate对象", description = "")
public class Questionrate implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("评分")
      private Integer rate;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("题目id")
      private Integer questionId;

      @ApiModelProperty("评分时间")
      private String time;


}
