<template>
  <div class="box" ref="box">
    <div class="left" v-loading="loading">
      <el-scrollbar height="100%">
        <div style="margin: 10px">
        <h1>{{questions.number+'    '+questions.name}}</h1>
        <div class="bar">
          <span style="font-size: 15px;padding-right: 10px;cursor: pointer">难度：<el-tag :type="(questions.difficulty == '入门'?'':(questions.difficulty == '简单'?'warning':(questions.difficulty == '进阶'?'success':(questions.difficulty == '困难'?'danger':'info'))))" effect="dark">{{questions.difficulty}}</el-tag></span>
          <button class="button">
            <svg viewBox="0 0 24 24" width="1em" height="1em" class="css-1lc17o4-icon">
              <path
                  d="M7 19v-8H4v8h3zM7 9c0-.55.22-1.05.58-1.41L14.17 1l1.06 1.05c.27.27.44.65.44 1.06l-.03.32L14.69 8H21c1.1 0 2 .9 2 2v2c0 .26-.05.5-.14.73l-3.02 7.05C19.54 20.5 18.83 21 18 21H4a2 2 0 01-2-2v-8a2 2 0 012-2h3zm2 0v10h9l3-7v-2h-9l1.34-5.34L9 9z">
              </path>
            </svg>
            <span>114514</span>
          </button>
          <button class="button">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1em" height="1em" fill="currentColor" class="css-1rhb60f-Svg ea8ky5j0">
              <path d="M15.392 8.23l5.695.832a.942.942 0 01.521 1.607l-4.12 4.013.972 5.67a.942.942 0 01-1.367.993L12 18.667l-5.093 2.678a.942.942 0 01-1.367-.993l.972-5.67-4.12-4.013a.942.942 0 01.52-1.607l5.696-.833 2.547-5.16a.942.942 0 011.69 0l2.547 5.16zm-1.329 1.826L12 5.876l-2.063 4.18-4.615.675 3.34 3.252-.789 4.594L12 16.407l4.127 2.17-.788-4.594 3.34-3.252-4.616-.675z">
              </path>
            </svg>
            <span>16168</span>
          </button>
          <button class="button">
            <svg viewBox="0 0 24 24" width="1em" height="1em" class="css-1lc17o4-icon">
              <path d="M17 5V2l5 5h-9a2 2 0 00-2 2v8H9V9a4 4 0 014-4h4zm3 14V9h2v10a2 2 0 01-2 2H4a2 2 0 01-2-2V5a2 2 0 012-2h3v2H4v14h16z">
              </path>
            </svg>
            <span>分享</span>
          </button>
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
          <el-button type="primary" size="default">上一题</el-button>
          <el-button type="primary" size="default">下一题</el-button>
      </div>
    </div>
    <div class="resize" @mouseup="changeIframeDivStyle('none')" @mousedown="changeIframeDivStyle('')">
      ⋮
    </div>
    <div class="mid">
<!--      遮罩层-->
      <div  class="iframeDiv"></div>
      <iframe src="http://localhost:8889/lab"  frameborder="0" width="100%" height="100%"> </iframe>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "question_doing",
  data() {
    return {
      loading:'',
      questions:[],
      id:'',
    }
  },
  created() {
    this.getData();
    this.load();
  },
  mounted() {
    this.dragControllerDiv();
    this.changeIframeDivStyle('none');

  },
  methods: {
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
