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
          <el-menu-item index="1" @click="$router.push('/front/home')">主页</el-menu-item>
          <el-menu-item index="2" @click="$router.push('/front/forum')">讨论</el-menu-item>
          <el-menu-item index="3" @click="$router.push('/front/404')">关于</el-menu-item>
        </el-menu>
        <div class="search">
          <el-input
              v-model="search"
              placeholder="请输入你想要搜索的内容"
              style="width: 50%"
          />
          <el-button type="primary" size="default" style="margin-left: 10px">搜索</el-button>
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
