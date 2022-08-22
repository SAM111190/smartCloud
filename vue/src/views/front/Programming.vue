<template>
  <tab></tab>
  <div style="width:90%;margin: 15px auto;">
    <div v-if="!user.username" class="notice">
      <h1>详细内容请登陆后查看</h1>
    </div>
    <div v-else>

      <el-row :gutter="5">
        <el-col :span="6" v-for="course in courses" >
          <!--              :key="course.id"-->
          <el-card class="box-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <span class="title">{{course.name}}</span>
                <div class="operation button">
                  <el-button type="primary" @click="$router.push('/front/C++');">进入题库</el-button>
                </div>
              </div>
            </template>
            <div class="text item">开课时间： {{course.time}}</div>
            <div class="text item">当前题库题目总数：{{course.total}}</div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>

</template>

<script>
import {ArrowLeft} from "@element-plus/icons";
import request from "../../utils/request";
import tab from "@/components/foreground/tab"
export default {
  name: "Programming",
  props: ['course'],
  components:{
    tab,
  },
  data() {
    return {
      courses: [],
      search: '',
      total: null,
      ArrowLeft,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    //判断登录
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    request.get("/course", {
      params:
          {
            search: this.search
          }
    }).then(res => {
      console.log(res)
      this.courses = res.data.records

      // request.get("/question",{}).then(res => {
      //   console.log(res)
      // this.total = res.data.records.length
    })
  }
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
