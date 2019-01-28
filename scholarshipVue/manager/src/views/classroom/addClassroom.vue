<template>
  <div class="header">
    <div class="components-container">
      <div class="submit">
        <template>
          <el-form ref="form" :model="form" :rules="rules" class="demo-form-inline" size="small">
            <el-form-item label="班级名称:" prop="name">
              <el-input v-model="form.name"/>
            </el-form-item>
            <el-form-item label="所属院系:" prop="depId">
              <el-select v-model="form.depId" placeholder="全部">
                <el-option v-for="(item,index) in depList" :key="index" :label="item.name" :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="最大名额:" prop="places">
              <el-input v-model="form.places"/>
            </el-form-item>
            <el-form-item label="负责教师:" prop="teacherId">
              <el-select v-model="form.teacherId" placeholder="全部">
                <el-option v-for="(item,index) in teacherList" :key="index" :label="item.name" :value="item.id"/>
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

import { addClass } from '@/api/class'
import { getDepList } from '@/api/department'
import { getTeacherList } from '@/api/teacher'
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
        depId: [
          { required: true, message: '请选择所在院系', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入班级名称', trigger: 'blur' }
        ],
        places: [
          { required: true, message: '请输入最大名额', trigger: 'blur' }
        ],
        teacherId: [
          { required: true, message: '请选择负责教师', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      // 获取院系列表
      getDepList().then(e => {
        if (e.data) {
          this.depList = e.data
        }
      })
      getTeacherList().then(e => {
        this.teacherList = e.data
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          debugger
          addClass(this.form).then(e => {
            if (e.success && e.data === '') {
              this.$message({
                type: 'success',
                message: '添加班级信息成功！'
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
    width: 12.5%;
  }
  .components-container{
    width: 100%;
    height: 300px;
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
