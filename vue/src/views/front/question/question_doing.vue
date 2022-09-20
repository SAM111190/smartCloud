<template>
  <div class="box" ref="box">
    <div class="left" v-loading="loading">
      <el-scrollbar height="100%">
        <div style="margin: 10px">
        <h1>{{questions.number+'    '+questions.name}}</h1>
        <div class="bar">
          难度：<el-tag :type="(questions.difficulty == '入门'?'':(questions.difficulty == '简单'?'warning':(questions.difficulty == '进阶'?'success':(questions.difficulty == '困难'?'danger':'info'))))" effect="dark">{{questions.difficulty}}</el-tag>
            <el-rate @click="save" v-model="value" :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                     :max="4"
                     show-text
                     :texts="['入门', '简单', '进阶', '困难']"
                     style="position: relative;left: 10px;top: 5px"
            />

        </div>
        <el-divider />
        <div class="content">
          <h2>题目描述</h2>
          <div class="description">
            <p>
              {{questions.details}}
            </p>
          </div>
          <h2>输入格式</h2>
          <div class="description">
            <p>
              {{questions.input}}
            </p>
          </div>
          <h2>输出格式</h2>
          <div class="description">
            <p>
              {{questions.output }}
            </p>
          </div>
          <h2>输入输出样例</h2>
          <div class="input_output">
            输入：<pre> {{questions.einput}}</pre>
            输出：<pre> {{questions.eoutput}}</pre>
          </div>
          <h2>说明/格式</h2>
          <div class="description">
            <p>
              【样例解释】
            </p>
          </div>
          <div class="description">
            <p>
              {{questions.tips}}
            </p>
          </div>
          <img  :src="questions.src">
        </div>
        </div>
      </el-scrollbar>
      <div class="bottom_bar">
        <el-button type="info" size="default" @click="back">返回</el-button>
          <el-button type="primary" size="default" @click="drawer = true">查看题解</el-button>
          <el-button type="primary" size="default" @click="submitCoding = true">提交代码</el-button>
      </div>
    </div>
    <div class="resize" @mouseup="changeIframeDivStyle('none')" @mousedown="changeIframeDivStyle('')">
      ⋮
    </div>
    <div class="mid">
<!--      遮罩层-->
      <div  class="iframeDiv"></div>
      <iframe src="http://localhost:8888/lab"  frameborder="0" width="100%" height="100%"> </iframe>
    </div>
    <!--  题解-->
    <el-drawer v-model="drawer" direction="ltr" title="题解">
      <div class="answer">
        这里放题解
      </div>
    </el-drawer>
<!--    提交-->
    <el-dialog v-model="submitCoding" title="提交代码">
      <el-form
          ref="form"
          :model="form"
          label-width="10px"
          style="margin-top: 25px"
          :rules="rules"
      >
        <el-form-item prop="content">
          <el-input v-model="form.content" type="textarea" placeholder="在这里输入你的代码" :rows="15" maxlength="1000" show-word-limit></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="submit">提交</el-button>
        <el-button type="info" @click="submitCoding = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "question_doing",
  data() {
    return {
      submitCoding:false,
      form:{},
      loading:'',
      questions:[],
      id:'',
      drawer:false,
      form:{},
      rules:{
        content: [
          { required: true, message: '请输入代码', trigger: 'blur' },
          { max: 1000, message: '长度应该在1000之内', trigger: 'blur' },
        ],
      },
      value:0,
      user: sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {},
    }
  },
  created() {
    this.getData();
    this.load();
    this.getUser().then(res => {
      console.log(res)
      this.questions.userId=res.id
    })
  },
  mounted() {
    this.dragControllerDiv();
    this.changeIframeDivStyle('none');
  },
  methods: {
    submit() {
      this.$refs['form'].validate((valid) => {
        if(valid) {   //判断是否满足验证规则，才能进行下面的请求
        }
      })
    },
    async getUser() {
      return (await request.get("/user/username/" + this.user.username)).data
    },
    save(){
      // this.getUser().then(res => {
      //   console.log(res)
      //   this.questions.userId=res.id
      // })
      this.qid=this.$route.query.index
      this.questions.rate=this.value
      this.questions.questionId=this.qid
      request.get("/questionrate/"+this.questions.questionId+"/"+this.questions.userId).then(res => {
        if(res.data!='') {
          res.data.rate=this.value
          request.post("/questionrate/update",this.questions).then(res => {
            if(res) {
              this.$message.success("评分成功")
            }else
            {
              this.$message.error("发布失败")
            }
          })
        }
        else
        {
          request.post("/questionrate/insert",this.questions).then(res => {
            if(res) {
              this.$message.success("评分成功")
            }else
            {
              this.$message.error("发布失败")
            }
          })
        }
      })
    },
    load(){
      this.qid=this.$route.query.index
      //this.qid=this.$route.query.id
      request.get("/question/"+this.qid,{
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              searchData: this.searchData
            }
      }).then(res=>{
        console.log(res)
        this.questions = res.data
      })
    },
    changeIframeDivStyle(display) {
      var iframeDiv = document.getElementsByClassName('iframeDiv');
      iframeDiv[0].style.display = display;
    },
    getData(){
      this.loading = true;
      setTimeout(() =>{
        this.loading = false},500)
    },
    back(){
      this.$router.go(-1)
    },
    dragControllerDiv () {
      var resize = document.getElementsByClassName('resize');
      var left = document.getElementsByClassName('left');
      var mid = document.getElementsByClassName('mid');
      var box = document.getElementsByClassName('box');
      for (let i = 0; i < resize.length; i++) {
        // 鼠标按下事件
        resize[i].onmousedown = function (e) {
          //颜色改变提醒
          resize[i].style.background = '#818181';
          var startX = e.clientX;//鼠标水平坐标
          resize[i].left = resize[i].offsetLeft;
          // 鼠标拖动事件
          document.onmousemove = function (e) {
            var endX = e.clientX;
            var moveLen = resize[i].left + (endX - startX); // （endx-startx）=移动的距离。resize[i].left+移动的距离=左边区域最后的宽度
            var maxT = box[i].clientWidth - resize[i].offsetWidth; // 容器宽度 - 左边区域的宽度 = 右边区域的宽度

            if (moveLen < 300) moveLen = 300; // 左边区域的最小宽度为300px
            if (moveLen > maxT - 150) moveLen = maxT - 150; //右边区域最小宽度为150px

            resize[i].style.left = moveLen; // 设置左侧区域的宽度

            for (let j = 0; j < left.length; j++) {
              left[j].style.width = moveLen + 'px';
              mid[j].style.width = (box[i].clientWidth - moveLen - 11) + 'px';
            }
          };
          // 鼠标松开事件
          document.onmouseup = function (evt) {
            //颜色恢复
            resize[i].style.background = '#d6d6d6';
            document.onmousemove = null;
            document.onmouseup = null;
            resize[i].releaseCapture && resize[i].releaseCapture(); //当你不在需要继续获得鼠标消息就要应该调用ReleaseCapture()释放掉
          };
          resize[i].setCapture && resize[i].setCapture(); //该函数在属于当前线程的指定窗口里设置鼠标捕获
          return false;
        };
      }
    },
  }
}
</script>

<style scoped>
  .bottom_bar {
    height: 20px;
    border-top: 1px solid #ccc;
    padding: 5px 5px;
  }
  .bar {
    margin-top: 10px;
  }
  .left h1 {
    font-size: 18px;
  }
  .button {
    outline: 0px;
    border: 0px;
    background: transparent;
    margin-right: 6px;
    -webkit-box-align: center;
    align-items: center;
    padding: 0px 10px;
    font-size: 15px;
    cursor: pointer;
  }
  svg {
    position: relative;
    top:2px;
  }
  .content {
  }
  .description{
    margin-top: 10px;
  }
  .input_output {
    margin-top: 10px;
  }
  .content h2 {
    font-size: 1.25em;
    margin-top: 10px;
  }
  .content p {
    font-size: 0.9em;
  }
  pre {
    margin: .5em 0;
    padding: .3em .5em;
    border: #ddd solid 1px;
    background: #f8f8f8;
    border-radius: 3px;
    overflow: auto;
    font-size: .875em;
    font-family: monospace;
  }
  /* 拖拽相关样式 */
  /*包围div样式*/
  .box {
    width: 100vw;
    height: calc(100vh - 60px);
    margin: 0px;
    overflow: hidden;
    box-shadow: -1px 9px 10px 3px rgba(0, 0, 0, 0.11);
  }
  /*左侧div样式*/
  .left {
    width: calc(50vw - 10px);  /*左侧初始化宽度*/
    height: calc(100vh - 120px);
    background: #FFFFFF;
    float: left;
    padding-right: 0;
    padding-left: 10px;
    margin-top: 10px;
  }
  /*拖拽区div样式*/
  .resize {
    cursor: col-resize;
    float: left;
    position: relative;
    top: 45%;
    background-color: #d6d6d6;
    border-radius: 5px;
    margin-top: -10px;
    width: 10px;
    height: 50px;
    background-size: cover;
    background-position: center;
    z-index: 2;
    font-size: 32px;
    color: white;
  }
  /*拖拽区鼠标悬停样式*/
  .resize:hover {
    color: #444444;
  }
  /*右侧div'样式*/
  .mid {
    float: right;
    width: 50vw;   /*右侧初始化宽度*/
    height: calc(100vh - 60px);
    background: #fff;
    box-shadow: -1px 4px 5px 3px rgba(0, 0, 0, 0.11);
  }
  .iframeDiv {
    width: 100%;
    height: 100%;
    position: fixed;
    z-index: 1111;
    filter: alpha(opacity=0);
    opacity: 0;
    background: transparent;
    margin-top: 30px;
    /*display: none;*/
  }
</style>
