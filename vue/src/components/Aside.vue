<!-- 侧边栏 -->
<template>
    <div>
            <el-menu
                style="width:200px;min-height:calc(100vh - 50px)"
                active-text-color="#1260cc"
                background-color="#fff"
                class="el-menu-vertical-demo"
                default-active="user"
                :default-openeds="[1]"
                text-color="#000">
                <el-sub-menu index="1">
                  <template #title>系统管理</template>
                  <el-menu-item index="user" :route="{path:'/'}">用户管理</el-menu-item>
                </el-sub-menu>
              </el-menu>
    </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
export default {
    name:"Aside",
    components:{
        Document,
        Location,
        Setting,
        IconMenu
    }
}
</script>