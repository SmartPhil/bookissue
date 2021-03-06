<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导入</title>
<link href="<%=request.getContextPath()%>/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/uploadify/uploadify.css" rel="stylesheet"  type="text/css" media="screen"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/uploadify/jquery.uploadify.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	//覆盖导入
	$("#file_upload").uploadify({
		'swf' : '<%=request.getContextPath()%>/uploadify/uploadify.swf',  
	    'uploader' : '<%=request.getContextPath()%>/importExcel.action',  
	    'cancelImg'      : '<%=request.getContextPath()%>/img/uploadify-cancel.png',  
	    'folder'         : 'UploadFiles',
	    'dataType'       : 'json',
	    'queueID'        : 'some_file_queue',  
	    'auto'           : false,  
	    'multi'          : true,  
	    'simUploadLimit' : 2,
	    'fileObjName' : 'file_upload',
	    'buttonText' : '选择数据文件',
	    'onUploadStart' : function(file) {
	        $("#file_upload").uploadify('settings','formData',{'fileName':file.name,'flag':'0'});
	    },
	    'onUploadSuccess' : function(file, data, response) {
			var a = JSON.parse(data);
	    	if(a.result == "success"){
	    		alert("上传成功！");
	    	}else if(a.result == "fail"){
	    		alert("上传失败！");
	    	}else if(a.result == "null"){
	    		alert("请不要上传空文件！");
	    	}    	
        }
	});	
	$("#importExcel").click(function(){
		$("#file_upload").uploadify("upload");
	});
	
	//日常导入
	$("#file_upload1").uploadify({
		'swf' : '<%=request.getContextPath()%>/uploadify/uploadify.swf',
	    'uploader' : '<%=request.getContextPath()%>/importExcel.action',
	    'cancelImg'      : '<%=request.getContextPath()%>/img/uploadify-cancel.png',
	    'folder'         : 'UploadFiles',
	    'dataType'       : 'json',
	    'queueID'        : 'some_file_queue1',
	    'auto'           : false,  
	    'multi'          : true,  
	    'simUploadLimit' : 2,
	    'fileObjName' : 'file_upload',
	    'buttonText' : '选择数据文件',
	    'onUploadStart' : function(file) {
	        $("#file_upload1").uploadify('settings','formData',{'fileName':file.name,'flag':'1'});
	    },
	    'onUploadSuccess' : function(file, data, response) {
			var a = JSON.parse(data);
	    	if(a.result == "success"){
	    		alert("上传成功！");
	    	}else if(a.result == "fail"){
	    		alert("上传失败！");
	    	}else if(a.result == "null"){
	    		alert("请不要上传空文件！");
	    	}    	
        }
	});
	$("#dailyImportExcel").click(function(){
		$("#file_upload1").uploadify("upload");
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
  				<li role="presentation"><a href="<%=request.getContextPath()%>/issueinfo.jsp">配送信息</a></li>
  				<li role="presentation" class="active"><a href="#">导入</a></li>
			</ul>
		</div>
	</div>
</nav>
<br/>
<div class="panel panel-primary" style="width: 80%;margin-left: auto;margin-right: auto;">
	<div class="panel-heading">覆盖导入</div>
  	<div class="panel-body">
    	<input type="file" name="file_upload" id="file_upload"/>
		<div id="some_file_queue"></div>
		<button id="importExcel" class="btn btn-primary">覆盖导入</button>
  	</div>
</div>
<div class="panel panel-primary" style="width: 80%;margin-left: auto;margin-right: auto;">
	<div class="panel-heading">日常导入</div>
  	<div class="panel-body">
    	<input type="file" name="file_upload1" id="file_upload1"/>
		<div id="some_file_queue1"></div>
		<button id="dailyImportExcel" class="btn btn-primary">日常导入</button>
  	</div>
</div>
</body>
</html>