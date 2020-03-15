/**
 * @Description:
 *
 * @Author: KlayHu
 *
 * @Create: 2020/3/13 14:42
 **/


/**
 * 提交回复
 */
function Post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentToTarget(questionId, 1, content)
}

function commentToTarget(targetId, type, content) {
    if (!content) {
        alert("回复评论不能为空~");
        return
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                }
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.3129381aab788a9b&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                    window.localStorage.setItem("closable", "true")
                } else {
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#reply-" + commentId).val();
    commentToTarget(commentId, 2, content)
}


/**
 *二级评论弹框
 */

function commentStatement(e) {
    var id = e.getAttribute("data-id");
    var comment = $('#comment-' + id);

    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠二级评论
        comment.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active")
    } else {
        $.getJSON("/comment/" + id, function (data) {
            console.log(data);
            // var items = [];
            // var commentBody = $("#comment-body-" + id);
            // $.each( data.data, function( comment ) {
            //
            //     var child = $("<div/>", {
            //         "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-c",
            //         html:comment.content
            //     });
            //     items.push(child);
            // });
            //
            // commentBody.append($("<div/>", {
            //     "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments",
            //     "id":"comment-"+id,
            //     html:items.join("")
            // }));


            //展开二级评论
            comment.addClass("in");
            //标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active")
        });
    }
}