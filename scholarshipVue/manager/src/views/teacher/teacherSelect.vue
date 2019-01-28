<template>
  <div class="teacher-header">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" size="small" class="demo-form-inline">
          <el-form-item label="教师姓名:" prop="name">
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
              <el-button icon="el-icon-plus" type="primary" style="height: 75%;" @click="addTeacher">添加</el-button>
            </el-form-item>
            <el-form-item>
              <el-button icon="el-icon-upload2" type="primary" style="height: 75%;" @click="importClass">批量添加</el-button>
            </el-form-item>
          </div>
        </el-form>
      </template>
    </div>
    <div class="manager">
      <el-table :data="tableData">
        <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="140"/>
        <el-table-column prop="num" align="center" label="用户账号" width="140"/>
        <el-table-column prop="name" align="center" label="姓名" width="140"/>
        <el-table-column prop="sex" align="center" label="性别" width="140"/>
        <el-table-column prop="contact" align="center" label="联系方式" width="140"/>
        <el-table-column prop="classroom.name" align="center" label="管理班级" width="150"/>
        <el-table-column prop="dep.name" align="center" label="院系" width="150"/>
        <el-table-column prop="insertTime" align="center" label="日期" width="165"/>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="primary" size="small" icon="el-icon-edit" plain @click="editTeacher(scope.row.id)"/>
            <el-button type="danger" size="small" icon="el-icon-delete" plain @click="deleteTeacher(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
      <edit-teacher ref="edit" @success="getList"/>
    </div>
  </div>
</template>

<script>
import page from '@/components/page'
import editTeacher from './components/editTeacher'
import {
  pageTeacher, deletedTeacher } from '@/api/teacher'
export default {
  components: {
    page,
    editTeacher
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
        search: '',
        orderBy: 'id desc'
      },
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
      if (this.form.name !== null) {
        this.searchParams.form = {
          name: this.form.name
        }
      }
      pageTeacher(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
    },
    editTeacher(id) {
      this.$refs.edit.open(id)
    },
    addTeacher() {
      this.$router.push('addTeacher')
    },
    deleteTeacher(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deletedTeacher(this.tableData[index].id).then(e => {
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
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    }
  }

}
</script>

<style scoped>
   .teacher-header{
       margin-top: 15px;
   }
  .components-container{
    margin-left: 10px;
  }
    .manager{
       border: 1px solid seashell;
       margin: 15px;
       text-align: center
    }
    .el-input {
    width: 100%;
   }
    .operation-button{
      float:right
    }
</style>

