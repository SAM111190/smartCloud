<template>
  <div style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;">
    <el-card>
        <el-page-header @back="goBack" >
        </el-page-header>
        <div class="title">{{bulletin.title}}</div>
      <div class="aboutTheBulletin">
        <p class="info">最后一次修改时间：{{bulletin.time}}</p>
        <p class="info">发布人：{{bulletin.nickName}}</p>
      </div>
      <div class="bullet_detail">
        <el-divider></el-divider>
        <div class="content" v-html="bulletin.content">
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "bulletin_detail",
  data(){
    return{
      bulletin:{},
      id:this.$route.query.index,
    }
  },
  created() {
    this.getBulletin()
  },
  methods:{
    goBack() {
      this.$router.go(-1);
    },
    getBulletin() {
      request.get("/bulletin/" + this.id,{
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              searchData: this.searchData
            }
      }).then(res=>{
        console.log(res)
        this.bulletin = res.data
      })
    }
  }
}
</script>

<style scoped>
  .title {
    margin-top: 15px;
    font-weight: 550;
    color: #1e90ff;
    font-size: 24px;
    text-align: center;
  }
  .aboutTheBulletin {
    margin-top: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 12px;
    color: #979797;
    font-weight: bold;
    font-family: 微软雅黑;
  }
  .info {
    margin: 0 10px 0 10px;
  }
  .bullet_detail {
    margin-bottom: 20px;
  }
  .content {
    padding: 0 30px 0 30px ;
  }
</style>
