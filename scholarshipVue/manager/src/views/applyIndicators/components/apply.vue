<template>
  <el-form ref="form" :inline="true" :model="entity" label-width="100px">
    <el-form-item label="奖学金名称:" prop="title">
      <el-input v-model="entity.title"/>
    </el-form-item>
    <el-form-item label="奖学金等级:" prop="bgrade">
      <el-input v-model="entity.bgrade"/>
    </el-form-item>
    <el-form-item label="金额:" prop="amount">
      <el-input v-model="entity.amount"/>
    </el-form-item>
    <el-form-item label="最低分数:" prop="minScore">
      <el-input v-model="entity.minScore"/>
    </el-form-item>
    <el-form-item label="最低学分:" prop="subjectScore">
      <el-input v-model="entity.subjectScore"/>
    </el-form-item>
    <el-form-item label="班级排名:" prop="rank">
      <el-input v-model="entity.rank"/>
    </el-form-item>
    <el-form-item label="挂科要求:" prop="status">
      <el-input v-model="entity.status"/>
    </el-form-item>
  </el-form>
</template>

<script>
import {
  form
} from '@/mixins/form'
import { getOneAward } from '@/api/award'
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
        getOneAward(this.eid).then(e => {
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

