package com.example.dronemanagerhou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
@Getter
@Setter
  @TableName("sys_maintenance")
@ApiModel(value = "Maintenance对象", description = "")
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("使用需保养人id")
      private Integer userId;

      @ApiModelProperty("使用需保养人名字")
      private String userName;

      @ApiModelProperty("保修地址")
      private String address;

      @ApiModelProperty("申请单号保养id")
      private Integer orderId;

      @ApiModelProperty("使用需保养人电话")
      private String phone;

      @ApiModelProperty("报修员id")
      private Integer maintenanceId;

      @ApiModelProperty("保修员名字")
      private String maintenanceName;

      @ApiModelProperty("保修状态")
      private Boolean status;

      @ApiModelProperty("保养申请时间")
      private LocalDateTime createtime;

      @ApiModelProperty("保养完成时间")
      private LocalDateTime updatetime;

      @ApiModelProperty("维修无人机名字")
      private String droneName;

      private String note;


}
