<template>
  <div class="wrap">
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
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    const item = {
      level: '一等奖学金',
      score: '450',
      credit: '24',
      failed: '否',
      activity: '5',
      complex: '520'
    }
    return {
      tableData: Array(20).fill(item)
    }
  },
  methods: {
    indexMethod(index) {
      return index * 1 + 1
    }
  }

}
</script>

<style>
    .wrap{
       border: 1px solid silver;
       border-left: 0px;
       margin-left: 50px;
    }
</style>

