<template>
  <div class="manager">
    <el-table :data="tableData">
      <el-table-column :index="indexMethod" type="index" label="序号" align="center" width="180"/>
      <el-table-column prop="num" align="center" label="学号" width="120"/>
      <el-table-column prop="name" align="center" label="姓名" width="120"/>
      <el-table-column prop="classroom.name" align="center" label="班级" width="120"/>
      <el-table-column prop="dep.name" align="center" label="院系" width="120"/>
      <el-table-column prop="teacher.name" align="center" label="辅导员" width="120"/>
      <el-table-column prop="familyAccount" align="center" label="家庭户口" width="120"/>
      <el-table-column prop="familyNum" align="center" label="人口" width="120"/>
      <el-table-column prop="address" align="center" label="地址" width="120"/>
      <el-table-column prop="zipCode" align="center" label="邮编" width="120"/>
      <el-table-column prop="inCome" align="center" label="年收入" width="120"/>
      <el-table-column
        label="操作"
        align="center"
      >
        <template slot-scope="scope">
          <el-button type="primary" size="small" icon="el-icon-edit" plain @click="editStudent(scope.row.id)"/>
          <el-button type="danger" size="small" icon="el-icon-delete" plain @click="deleteStudent(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <page :page="form.page" @changed="getList"/>
    <edit-student ref="edit" @success="getList"/>
  </div>
</template>

<script>
import page from '@/components/page'
import editStudent from './components/editStudent'
import { searchPage, deleteStudent
} from '@/api/student'
export default {
  components: {
    page,
    editStudent
  },
  data() {
    return {
      // 分页和模糊查询
      form: {
        page: {
          current: 1,
          pageCount: 10,
          total: 0
        },
        search: '',
        orderBy: 'id desc'
      },
      loading: false, // 加载
      tableData: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      searchPage(this.form).then(e => {
        const data = e.data.records
        this.form.page.total = e.data.total
        this.tableData = data
      })
    },
    editStudent(id) {
      this.$refs.edit.open(id)
    },
    deleteStudent(index) {
      this.$confirm('很重要的信息，你确定删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(_ => {
        deleteStudent(this.tableData[index].id).then(e => {
          if (e.success) {
            this.getList()
            this.form.page.total--
            this.$message({
              type: 'success',
              message: '删除成功！'
            })
          } else {
            this.$message({
              type: 'error',
              message: '删除失败！'
            })
          }
        })
      }).catch(_ => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    }
  }

}
</script>

<style scoped>
   .manager-page{
     background-color: #E4E7ED;
   }
    .manager{
       border: 1px solid seashell;
       margin: 15px;
       text-align: center
    }
    .el-input {
    width: 75%;
   }
    .edit-input {
       padding-right: 80px;
    }
    .cancel-btn {
      position: absolute;
      right: 15px;
      top: 18px;
    }
    .active{
      color: rgb(21, 124, 124)
    }
</style>

