<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
	$(function(){
	    $("#cancel").linkbutton({
			text:'取消',
			onClick:function(){
			    $("#edit").dialog("close");
			    $.messager.show({
					title:'消息',
					msg:'窗口已被关闭',
					timeout:1500,
					showType:'slide'
				});
			}
		});
	    $("#submit").linkbutton({
			text:'提交',
			onClick:function(){
			    $("#editForm").form('submit',{
                    url:'${pageContext.request.contextPath}/editPic',
					success:function(data){
			            var res = JSON.parse(data);
						if(res.result="success"){
                            $("#edit").dialog("close");
                            $.messager.show({
								height:300,
                                title:'消息',
                                msg:'修改成功',
                                timeout:1500,
                                showType:'slide'
                            });
                        }
                    }
				});
			}
		});
        $("#pictureStatus").combobox({
			valueField:'text',
			textField:'value',
            panelHeight:30,
            required:true,
			data:[{
			    text:'1',
				value:'展示中'
			}, {
			    text:'0',
				value:'未展示'
			}]
        });
        $("#fb").filebox({
			buttonText:'选择图片',
			buttonAlign:'left'
		});
	});
</script>
<form id="editForm" method="post" enctype="multipart/form-data">
	<table align="center" cellpadding="10px">
		<tr>
			<td style="text-align: right">轮播图编号:</td>
			<td><input name="pictureId" class="easyui-textbox" data-options="required:true,readonly:true" /> </td>
		</tr>
		<tr>
			<td style="text-align: right">轮播图名称:</td>
			<td><input name="pictureName" class="easyui-textbox" data-options="required:true,readonly:true" /> </td>
		</tr>
		<tr>
			<td style="text-align: right">轮播图描述:</td>
			<td><input name="pictureDescription" class="easyui-textbox" data-options="required:true" /> </td>
		</tr>
		<tr>
			<td style="text-align: right">轮播图状态:</td>
			<td>
				<input id="pictureStatus" name="pictureStatus" />
			</td>
		</tr>
		<tr>
			<td style="text-align: right">上传轮播图:</td>
			<td>
				<input id="fb"  name="file" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a id="submit" ></a>&nbsp;
				<a id="cancel" ></a>
			</td>
		</tr>
	</table>
</form>