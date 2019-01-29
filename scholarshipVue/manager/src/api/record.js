import request from '@/utils/request'

/** 查询列表信息 */
export function recordList(data) {
  return request({
    url: '/record/list',
    method: 'get',
    data
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

export function updateManager(id, check) {
  return request({
    url: '/record/editManager',
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
