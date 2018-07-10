<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
	$(function(){
	   $("#masterId").combobox({
		   url:'${pageContext.request.contextPath}/article/getMaster',
           valueField: 'masterId',
           textField: 'masterName',
       });
	   $("mp").
	});
</script>
<form id="addArticleForm" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>
				文章名：
			</td>
			<td>
				<input name="articleName" class="easyui-textbox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<td>
				所属上师：
			</td>
			<td>
				<input name="masterId" id="masterId" />
			</td>
		</tr>
		<tr>
			<td>上传封面：</td>
			<td>
				<input id="mp" name="main_pic" />
			</td>
		</tr>
	</table>
</form>