<template>
  <div class="header">
    <div class="components-container">
      <div class="submit">
        <template>
          <el-form ref="form" :model="form" :rules="rules" class="demo-form-inline" size="small">
            <el-form-item label="账号：" prop="num">
              <el-input v-model="form.num"/>
            </el-form-item>
            <el-form-item label="密码：" prop="pwd">
              <el-input v-model="form.pwd" type="password"/>
            </el-form-item>
            <el-form-item label="姓名：" prop="name">
              <el-input v-model="form.name"/>
            </el-form-item>
            <el-form-item label="性别：" prop="sex">
              <el-radio v-model="form.sex" label="男">男</el-radio>
              <el-radio v-model="form.sex" label="女">女</el-radio>
            </el-form-item>
            <el-form-item label="联系方式：" prop="contact">
              <el-input v-model="form.contact"/>
            </el-form-item>
            <el-form-item label="管理班级：" prop="classId">
              <el-select v-model="form.classId" placeholder="全部" style="height: 20px;">
                <el-option
                  v-for="(item,index) in classList"
                  :key="index"
                  :label="item.name"
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

import { add } from '@/api/teacher'
import { getClassList } from '@/api/class'
import { getDepList } from '@/api/department'
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
        classId: null,
        search: '',
        orderBy: 'id desc'
      },
      loading: false, // 加载
      tableData: [],
      classList: [],
      depList: [],
      rules: {
        depId: [
          { required: true, message: '请选择所在院系', trigger: 'change' }
        ],
        classId: [
          { required: true, message: '请选择所在班级', trigger: 'change' }
        ],
        num: [
          { required: true, message: '请输入账号信息', trigger: 'blur' }
        ],
        pwd: [
          { required: true, message: '请设置初始密码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入用户姓名', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请输入用户性别', trigger: 'blur' }
        ],
        contact: [
          { required: true, message: '请输入联系方式', trigger: 'blur' }
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
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          add(this.form).then(e => {
            if (e.success && e.data === '') {
              this.$message({
                type: 'success',
                message: '添加教师信息成功！'
              })
              this.close()
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
    close() {
      this.$refs.form.reset()
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
    height: 400px;
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
