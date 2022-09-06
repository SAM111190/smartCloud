<template>
  <div class="wrapper">
    <div class="card">
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
        <el-form-item prop="address">
          <el-input v-model="form.address" :prefix-icon="Message"  placeholder="邮箱"/>
        </el-form-item>
        <el-form-item style="padding: 0px 10%">
          <el-button style="margin: 10px;width: 40%; float: right; box-sizing: border-box;" type="primary" @click="register">注册</el-button>
          <el-button style="margin: 10px;width: 40%; float: right; box-sizing: border-box;" type="default" @click="$router.push('/login')">返回登陆</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { Avatar,Lock,Message } from '@element-plus/icons-vue'
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
        address: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
        ],
      },
      Avatar,
      Lock,
      Message,
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
            if(res.code=='200'){
              this.$router.push("/login")
              this.$message.success("注册成功")
            }else {
              this.$message.error("用户名已存在")
              //登录成功后页面跳转到主页
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

.card {
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
