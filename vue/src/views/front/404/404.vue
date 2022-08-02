<template>
  <div class="page_404">
    <el-result title="404" sub-title="抱歉，出问题了">
      <template #icon>
        <el-image
            src="https://s1.hdslb.com/bfs/static/jinkela/video/asserts/no_video.png"
        />
      </template>
      <template #extra>
        <el-button type="primary" @click="$router.push('/front/home')">返回主页</el-button>
      </template>
    </el-result>
  </div>
</template>

<script>
export default {
  name: "404"
}
</script>

<style scoped>
 .page_404 {
   text-align: center;
   margin-top: 20vh;
 }

</style>
