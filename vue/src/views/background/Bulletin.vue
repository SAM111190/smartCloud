<template>
    <div class="bulletin">
          <div class="header">
            发布帖子
          </div>
          <el-form
              ref="form"
              :model="form"
              label-width="100px"
              style="margin-top: 30px"
              :rules="rules">
            <el-form-item prop="title">
              <el-input v-model="form.title" style="width: 86%" placeholder="标题为5-15个字" maxlength="15" minlength="5" show-word-limit></el-input>
            </el-form-item>
            <el-form-item prop="content">
              <el-input v-model="form.content" style="width: 86%" type="textarea" placeholder="在这里输入你要发布的公告" :rows="15" maxlength="1000" show-word-limit></el-input>
            </el-form-item>
          </el-form>
          <div style="text-align: center">
            <el-button type="primary" @click="post">发布</el-button>
            <el-button type="info">保存</el-button>
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
      area:'',
      treaty: [],
      rules:{
        title: [
          { required: true, message: '请输入公告的主题', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入公告的内容', trigger: 'blur' },
          { max: 1000, message: '长度应该在1000之内', trigger: 'blur' },
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
          request.post("/bulletin/insert",this.form).then(res => {
            if(res) {
              this.$message.success("发布成功")
            }else
            {
              this.$message.error("发布失败")
            }
          })
        }
      })
    },
  },
}
</script>

<style scoped>
.bulletin {
  margin-top: 15px;
  margin-left: 10px;
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
.header {
  font-size: larger;
  text-align: left;
  border-bottom: #cccccc 1px solid;
  margin-bottom: 20px;
  padding-bottom: 15px ;
  width:98%;
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
