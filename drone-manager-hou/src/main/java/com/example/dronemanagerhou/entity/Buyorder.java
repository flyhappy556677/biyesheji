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
 * @since 2024-04-03
 */
@Getter
@Setter
@TableName("sys_buyorder")
@ApiModel(value = "Buyorder对象", description = "")
public class Buyorder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("无人机名称")
      private String droneName;

      @ApiModelProperty("无人机编号")
      private String droneNo;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("用户姓名")
      private String userName;

      @ApiModelProperty("用户手机")
      private String userPhone;

      @ApiModelProperty("购买时间")
      private LocalDateTime buytime;

      @ApiModelProperty("机器总里程")
      private Integer score;

      @ApiModelProperty("所使用里程")
      private Integer usescore;

      @ApiModelProperty("状态")
      private Boolean status;

      private String cover;

      private String description;

      private String company;

      private String category;

      private String note;


}
