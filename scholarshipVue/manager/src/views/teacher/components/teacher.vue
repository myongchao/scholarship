<template>
  <el-form ref="form" :inline="true" :model="entity" label-width="100px">
    <el-form-item label="账号:" prop="num">
      <el-input v-model="entity.num"/>
    </el-form-item>
    <el-form-item label="密码：" prop="pwd">
      <el-input v-model="entity.pwd" type="password"/>
    </el-form-item>
    <el-form-item label="姓名:" prop="name">
      <el-input v-model="entity.name"/>
    </el-form-item>
    <el-form-item label="性别:" prop="sex">
      <el-radio v-model="entity.sex" label="男">男</el-radio>
      <el-radio v-model="entity.sex" label="女">女</el-radio>
    </el-form-item>
    <el-form-item label="联系方式:" prop="contact">
      <el-input v-model="entity.contact"/>
    </el-form-item>
    <el-form-item label="管理班级:" prop="classId">
      <el-select v-model="entity.classId">
        <el-option v-for="(item,index) in classList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="所属院系:" prop="depId">
      <el-select v-model="entity.depId">
        <el-option v-for="(item,index) in depList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
import { getClassList } from '@/api/class'
import { getDepList } from '@/api/department'
import { getTeacher } from '@/api/teacher'
export default {
  mixins: [form],
  props: ['eid', 'isEdit'],
  data() {
    return {
      loading: false,
      depList: [],
      classList: []
    }
  },
  created() {
  },
  methods: {
    load() {
      if (this.isEdit && this.eid) {
        getTeacher(this.eid).then(e => {
          this.entity = e.data
        })
        getClassList().then(e => {
          if (e.data) {
            this.classList = e.data
          }
        })
        getDepList().then(e => {
          if (e.data) {
            this.depList = e.data
          }
        })
      }
    },
    open() {
      this.dialogVisible = true
    }
  }
}
</script>

<style>

</style>

