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
      <el-table v-loading="loading" :data="tableData">
        <el-table-column :index="indexMethod" type="index" align="center" width="120"/>
        <el-table-column prop="num" align="center" label="学号" width="120"/>
        <el-table-column prop="name" align="center" label="姓名" width="120"/>
        <el-table-column prop="classroom.name" align="center" label="班级" width="120"/>
        <el-table-column prop="dep.name" align="center" label="院系" width="120"/>
        <el-table-column prop="familyAccount" align="center" label="家庭户口" width="120"/>
        <el-table-column prop="familyNum" align="center" label="人口" width="120"/>
        <el-table-column prop="address" align="center" label="地址" width="120"/>
        <el-table-column prop="zipCode" align="center" label="邮编" width="120"/>
        <el-table-column prop="inCome" align="center" label="年收入" width="120"/>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" plain @click="editClass(scope.row.id)"/>
            <el-button type="danger" icon="el-icon-delete" plain @click="deleteClass(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
    <edit-class ref="edit" @success="getList"/>
    <add-class ref="add" @success="getList"/>
    <import-data ref="imp" @success="onImportSuccess"/>
  </div>
</template>

<script>

import page from '@/components/page'
import { searchPage, deleteStudent } from '@/api/student'
import editClass from '../components/editClass'
import addClass from '../components/addClass'
import importData from '../components/importData'
// import importData from '../components/importData'
export default {
  components: {
    page,
    editClass,
    addClass,
    importData
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
      this.loading = true
      this.searchParams.page = this.form.page
      this.searchParams.form = {
        classId: 2
      }
      if (this.form.name !== null) {
        this.searchParams.form = {
          classId: 2,
          name: this.form.name
        }
      }
      searchPage(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
      this.loading = false
    },
    addClass() {
      this.$refs.add.open()
    },
    editClass(id) {
      this.$refs.edit.open(id)
    },
    importClass() {
      this.$refs.imp.open()
    },
    // 实现导入功能
    onImportSuccess(e) {
      if (e) {
        this.getList()
      }
    },
    deleteClass(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteStudent(this.tableData[index].id).then(e => {
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
              message: '删除失败[本教师已创建学生]！'
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
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }

}
</script>

<style  scoped>
  body{
    background-color: #E4E7ED;
  }
  .header{
    margin-top: 15px;
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
    .operation-button{
      float:right
    }
</style>
