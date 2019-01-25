<template>
  <div class="dashboard-container">
    <!-- <div class="dashboard-text">name:{{ name }}</div>
    <div class="dashboard-text">roles:<span v-for="role in roles" :key="role">{{ role }}</span></div> -->
    <img :src="logo_img">
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import logo_img from '@/assets/1.jpg'

export default {
  name: 'Dashboard',
  data() {
    return {
      logo_img
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }

}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
img{
      margin-top: 200px;
      margin-left: 200px;
      width: 70%;
      height: 300px;
    }
</style>
