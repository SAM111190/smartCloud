<template>
  <div style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;">
    <div class="top">
      <div class="top_header">
        <div class="user_information">
      <el-avatar :size="50" v-if="user.avatarUrl" :src="user.avatarUrl" />
      <div class="info">
<!--        有昵称则显示昵称，没有则显示用户名-->
      <div class="username">
        <p v-if="user.nickName">
          {{ user.nickName }}
        </p>
        <p v-else>
          {{user.username}}
        </p>
      </div>
      <div class="identify">
        <el-tag type="danger" v-if="user.role === 'ROLE_ADMIN'">管理员</el-tag>
        <el-tag type="primary" v-else>普通用户</el-tag>
      </div>
      </div>
      </div>
        <div class="button">
          <el-button type="info" @click="$router.push('/front/person')" style="background-color:transparent;backdrop-filter: blur(10px)" :icon="Tools">个人设置</el-button>
        </div>
      </div>
      <div class="top_menu">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handSelect"
        >
          <el-menu-item index="1">主页</el-menu-item>
          <el-menu-item index="2">讨论</el-menu-item>
          <el-menu-item index="3">收藏</el-menu-item>
        </el-menu>
      </div>
    </div>
    <div class="bottom">
<!--      个人主页-->
      <div v-if="activeIndex === '1'">
<!--    左边的内容-->
      <div class="left_content">
        <div class="left">
          <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">个人介绍</h2>
          <div class="description">
            <p v-if="user.introduce">
              {{user.introduce}}
            </p>
            <p v-else>
              这个家伙很懒，什么都没有留下。
            </p>
          </div>
        </div>
        <div class="left">
        <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">题目收藏</h2>
        <div class="description">
          <el-empty description="空空如也" :image-size="100" >
          </el-empty>
        </div>
      </div>
      </div>
  <!--    右边的内容-->
      <div class="right_content">
        <div class="right">
        <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">个人资料</h2>
        <div class="description">
          <el-row>
            <el-col :span="12">性别：{{user.sex}}</el-col>
            <el-col :span="12" v-if="user.birthday">生日：{{user.birthday}}</el-col>
            <el-col :span="12" v-else>生日：未知</el-col>
          </el-row>
        </div>
        </div>
        <div class="right">
          <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">空间公告</h2>
          <div class="description">
            <el-input type="textarea" v-model="board" placeholder="编辑我的空间公告" style="width: 100%;"/>
          </div>
        </div>
      </div>
        </div>
      <div v-else-if="activeIndex === '2'">
        <div class="bottom_content">
          <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">讨论</h2>
          <div class="description">
            <el-empty description="空空如也" :image-size="100" >
            </el-empty>
          </div>
        </div>
      </div>
      <div v-else-if="activeIndex === '3'">
        <div class="bottom_content">
          <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">收藏</h2>
          <div class="description">
            <el-empty description="空空如也" :image-size="100" >
            </el-empty>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Tools} from "@element-plus/icons";
export default {
  name: "user",
  data(){
    return {
      activeIndex:'1',
      Tools,
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
  .description{
    margin-top: 15px;
    font-size: 10px;
    color:grey;
  }
  .top {
    border-radius: 5px;
    box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
  }
  .top_header {
    background-image: url("https://img.win3000.com/m00/c9/51/72eb0f1bdf16989f2c2970099f46f450.jpg");
    background-size: cover;
    background-position: center;
    height: 200px;
  }
  .user_information {
    position: relative;
    top:calc(200px - 65px);
    left: 15px;
    display: inline-block;
  }
  .button {
    text-align: right;
    padding-right: 15px;
    position: relative;
    top:calc(200px - 100px);
  }
 .info {
   display: inline-block;
   position: relative;
   left: 10px;
   bottom: 5px;
   color: white;
 }
 .username {
   margin-bottom: 4px;
 }
 .identify {
 }
 .left_content {
   float: left;
   width: 67%;
 }
 .left {
   margin-top: 10px;
   border-radius: 5px;
   box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
   padding:15px
 }
 .left p{
   font-size: 16px;
 }
 .right_content{
   float: right;
   width: 32%;
 }
 .right {
   margin-top: 10px;
   border-radius: 5px;
   box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
   padding:15px
 }
 .bottom_content {
   margin-top: 10px;
   border-radius: 5px;
   box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
   padding:15px
 }
</style>
