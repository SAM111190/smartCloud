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
  @ApiModel(value = "Bulletin对象", description = "")
public class Bulletin implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("发布时间")
      private String time;

      @ApiModelProperty("发布人")
      private String username;

      @ApiModelProperty("发布内容")
      private String content;

      @ApiModelProperty("头像")
      private String avatarUrl;

      @ApiModelProperty("链接")
      private String href;

      @ApiModelProperty("点赞数")
      private Integer likes;

      @ApiModelProperty("标题")
     private String title;

  @ApiModelProperty("昵称")
  private String nickName;
}
