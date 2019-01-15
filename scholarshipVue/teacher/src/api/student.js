import request from '@/utils/request'

/** 添加学生信息 */
export function addStudent(data) {
  return request({
    url: 'student/create',
    method: 'post',
    data
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

/** 查询所有学生信息 */
export function searchPage(data) {
  return request({
    url: 'student/page',
    method: 'post',
    data
  })
}
