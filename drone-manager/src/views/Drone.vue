<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="请输入无人机名称名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入无人机编号" suffix-icon="el-icon-message" class="ml-5" v-model="droneNo"></el-input>

      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button  type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>

      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我在想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm= "delBatch"
      >
        <el-button  type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

    </div>


    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="140"></el-table-column>
      <el-table-column prop="name" label="无人机名称" width="140"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="company" label="公司" width="120"></el-table-column>
      <el-table-column prop="category" label="种类"></el-table-column>
      <el-table-column prop="droneNo" label="无人机编号"></el-table-column>
      <el-table-column prop="score" label="可运行公里数"></el-table-column>

      <el-table-column prop="cover" label="图片">
        <template slot-scope="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"  style="width: 70px; height: 70px"></el-image>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="300px"  align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="hanleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>

          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我在想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>

          </el-popconfirm>
          <el-button type="primary"  @click="$router.push('/detail?id='+scope.row.id)" style="margin-left: 5px">进入详情页面</el-button>

        </template>
      </el-table-column>

    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[8, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </div>


    <el-dialog title="无人机信息" :visible.sync="outerVisible" width="30%">
      <el-form label-width="100px" size="smail">
        <el-form-item label="无人机名字">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="详细介绍">
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="制造公司">
          <el-input v-model="form.company" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="无人机种类">
          <el-input v-model="form.category" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="无人机编号">
          <el-input v-model="form.droneNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="可运行里程">
          <el-input v-model="form.score" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="无人机图片">
          <el-upload
              style="text-align: center; padding-bottom: 10px"
              class="avatar-uploader"
              action="http://localhost:9090/file/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="form.cover" :src="form.cover" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="outerVisible = false">取 消</el-button>
        <el-button @click="save">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script >
export  default {
  name:"Drone",
  data(){
    return{
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:8,
      name:"",
      droneNo:"",
      form:{},
      multipleSelection:[],
      outerVisible: false,
      roles:[]
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("/drone/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
          droneNo:this.droneNo,
        }
      })
          .then(res =>{
            console.log(res)
            this.tableData =res.data.records
            this.total=res.data.total
          })

    },
    hanleEdit(row){
      this.form=row
      this.outerVisible=true
    },
    handleAdd(){
      this.outerVisible =true,
          this.form={}
    },
    del(id){
      this.request.delete("/drone/" +id).then(res =>{
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    save(){
      console.log(this.form)
      this.request.post("/drone",this.form).then(res =>{
        if(res.data){
          this.$message.success("保存成功")
          this.outerVisible=false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    reset(){
      this.name ='',
      this.droneNo='',
          this.load()

    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection=val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)//变为集合数组

      this.request.post("/drone/del/batch",ids).then(res =>{
        if(res.data){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()

    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },

    handleAvatarSuccess(res) {
      this.form.cover = res
    },
    handleImportSuccess(){
      this.$message.success("导入成功")
      this.load()
    }
  }

}
</script>

<style>
.headerBg{
  background-color: #eee!important;
}
 .avatar-uploader .el-upload {
   border: 1px dashed #d9d9d9;
   border-radius: 6px;
   cursor: pointer;
   position: relative;
   overflow: hidden;
 }
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>