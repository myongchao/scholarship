<template>
  <div>

    <el-dialog :visible.sync="visiable" title="批量导入" width="90%">
      <el-form :inline="true">
        <el-form-item label="验证状态">
          <el-select v-model="status" @change="statusChange">
            <el-option :value="0" label="全部"/>
            <el-option :value="1" label="验证成功"/>
            <el-option :value="2" label="验证失败"/>
          </el-select>
          <el-form-item>
            <el-button type="primary" @click="save">确定导入</el-button>
          </el-form-item>
        </el-form-item>
      </el-form>
      <el-table ref="multipleTable" :data="pageList" tooltip-effect="dark" style="width: 100%">

        <el-table-column type="index" width="50"/>
        <el-table-column type="tips">
          <template slot-scope="scope">
            <div style="color:red;">{{ scope.row.tips }}</div>
          </template>
        </el-table-column>
        <el-table-column
          prop="num"
          label="学号"
          align="center"
        />
        <el-table-column
          prop="name"
          label="学生姓名"
          align="center"
        />
        <el-table-column
          prop="className"
          label="班级"
          align="center"
        />
        <el-table-column
          prop="depName"
          label="院系"
          align="center"
        />
        <el-table-column
          prop="familyAccount"
          label="家庭户口"
          align="center"
        />
        <el-table-column
          prop="familyNum"
          label="人口"
          align="center"
        />
        <el-table-column
          prop="address"
          label="家庭住址"
          align="center"
        />
        <el-table-column
          prop="inCome"
          label="年收入"
          align="center"
        />
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle @click="editClass(scope.row)"/>
            <el-button type="danger" icon="el-icon-delete" circle @click="singleDelete(scope)"/>
          </template>
        </el-table-column>
      </el-table>
      <page :page="page"/>

    </el-dialog>
    <import-class ref="edit" @success="onSuccess"/>
  </div>

</template>

<script>

import page from '@/components/page'
import importClass from '../components/importClass'
import {
  insertBatches
} from '@/api/student'

export default {
  components: {
    page,
    importClass
  },
  data() {
    return {
      source: [], // 原始数据
      importData: [], // 导入数据
      visiable: false, // 批量导入弹框
      status: 0,
      // 获取分页数据
      page: {
        pageCount: 10,
        current: 1,
        total: 0
      }
    }
  },
  computed: {
    // 绑定的分页数据
    pageList() {
      const skip = (this.page.current - 1) * this.page.pageCount

      const rtn = []
      for (let i = skip, j = 0; i < this.importData.length; i++, j++) {
        rtn[j] = this.importData[i]
      }
      return rtn
    }
  },
  methods: {
    // 打开导入数据
    open(importData) {
      this.visiable = true
      this.source = importData
      this.getList(this.source)
    },
    // 获取导入数据
    getList(data) {
      this.importData = data
      this.page.total = data.length
    },
    // 关闭导入数据
    close() {
      this.status = 0
      this.visiable = false
    },
    // 修改专业
    editClass(entity) {
      this.$refs.edit.open(entity)
    },
    // 删除单条专业
    singleDelete(scope) {
      debugger
      const curData = scope.row
      const index = this.source.indexOf(curData)

      this.$confirm('确定删除当前这一项吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        this.source.splice(index, 1)
        this.getList(this.source)
      })
    },
    // 保存导入数据
    save() {
      // const len_suc = this.source.filter(b => b.success).length
      // let msg = ''
      // if (len_suc === 0) {
      //   this.$warnmsg('当前无可导入的数据')
      //   return
      // }
      // const len_fail = this.source.filter(b => !b.success).length
      // if (len_fail > 0) {
      //   msg = '有' + len_fail + '条数据验证未通过,继续导入吗？'
      // } else {
      //   msg = '确定导入' + len_suc + '条数据吗？'
      // }
      // this.$cfm(msg, e => {
      insertBatches(this.source.filter(b => b.success)).then(e => {
        const data = e.success
        if (data) {
          this.$message({ message: '添加成功！', type: 'success' })
        } else {
          this.$message({ message: '添加失败！请规范格式', type: 'error' })
        } // this.$emit('success', data)
        this.close()
      })
      // })
    },
    // 获取保存导入数据验证信息
    onSuccess(entity) {
      let len = this.source.filter(b => b.name === entity.name).length
      if (len > 1) {
        entity.success = false
        entity.tips = '列表中存在相同的' + entity.name + '教师名称'
      }
      len = this.source.filter(b => b.code === entity.code).length
      if (len > 1) {
        entity.success = false
        entity.tips = '列表中存在相同的' + entity.code + '教师编号'
      }
    },
    // 获取验证状态
    statusChange() {
      let data
      if (this.status === 1) {
        data = this.source.filter(b => b.success === true)
      } else if (this.status === 2) {
        data = this.source.filter(b => b.success === false)
      } else {
        data = this.source
      }
      this.getList(data)
    }
  }
}

</script>

<style scoped lang="scss">
  .btns {
    padding-bottom: 5px;
  }

</style>
