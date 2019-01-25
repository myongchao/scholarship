import request from '@/utils/request'

/** 查询所有学生成绩 */
export function pageWithSubject(data) {
  return request({
    url: 'score/pageWithSubject',
    method: 'post',
    data
  })
}

/** 查询所有学生成绩 */
export function getScoreWithStudent(id) {
  return request({
    url: 'score/get',
    method: 'get',
    params: {
      id
    }
  })
}

/** 删除单个学生成绩 */
export function deleteScore(id) {
  return request({
    url: 'score/del',
    method: 'post',
    params: {
      id
    }
  })
}
