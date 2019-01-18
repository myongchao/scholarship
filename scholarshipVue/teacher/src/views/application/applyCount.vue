<template>

  <div class="count">
    <el-table :data="tableData">
      <el-table-column :index="indexMethod" type="index" align="center" width="160"/>
      <el-table-column prop="level" align="center" label="奖学金级别" width="160"/>
      <el-table-column prop="score" align="center" label="成绩标准" width="160"/>
      <el-table-column prop="credit" align="center" label="学分标准" width="160"/>
      <el-table-column prop="failed" align="center" label="是否挂科" width="160"/>
      <el-table-column prop="activity" align="center" label="活动分要求" width="160"/>
      <el-table-column prop="complex" align="center" label="综合测评" width="160"/>
      <el-table-column
        label="操作"
        align="center"
      >
        <template slot-scope="scope">
          <el-row>
            <el-button type="primary" icon="el-icon-edit" @click="editProfession(scope.row.id)"/>
            <el-button type="danger" icon="el-icon-delete" @click="deleteProfession(scope.$index)"/>
          </el-row>
        </template>
      </el-table-column>
      <!-- <el-table-column min-width="300px" label="标题">
        <template scope="scope">
          <el-input v-show="scope.row.edit" v-model="scope.row.title" size="small"/>
          <span v-show="!scope.row.edit">{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="编辑" width="120">
        <template scope="scope">
          <el-button v-show="!scope.row.edit" type="primary" size="small" icon="edit" @click="scope.row.edit=true">编辑</el-button>
          <el-button v-show="scope.row.edit" type="success" size="small" icon="check" @click="scope.row.edit=false">完成</el-button>
        </template>
      </el-table-column> -->
    </el-table>
  </div>

</template>

<script>
export default {
  data() {
    return {
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
          state: '未审核' }
      ]
    }
  },
  methods: {
    indexMethod(index) {
      return index * 1 + 1
    }
  }
}
</script>

<style scoped>
.count{
       border: 1px seashell;
       margin: 10px;
       text-align: center;
    }
</style>

