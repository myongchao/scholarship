<template>
  <div class="header">
    <div class="components-container">
      <template>
        <el-form ref="form" :inline="true" :model="form" class="demo-form-inline" size="small">
          <el-form-item label="奖学金类型:" prop="awardId">
            <el-select v-model="form.awardId" placeholder="全部" style="height: 20px;">
              <el-option
                v-for="(item,index) in awards"
                :key="index"
                :label="item.title"
                :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="班级:" prop="classId">
            <el-select v-model="form.classId" placeholder="全部" style="height: 20px;">
              <el-option
                v-for="(item,index) in classRoomes"
                :key="index"
                :label="item.name"
                :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="审核状态:" prop="check1">
            <el-select v-model="form.check1" placeholder="全部" style="height: 20px;">
              <el-option
                v-for="(item,index) in checks"
                :key="index"
                :label="item.check1"
                :value="item.check1"/>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-search" type="primary" style="height: 75%;" @click="getList()">搜索</el-button>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-refresh" type="primary" style="height: 75%;" @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
        <!-- <el-button slot="append" type="primary" icon="el-icon-search" style="height: 75%;" >搜索</el-button> -->
      </template>
    </div>
    <div class="select">
      <el-table :data="tableData" :key="index">
        <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="100"/>
        <el-table-column prop="award.title" align="center" label="奖学金名称" width="120"/>
        <el-table-column prop="award.bgrade" align="center" label="奖学金级别" width="120"/>
        <el-table-column prop="totalScore" align="center" label="综合成绩" width="110"/>
        <el-table-column prop="totalSubjectScore" align="center" label="综合学分" width="110"/>
        <el-table-column prop="name" align="center" label="姓名" width="120"/>
        <el-table-column prop="classroom.name" align="center" label="班级" width="120"/>
        <el-table-column prop="department.name" align="center" label="院系" width="120"/>
        <el-table-column prop="insertTime" align="center" label="日期" width="120"/>
        <el-table-column prop="check1" align="center" label="辅导员审核状态" width="120"/>
        <el-table-column min-width="300px" label="教务处审核状态" align="center" width="250">
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
import {
  updateManager,
  searchWithPage } from '@/api/record'
import { awardList } from '@/api/award'
import { getClassList } from '@/api/class'
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
        awardId: null,
        classId: null,
        check1: null,
        search: '',
        orderBy: 'id desc'
      },
      awards: [], // 奖学金列表
      classRoomes: [], // 班级列表
      checks: [{ id: 1, check1: '通过' }, { id: 2, check1: '不通过' }, { id: 3, check1: '未审核' }],
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
      if (this.form.awardId !== null) {
        this.searchParams.form = {
          awardId: this.form.awardId
        }
      }
      if (this.form.classId !== null) {
        this.searchParams.form = {
          classId: this.form.classId
        }
      }
      if (this.form.check1 !== null) {
        this.searchParams.form = {
          check1: this.form.check1
        }
      }
      if (this.form.classId !== null && this.form.awardId !== null) {
        this.searchParams.form = {
          awardId: this.form.awardId,
          classId: this.form.classId
        }
      }
      if (this.form.classId !== null && this.form.check1 !== null) {
        this.searchParams.form = {
          check1: this.form.check1,
          classId: this.form.classId
        }
      }
      if (this.form.awardId !== null && this.form.check1 !== null) {
        this.searchParams.form = {
          check1: this.form.check1,
          awardId: this.form.awardId
        }
      }
      if (this.form.classId !== null && this.form.awardId !== null && this.form.check1 !== null) {
        this.searchParams.form = {
          check1: this.form.check1,
          awardId: this.form.awardId,
          classId: this.form.classId
        }
      }
      searchWithPage(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
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
      getClassList().then(e => {
        this.classRoomes = e.data
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
      row.originalCheck1 = row.check2
      updateManager(row.id, row.check2).then(e => {
        if (e.data) {
          this.$message({
            message: '审核状态已修改',
            type: 'success'
          })
        }
      })
    },
    resetForm(resetForm) {
      this.getList()
      this.$refs[resetForm].resetFields()
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
    width: 75%;
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
