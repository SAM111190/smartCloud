<template>
  <div style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;">
    <div style="width: 80%; margin:20px auto;text-align:center" class="page">
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
            <el-form-item prop="type">
              <el-select  clearable placeholder="选择类型" v-model="form.type">
                <el-option label="灌水区域" value="1"/>
                <el-option label="反馈区域" value="2"/>
                <el-option label="问题求助" value="3"/>
                <el-option label="其他区域" value="4"/>
              </el-select>
            </el-form-item>
                </el-col>
                <el-col :span="16">
            <el-form-item style="position: relative;right:120px" prop="title">
              <el-input v-model="form.title" placeholder="标题为5-15个字" maxlength="15" minlength="5" show-word-limit></el-input>
            </el-form-item>
                </el-col>
              </el-row>
            <el-form-item prop="content">
              <el-input v-model="form.content" style="width: 86%" type="textarea" placeholder="在这里输入你要发布的内容" :rows="15" maxlength="1000" show-word-limit></el-input>
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
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Person",
  inject:['reload'],
  data() {
    return {
      form: {},
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
      title:'',
      type:'',
      treaty: [],
      rules:{
        type: [
          {
            required: true,
            message: '请选择发布类型',
            trigger: 'change',
          },
        ],
        title: [
          { required: true, message: '请输入帖子的主题', trigger: 'blur' },
          { min: 5, max: 15, message: '长度应该在5-15之间', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入帖子的内容', trigger: 'blur' },
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
    this.getUser().then(res => {
      console.log(res)
      this.form = res
    })
  },
  methods: {
    async getUser() {
      return (await request.get("/user/username/" + this.user.username)).data
    },
    post() {
      this.$refs['form'].validate((valid) => {
        if(valid) {   //判断是否满足验证规则，才能进行下面的请求
          this.$message.success("发布成功")
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
