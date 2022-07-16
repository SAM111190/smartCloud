<template>
  <div style="margin: 10px 0px">
    <div v-if="!user.username" class="notice">
      <h1>详细内容请登陆后查看</h1>
    </div>
    <div v-else>
      <el-row :gutter="5">
        <el-col :span="6">
          <el-card class="box-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <span class="title">C++</span>
                <div class="operation button">
                  <el-button type="primary" @click="$router.push('/front/tab/C++');">进入题库</el-button>
                </div>
              </div>
            </template>
            <!--                      数据库更改为{{ '开课时间 ' + key }}  key是自己设置值  授课老师同理-->
            <div class="text item">更新时间：2022/7/7</div>
            <div class="text item">当前题库题目总数：10</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="box-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <span class="title">Java</span>
                <div class="operation button">
                  <el-button type="primary" @click="$router.push('/front/list1');">进入题库</el-button>
                </div>
              </div>
            </template>
            <!--                      数据库更改为{{ '开课时间 ' + key }}  key是自己设置值  授课老师同理-->
            <div class="text item">更新时间：2022/7/7</div>
            <div class="text item">当前题库题目总数：10</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="box-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <span class="title">Python</span>
                <div class="operation button">
                  <el-button type="primary" @click="$router.push('/front/list1');">进入题库</el-button>
                </div>
              </div>
            </template>
            <!--                      数据库更改为{{ '开课时间 ' + key }}  key是自己设置值  授课老师同理-->
            <div class="text item">更新时间：2022/7/7</div>
            <div class="text item">当前题库题目总数：10</div>
          </el-card>
        </el-col>
      </el-row>
      <!--    <el-row :gutter="5">-->
      <!--      <el-col :span="6" v-for="item in files" :key="item.id">-->
      <!--        <el-card class="box-card" shadow="hover">-->
      <!--          <template #header>-->
      <!--            <div class="card-header">-->
      <!--              <span class="title">{{item.name}}}</span>-->
      <!--              <div class="operation button">-->
      <!--                <el-button type="primary">作业详情</el-button>-->
      <!--                <el-button type="danger" @click="$router.push('/front/list1');">进入实验</el-button>-->
      <!--              </div>-->
      <!--            </div>-->
      <!--          </template>-->
      <!--          &lt;!&ndash;                      数据库更改为{{ '开课时间 ' + key }}  key是自己设置值  授课老师同理&ndash;&gt;-->
      <!--          <div class="text item">开课时间：2022/7/7</div>-->
      <!--          <div class="text item">授课老师：luoyongjun</div>-->
      <!--        </el-card>-->
      <!--      </el-col>-->
      <!--    </el-row>-->
    </div>
  </div>

</template>

<script>
import {ArrowLeft} from "@element-plus/icons";
export default {
  name: "Programming",
  data() {
    return {
      search: '',
      ArrowLeft,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  },
  // data(){
  //   return {
  //     user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
  //    files:[]
  //   }
  // },
  // created() {
  //   this.request.get("/echarts/file/front/all").then(rese => {
  //     //把id等号右边的字符串改成你们题目类型（如C++，Java），截止时间，授课老师的id
  //     this.files = res.data.filter(v = > v.id === 'subject' || v.id === 'time' || v.id === 'teacher')
  //   })
  // }
}
</script>

<style scoped>
.back {
  margin-bottom: 10px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.text {
  font-size: 14px;
}
.title {
  font-weight: bold;
}
.item {
  margin-bottom: 10px;
}
.box-card {
  width: 300px;
  margin: 15px 0;
}
.notice{
  text-align: center;
  margin: 35vh auto;
  color:grey;
}
</style>