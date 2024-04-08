<template>
  <div>
    <el-form :model="form" :rules="rules" ref="ruleForm" :inline="true" style="width: 70%" label-width="100px">

      <el-form-item label="操作用户" prop="userName">
        <el-input v-model="form.userName" disabled placeholder="请输入无人机名称"></el-input>
      </el-form-item>

      <el-form-item label="无人机名字" prop="droneName">
        <el-input v-model="form.droneName" disabled placeholder="请输入无人机名字"></el-input>
      </el-form-item>

      <el-form-item label="操作地址" prop="address">
        <el-input style="width:400px" disabled v-model="form.address"  placeholder="请输入描述"></el-input>
      </el-form-item>

      <el-form-item label="预留手机号码" prop="phone">
        <el-input style="width:400px" disabled v-model="form.phone"  placeholder="请输入描述"></el-input>
      </el-form-item>


      <el-form-item label="保养提交时间" prop="createtime">
        <el-date-picker disabled
                        v-model="form.createtime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="保养提交时间">
        </el-date-picker>
      </el-form-item>


      <el-form-item label="维修员名字" prop="maintenanceName">
        <el-input v-model="form.maintenanceName" disabled placeholder="请输入维修员名称"></el-input>
      </el-form-item>



      <el-form-item label="保养完成时间" prop="updatetime">
        <el-date-picker
            v-model="form.updatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="保养完成时间">
        </el-date-picker>
      </el-form-item>



      <div style="text-align:center; margin-top: 30px;" >
        <el-button type="primary" @click="save">提交</el-button>
        <el-button type="danger">取消</el-button>
      </div>

    </el-form>
  </div>
</template>

<script>

export default {
  name:'MATman',
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form:{},
      orders:[],
      rules: {}  ,// 添加 rules 属性
      orderId:null
    }
  },

  created(){
    const id = this.$route.query.id;

    this.request.get("/maintenance/"+id).then(res =>{
      this.form =res.data
      console.log(res.data)
      const orderId=res.data.orderId
      this.orderId=res.data.orderId
      this.request.get('/buyorder/'+orderId).then(res =>{
        this.form.score =res.data.score
        console.log(this.form)
        const user = JSON.parse(localStorage.getItem("user"));
        this.form.maintenanceId = user.id;
        this.form.maintenanceName=user.username;
      })

    })

  },
  Loading(){

  },

  methods:{

    save(){
      this.form.status=false
      this.form.id = this.$route.query.id;
      this.form.maintenanceId = this.user.id;
      this.form.maintenanceName = this.user.username;
      console.log(this.form)
      this.request.put('/maintenance/update',this.form).then(res =>{
        console.log(this.form)
        if(res.code ==='200'){
          this.$notify.success('更新成功')
        }else{
          this.$notify.error(res.msg)

        }
      })
      console.log(this.form)
        this.request.put(`/buyorder/newupdate/`+this.orderId)
          .then(res => {
            // 处理更新成功的逻辑
            console.log("状态更新成功");
          })
          .catch(error => {
            // 处理更新失败的逻辑
            console.error("状态更新失败", error);
          });
    }
  }

}
</script>
  

