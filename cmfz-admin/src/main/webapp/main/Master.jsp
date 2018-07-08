<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    function showEdit(index) {
		$("#master").datagrid("selectRow",index);
		var pic =$("#master").datagrid("getSelected")
        console.log(pic)
		$("#edit").dialog({
			title:'修改',
			width:400,
			height:400,
			href:"${pageContext.request.contextPath}/main/editPicForm.jsp",
			onLoad:function(){
			    $("#editForm").form("load",{
			        pictureId:pic.pictureId,
			        pictureName:pic.pictureName,
					pictureDescription:pic.pictureDescription,
				});
            }
		});
    }
	$(function(){
	   $("#master").datagrid({
		   title:'Master message',
		   singleSelect:true,
		   pagination:true,
		   fit:true,
           pageList: [2,4,10,20],
		   url:'${pageContext.request.contextPath}/master/showMaster',
		   columns:[[
		       {field:'masterId',title:'上师编号',width:100},
               {field:'masterName',title:'上师法号',width:100},
			   {field:'masterPhoto',title:'上师头像',width:100},
               {field:'masterSummary',title:'上师简介',width:100},
		   ]],
		   toolbar:[{
               iconCls:'icon-add',
			   text:'新增上师',
			   handler:function(){
					$("#add").dialog({
						title:'上师',
						width:400,
						height:300,
					});
			   }
		   }],
           view: detailview,
           detailFormatter: function(rowIndex, rowData){
               return "<img src='${pageContext.request.contextPath}/upload/"+rowData.masterPhoto+"'>";
           }
       });
	});
</script>
<table id="master">
</table>
<div id="edit"></div>
<div id="add"></div>

