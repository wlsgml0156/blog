let index = {
    init:function(){
        $("#btn-save").on("click",()=>{ // function(){} 대신 ()=> 사용한 이유는 this를 바인딩하기 위해서
            this.save();
        });

//        $("#btn-login").on("click",()=>{ // function(){} 대신 ()=> 사용한 이유는 this를 바인딩하기 위해서
//            this.login();
//        });
        $("#btn-update").on("click",()=>{ // function(){} 대신 ()=> 사용한 이유는 this를 바인딩하기 위해서
            this.update();
        });
    },

    save: function(){
        // alert('user의 save호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        // console.log(data);

        $.ajax({
            type: "POST",
//            url: "/api/user",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res){
//            if(res.status===500){
//                alert("회원가입에 실패하였습니다.");
//            }else{
//
//            }
            alert("회원가입이 완료 되었습니다.");
            location.href="/";
        }).fail(function(error){
            alert("회원가입에 실패하였습니다.");
//            alert(JSON.stringify(error));
        });


    },// save end

    login: function(){
        // alert('user의 save호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        }
        // console.log(data);

        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            // alert(resp);
            alert("로그인이 완료 되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    },// login end

    update: function(){
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            // alert(resp);
            alert("회원수정이 완료 되었습니다.")
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });


    },// update end

}// index end
index.init();



















