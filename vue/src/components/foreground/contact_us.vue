<template>
  <div>
    <el-dialog
        v-model="dialogVisible"
        title="联系我们"
        width="700px"
        center
        top="25vh"
        show-close
    >
      <el-form :model="form" :rules="rules" ref="form">
        <el-form-item label="反馈类型" :label-width="'150px'" prop="type">
          <el-radio-group v-model="form.type">
            <el-radio label="界面UI"/>
            <el-radio label="使用体验"/>
            <el-radio label="Bug反馈"/>
            <el-radio label="其他问题"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="'150px'" prop="number">
          <el-input v-model="form.number" placeholder="请输入您邮箱或者电话" style="width: 80%">
          </el-input>
        </el-form-item>
        <el-form-item label="反馈内容" :label-width="'150px'" prop="content">
          <el-input type="textarea" v-model="form.content" placeholder="请输入您的反馈内容" style="width: 80%;"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submit"
        >提交</el-button
        >
      </span>
      </template>
    </el-dialog>
    <el-dialog
        v-model="dialogVisible1"
        width="700px"
        center
        top="25vh"
    >
      <el-result
          icon="success"
          title="提交成功"
          sub-title="我们会尽快联系您，请注意查看您的邮箱或来电"
      >
        <template #extra>
          <el-button type="primary" @click="dialogVisible1 = false">返回</el-button>
        </template>
      </el-result>
    </el-dialog>
    <div>
      <div class="bar">
        <el-tooltip
            class="box-item"
            effect="light"
            content="联系我们"
            placement="top-end"
        >
          <el-button circle type="primary"
                     style="width: 60px;height: 60px;font-size: 30px"
                     @click="dialogVisible = true"
          ><el-icon><Iphone /></el-icon></el-button>
        </el-tooltip>
      </div>
    </div>
  </div>
</template>

<script>
import {Iphone, RemoveFilled} from "@element-plus/icons";
export default {
  name: "contact_us",
  components: {
    Iphone,
    RemoveFilled,
  },
  data() {
    return {
      select:'',
      feedback:'',
      type:'',
      form:{},
      dialogVisible:false,
      dialogVisible1:false,
      search: '',
      rules: {  //验证规则
        type: [
          {
            required: true,
            message: '请选择您要反馈的类型',
            trigger: 'change',
          },
        ],
        number: [
          { required: true, message: '请输入您的联系方式', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入您宝贵的意见或建议', trigger: 'blur' },
        ],
      },
    }
  },
  created() {
  },
  methods: {
    submit() {
      this.$refs['form'].validate((valid) => {
        if(valid) {   //判断是否满足验证规则，才能进行下面的请求
          this.dialogVisible = false;
          this.dialogVisible1 = true;
          this.form = {};//清空表单
        }
      })
    },
    logout() {
      window.sessionStorage.removeItem('tokenStr')
      window.sessionStorage.removeItem('user')
      this.$router.push("/login")
      this.$message.success("退出成功")
    },
  }
}
</script>
<style scoped>
.bar {
  position: fixed;
  right: 10px;
  bottom: 10px;
  z-index: 2;
}
</style>
