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
          <el-table
              :data="collectionList"
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
                <el-tag :type="(scope.row.difficulty === '入门'?'':(scope.row.difficulty === '简单'?'warning':(scope.row.difficulty === '进阶'?'success':(scope.row.difficulty === '困难'?'danger':'info'))))" effect="dark">{{scope.row.difficulty}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="题目"/>
            <el-table-column label="操作">
              <template #default="scope">
                <div style="margin-right:100px">
                  <el-button size="small" link><router-link :to="{path:'/front/question',query: {index: scope.row.id}}" >开始做题</router-link></el-button>
                  <el-button size="small" link type="danger" @click="deleteCollectioin(scope.row.id)">取消收藏</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
<!--          <el-empty description="空空如也" :image-size="100" >-->
<!--          </el-empty>-->
        </div>
      </div>
      </div>
  <!--    右边的内容-->
      <div class="right_content">
        <div class="right">
        <h2 style="border-bottom:1px solid #ccc;padding-bottom: 10px">个人资料</h2>
        <div class="description">
          <el-row>
            <el-col :span="8">用户名：{{user.username}}</el-col>
            <el-col :span="8">性别：{{user.sex}}</el-col>
            <el-col :span="8" v-if="user.birthday">生日：{{user.birthday}}</el-col>
            <el-col :span="8" v-else>生日：未知</el-col>
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
    </div>
  </div>
</template>

<script>
import {Tools} from "@element-plus/icons";
import request from "@/utils/request";
export default {
  name: "user",
  data(){
    return {
      collectionList:[],
      activeIndex:'1',
      Tools,
      board:'',
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
    }
  },
  created() {
    this.getCollection();
  },
  methods:{
    handSelect(key){
      this.activeIndex = key;
    },
    getCollection() {
      request.get("/collection/findall/" + this.user.id).then(res => {
        this.collectionList = res.data;
      })
    },
    deleteCollectioin(qid) {
      request.delete("/collection/delete",{
        params:{
          userId:Number(this.user.id),
          questionId:Number(qid)
        }
      }).then(res => {
        if(res){
          this.$message.info("已取消收藏")
          this.getCollection()
        }
        else{
          this.$message.error("取消收藏失败")
        }
      })
    }
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
