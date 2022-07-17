<template>
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
      >
        <el-table-column prop="submit_status" label="状态" width="100px"/>
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
            <el-button size="small" type="text"
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
import { ElMessage } from 'element-plus'
import {ArrowLeft,Search} from "@element-plus/icons";
var listJson = {
      list: [
        {
          submit_status:'未通过',
          number: 'P1001',
          name: 'Hello World',
          passrate:100,
          difficulty:'入门',
        },
        {
          submit_status:'未通过',
          number: 'P1002',
          name: '指针',
          passrate:98.7,
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: 'P1003',
          name: '过河卒',
          passrate:90,
          difficulty:'进阶',
        },
        {
          submit_status:'未通过',
          number: 'P1004',
          name: '独木桥',
          passrate:49.7,
          difficulty:'进阶',
        },
        {
          submit_status:'未通过',
          number: 'P1005',
          name: '地球人口承受估计',
          passrate:20.7,
          difficulty:'困难',
        },
        {
          submit_status:'未通过',
          number: 'P1006',
          name: '排队接水',
          passrate:50.8,
          difficulty:'简单',
        },
      ]
    }
export default {
  name: "C++",
  data() {
    return {
      h,
      difficulty: '全部',
      select_status: '全部',
      searchData: '',//这是搜索框的内容
      Search,//这是图标
      ArrowLeft,
      ElMessage,
      limit: 15,
      total: null,
      page: 1,
      list: [],
      data: [],
    }
  },
  created() {
    this.pageList()
  },
  methods: {
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    pageList() {
      // 发请求拿到数据并暂存全部数据,方便之后操作
      this.data = listJson.list
      this.getList()
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