<template>
  <div class="indicatorView">
    <el-table :data="tableData" :key="index">
      <el-table-column :index="indexMethod" type="index" align="center" width="190"/>
      <el-table-column prop="title" align="center" label="奖学金名称" width="190"/>
      <el-table-column prop="bgrade" align="center" label="奖学金级别" width="190"/>
      <el-table-column prop="amount" align="center" label="奖学金金额" width="190"/>
      <el-table-column prop="minScore" align="center" label="最低成绩" width="190"/>
      <el-table-column prop="subjectScore" align="center" label="最低学分" width="190"/>
      <el-table-column prop="rank" align="center" label="班级最低排名" width="190"/>
      <el-table-column prop="status" align="center" label="挂科要求" width="190"/>
    </el-table>
    <page :page="form.page" @changed="getList"/>
  </div>
</template>

<script>
import page from '@/components/page'
import {
  searchPage
} from '@/api/award'
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
        orderBy: 'amount desc'
      },
      loading: false, // 加载
      tableData: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      // awardList().then(e => {
      //   this.tableData = e.data
      // })
      searchPage(this.form).then(e => {
        const data = e.data.records
        this.tableData = data
        this.form.page.total = e.data.total
      })
    },
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    }
  }

}
</script>

<style scoped>
    .indicatorView{
       border: 1px solid seashell;
       margin: 15px;
       text-align: center;
       width: 98%;
    }
    body{
        background-color: #E4E7ED;
    }
</style>

