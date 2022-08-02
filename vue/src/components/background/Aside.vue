<!-- 侧边栏 -->
<template>
  <el-menu
      style="width:200px;min-height:calc(100vh - 50px);margin-right: 10px"
      active-text-color="#1260cc"
      background-color="#fff"
      class="el-menu-vertical-demo"
      default-active="user"
      :default-openeds="opens"
      text-color="#000"
      :collapse-transition="false"
      :collapse="isCollapse"
      router>
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="@/assets/logo1.png" alt="" style="width: 20px; position: relative; top: 5px;">
      <b>后台管理系统</b>
    </div>
    <div v-for="item in menus" :key="item.id">
      <div v-if="item.path">
        <el-menu-item :index="item.path">
          <i :class="item.icon"></i>
          <span slot="title">{{item.name}}</span>
        </el-menu-item>
      </div>
      <div v-else>
        <el-sub-menu :index="item.id ">
          <template #title>
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
          </template>
          <div  v-for="subItem in item.children" :key="subItem.id">
            <el-menu-item :index="subItem.path">
              <i :class="subItem.icon"></i>
              <span slot="title">{{ subItem.name }}</span>
            </el-menu-item>
          </div>
        </el-sub-menu>
      </div>
    </div>
    <!--  <div>-->
    <!--            <el-menu-->
    <!--                style="width:200px;min-height:calc(100vh - 50px);margin-right: 10px"-->
    <!--                active-text-color="#1260cc"-->
    <!--                background-color="#fff"-->
    <!--                class="el-menu-vertical-demo"-->
    <!--                default-active="user"-->
    <!--                :default-openeds="[1]"-->
    <!--                text-color="#000">-->
    <!--                <el-sub-menu index="1">-->
    <!--                  <template #title>系统管理</template>-->
    <!--                  <el-menu-item index="user" @click="$router.push('/home')">用户管理 </el-menu-item>-->
    <!--                  <el-menu-item index="role"  @click="$router.push('/role')">角色管理</el-menu-item>-->
    <!--                  <el-menu-item index="menu"  @click="$router.push('/menu')">菜单管理</el-menu-item>-->
    <!--                </el-sub-menu>-->
    <!--              </el-menu>-->
    <!--    </div>-->
  </el-menu>
</template>

<script>

export default {
  name:"Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean,
  },
  data() {
    return {
      menus: sessionStorage.getItem("menus") ? JSON.parse(sessionStorage.getItem("menus")) : [],
      opens: sessionStorage.getItem("menus") ? JSON.parse(sessionStorage.getItem("menus")).map(v => v.id + '') : []
    }
  },

}
</script>