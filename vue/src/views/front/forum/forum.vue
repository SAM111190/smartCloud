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
          <div v-for="form in forms">
<!--          灌水区域-->
          <div  v-if=" activeIndex === '1' && form.area === '灌水区域'">
          <div class="left">
            <div class="user_information">
              <el-avatar :size="50" :src="form.avatarUrl" />
              <div class="info">
                <div class="username">
                  <p>{{form.nickName}}</p>
                </div>
                <el-tag v-if="form.role === 'ROLE_USER'">普通用户</el-tag>
                <el-tag v-if="form.role === 'ROLE_ADMIN'" type="danger">管理员</el-tag>
              </div>
            </div>
            <div class="post_title">
              <el-link  type="primary" style="font-size: 18px" :underline="false">{{form.title}}</el-link>
              <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in {{form.area}} @{{form.time}}</p>
            </div>
          </div>
        </div>
<!--          反馈区域-->
          <div v-if=" activeIndex === '2' && form.area === '反馈区域'">
            <div class="left">
              <div class="user_information">
                <el-avatar :size="50" :src="form.avatarUrl" />
                <div class="info">
                  <div class="username">
                    <p>{{form.nickName}}</p>
                  </div>
                  <el-tag v-if="form.role === 'ROLE_USER'">普通用户</el-tag>
                  <el-tag v-if="form.role === 'ROLE_ADMIN'" type="danger">管理员</el-tag>
                </div>
              </div>
              <div class="post_title">
                <el-link  type="primary" style="font-size: 18px" :underline="false">{{form.title}}</el-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in {{form.area}} @{{form.time}}</p>
              </div>
            </div>
          </div>
<!--          问题求助-->
          <div v-if=" activeIndex === '3' && form.area === '问题求助'">
            <div class="left">
              <div class="user_information">
                <el-avatar :size="50" :src="form.avatarUrl" />
                <div class="info">
                  <div class="username">
                    <p>{{form.nickName}}</p>
                  </div>
                  <el-tag v-if="form.role === 'ROLE_USER'">普通用户</el-tag>
                  <el-tag v-if="form.role === 'ROLE_ADMIN'" type="danger">管理员</el-tag>
                </div>
              </div>
              <div class="post_title">
                <el-link type="primary" style="font-size: 18px" :underline="false">{{form.title}}</el-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in {{form.area}} @{{form.time}}</p>
              </div>
            </div>
          </div>
<!--          其他区域-->
          <div v-if=" activeIndex === '4' && form.area === '其他区域'">
            <div class="left">
              <div class="user_information">
                <el-avatar :size="50" :src="form.avatarUrl" />
                <div class="info">
                  <div class="username">
                    <p>{{form.nickName}}</p>
                  </div>
                  <el-tag v-if="form.role === 'ROLE_USER'">普通用户</el-tag>
                  <el-tag v-if="form.role === 'ROLE_ADMIN'" type="danger">管理员</el-tag>
                </div>
              </div>
              <div class="post_title">
                <el-link  type="primary" style="font-size: 18px" :underline="false">{{form.title}}</el-link>
                <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">in {{form.area}} @{{form.time}}</p>
              </div>
            </div>
            </div>
          </div>
          </div>
        <!--    右边的内容-->
        <div class="right_content">
          <div class="right">
              <el-row>
                <el-col :span="12"><el-button type="primary" style="width: 95%" size="large" :icon="Edit" @click="$router.push('/front/post')">发布帖子</el-button></el-col>
                <el-col :span="12"><el-button type="primary" style="width: 95%" size="large" :icon="Document">我的帖子</el-button></el-col>
              </el-row>
          </div>
          <div class="hot_board">
            <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px;">热门讨论</h2>
            <div class="description">
              <el-table :data="tableData" style="width: 100%;cursor: pointer" :show-header="false">
                <el-table-column prop="title"  />
              </el-table>
            </div>
          </div>
        </div>
        </div>
  </div>
</template>

<script>
import {Tools,Edit,Document} from "@element-plus/icons";
import request from "@/utils/request";
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
      form:{},
      forms: [],
      tableData: [],
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.get("/forum",{
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              searchData: this.searchData
            }
      }).then(res=>{
        console.log(res)
        this.forms = res.data
      })
      request.get("/forum/page1", {
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              search: this.search,
              nickName: this.nickName
            }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
      })
    },
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
    width: 100px;
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
  .username p {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
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
