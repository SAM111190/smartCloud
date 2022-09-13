package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZW
 * @since 2022-09-13
 */
@Getter
@Setter
  @ApiModel(value = "Validation对象", description = "")
public class Validation implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("邮箱")
      private String address;

      @ApiModelProperty("验证码")
      private String code;

      @ApiModelProperty("过期时间")
      private Date time;


}
