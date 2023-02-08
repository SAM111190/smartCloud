<template>
  <div>
    <!--    头部-->
    <div class="header">
      <div class="logo">
        <div style="width: 60px">
          <img src="@/assets/logo1.png" alt="" style="width:40px; position: relative;top: 5px; right: 0">
        </div>
        <div style="flex:1;font-family: 幼圆;color: #1260cc;font-weight: bold">智慧云平台</div>
      </div>
      <div class="nav">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            router
        >
          <el-menu-item index="" @click="$router.push('/front/home')">主页</el-menu-item>
          <el-menu-item index="" @click="$router.push('/front/forum')">讨论</el-menu-item>
          <el-menu-item index="" @click="dialogVisible=true">联系我们</el-menu-item>
        </el-menu>
        <div class="search">
          <el-input
              v-model="search"
              placeholder="搜索一下"
              style="width: 40%;"
          />
          <el-button type="primary" size="default" style="margin-left: 10px" @click="goToSearch">搜索</el-button>
        </div>
      </div>
            <div class="nickname" style="text-align: right; padding-right: 30px">
              <div v-if="!user.username">
                <el-button @click="$router.push('/login')">登录</el-button>
                <el-button @click="$router.push('/register')">注册</el-button>
              </div>
              <div v-else>
              <el-dropdown>
                <div  class="avatar">
                    <el-avatar :size="40" v-if="user.avatarUrl" :src="user.avatarUrl" />
                </div>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="$router.push('/front/user')">个人空间</el-dropdown-item>
                    <el-dropdown-item v-if="user.role === 'ROLE_ADMIN' " @click="$router.push('/home')">后台管理</el-dropdown-item>
                    <el-dropdown-item>通知</el-dropdown-item>
                    <el-dropdown-item>
                      <span @click="logout">注销</span>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
            </div>
    </div>
  </div>
  <div>
    <el-dialog
        v-model="dialogVisible"
        title="联系我们"
        width="700px"
        center
        top="25vh"
        show-close
    >
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="反馈类型" :label-width="'150px'" prop="type">
          <el-radio-group v-model="form.type">
            <el-radio label="界面UI"/>
            <el-radio label="使用体验"/>
            <el-radio label="Bug反馈"/>
            <el-radio label="其他问题"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="'150px'" prop="number">
          <el-input v-model="form.number" placeholder="请输入您邮箱或者电话" style="width: 80%">
          </el-input>
        </el-form-item>
        <el-form-item label="反馈内容" :label-width="'150px'" prop="content">
          <el-input type="textarea" v-model="form.content" placeholder="请输入您的反馈内容" style="width: 80%;"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submit"
        >提交</el-button
        >
      </span>
      </template>
    </el-dialog>
    <el-dialog
        v-model="dialogVisible1"
        width="700px"
        center
        top="25vh"
    >
      <el-result
          icon="success"
          title="提交成功"
          sub-title="我们会尽快联系您，请注意查看您的邮箱或来电"
      >
        <template #extra>
          <el-button type="primary" @click="dialogVisible1 = false">返回</el-button>
        </template>
      </el-result>
    </el-dialog>
    <div>
    </div>
  </div>
</template>

<script>
import {Iphone, RemoveFilled} from "@element-plus/icons";
export default {
  name: "Front_header",
  props:[
      'user'
  ],
  components: {
    Iphone,
    RemoveFilled,
  },
  data() {
    return {
      select:'',
      feedback:'',
      type:'',
      form:{},
      dialogVisible:false,
      dialogVisible1:false,
      activeIndex:'0',
      search: '',
      rules: {  //验证规则
        type: [
          {
            required: true,
            message: '请选择您要反馈的类型',
            trigger: 'change',
          },
        ],
        number: [
          { required: true, message: '请输入您的联系方式', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入您宝贵的意见或建议', trigger: 'blur' },
        ],
      },
    }
  },
  created() {
  },
  methods: {
    goToSearch() {
      this.$emit('getSearchContent',this.search)//将输入框的search传值到layout中，子传父
      this.$router.push("/front/search")//跳转到搜索页面
    },
    submit() {
      this.$refs['form'].validate((valid) => {
        if(valid) {   //判断是否满足验证规则，才能进行下面的请求
          this.dialogVisible = false;
          this.dialogVisible1 = true;
          this.form = {};//清空表单
          }
        })
    },
    logout() {
      window.sessionStorage.removeItem('tokenStr')
      window.sessionStorage.removeItem('user')
      this.$router.push("/login")
      this.$message.success("退出成功")
    },
  }
}
</script>
<style scoped>
.header {
  display: flex;
  height: 60px;
  line-height: 60px;
  border-bottom:1px solid #E6E6E6;
  background-color: white;
}
.logo {
  width: 300px;
  display: flex;
  padding-left: 30px;
  cursor: pointer;
}
.nav {
  flex: 1;
}
.nickname {
  width: 200px;
  text-align: right;
  padding-right: 50px;
}
.search {
  text-align: right;
  float: right;
  width: 40%;
}
a:link {
  color:#000;
}
a:visited{
  color: #000;
}
a:hover {
  color: #ccc;
}
el-dropdown {
  cursor: pointer;
}
.avatar {
  cursor: pointer;
  position: relative;
  top:10px;
  right: 30px;
}
.el-menu-demo {
  height: 60px;
  float:left;
  width: 60%;
}
.bar {
  position: fixed;
  right: 10px;
  bottom: 10px;
  z-index: 2;
}
</style>
