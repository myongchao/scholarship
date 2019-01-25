<template>
  <div style="margin: 20px auto" class="userInfo">
    <el-row :gutter="20">
      <el-col :span="2"><div class="f_label">账号:</div></el-col>
      <el-col :span="5"><div class="f_value">{{ admin.num }}&nbsp;</div></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="2"><div class="f_label">学号:</div></el-col>
      <el-col :span="5"><div class="f_value">{{ admin.code }}&nbsp;</div></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="2"><div class="f_label">姓名:</div></el-col>
      <el-col :span="5"><div class="f_value">{{ admin.name }}&nbsp;</div></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="2"><div class="f_label">角色:</div></el-col>
      <el-col :span="5"><div class="f_value">{{ admin.role }}&nbsp;</div></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="2"><div class="f_label">学校:</div></el-col>
      <el-col :span="5"><div class="f_value">郑州师范学院 &nbsp;</div></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="2"><div class="f_label">院系:</div></el-col>
      <el-col :span="5"><div class="f_value">{{ admin.dep.name }}&nbsp;</div></el-col>
    </el-row>
  </div>
</template>

<script>
import { get } from '@/api/login'
import store from '@/store'
export default {
  data() {
    return {
      admin: undefined
    }
  },
  created() {
    this.getStudent()
  },
  methods: {
    getStudent() {
      get(store.getters.token).then(e => {
        this.admin = e.data
      })
    }
  }
}
</script>

<style scoped>
   .wrap{
       background-color: #fff;
   }
   .userInfo .el-row{margin:20px 0px;}

</style>

