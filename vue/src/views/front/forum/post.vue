<template>
  <div style="padding: 15px 0;font-size: large;width: 1100px;margin:20px auto;">
    <el-card>
      <div class="setting_header">
        发布帖子
      </div>
      <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="margin-top: 25px"
          :rules="rules">
        <el-row :gutter="0">
          <el-col :span="8">
            <el-form-item prop="area">
              <el-select  clearable placeholder="选择类型" v-model="form.area">
                <el-option label="灌水区域" value="灌水区域"/>
                <el-option label="问题求助" value="问题求助"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item style="position: relative;right:145px" prop="title">
              <el-input v-model="form.title" placeholder="标题为5-20个字" maxlength="20" minlength="5" show-word-limit></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item prop="content">
          <div id="richText" style="width: 85%;z-index: 1"></div>
          <!--              <el-input v-model="form.content" style="width: 85%" type="textarea" placeholder="在这里输入你要发布的内容" :rows="15" maxlength="1000" show-word-limit></el-input>-->
        </el-form-item>
        <el-form-item prop="treaty">
          <el-checkbox v-model="form.treaty" size="large">
            我同意且遵守
            <a href="https://www.baidu.com">智慧云平台讨论区管理条约</a>
          </el-checkbox>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="post">发布</el-button>
        <el-button type="info" @click="$router.push('/front/forum')">返回</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import wangEditor from "wangeditor";
import hljs from 'highlight.js'
import 'highlight.js/styles/monokai-sublime.css'
import request from "@/utils/request";
import {UploadFilled} from "@element-plus/icons";
let editor;
export default {
  name: "Person",
  inject:['reload'],
  data() {
    return {
      form: {},
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
      title:'',
      area:'',
      UploadFilled,
      treaty: [],
      rules:{
        area: [
          {
            required: true,
            message: '请选择发布类型',
            trigger: 'change',
          },
        ],
        title: [
          { required: true, message: '请输入帖子的主题', trigger: 'blur' },
          { min: 5, max: 20, message: '长度应该在5-20之间', trigger: 'blur' },
        ],
        // content: [
        //   { required: true, message: '请输入帖子的内容', trigger: 'blur' },
        //   { max: 1000, message: '长度应该在1000之内', trigger: 'blur' },
        // ],
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
    this.getUser().then(res => {
      console.log(res)
      this.form = res
    })
  },
  mounted() {
    editor = new wangEditor("#richText");
    editor.config.focus = false
    editor.highlight = hljs
    editor.config.excludeMenus = [
      'video',
      'backColor',

      'link',
      'list',
      'todo',
      'justify',
      'quote',
      'table',
    ]
    editor.config.uploadImgServer = 'http://localhost:9091/file/uploadImg'
    editor.config.uploadFileName='file'
    editor.create();
  },
  methods: {
    async getUser() {
      return (await request.get("/user/username/" + this.user.username)).data
    },
    post() {
      const content = editor.txt.html()
      //富文本框手动赋值
      this.form.content = content
      this.$refs['form'].validate((valid) => {
        if(valid) {   //判断是否满足验证规则，才能进行下面的请求
          if(!content)
          {
            this.$message.error("请填写内容")
          }
          else {
            request.post("/forum/insert",this.form).then(res => {
              if(res) {
                this.$message.success("发布成功")
                this.$router.push('/front/forum')
              }else
              {
                this.$message.error("发布失败")
              }
            })
          }
        }
      })
    },
  },
}
</script>

<style scoped>
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
.setting_header {
  font-size: larger;
  text-align: left;
  border-bottom: #cccccc 1px solid;
  margin-bottom: 20px;
  padding-bottom: 15px ;
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
</style>
