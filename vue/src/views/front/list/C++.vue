<template>
<!--  返回组件-->
  <tab></tab>
  <div style="padding: 10px 0;font-size: large;width: 80%;margin:0 auto;">
    <div class="operation">
      <el-card shadow="always">
          <p style="display: inline-block;line-height:10px;margin-right: 10px">查找题目</p>
          <el-input v-model="searchData" placeholder="请输入题目序号" style="width: 85%;margin-right: 10px"/>
        <el-button :icon="Search" type="primary" size="normal" @click="search"/>
      </el-card>
    </div>
    <div class="list">
      <el-card shadow="always">
      <el-table
          :data="list"
          stripe
          style="font-size: 12px"
          ref="filterTable"
          v-loading="loading"
      >
        <el-table-column prop="submitStatus" label="状态" width="100px"/>
        <el-table-column prop="number" label="序号" width="100px"/>
        <el-table-column prop="difficulty" label="难度" column-key="difficulty" width="100px"
                         :filters="[
                          { text: '入门', value: '入门' },
                          { text: '简单', value: '简单' },
                          { text: '进阶', value: '进阶' },
                          { text: '困难', value: '困难' },
                          ]"
                         :filter-method="filterHandler"
        >
          <template #default="scope">
            <el-tag :type="(scope.row.difficulty == '入门'?'primary':(scope.row.difficulty == '简单'?'warning':(scope.row.difficulty == '进阶'?'success':(scope.row.difficulty == '困难'?'danger':'info'))))" effect="dark">{{scope.row.difficulty}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="题目" width="200px"/>
        <el-table-column prop="passrate" label="通过率" sortable width="250px">
          <template #default="scope">
            <el-progress :percentage="scope.row.passrate" :text-inside="true" :stroke-width="15"/>
          </template>
        </el-table-column>
        <el-table-column label="">
          <template #default="scope">
            <div style="text-align: right;margin-right:100px">
            <el-button size="small" type="text" @click="$router.push('/front/question')"
            >开始做题</el-button
            >
            </div>
          </template>
        </el-table-column>
      </el-table>
      </el-card>
    </div>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
                   :page-sizes="[1, 2,5, 10]" :page-size="limit" layout="total, sizes, prev, pager, next, jumper"
                   :total="total" style="margin-top: 10px"/>
  </div>
</template>

<script>
import { h } from 'vue'
import { ElMessage,ElLoading } from 'element-plus'
import {ArrowLeft,Search} from "@element-plus/icons";
import request from "@/utils/request";
import tab from "@/components/foreground/tab"
export default {
  name: "C++",
  components:{
    tab,
  },
  data() {
    return {
      h,
      loading:"",
      difficulty: '全部',
      select_status: '全部',
      searchData: '',//这是搜索框的内容
      Search,//这是图标
      ArrowLeft,
      ElMessage,
      ElLoading,
      limit: 6,
      total: null,
      page: 1,
      list: [],
      data: [],
    }
  },
  created() {
    this.pageList();
    this.getData();
  },
  methods: {
    getData(){
      this.loading = true;
      setTimeout(() =>{
        this.loading = false},500)
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    pageList() {
      request.get("/question",{
        params:
            {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
              searchData: this.searchData
            }
      }).then(res=>{
        console.log(res)
        this.data = res.data.records
        this.getList()
      })
    },
    // 处理数据
    getList() {
      // es6过滤得到满足搜索条件的展示数据list
      let list = this.data.filter((item, index) =>
          item.number.includes(this.searchData)
      )
      this.list = list.filter((item, index) =>
          index < this.page * this.limit && index >= this.limit * (this.page - 1)
      )
      this.total = list.length
    },
    // 当每页数量改变
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.limit = val
      this.getList()
    },
    // 当当前页改变
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.page = val
      this.getList()
    },
    // 搜索过滤数据
    search() {
      this.page = 1
      this.getList()
    }
  },
}
</script>

<style scoped>
.operation{
  margin:10px auto;
}
.list {
  text-align: center;
}
</style>
