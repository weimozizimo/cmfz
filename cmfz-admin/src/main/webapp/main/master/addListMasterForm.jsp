<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
	$(function(){
	    $("#addList_cancel").linkbutton({
			text:'取消',
			onClick:function(){
			    $("#addList").dialog("close");
			    $.messager.show({
					title:'消息',
					msg:'窗口已被关闭',
					timeout:1500,
					showType:'slide'
				});
			}
		});
	    $("#addList_sub").linkbutton({
			text:'提交',
			onClick:function(){
			    $("#addListForm").form('submit',{
                    url:'${pageContext.request.contextPath}/master/addList',
					success:function(data){
			            var res = JSON.parse(data);
						if(res.result=="success"){
                            $("#addList").dialog("close");
                            $.messager.show({
								height:300,
                                title:'消息',
                                msg:'添加成功',
                                timeout:1500,
                                showType:'slide'
                            });
                        }
                    }
				});
			}
		});
        $("#addList_fb").filebox({
			buttonText:'选择文档',
			buttonAlign:'left'
		});
	});
</script>
<form id="addListForm" method="post" enctype="multipart/form-data">
	<table align="center" cellpadding="10px">
		<tr>
			<td style="text-align: right">上传Excel文件:</td>
			<td>
				<input id="addList_fb" type="text"  name="file" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a id="addList_sub" ></a>&nbsp;
				<a id="addList_cancel" ></a>
			</td>
		</tr>
	</table>
</form>