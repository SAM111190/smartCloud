<template>
  <div style="width: 65%; margin:20px auto;text-align:center;">
   <el-card class="box-card" >
    <div slot="header" class="clearfix" style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;text-align:center;">
      <span>讨论区</span>
    </div>
    <div  class="text item" style="width: 80%; margin:20px auto;">
        <div style="margin-top: 15px;">
          <el-input placeholder="请输入标题" v-model="input4" class="input-with-select">
            <el-select v-model="select" slot="prepend" placeholder="请选择要发表的区域">
              <el-option label="灌水区域" value="1"></el-option>
              <el-option label="反馈区域" value="2"></el-option>
              <el-option label="问题求助" value="3"></el-option>
              <el-option label="其他区域" value="4"></el-option>
            </el-select>
          </el-input>
        </div>
      <div style="margin: 20px 0;"></div>
      <el-input
          type="textarea"
          placeholder="请输入内容"
          v-model="textarea"
          maxlength="100"
          show-word-limit
      >
      </el-input>
    </div>
     <el-button type="primary" @click="open">发布</el-button>
  </el-card>
  </div>
</template>

<script>
export default {
  name: "post",
  data() {
    return {
      text: '',
      textarea: '',
      input1: '',
      input2: '',
      input3: '',
      input4: '',
      select: ''
    }
  },
  methods: {
    open() {
      this.$alert('发布成功', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    }
  }

  }

</script>

<style scoped>
.text {
  font-size: 20px;
  alignment: center;
}

.item {
  margin-bottom: 100px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 1000px;
  text-align: center;
}
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

</style>