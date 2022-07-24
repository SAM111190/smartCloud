<template>
  <div>
    <el-card style="width: 40%; margin:10px;text-align:center">
      <el-form ref="form" :model="form" label-width="80px">
<!--        <div style="text-align: center;margin: 10px 0">-->
<!--          <el-upload-->
<!--              class="avatar-uploader"-->
<!--              action="https://localhost:9090/file/upload"-->
<!--              :show-file-list="false"-->
<!--              :on-success="handleAvatarSuccess"-->
<!--          >-->
<!--            <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">-->
<!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </div>-->
        <el-form-item label="用户名">
          <el-input v-model="form.username" ></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" ></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex" ></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" ></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" ></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: {}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    request.get("/user/username/"+this.user.username).then(res=>{
      if(res.code==='200')
      {

        this.form=res.data
      }
    })
  },
  methods: {
    save(){

      //有id更新
      request.post("/user",this.form).then(res => {
        if(res) {
          this.$message.success("保存成功")
        }else
        {
          this.$message.error("保存失败")
        }
      })
    },
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
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
