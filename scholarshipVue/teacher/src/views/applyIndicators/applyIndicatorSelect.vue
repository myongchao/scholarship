<template>
  <div class="tree">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" size="small" class="demo-form-inline">
          <el-form-item label="奖学金类型:" prop="awardId">
            <!-- <el-select v-model="form.departmentsId" clearable filterable placeholder="全部">
              <el-option v-for="(item,index) in departments" :key="index" :label="item.name" :value="item.id"/>
            </el-select> -->
            <el-select v-model="form.awardId" placeholder="全部" style="height: 20px;">
              <el-option
                v-for="(item,index) in awards"
                :key="index"
                :label="item.title"
                :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" style="height: 75%;" @click="getList()">搜索</el-button>
          </el-form-item>
        </el-form>
        <!-- <el-button slot="append" type="primary" icon="el-icon-search" style="height: 75%;" >搜索</el-button> -->
      </template>
    </div>
    <div class="indicator">
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
  </div>
</template>

<script>
import page from '@/components/page'
import {
  awardList,
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
