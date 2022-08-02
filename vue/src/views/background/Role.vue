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
      <el-input v-model="name" placeholder="请输入名称" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="danger"  @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="ID"  sortable />
      <el-table-column prop="name" label="名称"  />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="flag" label="唯一标识" />
      <el-table-column fixed="right" label="操作">
        <template #default="scope">
          <el-button type="info" @click="selectMenu(scope.row.id)">分配菜单 <i class="el-icon-menu"></i></el-button>
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
          :page-sizes="[1,5,10,20]"
          :page-size="pageSize"
          layout="total,sizes,prev,pager,next,jumper"
          :total="total">
      </el-pagination>
      <el-dialog
          v-model="dialogVisible"
          title="角色信息"
          width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="form.description" style="width: 80%"/>
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

      <el-dialog  title="菜单分配" v-model="menuDialogVis" width="30%" >
        <el-tree
            :props="props"
            :data="menuData"
            show-checkbox
            node-key="id"
            ref="tree"
            :default-expanded-keys="expends"
            :default-checked-keys="checks"
        >
             <template class="custom-tree-node" #default="{ node, data }">
            <span><i :class="data.icon"></i> {{ data.name }}</span>
         </template>
        </el-tree>
        <div slot="footer" class="dialog-footer">
          <el-button @click="menuDialogVis = false">取 消</el-button>
          <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>


import request from "@/utils/request";
import router, {resetRouter} from "@/router";

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
      menuDialogVis:false,
      multipleSelection:[],
      menuData: [],
      props: {
        label: 'name',
      },
      expends:[],
      checks:[],
      roleId: 0,
      roleFlag: '',
      ids: []
    }
  },
  created() {
    this.load()
  },
  methods:
      {
        load()//显示后台数据
        {
          request.get("/role/page",{
          params:
              {
                pageNum: this.currentPage,
                pageSize: this.pageSize,
                search: this.search,
                name:this.name
              }
          }).then(res=>{
            console.log(res)
            this.tableData = res.data.records
            this.total=res.data.total
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
            request.post("/role",this.form).then(res => {
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
          request.delete("/role/"+id).then(res=>{
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
        selectMenu(roleId)
        {
          this.menuDialogVis=true
          this.roleId=roleId
          this.roleFlag = roleId.flag
          //请求菜单数据
          request.get("/menu",{
          }).then(res=>{
            this.menuData=res.data
            this.expends=this.menuData.map(v =>v.id)
          })
          request.get("/role/roleMenu/"+this.roleId,{
          }).then(res=>{
            this.checks= res.data
          })
        },
        saveRoleMenu() {
          request.post("/role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
            console.log(res)
            if (res.code === '200') {
              this.$message.success("绑定成功")
              this.menuDialogVis = false

              // 操作管理员角色后需要重新登录
              if (this.roleFlag === 'ROLE_ADMIN') {
                sessionStorage.removeItem("user")
                sessionStorage.removeItem("menus")
                this.$router.push("/login")
              }

            } else {
              this.$message.error(res.msg)
            }
          })
        },
      }
}
</script>

<style scoped>
</style>
