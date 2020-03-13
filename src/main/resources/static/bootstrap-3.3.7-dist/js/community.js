/**
 * @Description:
 *
 * @Author: KlayHu
 *
 * @Create: 2020/3/13 14:42
 **/
function Post() {
   var questionId = $("#question_id").val();
   var commentContent = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "parentId":questionId,
            "content":commentContent,
            "type":1
        }),
        success: function (response) {
            if(response.code==200){
                $("#comment_section").hide()
            }else{
                alert(response.message)
            }
            console.log(response)
        },
        dataType: "json"
    });
}