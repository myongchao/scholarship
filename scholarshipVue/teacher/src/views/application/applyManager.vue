<template>
  <div class="wrap">
    <el-table v-for="(item,index) in tableData1" :key="index">
      <el-table-column :index="indexMethod" type="index" align="center" width="140"/>
      <el-table-column prop="level" align="center" label="奖学金级别" width="140"/>
      <el-table-column prop="score" align="center" label="成绩" width="120"/>
      <el-table-column prop="credit" align="center" label="学分" width="120"/>
      <el-table-column prop="name" align="center" label="姓名" width="120"/>
      <el-table-column prop="address" align="center" label="地址" width="120"/>
      <el-table-column prop="date" align="center" label="日期" width="140"/>
      <el-table-column prop="item.check1" align="center" label="状态" width="140">
        <template slot-scope="scope">
          <el-input v-show="scope.row.edit" v-model="scope.row.state" size="small"/>
          <span v-show="!scope.row.edit">{{ scope.row.state }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
      >
        <template slot-scope="scope">
          <el-row>
            <el-button v-show="!scope.row.edit" type="primary" size="small" icon="edit" @click="scope.row.edit=true">编辑</el-button>
            <el-button v-show="scope.row.edit" type="success" size="small" icon="check" @click="scope.row.edit=false">完成</el-button>
            <!-- <el-button type="primary" icon="el-icon-edit" @click="editProfession(scope.row.id)"/>
            <el-button type="danger" icon="el-icon-delete" @click="deleteProfession(scope.$index)"/> -->
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <!-- <page :page="form.page" @changed="getList"/> -->
  </div>
</template>

<script>
// import page from '@/components/page'
import { recordList } from '@/api/record'
export default {
  components: {
    // page
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
        search: ''
        // orderBy: 'id desc'
      },
      departments: [], // 院系信息
      loading: false, // 加载
      multipleSelection: [], // 获取复选框被选中的
      // tableData: Array(20).fill(item)
      tableData: [
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' },
        { level: '一等奖学金',
          score: '600',
          credit: '24',
          date: '2018-11-24',
          name: 'myc',
          address: '郑州师范学院',
          state: '未审核' }
      ],
      tableData1: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    indexMethod(index) {
      return index + 1 + this.form.page.pageCount * (this.form.page.current - 1)
    },
    getList() {
      return new Promise((resolve, reject) => {
        recordList().then(response => {
          const data = response.data
          this.tableData1 = data
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    }
  }

}
</script>

<style>
    .wrap{
       border: 1px solid seashell;
       margin: 15px;
       text-align: center
    }
    body{
        background-color: #E4E7ED;
    }
</style>

