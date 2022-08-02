<template xmlns:font-size="http://www.w3.org/1999/xhtml">
  <div>
    <!--功能区域-->
    <div style="margin: 10px 0;">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--搜索区域-->
    <div>
      <el-input v-model="name" placeholder="请输入名称" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="danger"  @click="reset">重置</el-button>
    </div>
    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        row-key="id"
        default-expand-all
    >
      <el-table-column prop="id" label="ID"  sortable />
      <el-table-column prop="name" label="名称"  />
      <el-table-column prop="path" label="路径" />
      <el-table-column prop="pagePath" label="页面路径" />
      <el-table-column label="图标" class-name="fontSize18" align="center" label-class-name="fontSize12">
        <template #default="scope">
        <i :class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="light"
              content="新增子菜单"
              placement="left-end"
          >
          <el-button type="primary" @click="handleAdd(scope.row.id)" ><i class="el-icon-plus"></i></el-button>
          </el-tooltip>
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
      <el-dialog
          v-model="dialogVisible"
          title="菜单信息"
          width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width: 80%" autocomplete="on"/>
          </el-form-item>
          <el-form-item label="路径">
            <el-input v-model="form.path" style="width: 80%" autocomplete="on"/>
          </el-form-item>
          <el-form-item label="页面路径">
            <el-input v-model="form.pagePath" style="width: 80%" autocomplete="on"/>
          </el-form-item>
          <el-form-item label="图标">
            <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 80%">
              <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.value">
                <i :class="item.value" /> {{ item.name }}
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="form.description" style="width: 80%" autocomplete="on"/>
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
      name:'',
      currentPage:1,
      pageSize:10,
      total:0,
      tableData:[],
      options: []
    }
  },
  created() {
    this.load()
  },
  methods:
      {
        load()//显示后台数据
        {
          request.get("/menu",{
          params:
              {
                name:this.name
              }
          }).then(res=>{
            console.log(res)
            this.tableData = res.data
          })
        },
        reset(){
          this.name=""
          this.load()
        },
        add(){//增加新数据
          this.dialogVisible =true
          this.form = {}
        },
        save(){

            //有id更新
            request.post("/menu",this.form).then(res => {
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
          request.get("/menu/icons").then(res => {
            this.options = res.data
          })
        },

        handleDelete(id)
        {
          console.log(id)
          request.delete("/menu/"+id).then(res=>{
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
        handleAdd(pid) {
          this.dialogVisible = true
          this.form = {}
          if (pid) {
            this.form.pid = pid
          }
        },
      }
}
</script>

<style scoped>
.fontSize18{
  font-size: 18px;
}
.fontSize12{
  font-size: 12px;
}
</style>
