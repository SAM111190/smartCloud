<!--头部-->
<template>
    <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display:flex">
        <div style="width: 200px;padding-left: 30px; font-weight: bold; color: #1260cc">智慧云实验平台</div>
        <div style="flex: 1"></div>
        <div style="width: 100px; padding-top:18px">
            <el-dropdown>
                <span class="el-dropdown-link" style="color:#1260cc;cursor: pointer">
                 {{ user.username }}
                <el-icon class="el-icon--right">
                    <arrow-down />
                 </el-icon>
                </span>
                <template #dropdown>
                     <el-dropdown-menu>
                        <el-dropdown-item>个人空间</el-dropdown-item>
                        <el-dropdown-item>通知</el-dropdown-item>
                        <el-dropdown-item @click="$router.push('/login')">注销</el-dropdown-item>
                      </el-dropdown-menu>
                </template>
                </el-dropdown>
          </div>
    </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons-vue'
import user from "@element-plus/icons/lib/User";
export default {
  name: "Header",
  props: ['user'],
  data() {
    return {
      user: {}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  },
    components:{
        ArrowDown
    }
}
</script>
