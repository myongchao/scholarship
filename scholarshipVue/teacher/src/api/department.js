import request from '@/utils/request'

/** 获取所有院系 */
export function getDepList(data) {
  return request({
    url: 'department/list',
    method: 'get',
    params: {
      data
    }
  })
}
