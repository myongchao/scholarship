/**
 * 查询mixin
 */
export const search = {
  data() {
    return {
      form: {
        orderBy: 'id',
        search: '',
        page: {
          current: 1,
          pageCount: 10,
          total: 0
        }
      },
      loading: false
    }
  }
}
