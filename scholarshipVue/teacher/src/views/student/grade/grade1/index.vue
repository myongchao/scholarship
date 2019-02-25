<template>
  <div class="header">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" size="small" class="demo-form-inline">
          <el-form-item label="学生姓名:" prop="name">
            <el-input v-model="form.name" placeholder="输入名字"/>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" style="height: 75%;" @click="getList()">搜索</el-button>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-refresh" type="primary" style="height: 75%;" @click="resetForm('form')">重置</el-button>
          </el-form-item>
          <div class="operation-button">
            <el-form-item>
              <el-button icon="el-icon-plus" type="primary" style="height: 75%;" @click="addClass">添加</el-button>
            </el-form-item>
            <el-form-item>
              <el-button icon="el-icon-upload2" type="primary" style="height: 75%;" @click="importClass">批量添加</el-button>
            </el-form-item>
          </div>
        </el-form>
      </template>
    </div>
    <div class="select">
      <el-table :data="tableData">
        <el-table-column :index="indexMethod" type="index" align="center" width="120"/>
        <el-table-column prop="num" align="center" label="学号" width="125"/>
        <el-table-column prop="name" align="center" label="姓名" width="125"/>
        <el-table-column prop="subject.code" align="center" label="课程编号" width="125"/>
        <el-table-column prop="subject.name" align="center" label="课程名称" width="125"/>
        <el-table-column prop="score" align="center" label="期末成绩" width="125"/>
        <el-table-column prop="score" align="center" label="成绩" width="125"/>
        <el-table-column prop="secondScore" align="center" label="补考成绩" width="125"/>
        <el-table-column prop="subject.subjectScore" align="center" label="学分" width="125"/>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="primary" size="small" icon="el-icon-edit" plain @click="editGrade(scope.row.id)"/>
            <el-button type="danger" size="small" icon="el-icon-delete" plain @click="deleteGrade(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
    <edit-grade ref="edit" @success="getList"/>
  </div>
</template>

<script>

import editGrade from '../components/editGrade'
import page from '@/components/page'
import { pageWithSubject, deleteScore } from '@/api/score'
export default {
  components: {
    page,
    editGrade
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
    editGrade(id) {
      // debugger
      this.$refs.edit.open(id)
    },
    deleteGrade(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteScore(this.tableData[index].id).then(e => {
          if (e.success) {
            this.getList()
            this.form.page.total--
            this.$message({
              type: 'success',
              message: '删除成功！'
            })
          } else {
            this.$message({
              type: 'error',
              message: '删除失败！'
            })
          }
        })
      }).catch(_ => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
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
    .operation-button{
      float: right;
    }
</style>
