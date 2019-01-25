import request from '@/utils/request'

export function login(num, pwd) {
  return request({
    url: '/user/login?num=' + num + '&pwd=' + pwd,
    method: 'post'
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function get(token) {
  return request({
    url: '/user/get',
    method: 'get',
    params: { token }
  })
}

export function changePwd(data) {
  return request({
    url: '/user/changePwd',
    method: 'post',
    data
  })
}
