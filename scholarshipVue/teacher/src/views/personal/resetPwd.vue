<template>
  <div class="pwd">
    <el-form v-loading="loading" ref="ruleForm" :model="ruleForm" :rules="rules2" status-icon label-width="100px" class="demo-ruleForm">
      <el-form-item label="原密码" prop="oldPwd">
        <el-input v-model="ruleForm.oldPwd" type="password" />
      </el-form-item>
      <el-form-item label="新密码" prop="pass">
        <el-input v-model="ruleForm.pass" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input v-model="ruleForm.checkPass" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { changePwd } from '@/api/login'
export default {
  data() {
    // var validateOldPwd = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请输入密码'))
    //   } else {
    //     if (this.ruleForm.oldPwd !== '') {
    //       this.$refs.ruleForm.validateField('checkPass')
    //     }
    //     callback()
    //   }
    // }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        oldPwd: '', // 原密码
        pass: '', // 新密码
        checkPass: '' // 确认密码
      },
      rules2: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
        // oldPwd: [
        //   { validator: validateOldPwd, trigger: 'blur' }
        // ]
      },
      loading: true,
      answer: null
    }
  },
  created() {
    setTimeout(() => {
      this.loading = false
    }, 1000)
  },
  methods: {
    submitForm() {
      debugger
      changePwd(this.resetForm).then(e => {
        this.answer = e.data.data
      })
      // this.$refs[formName].validate((valid) => {
      //   if (valid) {
      //     alert('submit!')
      //   } else {
      //     console.log('error submit!!')
      //     return false
      //   }
      // })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
   *{
       margin: 0;
       padding: 0;
   }
   .el-form{
       position: absolute;
       left: 50%;
       top: 50%;
       transform: translate3d(-50%,-50%,0)
   }
</style>
