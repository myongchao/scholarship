import request from '@/utils/request'

/** 添加奖学金信息 */
export function addAward(data) {
  return request({
    url: '',
    method: 'post',
    params: {
      data
    }
  })
}

/** 查询所有奖学金类型 */
export function awardList(data) {
  return request({
    url: '/award/list',
    method: 'get',
    data
  })
}

/** 分页奖学金类型 */
export function searchPage(data) {
  return request({
    url: '/award/page',
    method: 'post',
    data
  })
}
