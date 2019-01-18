<template>
  <div class="header">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" class="demo-form-inline">
          <el-form-item label="学生姓名:" prop="name">
            <el-input v-model="form.name" placeholder="输入名字"/>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" style="height: 75%;" @click="getList()">搜索</el-button>
          </el-form-item>
        </el-form>
      </template>
    </div>
    <div class="select">
      <el-table :data="tableData" :key="index">
        <el-table-column :index="indexMethod" type="index" align="center" width="120"/>
        <el-table-column prop="num" align="center" label="学号" width="125"/>
        <el-table-column prop="name" align="center" label="姓名" width="125"/>
        <el-table-column prop="subject.code" align="center" label="课程编号" width="125"/>
        <el-table-column prop="subject.name" align="center" label="课程名称" width="125"/>
        <el-table-column prop="score" align="center" label="期末成绩" width="125"/>
        <el-table-column prop="score" align="center" label="成绩" width="125"/>
        <el-table-column prop="upScore" align="center" label="补考成绩" width="125"/>
        <el-table-column prop="subject.subjectScore" align="center" label="学分" width="125"/>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" plain @click="editProfession(scope.row.id)"/>
            <el-button type="danger" icon="el-icon-delete" plain @click="deleteProfession(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
  </div>
</template>

<script>

import page from '@/components/page'
import { pageWithSubject } from '@/api/score'
export default {
  components: {
    page
  },
  data() {
    return {
      // 分页和模糊查询
      form: {
        page: {
          current: 1,
          pageCount: 10,
          total: 0
        },
        classId: null,
        search: '',
        orderBy: 'id desc'
      },
      awards: [],
      loading: false, // 加载
      tableData: [],
      searchParams: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.searchParams.page = this.form.page
      this.searchParams.form = {
        classId: 1
      }
      if (this.form.name !== null) {
        this.searchParams.form = {
          classId: 1,
          name: this.form.name
        }
      }
      pageWithSubject(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
    },
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    }
  }

}
</script>

<style  scoped>
  body{
    background-color: #E4E7ED;
  }
  .header{
    margin-top: 10px;
  }
  .el-input {
    width: 100%;
  }
  .components-container{
    margin-left: 10px;
  }
  .select{
       border: 1px solid seashell;
       margin: 10px;
       text-align: center;
    }
    .edit-input {
       padding-right: 80px;
    }
    .cancel-btn {
      position: absolute;
      right: 15px;
      top: 14px;
    }
</style>
