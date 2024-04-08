<template>
  <div>
    <el-form :model="form" :rules="rules" ref="ruleForm" :inline="true" style="width: 70%" label-width="100px">

      <el-form-item label="操作用户" prop="userName">
        <el-input v-model="form.userName" disabled placeholder="请输入无人机名称"></el-input>
      </el-form-item>

      <el-form-item label="无人机名字" prop="droneName">
        <el-input v-model="form.droneName" disabled placeholder="请输入无人机名字"></el-input>
      </el-form-item>

      <el-form-item label="操作地址" prop="departureaddress">
        <el-input style="width:400px" v-model="form.departureaddress"  placeholder="请输入描述"></el-input>
      </el-form-item>

      <el-form-item label="飞行高度" prop="height">
        <el-input style="width:400px" disabled v-model="height" placeholder="飞行高度"></el-input>
        <el-input-number v-model="height"  :step="5" :min="0" :max="200" label="飞行高度"></el-input-number>
      </el-form-item>

      <el-form-item label="飞行速度" prop="speed">
        <el-input style="width:400px" disabled v-model="speed" placeholder="飞行速度"></el-input>
        <el-input-number v-model="speed"  :step="4" :min="0" :max="50" label="飞行速度"></el-input-number>
      </el-form-item>

      <div style="text-align:center; margin-top: 30px;">
        <el-button type="primary" @click="save">提交</el-button>
        <el-button type="danger">取消</el-button>
      </div>

      <div>
        <p>计时器: {{ formattedTime }}</p>
        <p>飞行距离: {{ formattedDistance }} 公里</p>
      </div>

      <el-button type="primary" @click="startTimer">开始飞行前进速度</el-button>
      <el-button type="primary" @click="stopTimer">停止飞行前进速度</el-button>
    </el-form>

  </div>
</template>

<script>

export default {
  name:'opratedrone',
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form:{},
      time: 0, // 时间，单位秒
      timer: null, // 定时器变量
      score:0,
      height:0,
      speed:0,
      distance:0,
      startTime: null, // 记录开始时间
      startDistance: 0, // 记录开始距离
      endTime: null, // 记录结束时间
      endDistance: 0, // 记录结束距离
      rules: {},  // 添加 rules 属性

    }
  },


  created(){
      this.load()
  },


  computed: {
    formattedTime() {
      // 将秒数格式化为 HH:mm:ss
      const hours = Math.floor(this.time / 3600);
      const minutes = Math.floor((this.time % 3600) / 60);
      const seconds = this.time % 60;

      return `${this.pad(hours)}:${this.pad(minutes)}:${this.pad(seconds)}`;
    },

    formattedDistance() {
      return this.distance.toFixed(2);
    }
  },

  beforeDestroy() {
    this.stopTimer();
  },

  methods:{
    load(){
      const id=this.$route.query.id
      this.request.get("/buyorder/"+id).then(res =>{
        this.form=res.data
        console.log(res.data)
      })
    },

    startTimer() {
      if (this.height < 5  ) {
        this.$notify.error('飞机必须达到5米的上升高度才能起飞');
        return ;
      }
      // 记录开始时间和距离
      this.startTime = new Date();
      this.startDistance = this.distance;

      // 启动计时器
      this.timer = setInterval(() => {
        this.time++;
        this.updateDistance(); // 更新飞行距离
      }, 1000);
    },
    stopTimer() {
      // 记录结束时间和距离
      this.endTime = new Date();
      this.endDistance = this.distance;

      // 停止计时器
      clearInterval(this.timer);
    },
    resetTimer() {
      // 重置计时器
      this.time = 0;
    },
    pad(value) {
      // 在个位数前面补零
      return value < 10 ? `0${value}` : value;
    },

    updateDistance() {
      // 根据当前时间、速度和开始/结束时间/距离计算飞行距离
      const currentTime = new Date();
      const timeDiff = (currentTime - this.startTime) / 1000; // 转换为秒
      const speeds = this.speed || 0; // 速度
      const distanceDiff = speeds * timeDiff / 60; // 每次计时器递增的距离增量

      // 更新飞行距离
      this.distance = this.startDistance + distanceDiff;
      console.log(this.distance)
    },

    handleChangeHeight(value) {
      console.log(value);
    },

    handleChangeSpeed(value) {
      console.log(value);
    },

    save(){

      this.form.distance = this.distance.toFixed(2);
      this.form.operateTime = this.formattedTime;

      this.form.height = this.height;
      this.form.speed = this.speed;

      //新的路程
      this.form.usescore=this.form.usescore-this.form.distance

      // 格式化日期为需要的格式
      if (this.form.height === 0 && this.form.speed === 0 ) {
        this.request.post('/operate',{...this.form,orderId:this.$route.query.id,userId:this.user.id
        }).then(res =>{
          console.log(this.form)
          if(res.code ==='200'){
            this.$notify.success('更新成功')
          }else{
            this.$notify.error(res.msg)

          }
        })


            this.request.put('/buyorder/update',{...this.form,id:this.$route.query.id
            }).then(res =>{
              console.log(this.form)
              if(res.code ==='200'){
                this.$notify.success('更新成功')
                this.$router.push("/buyorder")
              }else{
                this.$notify.error(res.msg)
              }
            })
      }
      else {
        // 提示用户飞行高度和速度必须为 0，且处于停止飞行状态
        this.$notify.error('飞行高度和速度必须为 0,且处于停止飞行状态');
      }

    }
  }

}
</script>


