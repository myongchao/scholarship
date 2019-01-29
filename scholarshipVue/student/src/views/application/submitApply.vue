<template>
  <div class="header">
    <div class="components-container">
      <div class="submit">
        <template>
          <el-form ref="form" :model="form" :rules="rules" class="demo-form-inline" size="small">
            <el-form-item label="奖学金类型:" prop="awardId">
              <el-select v-model="form.awardId" placeholder="全部" style="height: 20px;">
                <el-option
                  v-for="(item,index) in awards"
                  :key="index"
                  :label="item.title"
                  :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="所在院系：" prop="depId">
              <el-select v-model="form.depId" placeholder="全部" style="height: 20px;">
                <el-option
                  v-for="(item,index) in depList"
                  :key="index"
                  :label="item.name"
                  :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="所在班级：" prop="classId">
              <el-select v-model="form.classId" placeholder="全部" style="height: 20px;">
                <el-option
                  v-for="(item,index) in classList"
                  :key="index"
                  :label="item.name"
                  :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="综合成绩：" prop="totalScore">
              <el-input v-model="form.totalScore"/>
            </el-form-item>
            <el-form-item label="综合学分：" prop="totalSubjectScore">
              <el-input v-model="form.totalSubjectScore"/>
            </el-form-item>
            <el-form-item label="班级排名：" prop="rank">
              <el-input v-model="form.rank"/>
            </el-form-item>
            <el-form-item label="是否挂科：" prop="status">
              <el-input v-model="form.status"/>
            </el-form-item>
            <el-form-item class="btn">
              <el-button :plain="true" type="primary" @click="submitForm('form')">提交</el-button>
              <el-button @click="resetForm('form')">重置</el-button>
            </el-form-item>
          </el-form>
        </template>
      </div>
    </div>
  </div>
</template>

<script>

import page from '@/components/page'
import {
  searchWithPage, submitApply } from '@/api/record'
import { awardList } from '@/api/award'
import { getClassList } from '@/api/class'
import { getDepList } from '@/api/department'
import store from '@/store'
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
        num: null, // 当前用户账号（学号）
        awardId: null,
        depId: null,
        classId: null,
        totalScore: null,
        totalSubjectScore: null,
        rank: null,
        status: null,
        search: '',
        orderBy: 'id desc'
      },
      awards: [],
      loading: false, // 加载
      tableData: [],
      classList: [],
      depList: [],
      searchParams: {}, // 搜索条件
      subForm: {}, // 申请信息
      rules: {
        awardId: [
          { required: true, message: '请选择奖学金类型', trigger: 'change' }
        ],
        depId: [
          { required: true, message: '请选择所在院系', trigger: 'change' }
        ],
        classId: [
          { required: true, message: '请选择所在班级', trigger: 'change' }
        ],
        totalScore: [
          { required: true, message: '请输入综合成绩', trigger: 'blur' }
        ],
        totalSubjectScore: [
          { required: true, message: '请输入综合学分', trigger: 'blur' }
        ],
        rank: [
          { required: true, message: '请输入班级排名', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请输入挂科情况', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      // 获取班级列表
      getClassList().then(e => {
        this.classList = e.data
      })
      // 获取院系列表
      getDepList().then(e => {
        this.depList = e.data
      })
      this.searchParams.page = this.form.page
      if (this.form.awardId !== null) {
        this.searchParams.form = {
          awardId: this.form.awardId
        }
      }
      searchWithPage(this.searchParams).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
      // 获取奖学金列表
      awardList().then(e => {
        this.awards = e.data
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const num = store.getters.token
          this.form.num = num
          submitApply(this.form).then(e => {
            if (e.success && e.data === '') {
              this.$message({
                type: 'success',
                message: '提交申请成功！'
              })
              this.$refs.form.resetFields()
            } else if (e.success && e.data !== '') {
              this.$message({
                type: 'error',
                message: e.data
              })
            } else {
              this.$message({
                type: 'error',
                message: '申请失败，请重新提交!'
              })
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
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
  .header{
    margin-top: 10px;
  }
  .el-input {
    width: 15%;
  }
  .components-container{
    width: 100%;
    height: 500px;
    margin:20px;
    background-color: #fff;
  }
  .submit{
    margin: 35px;
    margin-top: 10px;
    padding-top: 10px
  }
  .btn{
    margin-left: 90px;
  }
</style>
