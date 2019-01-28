import request from '@/utils/request'

/** 添加教师信息 */
export function add(data) {
  return request({
    url: 'teacher/add',
    method: 'post',
    data
  })
}

/** 添加教师信息 */
export function addTeacher(list) {
  return request({
    url: 'teacher/multipleAdd',
    method: 'post',
    data: {
      data: list
    }
  })
}

/**  */
export function pageTeacher(data) {
  return request({
    url: 'teacher/pageTeacher',
    method: 'post',
    data
  })
}

export function getTeacherList(data) {
  return request({
    url: 'teacher/list',
    method: 'get',
    params: {
      data
    }
  })
}

/** 获取单个教师信息 */
export function getTeacher(id) {
  return request({
    url: 'teacher/get',
    method: 'get',
    params: {
      id
    }
  })
}

/** 修改教师信息 */
export function editTeacher(data) {
  return request({
    url: 'teacher/edit',
    method: 'post',
    data
  })
}

/** 修改教师信息 */
export function deletedTeacher(id) {
  return request({
    url: 'teacher/del',
    method: 'post',
    params: {
      id
    }
  })
}

