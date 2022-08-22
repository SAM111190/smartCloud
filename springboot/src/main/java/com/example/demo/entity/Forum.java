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
 * @author ZW
 * @since 2022-08-19
 */
@Getter
@Setter
  @ApiModel(value = "Forum对象", description = "")
public class Forum implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户")
      private String username;

      @ApiModelProperty("角色")
      private String role;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("时间")
      private String time;

      @ApiModelProperty("区域")
      private String area;

      @ApiModelProperty("头像")
      private String avatarUrl;
  @ApiModelProperty("点赞")
  private Integer likes;

  @ApiModelProperty("标题")
  private String title;


}
