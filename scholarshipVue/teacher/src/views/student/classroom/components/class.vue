<template>
  <el-form ref="form" :inline="true" :model="entity" class="student-class">
    <el-form-item label="学生姓名:" prop="name">
      <el-input v-model="entity.name"/>
    </el-form-item>
    <el-form-item label="家庭户口:" prop="familyAccount">
      <el-input v-model="entity.familyAccount"/>
    </el-form-item>
    <el-form-item label="家庭人口:" prop="familyNum">
      <el-input v-model="entity.familyNum"/>
    </el-form-item>
    <el-form-item label="家庭住址:" prop="address">
      <el-input v-model="entity.address"/>
    </el-form-item>
    <el-form-item label="邮编:" prop="zipCode">
      <el-input v-model="entity.zipCode"/>
    </el-form-item>
    <el-form-item label="年收入:" prop="inCome">
      <el-input v-model="entity.inCome"/>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
import { getStudentOne } from '@/api/student'
export default {
  mixins: [form],
  props: ['eid', 'isEdit'],
  data() {
    return {
      loading: false
    }
  },
  created() {
    this.getStudent()
  },
  methods: {
    getStudent() {
      console.log(123)
    },
    load() {
      if (this.isEdit && this.eid) {
        getStudentOne(this.eid).then(e => {
          this.entity = e.data
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
 /* .student-class{} */
</style>

