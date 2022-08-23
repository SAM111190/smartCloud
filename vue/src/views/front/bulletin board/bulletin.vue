<template>
  <div style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;">
    <div class="top">
      <div class="top_header">
        <div class="title">
          <h1>公告栏</h1>
        </div>
      </div>
    </div>
    <div class="bottom" >
        <!--    左边的内容-->
        <div class="left_content">
          <div class="left">
            <el-timeline  >
              <el-timeline-item  v-for="form in forms" :timestamp="form.time" placement="top">
                  <h4>{{form.title}}</h4>
                  <p>管理员提交于{{form.time}}</p>
              </el-timeline-item>
            </el-timeline>
          </div>
        </div>
      <!--    右边的内容-->
      <div class="right_content" v-for="form in forms">
        <div class="right">
          <div class="user_information">
            <el-avatar :size="50" :src="form.avatarUrl" />
            <div class="info">
              <div class="username">
                <p>{{form.username}}</p>
              </div>
              <el-tag type="danger">管理员</el-tag>
            </div>
          </div>
          <div class="bulletin_title">
            <el-link :href="form.href" type="primary" style="font-size: 18px" :underline="false">【{{form.time}}】{{form.title}}</el-link>
            <p style="text-align: center;color: grey;font-size: 12px;padding-top: 5px">@{{form.time}}</p>
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
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.get("/bulletin",{
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
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Fdrawyoo%2F8e5ef0ab0876608771d4b42829eb61010d116a26.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1661841771&t=a1c01d878da9484e1ec31a23c2e2dc80");
  background-size: cover;
  background-position: bottom;
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
  width: 25%;
}
.left {
  margin-top: 10px;
  border-radius: 5px;
  box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
  padding:15px
}
.left h4 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.right_content{
  float: right;
  width: 74%;
}
.right {
  margin-top: 10px;
  border-radius: 5px;
  box-shadow: -1px 0px 10px 3px rgba(0, 0, 0, 0.11);
  padding:15px
}
.bulletin_title {
  display: inline-block;
  width: 80%;
  text-align: center;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
