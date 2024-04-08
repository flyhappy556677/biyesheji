<template>
  <div>
<!--    头部-->
    <div style="display: flex;height: 60px; line-height: 60px; border-bottom: 1px solid #ccc">
      <div style="width: 300px; display: flex;padding-left: 30px;">
        <div>
          <img src="../../assets/logo.png" alt="" style="width: 50px; position: relative;top: 5px ;right: 0">
        </div>
        <div style="flex: 1 ; ">欢迎来到无人机管理系统</div>

      </div>
<!--      导航菜单-->
      <div style="flex: 1">
<!--        <ul style="list-style: none; background-color: #98FB98;">
          <li class="item"><a href="/login"> 菜单1 </a></li>
          <li class="item"><a href="/login"> 菜单2 </a></li>
          <li class="item"><a href="/login"> 菜单3 </a></li>
          <li class="item"> ><a href="/login">菜单4</a></li>
        </ul>-->
        <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/front/home">首页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <el-menu-item index="/front/item">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项1</el-menu-item>
              <el-menu-item index="2-4-2">选项2</el-menu-item>
              <el-menu-item index="2-4-3">选项3</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="3" disabled>消息中心</el-menu-item>
          <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
        </el-menu>
      </div>
      <div style="width: 200px">

        <div v-if="!user.username">
          <el-button>登录</el-button>
        </div>
        <div v-else>
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

      </div>
    </div>

    <div style="width: 1200px; margin: 0 auto">
      <router-view/>
    </div>

  </div>
</template>
<script >
export  default {
  name:"Front",
  data(){
    return{
      user:localStorage.getItem("user") ?JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {

  },
  methods:{
    logout(){
      this.$store.commit("logout")
      this.$message.success("退出成功")
    }
  }
}
</script>

<style>
.item{
  display: inline-block;
  width: 100px;
  color: #1E90EF;
  text-align: center;
  cursor: pointer;
}
.item:hover{
  background-color: #cf9236;
}
</style>