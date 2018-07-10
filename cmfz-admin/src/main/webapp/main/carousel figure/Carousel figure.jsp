<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    $.fn.datebox.defaults.parser = function(s) {
        var t = new Date(s);
        return t;
    }
    function showEdit(index) {
		$("#img").datagrid("selectRow",index);
		var pic =$("#img").datagrid("getSelected")
        console.log(pic)
		$("#edit").dialog({
			title:'修改',
			width:400,
			height:400,
			href:"${pageContext.request.contextPath}/main/carousel figure/editPicForm.jsp",
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
	   $("#img").datagrid({
		   title:'slideshow message',
		   singleSelect:true,
		   pagination:true,
		   fit:true,
           pageList: [2,4,10,20],
		   url:'${pageContext.request.contextPath}/slideShow',
		   columns:[[
		       {field:'pictureId',title:'图片编号',width:100},
               {field:'pictureName',title:'图片名字',width:100},
               {field:'pictureDate',title:'轮播图创建时间',width:100,
                   formatter : function(value, row, index) {
                       var date = new Date(value);
                       return date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日";
                   }},
               {field:'pictureDescription',title:'图片描述',width:100},
               {field:'pictureStatus',title:'轮播图状态',width:100},
               {field:'operation',title:'操作',width:100,
			   		formatter:function(value,row,index){
		       			var str="<a href='#' name='opera' class='easyui-linkbutton' onclick='showEdit("+index+")' ></a>";
		       			return str;
					}
			   }
		   ]],
           onLoadSuccess:function(data){
               $("a[name='opera']").linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
           },
		   toolbar:[{
               iconCls:'icon-add',
			   text:'新增轮播图',
			   handler:function(){
					$("#add").dialog({
						title:'新增轮播图',
						width:400,
						height:300,
                        href:"${pageContext.request.contextPath}/main/carousel figure/addPicForm.jsp",
					});
			   }
		   }],
           view: detailview,
           detailFormatter: function(rowIndex, rowData){
               return "<img src='${pageContext.request.contextPath}/upload/"+rowData.pictureName+"'>";
           }
       });
	});
</script>
<table id="img">
</table>
<div id="edit"></div>
<div id="add"></div>

