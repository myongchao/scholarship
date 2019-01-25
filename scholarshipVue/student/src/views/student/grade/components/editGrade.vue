<template>
  <el-dialog
    :visible.sync="dialogVisible"
    title="修改成绩信息"
    width="30%">
    <grade ref="form" :eid="eid" :is-edit="true"/>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Grade from './grade'
import { editStudent } from '@/api/student'
export default {
  components: {
    Grade
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
      editStudent(value).then(e => {
        if (e.success) {
          this.$message({
            message: '修改学生成绩成功',
            type: 'success'
            // center: true
          })
          this.close()
        }
      })
    },
    open(id) {
      this.eid = id
      this.dialogVisible = true
    },
    close() {
      this.eid = 0
      this.$refs.form.reset()
      this.dialogVisible = false
    }
  }
}
</script>
