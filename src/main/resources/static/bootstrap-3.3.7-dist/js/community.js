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
   if(!commentContent){
       alert("回复评论不能为空~");
       return
   }
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
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                }if(isAccepted){
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.3129381aab788a9b&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                    window.localStorage.setItem("closable","true")
                }else{
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
}