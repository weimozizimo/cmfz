<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">
    $.fn.datebox.defaults.parser = function(s) {
        var t = new Date(s);
        return t;
    }
	$(function(){
        $("#log").datagrid({
		   title:'Log message',
		   singleSelect:true,
		   pagination:true,
		   fit:true,
           pageList: [2,4,10,20],
		   url:'${pageContext.request.contextPath}/log/showLog',
		   columns:[[
		       {field:'id',title:'日志编号',width:100},
               {field:'user',title:'操作用户',width:100},
			   {field:'time',title:'操作时间',width:100,
                   formatter : function(value, row, index) {
                       var date = new Date(value);
                       return date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日";
                   }},
               {field:'resource',title:'操作类',width:100},
               {field:'action',title:'操作类型',width:100},
               {field:'message',title:'详细信息',width:100},
		   ]]
       });
	});
</script>
<table id="log">
</table>


