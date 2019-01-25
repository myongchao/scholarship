import request from '@/utils/request'

/** 提交申请信息 */
export function submitApply(data) {
  return request({
    url: '/record/add',
    method: 'post',
    data
  })
}

/** 查询列表信息 */
export function recordList(data) {
  return request({
    url: '/record/list',
    method: 'get',
    data
  })
}

/** 删除个人申请信息 */
export function deleteRecord(id) {
  return request({
    url: '/record/del',
    method: 'post',
    params: {
      id
    }
  })
}

export function update(id, check) {
  return request({
    url: '/record/edit',
    method: 'post',
    params: {
      id,
      check
    }
  })
}

export function searchWithPage(data) {
  return request({
    url: '/record/pageRecord',
    method: 'post',
    data
  })
}
