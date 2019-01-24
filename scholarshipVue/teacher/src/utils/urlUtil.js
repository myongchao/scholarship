function getQueryParams() {
  let search = window.location.search
  search = search.replace('?', '')
  const params = search.split('&')
  const paramObj = {}
  params.forEach(e => {
    if (e) {
      const kv = e.split('=')
      if (kv.length === 2) {
        paramObj[kv[0]] = kv[1]
      }
    }
  })
  return paramObj
}
export { getQueryParams }
