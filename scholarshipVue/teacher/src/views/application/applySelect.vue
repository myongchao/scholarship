<template>
  <div class="header">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" class="demo-form-inline">
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
    <div class="select">
      <el-table :data="tableData" :key="index">
        <el-table-column :index="indexMethod" type="index" align="center" width="120"/>
        <el-table-column prop="award.title" align="center" label="奖学金名称" width="120"/>
        <el-table-column prop="award.bgrade" align="center" label="奖学金级别" width="120"/>
        <el-table-column prop="score.score" align="center" label="成绩" width="120"/>
        <el-table-column prop="score.subjectScore" align="center" label="学分" width="120"/>
        <el-table-column prop="name" align="center" label="姓名" width="120"/>
        <el-table-column prop="classroom.name" align="center" label="班级" width="120"/>
        <el-table-column prop="department.name" align="center" label="院系" width="120"/>
        <el-table-column prop="insertTime" align="center" label="日期" width="120"/>
        <el-table-column prop="check1" align="center" label="教师审核状态" width="120"/>
        <el-table-column min-width="300px" label="教务处审核状态" align="center">
          <template slot-scope="scope">
            <template v-if="scope.row.edit">
              <el-input v-model="scope.row.check2" class="edit-input" size="small"/>
              <el-button class="cancel-btn" size="small" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">取消</el-button>
            </template>
            <span v-else>{{ scope.row.check2 }}</span>
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
  </div>
</template>

<script>

import page from '@/components/page'
import { recordList, update } from '@/api/record'
import { awardList } from '@/api/award'
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
      awards: [],
      loading: false, // 加载
      tableData: [],
      recordId: undefined
    }
  },
  created() {
    this.getList()
  },
  methods: {
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    },
    getList() {
      recordList().then(response => {
        const data = response.data
        this.tableData = data
        this.tableData = data.map(v => {
          this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
          v.originalCheck2 = v.check2 //  will be used when user click the cancel botton
          return v
        })
      })
      awardList().then(e => {
        this.awards = e.data
      })
    },
    cancelEdit(row) {
      row.check2 = row.originalCheck2
      row.edit = false
      this.$message({
        message: '审核状态未修改',
        type: 'warning'
      })
    },
    confirmEdit(row) {
      row.edit = false
      row.originalCheck2 = row.check2
      update(row.id, row.check2).then(e => {
        if (e.data) {
          this.$message({
            message: '审核状态已修改',
            type: 'success'
          })
        }
      })
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
    width: 58%;
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
       padding-right: 100px;
    }
    .cancel-btn {
      position: absolute;
      right: 15px;
      top: 18px;
    }
</style>
