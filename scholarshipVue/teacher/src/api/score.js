import request from '@/utils/request'

/** 查询所有学生成绩 */
export function pageWithSubject(data) {
  return request({
    url: 'score/pageWithSubject',
    method: 'post',
    data
  })
}
