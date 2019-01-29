<template>
  <div class="header">
    <div class="components-container">
      <div class="submit">
        <template>
          <el-form ref="form" :model="form" :rules="rules" class="demo-form-inline" size="small">
            <el-form-item label="学号(工号):" prop="num">
              <el-input v-model="form.num"/>
            </el-form-item>
            <el-form-item label="学生姓名:" prop="name">
              <el-input v-model="form.name"/>
            </el-form-item>
            <el-form-item label="班级名称:" prop="classId">
              <el-select v-model="form.classId" placeholder="全部">
                <el-option v-for="(item,index) in classList" :key="index" :label="item.name" :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="所属院系:" prop="depId">
              <el-select v-model="form.depId" placeholder="全部">
                <el-option v-for="(item,index) in depList" :key="index" :label="item.name" :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="负责教师:" prop="teacherId">
              <el-select v-model="form.teacherId" placeholder="全部">
                <el-option v-for="(item,index) in teacherList" :key="index" :label="item.name" :value="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="家庭户口:" prop="familyAccount">
              <el-input v-model="form.familyAccount"/>
            </el-form-item>
            <el-form-item label="家庭人口:" prop="familyNum">
              <el-input v-model="form.familyNum"/>
            </el-form-item>
            <el-form-item label="家庭地址:" prop="address">
              <el-input v-model="form.address"/>
            </el-form-item>
            <el-form-item label="所在地邮编:" prop="zipCode">
              <el-input v-model="form.zipCode"/>
            </el-form-item>
            <el-form-item label="年收入:" prop="inCome">
              <el-input v-model="form.inCome"/>
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
import { getClassList } from '@/api/class'
import { getDepList } from '@/api/department'
import { getTeacherList } from '@/api/teacher'
import { addStudent } from '@/api/student'
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
      classList: [],
      depList: [],
      teacherList: [],
      rules: {
        num: [
          { required: true, message: '请输入学生学号', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' }
        ],
        familyAccount: [
          { required: true, message: '请输入家庭户口', trigger: 'blur' }
        ],
        familyNum: [
          { required: true, message: '请输入家庭人数', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入家庭住址', trigger: 'blur' }
        ],
        zipCode: [
          { required: true, message: '请输入邮编', trigger: 'blur' }
        ],
        inCome: [
          { required: true, message: '请输入家庭年收入', trigger: 'blur' }
        ],
        classId: [
          { required: true, message: '请选择班级', trigger: 'blur' }
        ],
        depId: [
          { required: true, message: '请选择所在院系', trigger: 'change' }
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
      getClassList().then(e => {
        this.classList = e.data
      })
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
          addStudent(this.form).then(e => {
            if (e.success && e.data === '') {
              this.$message({
                type: 'success',
                message: '添加学生信息成功！'
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
    close() {
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
    height: 600px;
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
