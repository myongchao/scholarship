<template>
  <div class="header">
    <div class="components-container">
      <div class="submit">
        <template>
          <el-form ref="form" :model="form" :rules="rules" class="demo-form-inline" size="small">
            <el-form-item label="奖学金名称:" prop="title">
              <el-input v-model="form.title"/>
            </el-form-item>
            <el-form-item label="奖学金级别:" prop="bgrade">
              <el-input v-model="form.bgrade"/>
            </el-form-item>
            <el-form-item label="奖学金金额:" prop="amount">
              <el-input v-model="form.amount"/>
            </el-form-item>
            <el-form-item label="最低成绩:" prop="minScore">
              <el-input v-model="form.minScore"/>
            </el-form-item>
            <el-form-item label="最低学分:" prop="subjectScore">
              <el-input v-model="form.subjectScore"/>
            </el-form-item>
            <el-form-item label="班级排名:" prop="rank">
              <el-input v-model="form.rank"/>
            </el-form-item>
            <el-form-item label="挂科要求:" prop="status">
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
import { addAward } from '@/api/award'
export default {
  data() {
    return {
      // 分页和模糊查询
      form: {
        page: {
          current: 1,
          pageCount: 10,
          total: 0
        },
        depId: null,
        search: '',
        orderBy: 'id desc'
      },
      loading: false, // 加载
      tableData: [],
      depList: [],
      teacherList: [],
      rules: {
        title: [
          { required: true, message: '请输入奖学金名称', trigger: 'blur' }
        ],
        bgrade: [
          { required: true, message: '请输入奖学金级别', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入奖学金金额', trigger: 'blur' }
        ],
        minScore: [
          { required: true, message: '请输入最低分数', trigger: 'blur' }
        ],
        subjectScore: [
          { required: true, message: '请输入最低学分', trigger: 'blur' }
        ],
        rank: [
          { required: true, message: '请输入班级排名', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请输入挂科要求', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addAward(this.form).then(e => {
            if (e.success && e.data === '') {
              this.$message({
                type: 'success',
                message: '添加班级信息成功！'
              })
              this.$refs.form.resetFields()
            } else {
              this.$message({
                type: 'error',
                message: '添加失败!'
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
    width: 12.5%;
  }
  .components-container{
    width: 100%;
    height: 450px;
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
