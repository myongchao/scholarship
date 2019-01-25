import request from '@/utils/request'

/** 获取所有班级 */
export function getClassList(data) {
  return request({
    url: 'classroom/list',
    method: 'get',
    params: {
      data
    }
  })
}
