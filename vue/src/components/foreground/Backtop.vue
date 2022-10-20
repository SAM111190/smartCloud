<template>
  <el-tooltip
      effect="light"
      content="返回顶部"
      placement="top-end"
  >
  <el-backtop :right="20" :bottom="20" >
  </el-backtop>
  </el-tooltip>
</template>

<script>
import {ArrowUpBold} from "@element-plus/icons-vue";
export default {
  name: "Backtop",
  data() {
    return{
      ArrowUpBold,
    }
  }
}
</script>

<style scoped>
</style>
