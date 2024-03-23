<template>

      <el-container  style="min-height: 100vh">
        <el-aside :width="sideWidth +'px'"  style="background-color: rgb(238,241,246) ; box-shadow: 2px 0 6px rgb( 0 21 41  /35%) ">
          <Aside  :is-collapse="isCollapse" :logoTextShot="logoTextShot" />
        </el-aside>

        <el-container>
          <el-header style="  border-bottom: 1px solid #ccc;" >
            <Header :sollapseBthClass="sollapseBthClass "  :collapse="collapse" :user="user"/>
          </el-header>

          <el-main>
<!--            当前页面子路由全在router-view-->
            <router-view @refreshUser="getUser"  />
          </el-main>
        </el-container>
      </el-container>

</template>

<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";

export default {
  data() {

    return {
      sollapseBthClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShot: true,
      user:{}
    }
  },
  components: {
    Aside,
    Header
  },
  created() {
    //从后台获取最新的User数据
    this.getUser()
  },
  methods: {
    collapse() {  //点击收缩触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unflod'
        this.logoTextShot = false
      } else {
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-flod'
        this.logoTextShot = true
      }
    },
    getUser(){
      let username =localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
      //从后台获取数据
      this.request.get("/user/username/" + username).then(res =>{
        //重新赋值后台最新User数据
        this.user =res.data
      })
    }
  }
}
</script>
