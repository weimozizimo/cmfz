<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    function qq(value,name){
        console.log(name+":"+value);
		if(name=='id') {
            $("#master").datagrid("load","${pageContext.request.contextPath}/master/queryById?masterId="+value);
        }else{
            $("#master").datagrid("load","${pageContext.request.contextPath}/master/queryByName?masterName="+value);
		}
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
						href:'${pageContext.request.contextPath}/main/master/addMasterForm.jsp'
					});
			   }
		   },{
		       iconCls:'icon-edit',
			   text:'编辑',
			   handler:function(){
                   var rowData =$("#master").datagrid("getSelected");
                   console.log(rowData);
                   $("#edit").dialog({
                       title:'修改',
                       width:400,
                       height:400,
                       href:"${pageContext.request.contextPath}/main/master/editMasterForm.jsp",
                       onLoad:function(){
                           $("#editForm").form("load",rowData);
                       }
                   });
			   }
		   },
			   {text:"<input type='text id='queryText' class='easyui-searchbox' style='width:300px'" +
			   " data-options=\"searcher:qq,prompt:'请输入你要查询的数据',menu:'#mm'\" />"},
			   {
			       text:'批量导入',
				   handler:function () {
					   $("#addList").dialog({
						  title:'批量导入',
						   width:400,
						   height:400,
						   href:"${pageContext.request.contextPath}/main/master/addListMasterForm.jsp",
					   });
                   }
			   },{
		       		text:'导出上师信息',
				   	iconCls:'icon-save',
				    onClick:function () {
						window.location.href="${pageContext.request.contextPath}/master/exportMaster";
                    }


			   }
			  ],
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
<div id="mm" style="width:120px">
	<div data-options="name:'id'">根据id查询</div>
	<div data-options="name:'name'">根据法号查询</div>
</div>
<div id="addList"></div>

