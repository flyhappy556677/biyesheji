<template>
  <div>
    <h2>增加购买记录</h2>
      <el-form :model="form"  :inline="true" style="width: 70%" label-width="100px">

        <el-form-item label="无人机编号" prop="droneNo">
          <el-input v-model="form.droneNo" disabled placeholder="请输入所需编号"></el-input>
        </el-form-item>

        <el-form-item label="无人机名称" prop="name">
          <el-input v-model="form.droneName" disabled placeholder="请输入无人机名称"></el-input>
        </el-form-item>

        <el-form-item label="所需积分" prop="score">
          <el-input v-model="form.score" disabled placeholder="请输入所需积分"></el-input>
        </el-form-item>

        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" disabled placeholder="请输入用户名称"></el-input>
        </el-form-item>

        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="form.userName" disabled placeholder="请输入用户名称"></el-input>
        </el-form-item>

        <el-form-item label="用户联系方式" prop="userPhone">
          <el-input v-model="form.userPhone" disabled placeholder="请输入用户联系方式"></el-input>
        </el-form-item>

      </el-form>
    <div style="text-align:center; margin-top:30px;">
      <el-button type="primary" @click="save">提交</el-button>
      <el-button type="danger">取消</el-button>
    </div>
    </div>
</template>

<script>
 export default {
   data(){
     return{
       form:{},
       user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
     }
   },
   created() {
    this.load()
   },
   methods:{
     load(){
       const id=this.$route.query.id
       this.request.get("/drone/"+id).then(res=>{
         this.form=res.data
         this.form.droneName=res.data.name

         // Add user information to the form object
         this.form.userId = this.user.id
         this.form.userName = this.user.username
         this.form.userPhone = this.user.phone
       })
       console.log(this.form)
     },
     save(){
       console.log(this.form)
       this.request.post("/buyorder",this.form).then(res =>{
         if(res.data){
           this.$message.success("保存成功")
           this.load()
         }else{
           this.$message.error("保存失败")
         }
       })
     },
   }
 }
</script>