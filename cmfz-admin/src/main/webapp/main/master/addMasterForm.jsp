<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
	$(function(){
	    $("#add_cancel").linkbutton({
			text:'取消',
			onClick:function(){
			    $("#add").dialog("close");
			    $.messager.show({
					title:'消息',
					msg:'窗口已被关闭',
					timeout:1500,
					showType:'slide'
				});
			}
		});
	    $("#add_sub").linkbutton({
			text:'提交',
			onClick:function(){
			    $("#addForm").form('submit',{
                    url:'${pageContext.request.contextPath}/master/addMaster',
					success:function(data){
			            var res = JSON.parse(data);
						if(res.result="success"){
                            $("#add").dialog("close");
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
        $("#add_fb").filebox({
			buttonText:'选择图片',
			buttonAlign:'left'
		});
	});
</script>
<form id="addForm" method="post" enctype="multipart/form-data">
	<table align="center" cellpadding="10px">
		<tr>
			<td style="text-align: right">上师法号:</td>
			<td><input name="masterName" class="easyui-textbox" data-options="required:true" /> </td>
		</tr>
		<tr>
			<td style="text-align: right">上师简介:</td>
			<td>
				<input id="summary" name="masterSummary" />
			</td>
		</tr>
		<tr>
			<td style="text-align: right">上传头像:</td>
			<td>
				<input id="add_fb" type="text"  name="file" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a id="add_sub" ></a>&nbsp;
				<a id="add_cancel" ></a>
			</td>
		</tr>
	</table>
</form>