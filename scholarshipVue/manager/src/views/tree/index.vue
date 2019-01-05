<template>
  <div class="tree">
    <div class="components-container">
      <template>
        <el-select v-model="value" placeholder="奖学金类型" style="height: 20px;">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>
        <el-button slot="append" type="primary" icon="el-icon-search" style="height: 75%;" >搜索</el-button>
      </template>
    </div>
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
  </div>
</template>

<script>

export default {
  name: '',
  data() {
    const item = {
      level: '一等奖学金',
      score: '600',
      credit: '24',
      date: '2018-11-24',
      name: 'myc',
      address: '郑州师范学院',
      state: '未审核'
    }
    return {
      input3: '',
      tableData: Array(0).fill(item),
      options: [{
        value: '选项1',
        label: '一等奖学金'
      }, {
        value: '选项2',
        label: '二等奖学金'
      }, {
        value: '选项3',
        label: '国家奖学金'
      }],
      value: ''
    }
  },
  methods: {
    indexMethod(index) {
      return index * 1 + 1
    }
  }
}
</script>

<style  scoped>
  .tree{
    margin-top: 10px;
  }
  .components-container{
    margin-left: 50px;
  }
  .el-input {
    width: 50%;
  }
  .wrap{
       border: 1px solid silver;
       border-left: 0px;
       margin-left: 50px;
       margin-top: 10px;
       text-align: center;
    }
</style>
