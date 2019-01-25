<template>
  <div class="maincontent">
    <div class="userinfo">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="baseinfo">
          <user-base-info/>
        </el-tab-pane>
        <el-tab-pane label="密码修改" name="resetPwd">
          <reset-pwd/>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import userBaseInfo from './userBaseInfo'
import resetPwd from './resetPwd'
export default {
  components: {
    userBaseInfo, resetPwd
  },
  data() {
    return {
      activeName: 'baseinfo'
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style scoped>
 .maincontent{
    /* margin-top: 10px; */
    margin: 15px;
    background-color: #fff;
 }
 .userinfo{
     margin-left: 10px;
 }
</style>

