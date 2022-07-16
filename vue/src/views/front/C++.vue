<template>
  <div style="padding: 10px 0;font-size: large;width: 80%;margin:0 auto;">
    <div class="operation">
      <el-card shadow="always">
          <p style="display: inline-block;line-height:10px;margin-right: 10px">查找题目</p>
          <el-input v-model="search" placeholder="请输入题目序号、题目名词" style="width: 85%;margin-right: 10px"/>
        <el-button :icon="Search" type="primary" size="normal"/>
        <div class="checkbox">
          <p style="display: inline-block;font-size: small">难度：</p>
          <el-radio-group v-model="difficulty">
            <el-radio-button label="全部" />
            <el-radio-button label="入门" />
            <el-radio-button label="简单" />
            <el-radio-button label="进阶" />
            <el-radio-button label="困难" />
          </el-radio-group>
        </div>
        <div class="checkbox">
          <p style="display: inline-block;font-size: small">状态：</p>
          <el-radio-group v-model="select_status" fill="grey">
            <el-radio-button label="全部" />
            <el-radio-button label="未通过" />
            <el-radio-button label="已通过" />
          </el-radio-group>
        </div>
      </el-card>
    </div>
    <div class="list">
      <el-card shadow="always">
      <el-table
          :data="tableData"
          stripe
          style="font-size: 12px"
      >
        <el-table-column prop="submit_status" label="状态" width="100px"/>
        <el-table-column prop="number" label="序号" width="100px" sortable />
        <el-table-column prop="name" label="题目"/>
        <el-table-column prop="passrate" label="通过率" />
        <el-table-column prop="difficulty" label="难度" />
        <el-table-column label="">
          <template #default="scope">
            <el-button size="small" type="text"
            >开始做题</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      </el-card>
    </div>
    <el-pagination
        :page-size="20"
        :pager-count="11"
        layout="prev, pager, next"
        :total="200"
        background
        style="margin: 20px auto;width:50%"
    />
  </div>
</template>

<script>
import { h } from 'vue'
import { ElMessage } from 'element-plus'
import {ArrowLeft,Search} from "@element-plus/icons";
export default {
  name: "list1",
  data() {
    return {
      h,
      difficulty:'全部',
      select_status:'全部',
      search:'',//这是搜索框的内容
      Search,//这是图标
      ArrowLeft,
      ElMessage,
      tableData:[
        {
          submit_status:'未通过',
          number: '1',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '2',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '3',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '4',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '5',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '6',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '7',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '8',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '9',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
        {
          submit_status:'未通过',
          number: '10',
          name: 'C++入门',
          passrate:'100%',
          difficulty:'简单',
        },
      ],
    }
  },
  methods:{
    }
}
</script>

<style scoped>
.operation{
  margin:10px auto;
}
.checkbox {
  margin-top:15px;
}
.list {
  text-align: center;
}
</style>