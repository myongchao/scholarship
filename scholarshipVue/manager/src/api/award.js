import request from '@/utils/request'

/** 添加奖学金信息 */
export function addAward(data) {
  return request({
    url: 'award/add',
    method: 'post',
    data
  })
}

/** 查询单个奖学金信息 */
export function getOneAward(id) {
  return request({
    url: 'award/get',
    method: 'get',
    params: {
      id
    }
  })
}

/** 修改奖学金信息 */
export function editAward(data) {
  return request({
    url: 'award/edit',
    method: 'post',
    data
  })
}

/** 修改奖学金信息 */
export function deleteAward(id) {
  return request({
    url: 'award/del',
    method: 'post',
    params: {
      id
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
