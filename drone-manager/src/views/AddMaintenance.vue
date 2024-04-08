<template>
  <el-form :model="form" :rules="rules" ref="ruleForm" :inline="true" style="width: 70%" label-width="100px">

    <el-form-item label="操作用户" prop="userName">
      <el-input v-model="form.userName" disabled placeholder="请输入无人机名称"></el-input>
    </el-form-item>

    <el-form-item label="无人机名字" prop="droneName">
      <el-input v-model="form.droneName" disabled placeholder="请输入无人机名字"></el-input>
    </el-form-item>

    <el-form-item label="操作地址" prop="address">
      <el-input style="width:400px" v-model="form.address"  placeholder="请输入描述"></el-input>
    </el-form-item>

    <el-form-item label="手机号码" prop="phone">
      <el-input style="width:400px" v-model="form.phone"  placeholder="请输入描述"></el-input>
    </el-form-item>

    <el-form-item label="保养提交时间" prop="createtime">
      <el-date-picker
          v-model="form.createtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="保养提交时间">
      </el-date-picker>
    </el-form-item>

    <div style="text-align:center; margin-top:30px;">
      <el-button type="primary" @click="save">提交</el-button>
      <el-button type="danger">取消</el-button>
    </div>

  </el-form>
</template>

<script>
export default {
  name: 'addMaintenance',
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {
        userName: '',
        droneName: '',
        address: '',
        phone: '',
        createtime: '',
        orderId:''
      },
      rules: {
        userName: [{ required: true, message: '请输入操作用户', trigger: 'blur' }],
        droneName: [{ required: true, message: '请输入无人机名字', trigger: 'blur' }]
      }
    };
  },
  created() {
        const orderId = this.$route.query.id;
        this.orderId=this.$route.query.id;
        this.request.get("/buyorder/"+orderId).then(res =>{
          this.form =res.data
          console.log(this.form)
        })
        const user = JSON.parse(localStorage.getItem("user"));
        if (user) {
          // 如果缓存中存在用户数据，则将用户名和无人机名称填充到表单中
          this.form.userName = user.username || '';
        }
      },

  methods: {
    save() {
      // 在此处添加其他字段的验证规则
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          // 验证通过，执行保存逻辑
          this.form.status = true; // 例如将 status 设置为 true
          this.form.userId = this.user.id ;
          this.request.post('/maintenance', { ...this.form, orderId: this.$route.query.id })
              .then(res => {
                if (res.code === '200') {
                  this.$notify.success('更新成功');
                } else {
                  this.$notify.error(res.msg);
                }
              })
              .catch(error => {
                console.error('保存出错:', error);
                this.$notify.error('保存出错，请稍后重试');
              });
        } else {
          // 验证不通过，停止保存并提示用户
          return false;
        }

        this.request.put(`/buyorder/updateStatus/`+this.orderId)
            .then(res => {
              // 处理更新成功的逻辑
              console.log("状态更新成功");
            })
            .catch(error => {
              // 处理更新失败的逻辑
              console.error("状态更新失败", error);
            });
      });
    }
  }
};
</script>
