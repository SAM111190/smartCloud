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
      <el-input v-model="username" placeholder="请输入用户名" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="danger"  @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="ID"  sortable />
      <el-table-column prop="username" label="用户名"  />
      <el-table-column prop="role" label="角色"  />
      <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="password" label="密码" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="邮箱" />
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
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="角色">
            <el-select clearable v-model="form.role" placeholder="请选择角色" style="width: 80%">
              <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.flag"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label=“未知”>未知</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type=“textarea” v-model="form.address" style="width: 80%"/>
          </el-form-item>

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
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {

  },
  data(){
    return{
      form:{},
      dialogVisible:false,
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
        load()//显示后台数据
        {
          request.get("/user/page",{
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
          request.get("/role").then(res => {
            this.roles = res.data
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
          request.post("/user",this.form).then(res => {
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
          request.delete("/user/"+id).then(res=>{
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
</style>
