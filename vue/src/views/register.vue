<template>
  <div class="wrapper">
    <div class="form">
      <div style="color:#1260cc; font-size:30px; text-align: center; padding: 30px">智慧云平台</div>
      <el-form :model="form" size="large" :rules="rules" ref="form">
        <el-form-item prop="username">
          <el-input v-model="form.username" :prefix-icon="Avatar"  placeholder="用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password"  show-password :prefix-icon="Lock" placeholder="密码"/>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="form.confirm"  show-password :prefix-icon="Lock" placeholder="确认密码"/>
        </el-form-item>
        <el-form-item>
          <el-button style="width:100%" type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { Avatar,Lock } from '@element-plus/icons-vue'
import request from "@/utils/request";
export default {
  name: "register",
  data(){
    return{
      form:{},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度应该在6到20个字符', trigger: 'blur' },
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' },
        ],
      },
      Avatar,
      Lock
    }
  },
  methods:{
    register () {
      if(this.form.password != this.form.confirm){
        this.$message({
          type: "error",
          message: '两次密码输入不一致'
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if(valid){
          request.post( "/user/register", this.form).then(res =>{
            if(res.code === '0'){
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/login")  //登录成功后页面跳转到主页
            }else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.wrapper {
  background-image: linear-gradient(200deg,#3a9bdc,#1260cc);
  height: 100vh;
  overflow: hidden;
}

.form {
  margin: calc((100vh - 450px)/2) auto;
  height: 450px;
  width: 650px;
  border: 1px white solid;
  box-sizing: border-box;
  background-color: white;
  border-radius: 5px;
  padding: 10px 90px;
}
</style>