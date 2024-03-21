<template>

      <el-container  style="min-height: 100vh">
        <el-aside :width="sideWidth +'px'"  style="background-color: rgb(238,241,246) ; box-shadow: 2px 0 6px rgb( 0 21 41  /35%) ">
          <Aside  :is-collapse="isCollapse" :logoTextShot="logoTextShot" />
        </el-aside>

        <el-container>
          <el-header style="  border-bottom: 1px solid #ccc;" >
            <Header :sollapseBthClass="sollapseBthClass "  :collapse="collapse" />
          </el-header>

          <el-main>
<!--            当前页面子路由全在router-view-->
            <router-view/>
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
    }
  },
  components: {Aside, Header},
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
  }
}
</script>
