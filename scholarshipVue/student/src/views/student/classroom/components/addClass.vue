<template>
  <el-dialog
    :visible.sync="dialogVisible"
    title="添加学生信息"
    width="30%">
    <new-class ref="form" :is-edit="false"/>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import newClass from './newClass'
import { addStudent } from '@/api/student'
export default {
  components: {
    newClass
  },
  data() {
    return {
      dialogVisible: false,
      eid: undefined
    }
  },
  methods: {
    save() {
      const value = this.$refs.form.getValue()
      addStudent(value).then(e => {
        if (e.success) {
          this.$message({
            message: '添加学生信息成功',
            type: 'success'
            // center: true
          })
          this.close()
        }
      })
    },
    open() {
      this.dialogVisible = true
    },
    close() {
      this.$refs.form.reset()
      this.dialogVisible = false
    }
  }
}
</script>
