<template>
  <el-form ref="form" :inline="true" :model="entity" label-width="100px">
    <el-form-item label="学生姓名:" prop="name">
      <el-input v-model="entity.name"/>
    </el-form-item>
    <el-form-item label="班级名称:" prop="classId">
      <el-select v-model="entity.classId">
        <el-option v-for="(item,index) in classList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="院系:" prop="depId">
      <el-select v-model="entity.depId">
        <el-option v-for="(item,index) in depList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="负责教师:" prop="teacherId">
      <el-select v-model="entity.teacherId">
        <el-option v-for="(item,index) in teacherList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
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
    <el-form-item label="邮 编:" prop="zipCode">
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
import { getDepList } from '@/api/department'
import { getStudentOne } from '@/api/student'
import { getTeacherList } from '@/api/teacher'
import { getClassList } from '@/api/class'
export default {
  mixins: [form],
  props: ['eid', 'isEdit'],
  data() {
    return {
      loading: false,
      classList: [],
      depList: [],
      teacherList: []
    }
  },
  created() {
  },
  methods: {
    load() {
      if (this.isEdit && this.eid) {
        getStudentOne(this.eid).then(e => {
          this.entity = e.data
        })
        getClassList().then(e => {
          this.classList = e.data
        })
        getDepList().then(e => {
          if (e.data) {
            this.depList = e.data
          }
        })
        getTeacherList().then(e => {
          this.teacherList = e.data
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

