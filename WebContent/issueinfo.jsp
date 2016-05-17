<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>配送信息</title>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var table = $("#bookInfoTab").DataTable({});
	
	
	$("#search").click(function(){
		$btn = $(this).button("loading");
		
		var begin = $("#startDate").val();
		var end = $("#endDate").val();
		
		$.ajax({
			url : 'search.action',
			type : 'post',
			data : {'beginDate' : begin,'endDate' : end},
			dataType : 'json',
			success : function(e){
				var data = eval("(" + e +")");
				table.clear().draw(false);
				for (var i = 0; i < data.length; i++) {
					var obj = [
								data[i].code,data[i].beginDate,
							   	data[i].endDate,data[i].bookDeliveryType,
							   	data[i].printTime,data[i].currentCount,
							   	data[i].maxCount,data[i].deliveryCount,
							   	data[i].reIssue,data[i].memo
							  ];
					table.row.add(obj).draw(false);
				}
				$btn.button("reset");
			},
			error : function(e){
				$btn.button("reset");
				alert("查询出错！请联系管理员！" );
			}
		})
	});
	
	$("#export").click(function(){
		var begin = $("#startDate").val();
		var end = $("#endDate").val();
		var param = "?begin=" + begin + "&end=" + end;
		window.location.href = "export.action" + param;
	});
});
</script>
</head>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
        		<img alt="" src="<%=request.getContextPath()%>/img/logo.png">
      		</a>
		</div>
		<div class="collapse navbar-collapse" style="margin-left: auto;margin-right: auto;width: 70%;">
			<ul class="nav nav-pills">
  				<li role="presentation" class="active"><a href="#">配送信息</a></li>
  				<li role="presentation"><a href="<%=request.getContextPath()%>/import.jsp">导入</a></li>
			</ul>
		</div>
	</div>
</nav>
<br/>
<div style="width: 90%;margin-left: auto;margin-right: auto;" class="well well-lg">
	<form class="form-inline">
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">起始时间</div>
      			<input type="text" class="form-control" id="startDate" placeholder="起始时间" onclick="WdatePicker()">
    		</div>
  		</div>
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		<div class="form-group">
    		<div class="input-group">
      			<div class="input-group-addon">截止时间</div>
      			<input type="text" class="form-control" id="endDate" placeholder="截止时间" onclick="WdatePicker()">
    		</div>
  		</div>
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		<input type="button" class="btn btn-primary" id="search" value="查询" data-loading-text="查询中">
  		<input type="button" class="btn btn-success" id="export" value="导出">
	</form>
</div>
<div class="panel panel-primary" style="width: 90%;margin-left: auto;margin-right: auto;">
  <div class="panel-heading">教材配送情况</div>
  <table id="bookInfoTab" class="table table-striped table-bordered dataTable" style="width: 100%" aria-describedby="example_info" role="grid" cellspacing="0" width="100%">
  		<thead>
  			<tr>
  				<th>班级编码</th>
  				<th>开课日期</th>
  				<th>结课日期</th>
  				<th>教材发放形式</th>
  				<th>上课时间（打印）</th>
  				<th>当前人数</th>
  				<th>最大人数</th>
  				<th>发放量</th>
  				<th>补发量</th>
  				<th>备注</th>
  			</tr>
  		</thead>
  		<tbody>
  		</tbody>
  		<tfoot>
  			<tr>
  				<th>班级编码</th>
  				<th>开课日期</th>
  				<th>结课日期</th>
  				<th>教材发放形式</th>
  				<th>上课时间（打印）</th>
  				<th>当前人数</th>
  				<th>最大人数</th>
  				<th>发放量</th>
  				<th>补发量</th>
  				<th>备注</th>
  			</tr>
  		</tfoot>
  	</table>
</div>
</body>
</html>