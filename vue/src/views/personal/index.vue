<template>
    <div style="margin: 20px auto" class="wrap">
        <table class="m">
           <tr>
               <td class="m">账号(学号)：</td>
               <td>123456</td>
           </tr>
           <tr>
               <td class="m">姓名：</td>
               <td>myc</td>
           </tr>
           <tr>
               <td class="m">联系方式：</td>
               <td>15136127135</td>
           </tr>
           <tr>
               <td class="m">班级信息：</td>
               <td>B15计科一班</td>
           </tr>
        </table>
    </div>
</template>

<script>
export default {
    data() {
      return {
   
      };
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


