<!--框架-->
<template>
  <div>
    <!-- 头部 -->
    <Header> </Header>
    <!--主体-->
    <div style="display:flex">
      <!-- 侧边栏 -->
      <Aside></Aside>
      <!-- 内容 -->
      <router-view style="flex:1"/>
    </div>
  </div>
</template>

<script>
import Header from "@/components/background/Header";
import Aside from "@/components/background/Aside";
export default {
  name:"Layout",
  components:{
    Header,
    Aside
  }
}
</script>

<style scoped>

</style>
