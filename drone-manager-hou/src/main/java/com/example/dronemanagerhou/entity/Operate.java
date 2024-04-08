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
 * @since 2024-04-07
 */
@Getter
@Setter
  @TableName("sys_operate")
@ApiModel(value = "Operate对象", description = "")
public class Operate implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("用户名字")
      private String userName;

      @ApiModelProperty("用户个人飞机id")
      private Integer orderId;

      @ApiModelProperty("起飞地址")
      private String departureaddress;

      @ApiModelProperty("飞行创建时间")
      private LocalDateTime createtime;

      @ApiModelProperty("飞行高度")
      private Double height;

      @ApiModelProperty("飞行速度")
      private Double speed;

      @ApiModelProperty("飞行起飞时间")
      private String operateTime;

      @ApiModelProperty("飞行距离")
      private Double distance;


}
