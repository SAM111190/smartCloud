<template>
  <div>
    <!--    头部-->
    <div class="header">
      <div class="logo">
        <div style="width: 60px">
          <img src="../../assets/logo1.png" alt="" style="width:40px; position: relative;top: 5px; right: 0">
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
          <el-menu-item index="1" @click="$router.push('/front/home')">主页</el-menu-item>
          <el-menu-item index="2">讨论</el-menu-item>
          <el-menu-item index="3">关于</el-menu-item>
        </el-menu>
        <div class="search">
          <el-input
              v-model="search"
              placeholder="请输入你想要搜索的内容"
              style="width: 50%"
          />
          <el-button type="primary" size="normal" style="margin-left: 10px">搜索</el-button>
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
                  <el-avatar>{{ user.username[0] }}{{ user.username[1] }}</el-avatar>
                </div>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item><router-link to="/person">个人空间</router-link></el-dropdown-item>
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
    <div style="width:1400px;margin: 15px auto;">
      <div class="bar">
        <el-tooltip
            class="box-item"
            effect="light"
            content="联系我们"
            placement="top-end"
        >
          <el-button circle type="primary"
                     style="width: 60px;height: 60px;font-size: 30px"
                     @click="dialogVisible = true"
          ><el-icon><Iphone /></el-icon></el-button>
        </el-tooltip>
      </div>
      <router-view/>
    </div>
  </div>
</template>

<script>
import {Iphone, RemoveFilled} from "@element-plus/icons";
export default {
  name: "Front",
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
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
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
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  },
  methods: {
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
      this.$router.push("/login")
      window.sessionStorage.removeItem("user")
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
  width: 70%;
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
  width: 30%;
}
.bar {
  position: fixed;
  right: 10px;
  bottom: 10px;
  z-index: 2;
}
</style>
