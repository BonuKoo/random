<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board List</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id="regBtn" type="button"
					class="btn btn-xs pull-right btn-primary">Register New
					Button</button>
				<!-- /.panel-heading -->
			</div>
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}"></c:out>
							<td><a href='/board/get?bno=${board.bno}'>${board.title}</a></td>
							<td><c:out value="${board.writer}"></c:out>
							<td><f:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate}"></f:formatDate>
							<td><f:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}"></f:formatDate>
						</tr>
					</c:forEach>
				</table>




				<!-- The Modal Start-->

				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Modal Heading</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">Modal body..</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>
				<!-- The Modal  END -->

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<%@ include file="../includes/footer.jsp"%>

<script>
	$(document).ready(
			function() {
				var result = '<c:out value="${result}"/>';

				checkModal(result);

				history.replaceState({},null,null);//브라우저 주소 창을 clear하는 기능
					//현재 페이지 상태를 null값으로 초기화
				function checkModal(result) {
					if (result === '' || history.state) {
						//result값이 없거나, history.state 값이 있으면 모달창을 띄우지 말라.
						return;
					}

					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글 " + parseInt(result) + "번이 등록되었습니다.");
					}

					$("#myModal").modal("show");
				}

				$("#regBtn").on("click", function() {
					self.location = "/board/register";
				});

			});
</script>