let index = {
    init:function(){
        $("#btn-save").on("click",()=>{ // function(){} 대신 ()=> 사용한 이유는 this를 바인딩하기 위해서
            this.save();
        });

        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });

        $("#btn-update").on("click",()=>{
            this.update();
        });

        $("#btn-reply-save").on("click",()=>{
            this.replySave();
        });
    },

    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            // alert(resp);
            alert("글쓰기가 완료 되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert("js 글쓰기완료 호출"+JSON.stringify(error));
        });
    },// save end

    deleteById: function(){
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function(resp){
            // alert(resp);
            alert("삭제가 완료 되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert(id+"js 삭제에러 호출"+JSON.stringify(error));
        });
    },// deleteById end

    update: function(){
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            // alert(resp);
            alert("글 수정이 완료 되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert("js 글수정이 호출"+JSON.stringify(error));
        });
    },// update end

//    replySave: function () {
//        let data = {
//            content: $("#reply-content").val(),
//        };
//        let boardId = $("#boardId").val();
//
//        $.ajax({
//            type: "POST",
//            url: `/api/board/${boardId}/reply`,
//            data: JSON.stringify(data),
//            contentType: "application/json; charset=utf-8",
//            dataType: "json",
//        }).done(function (resp) {
//            alert("댓글작성이 완료되었습니다.");
//            location.href = `/board/${boardId}`;
//        }).fail(function (error) {
//            alert(JSON.stringify(error));
//        });
//    },

    replySave: function () {
        let data = {
            userId : $("#userId").val(),
            boardId : $("#boardId").val(),
            content: $("#reply-content").val(),
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (resp) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    replyDelete: function (boardId,replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json",
        }).done(function (resp) {
            alert("댓글 삭제 완료되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },



}// index end
index.init();



















