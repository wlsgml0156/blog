<%@ page language="java" contentType="text/html;charset=UTF-8 " pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <h2>Stacked form</h2>
  <form action="/auth/loginProc" method="post">

    <div class="form-group">
      <label for="username">username:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>


    <button id="btn-login" class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=a32fc77e17469c5118302c27b8e29ee2&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code">
        <img style="height="38px"" src="/image/kakao_login_button.png">
    </a>
  </form>

</div>


<%@ include file="../layout/footer.jsp"%>

