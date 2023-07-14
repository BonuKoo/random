<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form action="/board/modify" role="form" method="post">
					<input type="hidden" id="pageNum" name=pageNum value="${criteria.pageNum}">
					<input type="hidden" id="amount" name=amount value="${criteria.amount}">
				
					<div class="form-group">
						<label>Bno</label> <input class="form-control" name="bno"
							value="${board.bno}" readonly="readonly">
					</div>

					<div class="form-group">
						<label>Title</label> <input class="form-control" name="title"
							value="${board.title}">
					</div>

					<div class="form-group">
						<label>Content</label>
						<textarea class="form-control" rows="3" name="content">${board.content}</textarea>
					</div>

					<div class="form-group">
						<label>writer</label> <input class="form-control" name="writer"
							value="${board.writer}" readonly="readonly">
					</div>

					<div class="form-group">
						<label>Update Date</label>
						<input class="form-control" name='updateDate'
						value='<fmt:formatDate pattern ="yyyy/MM/dd" value="${board.updateDate}"/>' readonly="readonly">
					</div>

					<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>&nbsp;&nbsp;
					
					<button type="submit" data-oper="remove" class="btn btn-danger">remove</button>&nbsp;&nbsp;
					<button type="submit" data-oper="list" class="btn btn-info">List</button>&nbsp;&nbsp;

				<!-- <button data-oper="list" class="btn btn-info"
						onclick="location.href='/board/list'">List</button>  -->
				
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<%@ include file="../includes/footer.jsp"%>

<script>

   $(document).ready(function(){
      var formObj = $("form");
      $("button").on("click",function(e){
         e.preventDefault();
         var operation = $(this).data("oper");
         console.log(operation);
         
         if(operation === "remove") {
            formObj.attr("action","/board/remove")
      }else if(operation === "list") {
          formObj.attr("action", "/board/list").attr("method","get");
          var pageNumTag = $("input[name='pageNum']").clone();
          var amountTag = $("input[name='amount']").clone();
          
         formObj.empty();
         formObj.append(pageNumTag);
         formObj.append(amountTag);
         
      } formObj.submit();
      })
   });
</script>