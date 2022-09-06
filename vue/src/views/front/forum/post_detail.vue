<template>
  <div style="padding: 15px 0;font-size: large;width:1150px;margin:20px auto;">
    <el-card>
      <!--          顶部操作部分-->
      <div class="operation">
        <div class="left_operation">
          <el-button type="primary" size="default" @click="$router.push('/front/forum')">返回论坛</el-button>
          <el-button type="default" size="default" @click="reply">回复</el-button>
        </div>
        <div class="right_operation">
          <el-button type="default" size="default">只看楼主</el-button>
        </div>
      </div>
      <!--          清除浮动效果-->
      <div class="clear_float"/>
      <!--          楼主部分-->
      <div class="page_host">
        <div class="left_info">
          <el-avatar :size="100" :src="forum.avatarUrl" />
          <p class="nick_name">{{forum.nickName}}</p>
        </div>
        <div class="right_content">
          <p class="title">
            {{forum.title}}
          </p>
          <div class="post_source">
            <el-tag type="light">楼主</el-tag>
           {{forum.time}}
          </div>
          <div class="post_content">
          {{forum.content}}
          </div>
          <div class="likes">
            <el-button type="success" size="default">
              <svg viewBox="0 0 24 24" width="1em" height="1em" fill="white" style="margin-right: 5px">
                <path
                    d="M7 19v-8H4v8h3zM7 9c0-.55.22-1.05.58-1.41L14.17 1l1.06 1.05c.27.27.44.65.44 1.06l-.03.32L14.69 8H21c1.1 0 2 .9 2 2v2c0 .26-.05.5-.14.73l-3.02 7.05C19.54 20.5 18.83 21 18 21H4a2 2 0 01-2-2v-8a2 2 0 012-2h3zm2 0v10h9l3-7v-2h-9l1.34-5.34L9 9z">
                </path>
              </svg>赞同
            </el-button>
            <el-button type="danger" size="default">
              <svg viewBox="0 0 24 24" width="1em" height="1em" fill="white" style="margin-right: 5px">
                <path fill-rule="evenodd" d="M1 22L12 2l11 20H1zm18.617-2L12 6.15 4.383 20h15.234zM13 19h-2v-2h2v2zm0-3h-2v-5h2v5z">
                </path>
              </svg>
              举报
            </el-button>
          </div>
        </div>
      </div>
      <!--          清除浮动效果-->
      <div class="clear_float"/>
      <!--          回帖部分-->
      <div v-for="item in comments" :key="item.id">
      <div class="page_host">
        <div class="left_info">
          <el-avatar :size="100" :src="item.avatarUrl" />
          <p class="nick_name">{{item.nickName}}</p>
        </div>
        <div class="right_content">
          <div class="post_source_down">
            {{item.time }}
          </div>
          <div class="post_content">
            {{item.content }}
          </div>
          <div class="likes">
            <el-button type="primary" size="default" @click="handleReply(item.id)">
              <svg viewBox="0 0 24 24" width="1em" height="1em" fill="white" style="margin-right: 5px">
                <path fill-rule="evenodd" d="M8.995 22a.955.955 0 01-.704-.282.955.955 0 01-.282-.704V18.01H3.972c-.564 0-1.033-.195-1.409-.586A1.99 1.99 0 012 15.99V3.97c0-.563.188-1.032.563-1.408C2.94 2.188 3.408 2 3.972 2h16.056c.564 0 1.033.188 1.409.563.375.376.563.845.563 1.409V15.99a1.99 1.99 0 01-.563 1.432c-.376.39-.845.586-1.409.586h-6.103l-3.709 3.71c-.22.187-.454.281-.704.281h-.517zm.986-6.01v3.1l3.099-3.1h6.948V3.973H3.972V15.99h6.01zm-3.99-9.013h12.018v2.018H5.991V6.977zm0 4.037h9.014v1.972H5.99v-1.972z">
                </path>
              </svg>回复
            </el-button>
            <el-button v-if="user.id !== item.userId" type="danger" size="default">
              <svg viewBox="0 0 24 24" width="1em" height="1em" fill="white" style="margin-right: 5px">
                <path fill-rule="evenodd" d="M1 22L12 2l11 20H1zm18.617-2L12 6.15 4.383 20h15.234zM13 19h-2v-2h2v2zm0-3h-2v-5h2v5z">
              </path>
              </svg>
              举报
            </el-button>
            <el-popconfirm title="确认删除？" @confirm="del(item.id)">
              <template #reference>
                <el-button type="danger" size="default" v-if="user.id === item.userId">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="white" style="margin-right: 5px">
                    <path fill-rule="evenodd" d="M12 11a1 1 0 011 1v4a1 1 0 11-2 0v-4a1 1 0 011-1zm0-3a1 1 0 110 2 1 1 0 010-2zm0 14C6.477 22 2 17.523 2 12S6.477 2 12 2s10 4.477 10 10-4.477 10-10 10zm0-2a8 8 0 100-16 8 8 0 000 16z" clip-rule="evenodd">
                  </path>
                  </svg>
                  删除
                </el-button>
              </template>
            </el-popconfirm>
          </div>
          <div class="reply" v-if="item.children.length" >
            <div class="piece_reply" v-for="subItem in item.children" :key="subItem.id">
              <el-avatar :size="40" :src="subItem.avatarUrl" />
              <p class="reply_username">{{subItem.nickName}}</p>
              <p class="reply_object" v-if="subItem.pnickName">回复 {{ subItem.pnickName }} :</p>
              <p class="reply_content">{{subItem.content }}</p>
              <p class="reply_source">{{subItem.time}}</p>
              <div class="piece_reply_operation">
                <el-popconfirm title="确认删除？" @confirm="del(subItem.id)">
                  <template #reference>
                   <el-button link type="danger" v-if="user.id === subItem.userId">删除</el-button>
                  </template>
                </el-popconfirm>
                <el-button link type="primary" @click="handleReply(subItem.id)">回复</el-button>
                <el-button link type="primary">举报</el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--          清除浮动效果-->
      <div class="clear_float"/>
      </div>
      <!--          分页-->
      <div class="pagination">
        <el-pagination background layout="prev, pager, next" :total="1000" />
      </div>
      <!--          用户回复-->
      <div class="page_host">
        <div class="left_info">
          <el-avatar :size="100" :src="user.avatarUrl" />
          <p class="nick_name">{{user.nickName}}</p>
        </div>
        <div class="right_content">
          <el-form
              ref="form"
              :model="form"
              style="margin-top: 25px"
              id="reply_form"
              :rules="rules">
            <el-form-item prop="content">
              <el-input v-model="form.content" type="textarea" placeholder="在这里输入你要发布的内容" :rows="15" maxlength="1000" show-word-limit></el-input>
            </el-form-item>
            <el-form-item prop="treaty">
              <el-checkbox v-model="form.treaty" size="large">
                我同意且遵守
                <a href="https://www.baidu.com">智慧云平台讨论区管理条约</a>
              </el-checkbox>
            </el-form-item>
          </el-form>
          <el-button type="primary" size="default" @click="post">发布</el-button>
        </div>
      </div>
      <!--          清除浮动效果-->
      <div class="clear_float"/>

    </el-card>
    <el-dialog title="回复" v-model="dialogFormVisible" width="30%" >
      <el-form label-width="100px" size="small">
        <el-form-item label="回复内容">
          <el-input style="width: 80%" type="textarea" v-model="form.contentReply" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="post"  size="small">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "post_detail",
  data() {
    return {
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
      form:{},
      forum:[],
      comments: [],
      id:this.$route.query.index,
      dialogFormVisible: false,
      rules:{
        content: [
          { required: true, message: '请输入回复的内容', trigger: 'blur' },
          { max: 1000, message: '长度应该在1000之内', trigger: 'blur' },
        ],
        treaty: [
          {
            required: true,
            message: '请在阅读条例后勾选同意',
            trigger: 'change',
          },
        ],
      },
    }
  },
  created() {
    this.load();
    this.loadComment()
  },
  methods: {
    load(){
      // this.qid=this.$route.query.index
       //this.qid=this.$route.query.id
      request.get("/forum/"+this.id,{
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              searchData: this.searchData
            }
      }).then(res=>{
        console.log(res)
        this.forum= res.data
      })
    },
    loadComment() {
     request.get("/comment/tree/" + this.id).then(res => {
        this.comments = res.data
      })
    },
    post() {
      this.$refs['form'].validate((valid) => {
        if(valid) {   //判断是否满足验证规则，才能进行下面的请求
        }
      })
        this.form.forumId = this.id
        if (this.form.contentReply) {
          this.form.content = this.form.contentReply
        }
       request.post("/comment", this.form).then(res => {
          if (res.code === '200') {
            this.$message.success("评论成功")
            this.form = {}  // 初始化评论对象内容
            this.loadComment()
            this.dialogFormVisible = false
          } else {
            this.$message.error(res.msg)
          }
        })
    },
    reply() {
      reply_form.scrollIntoView();
    },
    handleReply(pid) {
      this.form = { pid: pid }
      this.dialogFormVisible = true
    },
    del(id) {
   request.delete("/comment/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.loadComment()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
  },
}
</script>

<style scoped>
.clear_float {
  clear: both;
}
.operation{
  padding-bottom: 45px;
}
.left_operation {
  width: 50%;
  float: left;
}
.right_operation {
  text-align: right;
  width: 50%;
  float: right;
}
.page_host {
  border-top: #cccccc 1px solid;
}
.left_info {
  padding-top: 20px;
  text-align: center;
  width: 12%;
  float: left;
  box-sizing: border-box;
}
.right_content {
  border-left: #cccccc 1px solid;
  width: 88%;
  float: right;
  box-sizing: border-box;
  padding-left: 20px;
}
.nick_name {
  margin-top: 15px;
  color: #3a9bdc;
}
.title {
  font-size: 24px;
  border-bottom: #cccccc 2px dotted;
  line-height: 30px;
  margin-top: 15px;
  padding-bottom: 15px;
}
.post_source {
  margin-top: 10px;
  font-size: 12px;
  color: #cccccc;
}
.post_source_down{
  margin-top: 10px;
  font-size: 12px;
  color: #cccccc;
  border-bottom: #cccccc 1px dotted;
  padding-bottom: 10px;
}
.post_content {
  margin-top: 20px;
  font-size: 14px;
  letter-spacing: 2px;
  margin-bottom: 200px;
}
.likes {
  text-align:right;
  margin-bottom: 20px;
}
a:link {
  color: #409eff;
}
a:visited{
  color: #409eff;
}
a:hover {
  color: grey;
}
a {
  text-decoration: none;
}
.reply {
  border: 1px solid #cccccc;
  border-bottom: none;
  background-color: #f5f5f5;
  margin-bottom: 10px;
}
.piece_reply {
  border-bottom: 1px solid #cccccc;
  padding: 10px 25px 5px 25px;
}
.pagination {
  border-top: #cccccc 1px solid;
  padding: 10px;
  background-color: #f5f5f5;
}
.reply_username {
  display: inline-block;
  position: relative;
  bottom: 15px;
  left: 5px;
  font-size: 12px;
  color: #3a9bdc;
  letter-spacing: 1px;
}
.reply_content {
  display: inline-block;
  position: relative;
  bottom: 15px;
  left: 10px;
  font-size: 12px;
  letter-spacing: 1px;
}
.reply_source {
  display: inline-block;
  position: relative;
  bottom: 15px;
  left: 15px;
  font-size: 12px;
  letter-spacing: 1px;
  color: #cccccc;
}
.reply_object {
  display: inline-block;
  position: relative;
  bottom: 15px;
  left: 10px;
  font-size: 12px;
  letter-spacing: 1px;
  color: #3a8ee6;
}
.piece_reply_operation {
  text-align: right;
}
.dialog-footer {
  margin-top: 50px;
  text-align: right;
}
</style>
