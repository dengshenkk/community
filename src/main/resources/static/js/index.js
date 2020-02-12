let commentObj = {
  post: function () {
    let questionId = $('#comment_parentId').val()
    let content = $('#comment_content').val()
    this.comment2target(questionId, 1, content)
    location.reload()
  },
  subComment: function (e) {
    console.log(e)
    let input = $(e).data('input')
    console.log(input)
    let content = $('#input-' + input).val()
    this.comment2target(input, 2, content)
    $('#input-' + input).val('')
    setTimeout(() => {
      this.getSubComment(e, input)
    }, 100)

  },
  comment2target: function (parentId, type, content) {
    if (!content) {
      return alert('评论内容不能为空')
    }
    $.ajax({
      contentType: 'application/json',
      type: 'post',
      url: '/comment',
      dataType: 'json',
      data: JSON.stringify({parentId, content, type}),
      success: function (result) {
        if (result.code !== 200) {
          window.confirm(result.message)
          window.open('https://github.com/login/oauth/authorize?client_id=ad21b80d19204366718d&redirect_uri=http://localhost:8080/callback&scope=user&state=1111')
          localStorage.setItem('closable', true)
          return
        }
        $('#comment').hide()
      }
    })
  },
  getSubComment: function getData(e) {
    let inputId = $(e).data('input')
    let subCommentBox = $('#comment-' + inputId + ' .well .comment-box')
    $.ajax({
      contentType: 'application/json',
      type: 'get',
      url: '/comment/' + inputId,
      success: function (result) {
        console.log(111)
        console.log(result)
        $(subCommentBox).empty()
        result.data.forEach(comment => {
          let commentItem = $(`<div class="media sub-comment-list">
                    <div class="media-left">
                      <img class="media-object avatar" src="${comment.user.avatarUrl}">
                    </div>
                    <div class="media-body">
                      <h5 class="media-heading">
                        <span>${comment.user.name}</span>
                      </h5>
                      <div class="text-content">${comment.content}</div>
                         <div class="text-desc">
                            <span class="pull-right">${utils.formatDate(comment.gmtCreate, 'full')}</span>
                    </div>  
                    </div>
                  </div>`)
          subCommentBox.append(commentItem)
        })
      }
    })
  }

}

let questionObj = {
  postComment: function (e) {
    $(e).toggleClass('in')
    commentObj.getSubComment(e)
  }
}

let common = {
  login: function () {

    location.href = 'https://github.com/login/oauth/authorize?client_id=ad21b80d19204366718d&redirect_uri=http://localhost:8080/callback?pathname=' + location.pathname + '&scope=user&state=1111'
  }
}

let utils = {
  formatDate: function (time, type = 'date') {
    if (!time) {
      return ''
    }
    let date = new Date(Number(time))
    let Y = date.getFullYear()
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1)
    let D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    let h = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    let m = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    switch (type) {
      case 'date':
        return `${Y}-${M}-${D}`
      case 'time':
        return `${h}:${m}:${s}`
      case 'full':
        return `${Y}-${M}-${D} ${h}:${m}:${s}`
      default:
        return `${Y}-${M}-${D}`
    }
  }


}
