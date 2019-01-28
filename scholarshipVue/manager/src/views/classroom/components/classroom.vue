<template>
  <el-form ref="form" :inline="true" :model="entity" label-width="100px">
    <el-form-item label="班级名称:" prop="name">
      <el-input v-model="entity.name"/>
    </el-form-item>
    <el-form-item label="所属院系:" prop="depId">
      <el-select v-model="entity.depId">
        <el-option v-for="(item,index) in depList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="名额：" prop="places">
      <el-input v-model="entity.places"/>
    </el-form-item>
    <el-form-item label="负责教师:" prop="teacherId">
      <el-select v-model="entity.teacherId">
        <el-option v-for="(item,index) in teacherList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
import { getDepList } from '@/api/department'
import { getOneClass } from '@/api/class'
import { getTeacherList } from '@/api/teacher'
export default {
  mixins: [form],
  props: ['eid', 'isEdit'],
  data() {
    return {
      loading: false,
      depList: [],
      teacherList: []
    }
  },
  created() {
  },
  methods: {
    load() {
      if (this.isEdit && this.eid) {
        getOneClass(this.eid).then(e => {
          this.entity = e.data
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

