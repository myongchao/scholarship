<template>
  <div class="manager">
    <el-table :data="tableData">
      <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="120"/>
      <el-table-column prop="award.title" align="center" label="奖学金名称" width="120"/>
      <el-table-column prop="award.bgrade" align="center" label="奖学金级别" width="120"/>
      <el-table-column prop="totalScore" align="center" label="综合成绩" width="120"/>
      <el-table-column prop="totalSubjectScore" align="center" label="综合学分" width="120"/>
      <el-table-column prop="name" align="center" label="姓名" width="120"/>
      <el-table-column prop="classroom.name" align="center" label="班级" width="120"/>
      <el-table-column prop="department.name" align="center" label="院系" width="120"/>
      <el-table-column prop="insertTime" align="center" label="日期" width="140"/>
      <el-table-column prop="check1" align="center" label="辅导员审核状态" width="120"/>
      <el-table-column prop="check2" align="center" label="教务处审核状态" width="120"/>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" plain @click="deleteClass(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <page :page="form.page" @changed="getList"/>
  </div>
</template>

<script>
import page from '@/components/page'
import store from '@/store'
import {
  deleteRecord,
  searchWithPage } from '@/api/record'
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
        search: '',
        orderBy: 'id desc'
      },
      departments: [], // 院系信息
      loading: false, // 加载
      tableData: [], // 数据
      searchParams: {} // 重新覆盖查询条件
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      const num = store.getters.token
      this.searchParams.page = this.form.page
      this.searchParams.form = {
        num: num
      }
      searchWithPage(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
    },
    deleteClass(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteRecord(this.tableData[index].id).then(e => {
          if (e.data === '') {
            this.getList()
            this.form.page.total--
            this.$message({
              type: 'success',
              message: '删除成功！'
            })
          } else {
            this.$message({
              type: 'error',
              message: e.data
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
   .manager-page{
     background-color: #E4E7ED;
   }
    .manager{
       border: 1px solid seashell;
       margin: 15px;
       text-align: center
    }
    .el-input {
    width: 75%;
   }
    .edit-input {
       padding-right: 80px;
    }
    .cancel-btn {
      position: absolute;
      right: 15px;
      top: 18px;
    }
    .active{
      color: rgb(21, 124, 124)
    }
</style>

