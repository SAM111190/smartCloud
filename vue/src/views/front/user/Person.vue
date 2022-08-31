<template>
  <div style="padding: 15px 0;font-size: large;width: 80%;margin:0 auto;">
    <div style="width: 80%; margin:20px auto;text-align:center;">
      <el-card>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
        >
          <el-menu-item index="1">个人设置</el-menu-item>
          <el-menu-item index="2">账号安全</el-menu-item>
        </el-menu>
      </el-card>
    </div>
    <div style="width: 80%; margin:20px auto;text-align:center">
      <div v-if="activeIndex === '1'">
        <el-card>
          <div class="setting_header">
            个人设置
          </div>
          <el-form ref="form" :model="form" label-width="100px">
            <!--       1
                   class="avatar-uploader"-->
            <!--              action="https://localhost:9090/file/upload"-->
            <!--              :show-file-list="false"-->
            <!--              :on-success="handleAvatarSuccess"-->
            <!--          >-->
            <!--            <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">-->
            <!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
            <!--          </el-upload>-->
            <!--        </div>-->
            <el-form-item label="头像">
              <el-avatar :size="100" v-if="form.avatarUrl" :src="form.avatarUrl" />
            </el-form-item>
            <div style="text-align: left;margin: 20px auto">
              <el-upload
                  class="upload-demo"
                  action="http://localhost:9091/file/upload"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
              >
                <el-button type="primary" style="position: relative;left: 115px;">上传头像</el-button>
              </el-upload>
            </div>
            <el-form-item label="昵称">
              <el-input v-model="form.nickName" style="width: 15%"></el-input>
              <span style="margin-left: 10px;color: grey">注：请不要使用具有人身攻击性的昵称</span>
            </el-form-item>
            <el-form-item label="用户名">
              <el-input v-model="form.username" style="width: 15%" disabled></el-input>
            </el-form-item>
            <el-form-item label="生日">
              <el-date-picker
                  v-model="form.birthday"
                  type="date"
                  :size="'default'"
                  format="YYYY/MM/DD"
                  value-format="YYYY-MM-DD"
              />
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="form.sex">
                <el-radio label="男"/>
                <el-radio label="女"/>
                <el-radio label="保密"/>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.address" style="width: 50%" disabled></el-input>
            </el-form-item>
            <el-form-item label="手机号码">
              <el-input v-model="form.telephone" style="width: 50%" disabled></el-input>
            </el-form-item>
            <el-form-item label="个人介绍">
              <el-input v-model="form.introduce" style="width: 50%" type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password" style="width: 50%" show-password disabled></el-input>
            </el-form-item>
          </el-form>
          <div style="text-align: center">
            <el-button type="primary" @click="save">保存</el-button>
            <el-button type="info" @click="$router.push('/front/user')">返回</el-button>
          </div>
        </el-card>
      </div>
      <div v-else>
        <el-card>
          <div class="setting_header">
            账号安全
          </div>
          <el-form ref="form" :model="form2" label-width="100px">
            <el-form-item label="安全邮箱">
              <el-input v-model="form2.address" style="width: 50%" disabled></el-input>
              <el-button type="danger" style="margin-left: 10px">修改邮箱</el-button>
            </el-form-item>
            <el-form-item label="手机号码">
              <el-input v-model="form2.telephone" style="width: 50%" disabled></el-input>
              <el-button type="danger" style="margin-left: 10px">修改手机号码</el-button>
            </el-form-item>
            <el-form-item label="密码">
              <el-button type="danger" @click="add" >修改密码</el-button>
            </el-form-item>
          </el-form>
          <div style="text-align: center">
            <el-button type="primary" @click="save">保存</el-button>
            <el-button type="info" @click="$router.push('/front/user')">返回</el-button>
          </div>
          <el-dialog
              v-model="dialogVisible"
              title="修改密码"
              width="30%"
              :rules="rules"
          >
            <el-form label-width="60px" size="small" :model="form3" :rules="rules" ref="pass">

              <el-form-item label="原密码"  prop="oldPassword">
                <el-input v-model="form3.oldPassword" autocomplete="off" show-password></el-input>
              </el-form-item>
              <el-form-item label="新密码" prop="newPassword">
                <el-input v-model="form3.newPassword" autocomplete="off" show-password></el-input>
              </el-form-item>
              <el-form-item label="确认新密码" prop="confirmPassword">
                <el-input v-model="form3.confirmPassword" autocomplete="off" show-password></el-input>
              </el-form-item>
            </el-form>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="savePassword">确认</el-button
        >
      </span>
            </template>
          </el-dialog>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: "Person",
  inject:['reload'],
  data() {
    return {
      form3:{},
      form2:{},
      form: {},
      dialogVisible:false,
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
      activeIndex:'1',
      rules:{
        oldPassword: [
          { required: true, message: '请输入旧密码', trigger: 'blur' },
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度应该在6到20个字符', trigger: 'blur' },
        ],
        confirmPassword: [
          { required: true, message: '请确认新密码', trigger: 'blur' },
        ],
      },
    }
  },
  created() {
    this.getUser().then(res => {
      console.log(res)
      this.form = res

    })
  },
  methods: {
    async getUser() {
      return (await request.get("/user/username/" + this.user.username)).data
    },
    add(){//修改密码弹窗显示
      this.dialogVisible =true
      this.getUser().then(res => {
        this.form3 =res
      })
    },
    save() {
      request.post("/user", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")

          // 触发父级更新User的方法
          this.$emit("refreshUser")

          // 更新浏览器存储的用户信息
          this.getUser().then(res => {
            res.token = JSON.parse(sessionStorage.getItem("user")).token
            sessionStorage.setItem("user", JSON.stringify(res))
          })
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    savePassword() {
      this.$refs.pass.validate((valid) => {
        if (valid) {
          if (this.form3.newPassword !== this.form3.confirmPassword) {
            this.$message.error("2次输入的新密码不相同")
            return false
          }
          request.post("/user/cpassword", this.form3).then(res => {
            if (res.code === '200') {
              this.$message.success("修改成功")
              this.$router.push("/login")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },

    handleSelect(key){
      this.activeIndex = key;
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res;
    }
  },
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.setting_header {
  font-size: larger;
  text-align: left;
  border-bottom: #cccccc 1px solid;
  margin-bottom: 20px;
  padding-bottom: 15px ;
}
</style>
