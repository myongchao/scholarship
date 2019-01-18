<template>
  <div v-show="showPwd" class="pwd">
    <el-form v-loading="loading" ref="ruleForm" :model="ruleForm" :rules="rules2" status-icon label-width="100px" class="demo-ruleForm">
      <el-form-item label="原密码" prop="oldPwd">
        <el-input v-model="ruleForm.oldPwd" type="password" />
      </el-form-item>
      <el-form-item label="新密码" prop="newPwd">
        <el-input v-model="ruleForm.newPwd" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input v-model="ruleForm.checkPass" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item>
        <el-button :plain="true" type="primary" @click="submitForm()">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { changePwd } from '@/api/login'
import { Message } from 'element-ui'
import Vue from 'vue'
Vue.use(Message)
export default {
  data() {
    var validateOldPwd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入旧密码'))
      }
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'))
      } else {
        if (value.length < 6) {
          callback(new Error('密码数不能少于6位'))
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass')
          }
          callback()
        }
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入新密码'))
      } else if (value !== this.ruleForm.newPwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        oldPwd: '', // 原密码
        newPwd: '', // 新密码
        checkPass: '' // 确认密码
      },
      rules2: {
        oldPwd: [
          { validator: validateOldPwd, trigger: 'blur' }
        ],
        newPwd: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      loading: true,
      showPwd: false,
      answer: null
    }
  },
  created() {
    setTimeout(() => {
      this.loading = false
      this.showPwd = true
    }, 100)
  },
  methods: {
    submitForm() {
      changePwd(this.ruleForm).then(e => {
        if (e.success) {
          this.$message({
            // showClose: true,
            message: e.data,
            type: 'success'
          })
          this.resetForm()
        } else {
          this.$message({
            // showClose: true,
            message: e.data,
            type: 'error'
          })
        }
      })
    },
    resetForm() {
      this.$refs.ruleForm.resetFields()
    }
  }
}
</script>

<style scoped>
   .pwd{
       position: absolute;
       left: 10%;
       top: 20%;
       transform: translate3d(-50%,-50%,0)
   }
</style>
