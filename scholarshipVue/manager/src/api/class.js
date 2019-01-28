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

/** 分页获取所有班级 */
export function pageClass(data) {
  return request({
    url: 'classroom/pageClassroom',
    method: 'post',
    data
  })
}

export function getOneClass(id) {
  return request({
    url: 'classroom/get',
    method: 'get',
    params: {
      id
    }
  })
}

export function addClass(data) {
  return request({
    url: 'classroom/add',
    method: 'post',
    data
  })
}

export function deleteClassroom(id) {
  return request({
    url: 'classroom/del',
    method: 'post',
    params: { id }
  })
}
