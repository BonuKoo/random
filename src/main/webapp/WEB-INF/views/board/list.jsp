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
							<!-- <td><a class='move' href='/board/get?bno=${board.bno}'>${board.title}</a></td>  -->
							<td><a class='move' href='${board.bno}'>${board.title}</a></td>
							<!-- class를 move로 설정해둔 채로 , 클릭하면 /board/get?bno를 붙이고, 뒤에 get 붙이기 실행해서 브라우저에 붙임 -->
							<td><c:out value="${board.writer}"></c:out>
							<td><f:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate}"></f:formatDate>
							<td><f:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}"></f:formatDate>
						</tr>
					</c:forEach>
				</table>

				<form id="actionForm" action="/board/list" method="get">
					<input type='hidden' name=pageNum value="${pageMaker.cri.pageNum}">
					<!-- 화면 상에 안보이게 하겠다  -->
					<input type='hidden' name=amount value="${pageMaker.cri.amount}">
					<!-- 화면 상에 안보이게 하겠다  -->
				</form>
				<!--  페이징 처리 시작 -->
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.startPage-1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="page-item  ${pageMaker.cri.pageNum == num ? "active":"" }">
								<!-- class="page-link" --> <a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next }">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.endPage+1}">Next</a></li>
							<!-- next 누르면 11 page로 넘어감 -->
						</c:if>
					</ul>
				</div>
				<!--  페이징 처리 끝 -->
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
							<div class="modal-body">처리 완료</div>

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

				history.replaceState({}, null, null);//브라우저 주소 창을 clear하는 기능
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

				var actionForm = $("#actionForm");
				$(".page-item a").on(
						"click",
						function(e) { //62 줄 page-item 를 누르면 이동 : 책의 paginate_button 부분 
							e.preventDefault();
							console.log("click");
							actionForm.find("input[name='pageNum']").val(
									$(this).attr("href"));
							actionForm.submit();
						});
			
				//상세페이지 이동 시 bno 뿐만 아니라 pageNum과 amount값도 같이 넘겨준다.
				//<td><a class='move' href='/board/get?bno=${board.bno}'>${board.title}</a></td> 약 39째 줄의 move로 이동
				$(".move").on("click", function(e){
					e.preventDefault();
					actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href")+"'>");
					actionForm.attr("action", "/board/get");
					actionForm.submit();
				});
				//<!--class를 move로 설정해둔 채로 , 클릭하면 /board/get?bno를 붙이고, 뒤에 get 붙이기 실행해서 브라우저에 붙임 -->
			});
</script>