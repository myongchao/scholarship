<template>
  <el-form ref="form" :inline="true" :model="entity" class="demo-form-inline">
    <el-form-item label="院系名称:" prop="depId">
      <el-select v-model="entity.depId" filterable placeholder="全部">
        <el-option v-for="(item,index) in depList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
    <el-form-item label="班级名称:" prop="classId">
      <el-select v-model="entity.classId" filterable placeholder="全部">
        <el-option v-for="(item,index) in classList" :key="index" :label="item.name" :value="item.id"/>
      </el-select>
    </el-form-item>
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
import { getClassList } from '@/api/class'
import { getDepList } from '@/api/department'
export default {
  mixins: [form],
  props: ['isEdit'],
  data() {
    return {
      loading: false,
      classList: [], // 班级列表
      depList: [] // 院系列表
    }
  },
  created() {
    this.getClassAndDep()
  },
  methods: {
    getClassAndDep() {
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
    },
    open() {
      this.dialogVisible = true
    }
  }
}
</script>

<style>

</style>

