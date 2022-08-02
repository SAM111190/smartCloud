<template>
  <div style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;">
    <div class="top">
      <div class="top_header">
        <div class="title">
              <h1>讨论区</h1>
        </div>
      </div>
      <div class="top_menu">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handSelect"
        >
          <el-menu-item index="1">灌水区域</el-menu-item>
          <el-menu-item index="2">反馈区域</el-menu-item>
          <el-menu-item index="3">问题求助</el-menu-item>
          <el-menu-item index="4">其他区域</el-menu-item>
        </el-menu>
      </div>
    </div>
    <div class="bottom">
        <!--    左边的内容-->
        <div class="left_content">
<!--          灌水区域-->
          <div v-if="activeIndex ==='1'">
          <div class="left">
            <div class="user_information">
              <el-avatar :size="50" src="http://inews.gtimg.com/newsapp_bt/0/14798561645/641" />
              <div class="info">
                <div class="username">
                  <p>原神官方</p>
                </div>
                <el-tag>普通用户</el-tag>
              </div>
            </div>
            <div class="post_title">
              <el-link href="https://ys.mihoyo.com/?utm_source=adbdpz&from_channel=adbdpz#/" type="primary" style="font-size: 18px" :underline="false">【不转不是中国人】50个塞尔达抄袭原神的铁证！没看过的后悔一辈子</el-link>
              <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in 灌水区域 @2022.7.30 15:32</p>
            </div>
          </div>
          <div class="left">
            <div class="user_information">
              <el-avatar :size="50" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.bdstatic.com%2F94o3dSag_xI4khGkpoWK1HF6hhy%2Fbaike%2Fw%3D268%3Bg%3D0%2Fsign%3D9f03f75094504fc2a25fb703dde6802c%2Fb151f8198618367a6a99048521738bd4b31ce552.jpg&refer=http%3A%2F%2Fgss0.bdstatic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661759220&t=afbaf3c1bbfe1835776b249f6b23db25" />
              <div class="info">
                <div class="username">
                  <p>任天堂</p>
                </div>
                <el-tag type="danger">管理员</el-tag>
              </div>
            </div>
            <div class="post_title">
              <el-link href="https://www.nintendo.com/" type="primary" style="font-size: 18px" :underline="false">【不转不是地球人】东半球最强法务部准备出击！戦う!</el-link>
              <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in 灌水区域 @2022.7.30 15:50</p>
            </div>
          </div>
        </div>
<!--          反馈区域-->
          <div v-else-if="activeIndex ==='2'">
            <div class="left">
              <div class="user_information">
                <el-avatar :size="50" src="https://imgsa.baidu.com/forum/pic/item/e850352ac65c10386762db0bbf119313b17e89de.jpg" />
                <div class="info">
                  <div class="username">
                    <p>APEX</p>
                  </div>
                  <el-tag>普通用户</el-tag>
                </div>
              </div>
              <div class="post_title">
                <el-link href="https://ys.mihoyo.com/?utm_source=adbdpz&from_channel=adbdpz#/" type="primary" style="font-size: 18px" :underline="false">【SEASON 14】最新赛季爆料，R99恐成最大赢家</el-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in 反馈区域 @2022.7.30 15:32</p>
              </div>
            </div>
          </div>
<!--          问题求助-->
          <div v-else-if="activeIndex ==='3'">
            <div class="left">
              <el-empty>
                <el-button type="primary">发布帖子</el-button>
              </el-empty>
            </div>
          </div>
<!--          其他区域-->
          <div v-else-if="activeIndex ==='4'">
            <div class="left">
                <el-empty>
                  <el-button type="primary">发布帖子</el-button>
                </el-empty>
              </div>
            </div>
          </div>
        <!--    右边的内容-->
        <div class="right_content">
          <div class="right">
              <el-row>
                <el-col :span="12"><el-button type="primary" style="width: 95%" size="large" :icon="Edit">发布帖子</el-button></el-col>
                <el-col :span="12"><el-button type="primary" style="width: 95%" size="large" :icon="Document">我的帖子</el-button></el-col>
              </el-row>
          </div>
          <div class="hot_board">
            <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px;">热门讨论</h2>
            <div class="description">
              <ul>
                <li><p>【不转不是中国人】50个塞尔达抄袭原神的铁证！没看过的后悔一辈子</p></li>
                <li><p>【不转不是地球人】东半球最强法务部准备出击！戦う！</p></li>
              </ul>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import {Tools,Edit,Document} from "@element-plus/icons";
export default {
  name: "user",
  data(){
    return {
      activeIndex:'1',
      Tools,
      Edit,
      Document,
      board:'',
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
    }
  },
  methods:{
    handSelect(key){
      this.activeIndex = key;
    },
  },
}
</script>

<style scoped>
  .user_information {
    display: inline-block;
  }
  .info {
    display: inline-block;
    position: relative;
    padding-left: 10px;
    bottom:2px;
  }
  .top {
    border-radius: 5px;
    box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
  }
  .top_header {
    background-image: url("https://dss0.bdstatic.com/-0U0bXSm1A5BphGlnYG/tam-ogel/-1023261319_440478637_3840_652.jpg");
    background-size: cover;
    background-position: center;
    height: 200px;
  }
  .title {
    position: relative;
    top:calc(200px - 75px);
    left: 15px;
    display: inline-block;
    color: white;
    backdrop-filter: blur(10px);
    border: 1px white solid;
    padding: 10px;
    border-radius: 18px;
  }
  .username {
    margin-bottom: 4px;
  }
  .left_content {
    float: left;
    width: 74%;
  }
  .left {
    margin-top: 10px;
    border-radius: 5px;
    box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
    padding:15px
  }
  .right_content{
    float: right;
    width: 25%;
  }
  .right {
    margin-top: 10px;
    border-radius: 5px;
    box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
    padding:15px
  }
  .post_title {
    display: inline-block;
    width: 80%;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .hot_board {
    margin-top: 10px;
    border-radius: 5px;
    box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
    padding:15px;
    color:grey;
  }
  .hot_board ul {
    margin-top: 10px;
    margin-left: 20px;
  }
  .hot_board li {
    list-style: number;
    font-size: 14px;
  }
  .hot_board p {
    font-size: 14px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
</style>
