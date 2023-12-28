<%@ page language="java" contentType="text/html;charset=UTF-8 " pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
  <h2>Stacked form</h2>
  <form>

    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
    </div>

    <div class="form-group">
      <label for="content">Content:</label><br>
      <textarea class="form-control summernote" rows="5" id="content"></textarea>
    </div>
  </form>
  <button id="btn-save" class="btn btn-primary">글작성완료</button>

</div>

<script>
$('.summernote').summernote({
    placeholder: 'Write Content',
    tabsize: 2,
    height: 300
});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

