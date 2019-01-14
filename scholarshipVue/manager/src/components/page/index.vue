<template>
  <div class="page">
    <el-pagination
      :current-page.sync="page.current"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="page.pageCount"
      :total="page.total"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="sizeChange"
      @current-change="pageChange"/>
  </div>
</template>

<script>
export default {
  props: ['page'],
  methods: {
    pageChange(cur) {
      this.page.current = cur
      this.$emit('changed')
    },
    sizeChange(size) {
      this.page.pageCount = size
      this.$emit('changed')
    }
  }
}

</script>

<style scoped lang="scss">
  .page {
    padding: 5px 0px;
    text-align: right;
  }

</style>
