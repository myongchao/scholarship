import request from '@/utils/request'

/** 添加学生信息 */
export function addStudent(data) {
  return request({
    url: 'student/create',
    method: 'post',
    data
  })
}
/** 批量添加学生信息 */
export function insertBatches(list) {
  return request({
    url: 'student/multipleAdd',
    method: 'post',
    data: {
      data: list
    }
  })
}
/** 删除学生信息 */
export function deleteStudent(id) {
  return request({
    url: 'student/del',
    method: 'post',
    params: {
      id
    }
  })
}
/** 修改学生信息 */
export function editStudent(data) {
  return request({
    url: 'student/edit',
    method: 'post',
    data
  })
}
/** 获取单个学生信息 */
export function getStudentOne(id) {
  return request({
    url: 'student/get',
    method: 'get',
    params: {
      id
    }
  })
}
/** 查询所有学生信息 */
export function getStudentList(data) {
  return request({
    url: 'student/list',
    method: 'get',
    data
  })
}

/** 分页查询所有学生信息 */
export function searchPage(data) {
  return request({
    url: 'student/page',
    method: 'post',
    data
  })
}

/** 下载学生上传模板 */
export function downTemplate(data) {
  return request({
    url: 'student/download/template',
    method: 'get',
    data
  })
}
