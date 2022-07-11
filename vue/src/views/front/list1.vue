<template>
  <div style="padding: 10px 0;font-size: large;">
    <el-button type="primary" style="margin-bottom: 10px;position: relative;left: 88%">实验详情</el-button>
    <el-button type="primary" style="margin-bottom: 10px;position: relative;left: 88%" @click="$router.push('/front/home')">返回</el-button>
    <div class="list">
      <el-table
          :data="tableData"
          stripe
          border
          style="width: 100%;"
      >
        <el-table-column prop="number" label="序号" width="100px" sortable />
        <el-table-column prop="name" label="实验名称" />
        <el-table-column prop="report" label="实验报告" />
        <el-table-column prop="deadline" label="截止时间" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small"
            >进入实验</el-button
            >
            <el-button
                size="small"
                type="primary"
                @click="open"
            >签到</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { h } from 'vue'
import { ElMessage } from 'element-plus'
export default {
  name: "list1",
  data() {
    return {
      h,
      ElMessage,
      tableData:[
        {
          number: '1',
          name: 'Java实验报告1',
          report: '已提交',
          deadline:'2023.1.1',
        },
        {
          number: '2',
          name: 'Java实验报告2',
          report: '未提交',
          deadline:'2023.1.1',
        },
        {
          number: '3',
          name: 'Java实验报告3',
          report: '未提交',
          deadline:'2023.1.1',
        },
      ],
    }
  },
  methods:{
    open() {
      ElMessage({
        message: '签到成功',
        type: 'success',
      })
    }
  }
}
</script>

<style>
.list {
  text-align: center;
}
</style>