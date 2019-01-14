<template>
  <div style="margin: 20px auto" class="wrap">
    <table class="m">
      <tr>
        <td class="m">账号：</td>
        <td>{{ admin.num }}</td>
      </tr>
      <tr>
        <td class="m">工号：</td>
        <td>{{ admin.code }}</td>
      </tr>
      <tr>
        <td class="m">姓名：</td>
        <td>{{ admin.name }}</td>
      </tr>
      <tr>
        <td class="m">角色：</td>
        <td>{{ admin.role }}</td>
      </tr>
      <tr>
        <td class="m">院系：</td>
        <td>{{ admin.dep.name }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import { get } from '@/api/login'
import store from '@/store'
export default {
  data() {
    return {
      admin: undefined
    }
  },
  created() {
    this.getStudent()
  },
  methods: {
    getStudent() {
      get(store.getters.token).then(e => {
        this.admin = e.data
      })
    }
  }
}
</script>

<style>
   *{
       margin: 0;
       padding: 0;
   }
   .m{
       text-align: center;
   }
   .wrap{
       position: absolute;
       left: 25%;
       top: 30%;
       transform: translate3d(-50%,-50%,0)；
   }
   table{
       width: 800px;
       height: 200px;
   }
   tr{
       width: 200px;
   }
   td{
       border: 0.5px solid #dddddd;
       margin: 5px 5px;
       border-color: #E4E7ED;
   }
</style>

