<template>
  <div>
    <div style="margin: 10px 0">
      <el-carousel height="450px" :interval="10000">
        <el-carousel-item v-for="item in imgs" :key="item">
          <img :src="item" alt="" style="width: 50% ;height: auto">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px ">
          <div style="border: 1px solid #ccc; padding-bottom: 10px">
            <img :src="item.url" alt="" style="width: 100%">
            <div style="color: #666;padding: 10px">{{item.name}}</div>
            <div style="padding: 10px"><el-button type="primary">购买</el-button></div>
          </div>
        </el-col>

      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name:'FrontHome',
  data(){
    return{
      imgs:[
          'https://img14.360buyimg.com/n0/jfs/t1/117150/7/40224/94097/6603b6efFcb334ab0/d971cad2b0cc6745.jpg.avif',
        'https://img14.360buyimg.com/n0/jfs/t1/125095/24/33580/17752/647e526aF03002b37/315aad9d2bfdb2cb.jpg.avif'
      ],
      files:[]
    }
  },
  created() {
    this.request.get("echarts/front/all").then(res =>{
      console.log(res)
      this.files =res.data.filter(v => v.type === 'png'||v.type === 'jpg' || v.type === 'avif')
    })
  },
  methods:{

  }
}
</script>
<style>

</style>