<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">Board Register</h1>
   </div>
   <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
         <div class="panel-heading">Board Register</div>
         <!-- /.panel-heading -->
         <div class="panel-body">
            <form role="form" action="/board/register" method="post">
               <div class="form-group">
                  <label>Title</label> <input name="title" class="form-control">
               </div>
               <div class="form-group">
                  <label>Text Area</label>
                  <textarea rows="3" name="content" class="form-control"></textarea>
               </div>
               <div class="form-group">
                  <label>Writer</label><input name="writer" class="form-control">
               </div>

               <button type="submit">Submit Button</button>&nbsp;&nbsp;&nbsp;
               <button type="reset">Reset Button</button>
            </form>

         </div>
         <!-- /.panel-body -->
      </div>
      <!-- /.panel -->
   </div>
   <!-- /.col-lg-12 -->
</div>


<%@ include file="../includes/footer.jsp"%>