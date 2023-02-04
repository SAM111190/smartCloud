<template>
  <div>
    <!--功能区域-->
    <div style="margin: 10px 0;">
      <el-button type="primary" @click="add">发布公告</el-button>
    </div>
    <!--搜索区域-->
    <div>
      <el-input v-model="title" placeholder="请输入公告标题" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="danger"  @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="ID" sortable />
      <el-table-column prop="time" label="时间"  />
      <el-table-column prop="username" label="管理员"  />
      <el-table-column prop="title" label="公告标题" />
      <el-table-column prop="content" label="内容">
        <template v-slot="scope">
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
          title="公告操作"
          width="80%">
        <el-form
              ref="form"
              :model="form"
              label-width="10px"
              style="margin-top: 30px"
              :rules="rules">
            <el-form-item prop="title">
              <el-input v-model="form.title" style="width: 100%" placeholder="标题为5-15个字" maxlength="15" minlength="5" show-word-limit></el-input>
            </el-form-item>
            <el-form-item prop="content">
              <div id="richText" style="width: 100%"></div>
            </el-form-item>
          </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="save">确认</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
        </template>
      </el-dialog>
    </div>

    <!--    查看内容弹窗-->
    <el-dialog
        title="查看内容"
        width="80%"
        v-model="dialogVisible1"
    >
      <div v-html="content" style="border: 1px solid #ccc;padding: 10px"></div>
    </el-dialog>

  </div>
</template>

<script>

import wangEditor from "wangeditor"
import request from "@/utils/request";
let editor;
export default {
  name: 'Bulletin',
  components: {
  },
  data(){
    return{
      form:{},
      dialogVisible:false,
      dialogVisible1:false,
      content:'',
      search:'',
      title:'',
      currentPage:1,
      pageSize:10,
      total:0,
      tableData:[],
      multipleSelection: [],
      rules:{
        title: [
          { required: true, message: '请输入帖子的主题', trigger: 'blur' },
          { min: 5, max: 15, message: '长度应该在5-15之间', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入帖子的内容', trigger: 'blur' },
        ],
      },
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
    }
  },
  created() {
    this.load()
  },
  methods:
      {
        async getUser() {
          return (await request.get("/user/username/" + this.user.username)).data
        },
        view(content) {
          this.content = content
          this.dialogVisible1 = true
        },
        load()//显示后台数据
        {
          request.get("/bulletin/page",{
            params:
                {
                  pageNum: this.currentPage,
                  pageSize: this.pageSize,
                  search: this.search,
                  title:this.title
                }
          }).then(res=>{
            console.log(res)
            this.tableData = res.records
            this.total=res.total
          })
        },
        save(){
          const content = editor.txt.html()
          //富文本框手动赋值
          this.form.content = content
          //有id更新
          request.post("/bulletin",this.form).then(res => {
            if(res) {
              this.$message.success("保存成功")
            }else
            {
              this.$message.error("保存失败")
            }
            this.load()//刷新表格的数据
            this.dialogVisible = false//关闭弹窗
          })
        },
        reset(){
          this.title=""
          this.load()
        },
        add(){//增加新数据
          this.dialogVisible = true;
          this.form = {};
          this.$nextTick(() => {
            if(!editor) {
              editor = new wangEditor("#richText");
              editor.config.focus = false
              editor.config.zIndex = 1
              editor.config.uploadImgServer = 'http://localhost:9091/file/uploadImg'
              editor.config.uploadFileName='file'
              editor.create();
            }
            editor.txt.html('');
          })
        },
        handleEdit(row){
          this.form = row;
          this.dialogVisible = true;
          this.$nextTick(() => {
            if(!editor) {
              editor = new wangEditor("#richText");
              editor.config.focus = false
              editor.config.zIndex = 1
              editor.config.uploadImgServer = 'http://localhost:9094/file/uploadImg'
              editor.config.uploadFileName='file'
              editor.create();
            }
            editor.txt.html(this.form.content);
          })
        },

        handleDelete(id)
        {
          console.log(id)
          request.delete("/bulletin/"+id).then(res=>{
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



