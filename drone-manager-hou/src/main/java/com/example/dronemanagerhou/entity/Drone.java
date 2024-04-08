package com.example.dronemanagerhou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author drone
 * @since 2024-04-02
 */
@Getter
@Setter
  @TableName("sys_drone")
@ApiModel(value = "Drone对象", description = "")
public class Drone implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名字")
      private String name;

      @ApiModelProperty("描述")
      private String description;

      @ApiModelProperty("创造时间")
      private LocalDateTime createtime;

      @ApiModelProperty("公司")
      private String company;

      @ApiModelProperty("分类")
      private String category;

      @ApiModelProperty("标识码")
      private String droneNo;

      @ApiModelProperty("图片")
      private String cover;

      @ApiModelProperty("积分")
      private Integer score;


}
