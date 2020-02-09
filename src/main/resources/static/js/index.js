let comment = {
  post: function () {
    let parentId = $('#comment_parentId').val()
    let content = $('#comment_content').val()
    let type = $('#comment_type').val()
    let param = {parentId, content, type}
    if (!content) {
      return alert('评论内容不能为空')
    }
    $.ajax({
      contentType: 'application/json',
      type: 'post',
      url: '/comment',
      dataType: 'json',
      data: JSON.stringify(param),
      success: function (result) {
        if (result.code !== 200) {
          window.confirm(result.message)
          window.open('https://github.com/login/oauth/authorize?client_id=ad21b80d19204366718d&redirect_uri=http://localhost:8080/callback&scope=user&state=1111')
          localStorage.setItem('closable', true)
          return
        }
        $('#comment').hide()
        location.reload()
      }
    })
  },
  getData: function getData() {
    let parentId = $('#comment_parentId').val()
    $.ajax({
      contentType: 'application/json',
      type: 'get',
      url: '/comment/' + parentId,
      success: function (result) {
        console.log(result)
      }
    })
  }

}

let common = {
  login: function () {
    location.href = 'https://github.com/login/oauth/authorize?client_id=ad21b80d19204366718d&redirect_uri=http://localhost:8080/callback&scope=user&state=1111'
  }
}

window.onload = function () {
  comment.getData()
}
