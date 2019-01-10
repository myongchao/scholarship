<template>
  <div class="header">
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
    <div class="select">
      <el-table :data="tableData">
        <el-table-column :index="indexMethod" type="index" align="center" width="160"/>
        <el-table-column prop="level" align="center" label="奖学金级别" width="160"/>
        <el-table-column prop="score" align="center" label="成绩" width="160"/>
        <el-table-column prop="credit" align="center" label="学分" width="160"/>
        <el-table-column prop="name" align="center" label="姓名" width="160"/>
        <el-table-column prop="address" align="center" label="地址" width="160"/>
        <el-table-column prop="date" align="center" label="日期" width="160"/>
        <el-table-column prop="state" align="center" label="状态" width="160"/>
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
      tableData: Array(20).fill(item),
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
  body{
    background-color: #E4E7ED;
  }
  .header{
    margin-top: 10px;
  }
  .el-input {
    width: 50%;
  }
  .components-container{
    margin-left: 10px;
  }
  .select{
       border: 1px solid seashell;
       margin: 10px;
       text-align: center;
    }
</style>
