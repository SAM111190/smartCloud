<template>
  <div class="back">
    <el-page-header :icon="ArrowLeft" @click="back"/>
  </div>
  <div>
    <router-view/>
  </div>
</template>

<script>
import {ArrowLeft} from "@element-plus/icons";
export default {
  name: "tab",
  data() {
    return {
      ArrowLeft,
    }
  },
  methods: {
    back(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
 .back{
   width:90%;
   margin: 15px auto;
 }
</style>
