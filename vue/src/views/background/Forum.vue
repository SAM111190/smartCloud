<template>
  <div>
    <!--功能区域-->
    <div style="margin: 10px 0;">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--搜索区域-->
    <div>
      <el-input v-model="username" placeholder="请输入贴子名" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="danger"  @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="ID"  sortable />
      <el-table-column prop="title" label="贴子名"  min-width="100"/>
      <el-table-column prop="nickName" label="发帖人" />
      <el-table-column prop="time" label="发帖时间" />
      <el-table-column prop="postcontent" label="帖子内容">
        <template #default="scope">
          <el-button type="text" @click="view(scope.row.content)">查看内容</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button   @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button  type="danger" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handLeSizeChange"
          @current-change="handLeCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,20]"
          :page-size="pageSize"
          layout="total,sizes,prev,pager,next,jumper"
          :total="total">
      </el-pagination>
      <el-dialog
          v-model="dialogVisible"
          title="用户信息"
          width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="帖子标题">
            <el-input v-model="form.title" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="发布时间">
            <el-input v-model="form.time" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="发帖人">
            <el-input  v-model="form.nickName" style="width: 90%"></el-input>
          </el-form-item>
<!--          <el-form-item label="帖子内容">-->
<!--            <div id="richText" style="width: 90%"></div>-->
<!--          </el-form-item>-->
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save"
        >确认</el-button
        >
      </span>
        </template>
      </el-dialog>
    </div>
    <!--    查看内容弹窗-->
    <el-dialog
        title="查看内容"
        width="80%"
        v-model="dialogFormVisible1"
    >
      <div v-html="content" style="border: 1px solid #ccc;padding: 10px"></div>
    </el-dialog>
  </div>
</template>

<script>


import request from "@/utils/request";
import wangEditor from "wangeditor"

export default {
  beforeRouteEnter(to,from,next){
    let user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
    if(user.role === 'ROLE_ADMIN') next()
    else next('/front/home')
  },
  name: 'HomeView',
  components: {
  },
  data(){
    return{
      form:{},
      dialogVisible:false,
      dialogFormVisible1:false,
      search:'',
      username:'',
      currentPage:1,
      pageSize:10,
      total:0,
      tableData:[],
      multipleSelection: [],
      roles: [],
    }
  },
  created() {
    this.load()
  },
  methods:
      {
        view(content) {
          this.content = content
          this.dialogFormVisible1 = true
        },
        load()//显示后台数据
        {
          request.get("/forum/page1",{
            params:
                {
                  pageNum: this.currentPage,
                  pageSize: this.pageSize,
                  search: this.search,
                  username:this.username
                }
          }).then(res=>{
            console.log(res)
            this.tableData = res.records
            this.total=res.total
          })
        },
        reset(){
          this.username=""
          this.load()
        },
        add(){//增加新数据
          this.dialogVisible =true
          this.form = {}
        },
        save(){
          //有id更新
          request.post("/forum",this.form).then(res => {
            if(res) {
              this.$message.success("保存成功")
            }else
            {
              this.$message.error("保存失败")
            }
            this.load()//刷新表格的数据
            this.dialogVisible=false//关闭弹窗
          })
        },
        handleEdit(row){
          this.form =JSON.parse(JSON.stringify(row))
          this.dialogVisible=true

        },

        handleDelete(id)
        {
          console.log(id)
          request.delete("/forum/"+id).then(res=>{
            if(res) {
              this.$message.success("删除成功")
            }else
            {
              this.$message.error("删除失败")
            }
            this.load()//刷新表格的数据
            this.dialogVisible=false//关闭弹窗
          })
          this.load()
        },
        handLeSizeChange(pageSize)//改变当前每页的个数触发
        {
          this.pageSize=pageSize
          this.load()
        },
        handLeCurrentChange(pageNum)//改变当前页码触发
        {
          this.currentPage=pageNum
          this.load()
        },
      }
}
</script>

<style scoped>
.el-table
/deep/
.cell {
  white-space:nowrap;
}
</style>
