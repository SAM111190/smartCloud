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
          <el-menu-item index="3">联系我们</el-menu-item>
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
                    <el-dropdown-item>个人空间</el-dropdown-item>
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
    <div style="width:1400px;margin: 15px auto;">
      <router-view/>
    </div>
  </div>
</template>

<script>
import {ArrowDown, Avatar} from '@element-plus/icons-vue'
import {RemoveFilled} from "@element-plus/icons";
export default {
  name: "Front",
  components: {
    RemoveFilled,
    Avatar,
    ArrowDown,
  },
  data() {
    return {
      activeIndex:'0',
      search: '',
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  },
  methods: {
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("user")
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
</style>
