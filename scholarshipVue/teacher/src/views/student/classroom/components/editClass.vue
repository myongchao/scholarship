<template>
  <el-dialog
    :visible.sync="dialogVisible"
    title="修改学生信息"
    width="30%">
    <class ref="form" :eid="eid" :is-edit="true"/>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import Class from './class'
import { editStudent } from '@/api/student'
export default {
  components: {
    Class
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
            message: '修改学生信息成功',
            type: 'success'
            // center: true
          })
          this.close()
        }
      })
    },
    // handleClose(done) {
    //   this.$confirm('确认关闭？')
    //     .then(_ => {
    //       done()
    //     })
    //     .catch(_ => {})
    // },
    // 打开学生弹窗
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
