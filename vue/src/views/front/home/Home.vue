<template>
  <div style="width:1400px;margin: 15px auto;">
    <div v-if="!user.username" class="notice">
      <h1>详细内容请登陆后查看</h1>
    </div>
    <div v-else>
      <div class="carousel">
      <el-carousel :interval="4000" type="card" height="240px" arrow="never">
        <el-carousel-item v-for="item in imgs" :key="item">
          <img :src="item" alt="" style="width: 500px;height: 240px">
        </el-carousel-item>
      </el-carousel>
      </div>
      <div class="board">
        <el-card style="margin-bottom:20px">
          <template #header>
            <div class="card-header">
              <span>公告</span>
              <el-button type="text">查看更多</el-button>
            </div>
          </template>
          <div>
            暂无数据
          </div>
        </el-card>
        <el-card style="margin-bottom:20px">
          <template #header>
            <div class="card-header">
              <span>讨论区</span>
              <el-button type="text">查看更多</el-button>
            </div>
          </template>
          <div>
            还没有帖子，快来发布一个吧！
          </div>
        </el-card>
      </div>
      <div class="content">
    <el-row :gutter="0">
      <el-col :span="6" style="margin-bottom: 10px">
        <div>
          <img class="img" @click="$router.push('/front/programming')" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/220410155702/Algorithm__Interview_Skills.jpg?x-oss-process=image%2Fformat%2Cwebp">
        </div>
          <span class="title">程序设计</span>
      </el-col>
      <el-col :span="6" style="margin-bottom: 10px">
        <div>
          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/200731021415/Machine-Learning.jpeg?x-oss-process=image%2Fformat%2Cwebp">
        </div>
        <span class="title">人工智能</span>
      </el-col>
      <el-col :span="6" style="margin-bottom: 10px">
        <div>
          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/201224032912/%E4%BB%8E%E9%9B%B6%E5%AD%A6%E7%AE%97%E6%B3%95.jpg?x-oss-process=image%2Fformat%2Cwebp">
        </div>
        <span class="title">大数据</span>
      </el-col>
      <el-col :span="6"  style="margin-bottom: 10px">
        <div>
          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/200901033854/Leetbook_%E5%9B%BE%E8%A7%A3%E7%AE%97%E6%B3%95%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84_300x400.jpg?x-oss-process=image%2Fformat%2Cwebp">
        </div>
        <span class="title">图解算法数据结构</span>
      </el-col>
      <el-col :span="6"  style="margin-bottom: 10px">
        <div>
          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/200731021727/Top_Interview_Questions.jpeg?x-oss-process=image%2Fformat%2Cwebp">
        </div>
        <span class="title">算法汇总</span>
      </el-col>
    </el-row>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "FrontHome",
  data() {
    return {
      imgs: [
          'https://pic.leetcode-cn.com/1657187378-PtlIas-%E5%B2%97%E4%BD%8D%E9%9D%A2%E8%AF%95%E6%A8%A1%E6%8B%9F%E9%A2%98%E9%9B%86.png?x-oss-process=image%2Fformat%2Cwebp',
          'https://pic.leetcode-cn.com/1657766076-HMNnQn-%E5%AD%A6%E4%B9%A0%E6%9D%BF%E5%9D%97.png?x-oss-process=image%2Fformat%2Cwebp',
          'https://pic.leetcode-cn.com/1657178873-aVniBV-%E7%AC%AC%E4%B8%80%E5%9C%BA.png?x-oss-process=image%2Fformat%2Cwebp',
      ],
      search: '',
      activeIndex: '/front/home',
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  },
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.notice{
  text-align: center;
  margin: 35vh auto;
  color:grey;
}
.img{
  border-radius: 8px;
  width: 144px;
  height: 192px;
}
.img:hover{
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.3);/* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -5px);/* 鼠标悬浮时盒子上移10px */
  cursor: pointer;
}
.title {
  font-size: 16px;
  line-height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.content {
  margin-top: 20px;
}
.carousel {
  width: 1000px;
  margin: 0 auto;
}
.board {
  float: left;
  width: 25%;
  box-sizing: border-box;
  position: relative;
  left: 50px;
}
.content {
  float:right;
  width: 65%;
  box-sizing: border-box;
  padding: 5px 10px;
}
</style>
