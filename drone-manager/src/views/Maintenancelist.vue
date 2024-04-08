<template>
  <div>
    <!-- 搜索表单 -->
    <div>
      <el-input style="width: 240px;" placeholder="请输入无人机名称" v-model="this.name"></el-input>
      <el-input style="width: 240px; margin-left:5px" placeholder="请输入无人机编号" v-model="this.droneNo"></el-input>
      <el-button @click="load">搜索</el-button>
      <el-button @click="resert">重置</el-button>
    </div>


    <el-table :data="tableData" style="width: 100%" stripe row-key="id" default-expand-all>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="userName" label="申请维修人"></el-table-column>
      <el-table-column prop="droneName" label="无人机名称"></el-table-column>
      <el-table-column prop="address" label="申请地址"></el-table-column>
      <el-table-column prop="phone" label="所填手机号"></el-table-column>
      <el-table-column prop="createtime" label="申请时间"></el-table-column>

      <el-table-column prop="note" label="目前状态">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.note === '已经保修完成'">{{ scope.row.note }}</el-tag>
          <el-tag type="warning" v-if="scope.row.note === '需要进行保修'">{{ scope.row.note }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="320">
        <template v-slot="scope">
          <div v-if="scope.row.status">
            <el-button type="primary" size="7" @click="$router.push('/MATman?id=' + scope.row.id)">进行维修</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>


  </div>
</template>
<script>

export default{
  name:'Maintenancelist',
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      tableData: [],
      total:0,
      cover:'',
      pageNum:1,
      pageSize:8,
      name:'',
      droneNo:''
    }
  },
  created(){
    this.load()

  },
  methods:{
    load(){
      this.request.get("/maintenance/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      })
          .then(res =>{
            console.log(res)
            this.tableData = res.data.records; // 直接赋值整个记录数组
            this.total = res.data.total;
          })
    },

    resert(){
      this.params={
        pageNum:1,
        pageSize:10,
        name:'',
        droneNo:''
      }
      this.load()
    },

    handelCurrentChange(pageNum){
      console.log(pageNum)
      this.params.pageNum=pageNum
      this.load()
    },
    del(id){
      request.delete("/operate/delete/"+id).then(res =>{
        if(res.code ==='200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },



  },
}


</script>