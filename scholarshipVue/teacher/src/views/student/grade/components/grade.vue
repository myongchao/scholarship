<template>
  <el-form ref="form" :inline="true" :model="entity" class="student-class">
    <el-form-item label="学生学号:" prop="code">
      <el-input v-model="entity.code" :disabled="true"/>
    </el-form-item>
    <el-form-item label="学生姓名:" prop="name">
      <el-input v-model="entity.name" :disabled="true"/>
    </el-form-item>
    <el-form-item label="科目:" prop="subject.name">
      <el-input v-model="entity.subject.name" :disabled="true"/>
    </el-form-item>
    <el-form-item label="期末成绩:" prop="score">
      <el-input v-model="entity.score"/>
    </el-form-item>
    <el-form-item label="补考成绩:" prop="secondScore">
      <el-input v-model="entity.secondScore"/>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
import { getScoreWithStudent } from '@/api/score'
export default {
  mixins: [form],
  props: ['eid', 'isEdit'],
  data() {
    return {
      loading: false
    }
  },
  created() {
  },
  methods: {
    load() {
      if (this.isEdit && this.eid) {
        getScoreWithStudent(this.eid).then(e => {
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

</style>

