<template>
  <div class="wrapper">
    <div class="card">
      <div class="steps">
      <el-steps :active=activeIndex align-center>
        <el-step title="填写用户名和邮箱" />
        <el-step title="更改密码"/>
        <el-step title="完成" />
      </el-steps>
      </div>
      <el-form :model="form" size="large" :rules="rules" ref="form" class="form">
<!--        第一步-->
        <div v-if="activeIndex === 1" class="step">
        <el-form-item prop="username">
          <el-input v-model="form.username" :prefix-icon="Avatar" placeholder="用户名"/>
        </el-form-item>
        <el-form-item prop="address">
          <el-input v-model="form.address"   :prefix-icon="Message" placeholder="邮箱"/>
        </el-form-item>
        <el-form-item>
        </el-form-item>
        <el-form-item style="padding: 0px 10%">
          <el-button style="margin: 10px;width: 40%; float:left; box-sizing: border-box;" type="primary" @click="next1">下一步</el-button>
          <el-button style="margin: 10px;width: 40%; float: right; box-sizing: border-box;" type="default" @click="$router.push('/login')">返回登录</el-button>
        </el-form-item>
        </div>
<!--        第二步-->
        <div v-else-if="activeIndex === 2" class="step">
          <el-form-item prop="password">
            <el-input v-model="form.password" show-password :prefix-icon="Lock" placeholder="新密码"/>
          </el-form-item>
          <el-form-item prop="confirm">
            <el-input v-model="form.confirm"  show-password :prefix-icon="Lock" placeholder="确认密码"/>
          </el-form-item>
          <el-form-item>
          </el-form-item>
          <el-form-item style="padding: 0px 10%">
            <el-button style="margin: 10px;width: 40%; float:left; box-sizing: border-box;" type="primary" @click="next2">下一步</el-button>
            <el-button style="margin: 10px;width: 40%; float: right; box-sizing: border-box;" type="default" @click="$router.push('/login')">返回登录</el-button>
          </el-form-item>
        </div>
        <!--        第三步-->
        <div v-else-if="activeIndex === 3" class="result">
          <el-result
              icon="success"
              title="密码重置成功"
              sub-title="请牢记新密码并尝试重新登录"
          >
            <template #extra>
              <el-button type="primary" @click="$router.push('/login')">返回登录</el-button>
            </template>
          </el-result>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { Avatar,Lock,Message } from '@element-plus/icons-vue'
import request from "@/utils/request";
export default {
  name: "forget_password",
  data(){
    return{
      form:{},
      rules: {  //验证规则
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        address: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度应该在6到20个字符', trigger: 'blur' },
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' },
        ],
      },
      activeIndex:1,
      Avatar,
      Lock,
      Message,
    }
  },
  methods:{

    next1 () {
      this.$refs['form'].validate((valid) => {
            if(valid) {   //判断是否满足验证规则，才能进行下面的请求
              request.post("/user/forget", this.form).then(res => {
                sessionStorage.setItem("user",JSON.stringify(res.data))
                if (res.code === '200') {
                  this.activeIndex++;//进入下一步
                } else {
                  this.$message.error("用户名和邮箱不匹配")
                }
              })
            }
      })

    },


    next2 () {
      this.$refs['form'].validate((valid) => {
        if(valid){
          if(this.form.password != this.form.confirm){
            this.$message({
              type: "error",
              message: '两次密码输入不一致'
            })
            return
          }//判断是否满足验证规则，才能进行下面的请求
          request.post("/user/password",this.form).then(res => {
            if(res) {
              this.$message.success("保存成功")
            }else
            {
              this.$message.error("保存失败")
            }
          })
          this.activeIndex++;//进入下一步
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
  margin: calc((100vh - 420px)/2) auto;
  height: 420px;
  width: 650px;
  border: 1px white solid;
  box-sizing: border-box;
  background-color: white;
  border-radius: 5px;
  padding: 20px 90px;
}
.steps {
  margin-top: 30px;
}
.form {
}
.step {
  margin-top: 60px;
}
</style>
