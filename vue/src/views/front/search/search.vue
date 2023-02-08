<template>
  <div>
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
        <el-button  @click="toSearch" style="margin-left: 20px;border-radius: 12px;height: 50px;width: 100px;font-size: 15px" type="success" plain>GO</el-button>
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
            <el-tag  round style="margin-left: 5px">{{list_num}}</el-tag>
          </el-menu-item>
          <el-menu-item index="2">
            用户
            <el-tag round style="margin-left: 5px">{{user_num}}</el-tag>
          </el-menu-item>
          <el-menu-item index="3">
            讨论
            <el-tag  round style="margin-left: 5px">{{post_num}}</el-tag>
          </el-menu-item>
          <el-menu-item index="4">
            公告
            <el-tag round style="margin-left: 5px">{{bulletin_num}}</el-tag>
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
            <div v-if="users.length === 0" class="bottom">
              <el-empty description="暂无数据"></el-empty>
            </div>
            <div class="bottom" v-for="user in users" v-else>
              <div class="user_information">
                <el-avatar :size="50" :src=user.avatarUrl />
                <div class="info">
                  <div class="username">
                    <p>{{user.nickName}}</p>
                  </div>
                  <el-tag type="danger" v-if="user.role === 'ROLE_ADMIN'">管理员</el-tag>
                  <el-tag type="primary" v-else>普通用户</el-tag>
                </div>
              </div>
                <el-button type="success" size="small" :icon="UserFilled" class="like">空间</el-button>
            </div>
          </div>
<!--          讨论-->
          <div v-else-if="activeIndex ==='3'">
            <div v-if="posts.length === 0" class="bottom">
              <el-empty description="暂无数据"></el-empty>
            </div>
            <div class="bottom" v-for="post in posts" v-else>
              <div class="user_information">
                <el-avatar :size="50" :src=post.avatarUrl />
                <div class="info">
                  <div class="username">
                    <p>{{post.nickName}}</p>
                  </div>
                  <el-tag type="danger" v-if="post.role === 'ROLE_ADMIN'">管理员</el-tag>
                  <el-tag type="primary" v-else>普通用户</el-tag>
                </div>
              </div>
              <div class="post_title">
                <router-link :to="{path:'/front/post_detail',query: {index: post.id}}" >{{post.title}}</router-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in {{post.area}} @{{post.time}}</p>
              </div>
            </div>
          </div>
<!--          公告-->
          <div v-else-if="activeIndex === '4'">
            <div v-if="bulletins.length === 0" class="bottom">
              <el-empty description="暂无数据"></el-empty>
            </div>
            <div class="bottom" v-for="bulletin in bulletins" v-else>
              <div class="user_information">
                <el-avatar :size="50" :src=bulletin.avatarUrl />
                <div class="info">
                  <div class="username">
                    <p>{{bulletin.nickName}}</p>
                  </div>
                  <el-tag type="danger">管理员</el-tag>
                </div>
              </div>
              <div class="post_title">
                <router-link :to="{path:'/front/bulletin_detail',query: {index: bulletin.id}}" >{{bulletin.title}}</router-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">@{{bulletin.time}}</p>
              </div>
            </div>
          </div>
        </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import { Search,UserFilled } from '@element-plus/icons-vue'
export default {
  name: "search",
  props:[
    'page_search'//搜索页面的搜索内容
  ],
  data() {
    return{
      list:[],//搜索的题目
      users:[],//搜索的用户
      posts:[],//搜索的贴子
      bulletins:[],//搜索的公告
      list_num:0,//搜索题目数量
      user_num:0,//搜索用户数量
      post_num:0,//搜索贴子数量
      bulletin_num:0,//搜索公告数量
      Search,//搜索框的图标
      UserFilled,
      activeIndex:'1',
    }
  },
  created() {
    this.toSearch();
  },
  methods:{
    handSelect(key){
      this.activeIndex = key;
    },
    getLength(value){
      let length = 0;
      for(let i in value){
        length++;
      }
      return length;
    },
    toSearch() {
      if(this.page_search === ''){
        this.list = [];//搜索的题目
        this.users = [];//搜索的用户
        this.posts = [];//搜索的贴子
        this.bulletins = [];//搜索的公告
        this.list_num = 0;//搜索题目数量
        this.user_num = 0;//搜索用户数量
        this.post_num = 0;//搜索贴子数量
        this.bulletin_num = 0;//搜索公告数量
      }
      else {
        //查询题目
        request.get("/search/question",{
          params:{
            name:this.page_search
          }
        }).then(res => {
          this.list = res;
          this.list_num = this.getLength(res);
        })

        //查询用户
        request.get("/search/user",{
          params:{
            name:this.page_search
          }
        }).then(res => {
          this.users = res;
          this.user_num = this.getLength(res);
        })

        //查询贴子
        request.get("/search/forum",{
          params:{
            name:this.page_search
          }
        }).then(res => {
          this.posts = res;
          this.post_num = this.getLength(res);
        })

        //查询公告
        request.get("/search/bulletin",{
          params:{
            name:this.page_search
          }
        }).then(res => {
          this.bulletins = res;
          this.bulletin_num = this.getLength(res);
        })
      }
    }
  },
}
</script>

<style scoped>
  .search
  /deep/
  .el-input__wrapper{
    border-radius: 12px;
    background-color: #f7f8fa;
  }
  .search
  /deep/
  .el-input__prefix-inner {
    margin-left: 8px;
  }
 .search {
   justify-content: center;
   padding-top: 25px;
   display: flex;
   align-items: center;
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
   position: relative;
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
    position: absolute;
    left: 70px;
  }
  .username {
    margin-bottom: 4px;
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
  a {
    text-decoration: none;
  }
  a:link {
    color: #409eff;
  }
  a:visited{
    color: #409eff;
  }
  a:hover {
    color: grey;
  }
</style>
