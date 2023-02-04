<template>
      <div class="carousel">
      <el-carousel :interval="4000" type="card" height="240px" arrow="never" style="width: 1000px;margin: 0 auto">
        <el-carousel-item v-for="item in imgs" :key="item">
          <img :src="item" alt="" style="width: 500px;height: 240px">
        </el-carousel-item>
      </el-carousel>
      </div>
  <div style="width:1100px;margin: 15px auto;">
    <div class="nav">
      <el-menu
          :default-active="activeIndex2"
          mode="horizontal"
          text-color="#000"
          active-text-color="#409eff"
          @select="handSelect"
          :ellipsis="false"
          style="border: 1px solid #ededed;border-radius: 8px;height: 50px;"
      >
        <el-menu-item index="1" style="border-right:1px solid #ededed;box-sizing:border-box;width:33%;border-bottom: none;"><img src="https://jstc.lintcode.com/lintcode-react/assets/2dda933de47e369fc233768004aea60f.svg" style="padding-right: 10px">题目类型</el-menu-item>
        <el-menu-item index="2" style="border-right:1px solid #ededed;box-sizing:border-box;width:33%;border-bottom: none"><img src="https://jstc.lintcode.com/lintcode-react/assets/7cd6346ec22d7ad180e2a2747830a45e.svg" style="padding-right: 10px">公告通知</el-menu-item>
        <el-menu-item index="3" style="width:34%;border-bottom: none"><img src="https://jstc.lintcode.com/lintcode-react/assets/71cd1f5d55eade3e0628e512425433cd.svg" style="padding-right: 10px">讨论交流</el-menu-item>
      </el-menu>
    </div>
    <!--    题目类型-->
    <div v-if="activeIndex2 === '1'">
    <div class="header">各类题目齐上阵，快速提高编程能力</div>
      <el-row>
        <el-col :span="5">
          <div @click="$router.push('/front/Python')" class="block" style="background-image: url('https://media-cn.lintcode.com/new_storage_v2/public/202206/bd0f7b39-8c44-4da0-9990-d2f7cae50c7f.png')">
            <span class="title">C++</span>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="block" style="background-image: url('https://media-cn.lintcode.com/new_storage_v2/public/staff/unknown/4/2/b421c05e-938f-11eb-83d2-0242ac1d0002/JAVA%E7%B1%BB%E5%9E%8B%E5%8D%A1%E7%89%87%E8%83%8C%E6%99%AF.png')">
            <span class="title">Java</span>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="block" style="background-image: url('https://media-cn.lintcode.com/new_storage_v2/public/staff/unknown/4/2/39854fcc-9390-11eb-9724-0242ac1d0002/%E5%A4%A7%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B%E5%8D%A1%E7%89%87%E8%83%8C%E6%99%AF.png')">
            <span class="title">大数据</span>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="block" style="background-image: url('https://media-cn.lintcode.com/new_storage_v2/public/staff/unknown/4/2/f68e063e-9374-11eb-83d2-0242ac1d0002/%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD%E7%B1%BB%E5%9E%8B%E5%8D%A1%E7%89%87%E8%83%8C%E6%99%AF.png')">
            <span class="title">人工智能</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="block" style="background-image: url('https://media-cn.lintcode.com/new_storage_v2/public/staff/unknown/4/2/c212b0ce-938f-11eb-83d2-0242ac1d0002/Python类型卡片背景.png')">
            <span class="title">Python</span>
          </div>
        </el-col>
      </el-row>
    </div>
<!--    公告通知-->
    <div v-else-if="activeIndex2 === '2'">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>公告</span>
          <el-button link @click="$router.push('/front/bulletin')">查看更多</el-button>
        </div>
      </template>
      <el-table :data="tableData2" style="width: 100%;cursor: pointer" :show-header="false">
        <el-table-column prop="title"  />
      </el-table>
    </el-card>
    </div>
<!--    讨论交流-->
    <div v-else-if="activeIndex2 === '3'">
      <el-card>
        <template #header>
          <div class="card-header">
            <span>讨论区</span>
            <el-button link @click="$router.push('/front/forum')">查看更多</el-button>
          </div>
        </template>
        <el-table :data="tableData1" style="width: 100%;cursor: pointer" :show-header="false">
          <el-table-column prop="title"   />
        </el-table>
      </el-card>
    </div>
<!--      <div class="board">-->
<!--        <el-card style="margin-bottom:20px">-->
<!--          <template #header>-->
<!--            <div class="card-header">-->
<!--              <span>公告</span>-->
<!--              <el-button link @click="$router.push('/front/bulletin')">查看更多</el-button>-->
<!--            </div>-->
<!--          </template>-->
<!--          <el-table :data="tableData2" style="width: 100%;cursor: pointer" :show-header="false">-->
<!--            <el-table-column prop="title"  />-->
<!--          </el-table>-->
<!--        </el-card>-->
<!--        <el-card style="margin-bottom:20px">-->
<!--          <template #header>-->
<!--            <div class="card-header">-->
<!--              <span>讨论区</span>-->
<!--              <el-button link @click="$router.push('/front/forum')">查看更多</el-button>-->
<!--            </div>-->
<!--          </template>-->
<!--          <el-table :data="tableData1" style="width: 100%;cursor: pointer" :show-header="false">-->
<!--            <el-table-column prop="title"   />-->
<!--          </el-table>-->
<!--        </el-card>-->
<!--      </div>-->
<!--      <div class="content">-->
<!--    <el-row :gutter="0">-->
<!--      <el-col :span="6" style="margin-bottom: 10px">-->
<!--        <div>-->
<!--          <img class="img" @click="$router.push('/front/programming')" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/220410155702/Algorithm__Interview_Skills.jpg?x-oss-process=image%2Fformat%2Cwebp">-->
<!--        </div>-->
<!--          <span class="title">程序设计</span>-->
<!--      </el-col>-->
<!--      <el-col :span="6" style="margin-bottom: 10px">-->
<!--        <div>-->
<!--          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/200731021415/Machine-Learning.jpeg?x-oss-process=image%2Fformat%2Cwebp">-->
<!--        </div>-->
<!--        <span class="title">人工智能</span>-->
<!--      </el-col>-->
<!--      <el-col :span="6" style="margin-bottom: 10px">-->
<!--        <div>-->
<!--          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/201224032912/%E4%BB%8E%E9%9B%B6%E5%AD%A6%E7%AE%97%E6%B3%95.jpg?x-oss-process=image%2Fformat%2Cwebp">-->
<!--        </div>-->
<!--        <span class="title">大数据</span>-->
<!--      </el-col>-->
<!--      <el-col :span="6"  style="margin-bottom: 10px">-->
<!--        <div>-->
<!--          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/200901033854/Leetbook_%E5%9B%BE%E8%A7%A3%E7%AE%97%E6%B3%95%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84_300x400.jpg?x-oss-process=image%2Fformat%2Cwebp">-->
<!--        </div>-->
<!--        <span class="title">图解算法数据结构</span>-->
<!--      </el-col>-->
<!--      <el-col :span="6"  style="margin-bottom: 10px">-->
<!--        <div>-->
<!--          <img class="img" src="https://assets.leetcode.cn/aliyun-lc-upload/leetbook/cover/200731021727/Top_Interview_Questions.jpeg?x-oss-process=image%2Fformat%2Cwebp">-->
<!--        </div>-->
<!--        <span class="title">算法汇总</span>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--      </div>-->
    <div class="footer">
      <div>©智慧云平台</div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

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
      activeIndex2:'1',
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      tableData1:[],
      tableData2:[],
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    this.load()
  },
  methods: {
    handSelect(key){
      this.activeIndex2 = key;
    },
    load()//显示后台数据
    {
      request.get("/forum/page", {
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search,
              username: this.username
            }
      }).then(res => {
        console.log(res)
        this.tableData1 = res.records
      })

      request.get("/bulletin/page", {
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search,
              username: this.username
            }
      }).then(res => {
        console.log(res)
        this.tableData2 = res.records
      })
    }
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
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.img{
  border-radius: 8px;
  width: 144px;
  height: 192px;
}
.block{
  width: 200px;
  height: 127px;
  border-radius: 2px;
  display:flex;
  background-size: 100% 100%;
  transition-property: all;
  transition-duration: 0.2s;
}
.block:hover{
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
  background-color: #f5f5f5;
  padding-top: 20px;
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
.board
/deep/
.el-card__body
{
  padding-top: 5px;
}
.title {
  font-size: 18px;
  color:whitesmoke;
  line-height: 22px;
  margin: 20px 20px 0;
}
.number {
  font-size: 16px;
  color: #f5f5f5;
  line-height: 16px;
  position: absolute;
  bottom: 20px;
  margin-left: 20px;
}
.nav {
  margin: 20px 0;
}
.header {
  font-size: 20px;
  line-height: 24px;
  color: #444444;
  margin-bottom: 20px;
}
.footer {
  font-size: 12px;
  color:#b7b7b7;
  margin-top: 40px;
}
</style>
