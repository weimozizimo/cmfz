<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
	$(function(){
	   $("#masterId").combobox({
		   url:'${pageContext.request.contextPath}/article/getMaster',
           valueField: 'masterId',
           textField: 'masterName',
       });
	   $("#mp").filebox({
		   buttonText:'选择文件',
		   buttonAlign:'right'
	   });
	});
    var E = window.wangEditor;
    var editor = new E('#editor');
    var $text1 = $('#text');
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $text1.val(html)
    }
    editor.create()
    // 初始化 textarea 的值
    $text1.val(editor.txt.html())
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
		<tr>
			<td colspan="2">
				<div id="editor">
				</div>
			</td>
		</tr>
	</table>
	<textarea id="text" name="introduction"  hidden></textarea>
</form>