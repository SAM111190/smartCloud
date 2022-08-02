<template>
  <div class="wrapper">
    <div class="form">
      <div style="color: #1260cc; font-size:30px; text-align: center; padding: 30px; ">智慧云平台</div>
      <el-form :model="form" size="large" :rules="rules" ref="form">
        <el-form-item prop="username">
          <el-input v-model="form.username" :prefix-icon="Avatar" placeholder="用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password"  show-password :prefix-icon="Lock" placeholder="密码"/>
        </el-form-item>
        <el-form-item>
          <div style="display: flex">
            <el-input v-model="form.validCode" :prefix-icon="Key" style="width: 50%" placeholder="验证码"/>
            <ValidCode style="margin-left: 60px" @input="createValidCode"/>
          </div>
        </el-form-item>
        <el-form-item style="padding: 0px 10%">
          <el-button style="margin: 10px;width: 40%; float:left; box-sizing: border-box;" type="primary" @click="login">登录</el-button>
          <el-button style="margin: 10px;width: 40%; float: right; box-sizing: border-box;" type="default" @click="$router.push('/register')">去注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { Avatar,Lock,Key } from '@element-plus/icons-vue'
import request from "@/utils/request";
import ValidCode from "../components/ValidCode";
export default {
  name: "login",
  components: {
    ValidCode,
  },
  data(){
    return{
      form:{},
      rules: {  //验证规则
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度应该在6到20个字符', trigger: 'blur' },
        ],
      },
      Avatar,
      Lock,
      Key,
      validCode:'',
    }
  },
  methods:{
    //接收验证码组件提交的四位验证码
    createValidCode(data) {
      this.validCode = data
    },
    login () {
      this.$refs['form'].validate((valid) => {
        if(valid){   //判断是否满足验证规则，才能进行下面的请求
          if(!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()){
            this.$message.error("验证码错误")
            return
          }
          request.post( "/user/login", this.form).then(res =>{
            if(res.code==='200'){
              sessionStorage.setItem("user",JSON.stringify(res.data))
              sessionStorage.setItem("menus", JSON.stringify(res.data.menus))
              this.$router.push("/front/home")
              this.$message.success("登录成功")
            }else {
              this.$message.error("用户名或密码错误")
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

.form {
  margin: calc((100vh - 400px)/2) auto;
  height: 400px;
  width: 650px;
  border: 1px white solid;
  box-sizing: border-box;
  background-color: white;
  border-radius: 5px;
  padding: 20px 90px;
}
</style>