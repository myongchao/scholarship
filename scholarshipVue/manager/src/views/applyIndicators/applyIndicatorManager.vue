<template>
  <div class="tree">
    <div class="indicator">
      <el-table :data="tableData" :key="index">
        <el-table-column :index="indexMethod" type="index" align="center" width="160"/>
        <el-table-column prop="title" align="center" label="奖学金名称" width="160"/>
        <el-table-column prop="bgrade" align="center" label="奖学金级别" width="160"/>
        <el-table-column prop="amount" align="center" label="奖学金金额" width="160"/>
        <el-table-column prop="minScore" align="center" label="最低成绩" width="160"/>
        <el-table-column prop="subjectScore" align="center" label="最低学分" width="160"/>
        <el-table-column prop="rank" align="center" label="班级最低排名" width="160"/>
        <el-table-column prop="status" align="center" label="挂科要求" width="160"/>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="primary" size="small" icon="el-icon-edit" plain @click="editApply(scope.row.id)"/>
            <el-button type="danger" size="small" icon="el-icon-delete" plain @click="deleteAward(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="form.page" @changed="getList"/>
    </div>
    <edit-apply ref="edit" @success="getList"/>
  </div>
</template>

<script>
import page from '@/components/page'
import editApply from './components/editApply'
import {
  awardList,
  searchPage,
  deleteAward
} from '@/api/award'
export default {
  components: {
    page,
    editApply
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
        orderBy: 'amount desc'
      },
      loading: false, // 加载
      tableData: [],
      searchParams: {},
      awards: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.searchParams.page = this.form.page
      if (this.form.awardId !== null) {
        this.searchParams.form = {
          id: this.form.awardId
        }
      }
      // 分页
      searchPage(this.searchParams).then(e => {
        const data = e.data.records
        this.tableData = data
        this.form.page.total = e.data.total
      })
      // 获取奖学金类型类别无分页
      awardList().then(e => {
        this.awards = e.data
      })
    },
    editApply(id) {
      this.$refs.edit.open(id)
    },
    deleteAward(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteAward(this.tableData[index].id).then(e => {
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

<style  scoped>
  /* body{
    background-color: #E4E7ED;
  } */
  .tree{
    margin-top: 10px;
  }
  .components-container{
    margin-left: 10px;
  }
  .el-input {
    width: 50%;
  }
  .indicator{
       border: 1px solid seashell;
       margin: 10px;
       text-align: center;
    }
</style>
