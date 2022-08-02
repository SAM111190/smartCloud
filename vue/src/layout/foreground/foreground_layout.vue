<template>
<!--  前台头部导航栏和右底部联系我们-->
  <div>
    <front_header :user="user"></front_header>
  </div>
  <div>
    <contact_us></contact_us>
  </div>
  <div>
    <router-view @refreshUser="getUser"></router-view>
  </div>
</template>

<script>
import Front_header from "@/components/foreground/Front_header";
import contact_us from "@/components/foreground/contact_us";
import request from "@/utils/request";
export default {
  name: "foreground_layout",
  components:{
    Front_header,
    contact_us,
  },
  data(){
    return{
      user:{},
    }
  },
  created() {
    this.getUser()
  },
  methods:{
    getUser() {
      let userJson = sessionStorage.getItem("user");
      if(!userJson) {
        return
      }
      let userId = JSON.parse(userJson).id
      request.get("/user/" + userId).then(res => {
        this.user = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>
