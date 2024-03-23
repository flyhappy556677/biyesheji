<template>
  <div style="font-size: 12px ; line-height: 60px; display: flex ">
    <div style="flex: 1; ">
      <span :class="sollapseBthClass" style="cursor: pointer; font-size: 22px" @click="collapse" ></span>

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="{path:'/'}">首页</el-breadcrumb-item>
        <el-breadcrumb-item >{{currentPathName}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style=" cursor: pointer" >
      <div style="width: 100px">
        <img :src="user.avatarUrl" alt=""
              style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
        <span>{{user.nickname}}</span><i class="el-icon—arrow-down" style="margin-right:5px"></i>
      </div>

      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item>
          <router-link to="/person" style="text-decoration: none; color: black">个人信息</router-link >
        </el-dropdown-item>
        <el-dropdown-item>
          <span to="/login" style="text-decoration:  none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>
<script >
export  default {
  name:'Header',
  props:{
    sollapseBthClass:String,
    collapse:Boolean,
    user:Object

  },
  computed:{
    currentPathName(){
      return this.$store.state.currentPathName;//需要监听数据
    }
  },
  data(){
    return{

    }
  },
  watch:{//监听路由搜索出结果，监听路由变化
    currentPathName (newVal,oldVal){
      console.log(newVal)
    }
  },
  methods:{
    collapse(){
      this.$emit("asideCollapse")
    },
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  }


}
</script>