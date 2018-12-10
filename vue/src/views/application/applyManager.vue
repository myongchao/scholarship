<template>
    <div class="wrap">
        <el-table :data="tableData">
            <el-table-column type="index" :index="indexMethod" align="center" width="140">
            </el-table-column>
            <el-table-column prop="level" align="center" label="奖学金级别" width="140">
            </el-table-column>
            <el-table-column prop="score" align="center" label="成绩" width="120">
            </el-table-column>
            <el-table-column prop="credit" align="center" label="学分" width="120">
            </el-table-column>
            <el-table-column prop="name" align="center" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" align="center" label="地址" width="120">
            </el-table-column>
            <el-table-column prop="date" align="center" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="state" align="center" label="状态" width="140">
                <template scope="scope">
                    <el-input v-show="scope.row.edit" size="small" v-model="scope.row.state"></el-input>
                    <span v-show="!scope.row.edit">{{ scope.row.state }}</span>
                </template>
            </el-table-column>
             <el-table-column
              label="操作"
              align="center"
              >
               <template slot-scope="scope">
                <el-row>
                <el-button v-show='!scope.row.edit' type="primary" @click='scope.row.edit=true' size="small" icon="edit">编辑</el-button>
                <el-button v-show='scope.row.edit' type="success" @click='scope.row.edit=false' size="small" icon="check">完成</el-button>
                <el-button type="primary" icon="el-icon-edit"  @click="editProfession(scope.row.id)"></el-button>
                <el-button type="danger" icon="el-icon-delete"  @click="deleteProfession(scope.$index)"></el-button>
                </el-row>
               </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    data(){
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
            tableData: Array(20).fill(item)
         }
    },
    methods:{
      indexMethod(index) {
        return index * 1 + 1;
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

