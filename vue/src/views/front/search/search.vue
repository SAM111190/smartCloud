<template>
    <div class="top">
      <!--    搜索栏-->
      <div class="search">
        <el-input
            style="width: 40%;height:50px;font-size: large;"
            v-model="page_search"
            placeholder="输入关键词搜索"
            :prefix-icon="Search"
            clearable
        >
        </el-input>
      </div>
    <!--    分类-->
      <div class="type">
        <el-menu
            :default-active="activeIndex"
            mode="horizontal"
            @select="handSelect"
            style="border-bottom: none"
        >
          <el-menu-item index="1">
            题目
            <el-tag effect="light" round type="primary" style="margin-left: 5px">0</el-tag>
          </el-menu-item>
          <el-menu-item index="2">
            用户
            <el-tag effect="light" round type="primary" style="margin-left: 5px">2</el-tag>
          </el-menu-item>
          <el-menu-item index="3">
            讨论
            <el-tag effect="light" round type="primary" style="margin-left: 5px">1</el-tag>
          </el-menu-item>
          <el-menu-item index="4">
            公告
            <el-tag effect="light" round type="primary" style="margin-left: 5px">1</el-tag>
          </el-menu-item>
        </el-menu>
      </div>
    </div>
<!--    搜索内容-->
        <div class="bottom_content">
<!--          题目-->
          <div v-if="activeIndex ==='1'">
            <div class="bottom">
                <el-table
                    :data="list"
                    stripe
                    style="font-size: 12px"
                    ref="filterTable"
                    v-loading="loading"
                >
                  <el-table-column prop="submitStatus" label="状态" width="100px"/>
                  <el-table-column prop="number" label="序号" width="100px"/>
                  <el-table-column prop="difficulty" label="难度" column-key="difficulty" width="100px"
                                   :filters="[
                          { text: '入门', value: '入门' },
                          { text: '简单', value: '简单' },
                          { text: '进阶', value: '进阶' },
                          { text: '困难', value: '困难' },
                          ]"
                                   :filter-method="filterHandler"
                  >
                    <template #default="scope">
                      <el-tag :type="(scope.row.difficulty == '入门'?'':(scope.row.difficulty == '简单'?'warning':(scope.row.difficulty == '进阶'?'success':(scope.row.difficulty == '困难'?'danger':'info'))))" effect="dark">{{scope.row.difficulty}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="name" label="题目" width="200px"/>
                  <el-table-column prop="passrate" label="通过率" sortable width="250px">
                    <template #default="scope">
                      <el-progress :percentage="scope.row.passrate" :text-inside="true" :stroke-width="15"/>
                    </template>
                  </el-table-column>
                  <el-table-column label="">
                    <template #default="scope">
                      <div style="text-align: right;margin-right:100px">
                        <el-button size="small" link><router-link :to="{path:'/front/question',query: {index: scope.row.id}}" >开始做题</router-link></el-button
                        >
                      </div>
                    </template>
                  </el-table-column>
                </el-table>
            </div>
          </div>
<!--          用户-->
          <div v-else-if="activeIndex ==='2'">
            <div class="bottom">
              <div class="user_information">
                <el-avatar :size="50" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-3.yxdown.com%2F2016%2F6%2F13%2F171981a3-e4be-4543-95f7-104b7ef51d60.png&refer=http%3A%2F%2Fi-3.yxdown.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1662881451&t=060a082055836885174451cc5d711cb7" />
                <div class="info">
                  <div class="username">
                    <p>米哈游</p>
                  </div>
                  <el-tag>普通用户</el-tag>
                </div>
              </div>
                <el-button type="success" size="small" :icon="UserFilled" class="like">空间</el-button>
            </div>
            <div class="bottom">
              <div class="user_information">
                <el-avatar :size="50" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.bdstatic.com%2F94o3dSag_xI4khGkpoWK1HF6hhy%2Fbaike%2Fw%3D268%3Bg%3D0%2Fsign%3D9f03f75094504fc2a25fb703dde6802c%2Fb151f8198618367a6a99048521738bd4b31ce552.jpg&refer=http%3A%2F%2Fgss0.bdstatic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661759220&t=afbaf3c1bbfe1835776b249f6b23db25" />
                <div class="info">
                  <div class="username">
                    <p>任天堂</p>
                  </div>
                  <el-tag type="danger">管理员</el-tag>
                </div>
              </div>
                  <el-button type="success" size="small" :icon="UserFilled" class="like">空间</el-button>
            </div>
          </div>
<!--          讨论-->
          <div v-else-if="activeIndex ==='3'">
            <div class="bottom">
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
<!--          公告-->
          <div v-else-if="activeIndex === '4'">
            <div class="bottom">
              <div class="user_information">
                <el-avatar :size="50" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.mp.itc.cn%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170802%2Ff65b79abbfae40eea0c74ebf4fbf1776_th.jpg&refer=http%3A%2F%2Fimg.mp.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661842548&t=e53a876006ca8bd1cf1be9ad90fda5c0" />
                <div class="info">
                  <div class="username">
                    <p>APEX</p>
                  </div>
                  <el-tag type="danger">管理员</el-tag>
                </div>
              </div>
              <div class="post_title">
                <el-link href="https://ys.mihoyo.com/?utm_source=adbdpz&from_channel=adbdpz#/" type="primary" style="font-size: 18px" :underline="false">修复了一堆Bug，并且杀了前端程序员祭天</el-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">@2022.7.30 15:32</p>
              </div>
            </div>
          </div>
        </div>
</template>

<script>
import { Search,UserFilled } from '@element-plus/icons-vue'
export default {
  name: "search",
  data() {
    return{
      page_search:'',//搜索页面的搜索内容
      Search,//搜索框的图标
      UserFilled,
      activeIndex:'1',
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
  .search
  /deep/
  .el-input__wrapper{
    border-radius: 20px;
    background-color: #f7f8fa;
  }
  .search
  /deep/
  .el-input__prefix-inner {
    margin-left: 8px;
  }
 .search {
   text-align: center;
   padding-top: 25px;
 }
 .top {
   box-shadow: -1px 5px 6px 3px rgba(0, 0, 0, 0.05);
 }
 .type {
   margin: 15px auto;
   width: 70%;
 }
 .bottom_content {
   margin: 25px auto;
   width: 70%;
 }
 .bottom {
   /*border-radius: 5px;*/
   /*padding:15px 20px;*/
   /*box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.08);*/
   /*margin-bottom: 10px;*/
   margin-bottom: 10px;
   border-radius: 5px;
   box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.08);
   padding:15px;
 }
 .bottom_info p{
   color: #999aaa;
   font-size: 14px;
   overflow: hidden;
   text-overflow: ellipsis;
   display:-webkit-box;
   -webkit-line-clamp:2;
   -webkit-box-orient:vertical;
 }
  .user_information {
    display: inline-block;
    width: 12%;
  }
  .info {
    display: inline-block;
    position: relative;
    left: 10px;
    bottom: 2px;
  }
  .username {
    margin-bottom: 4px;
    width: 50px;
  }
  .username p {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .like {
    position: relative;
    left:82%;
    bottom: 35px;
  }
  .post_title {
    display: inline-block;
    width: 80%;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
</style>
