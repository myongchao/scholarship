<template>
  <div class="manager">
    <el-table :data="tableData">
      <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="140"/>
      <el-table-column prop="award.title" align="center" label="奖学金名称" width="140"/>
      <el-table-column prop="award.bgrade" align="center" label="奖学金级别" width="140"/>
      <el-table-column prop="score.score" align="center" label="成绩" width="120"/>
      <el-table-column prop="score.subjectScore" align="center" label="学分" width="120"/>
      <el-table-column prop="name" align="center" label="姓名" width="120"/>
      <el-table-column prop="classroom.name" align="center" label="班级" width="120"/>
      <el-table-column prop="department.name" align="center" label="院系" width="120"/>
      <el-table-column prop="insertTime" align="center" label="日期" width="140"/>
      <el-table-column min-width="300px" label="辅导员审核状态" align="center" width="250">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input v-model="scope.row.check1" class="edit-input" size="small"/>
            <el-button class="cancel-btn" size="small" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">取消</el-button>
          </template>
          <span v-else>{{ scope.row.check1 }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-button v-if="scope.row.edit" type="success" size="small" icon="el-icon-circle-check-outline" @click="confirmEdit(scope.row)">完成</el-button>
          <el-button v-else type="primary" size="small" icon="el-icon-edit" @click="scope.row.edit=!scope.row.edit">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <page :page="form.page" @changed="getList"/>
  </div>
</template>

<script>
import page from '@/components/page'
import {
  // recordList,
  update,
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
      tableData: [],
      recordId: undefined
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      searchWithPage(this.form).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
        this.tableData = data.map(v => {
          this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
          v.originalCheck1 = v.check1 //  will be used when user click the cancel botton
          return v
        })
      })
    },
    cancelEdit(row) {
      row.check1 = row.originalCheck1
      row.edit = false
      this.$message({
        message: '审核状态未修改',
        type: 'warning'
      })
    },
    confirmEdit(row) {
      row.edit = false
      row.originalCheck1 = row.check1
      update(row.id, row.check1).then(e => {
        if (e.data) {
          this.$message({
            message: '审核状态已修改',
            type: 'success'
          })
        }
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

