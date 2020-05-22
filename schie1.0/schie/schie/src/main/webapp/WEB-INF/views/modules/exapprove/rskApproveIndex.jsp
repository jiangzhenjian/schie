<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源申请审核列表</title>
<%@ include file="/WEB-INF/views/include/headMeta.jsp"%>
<%@ include file="/WEB-INF/views/include/headCss.jsp"%>
<%@ include file="/WEB-INF/views/include/headJs.jsp"%>
</head>
<body>
<!-- 内容-->
	<div class="wrapper">
		<!-- 内容盒子-->
		<div class="box box-main">
			<!-- 内容盒子头部 -->
			<div class="box-header">
				<div class="box-title"><i class="fa fa-edit"></i>待审核数据</div>
				<div class="box-tools pull-right">
						<a id="btnRefresh" title="刷新" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-repeat"></i>刷新</a>
						<!-- 工具功能 -->
                		<%@ include file="/WEB-INF/views/include/btnGroup.jsp" %>
				</div>
			</div>
			<!-- 内容盒子身体 -->
			<div class="box-body">
				<!-- 查询条件 -->
				<form:form id="searchForm" modelAttribute="exResAsk" action="${ctx}/exapprove/exRskApprove/" method="post"
                       class="form-inline">
                       <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		                <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		                <input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
                </form:form>
				<!-- 工具栏 不需要-->
				<table id="contentTable"
					class="table table-hover table-condensed dataTables-example dataTable">
					<thead>
						<tr>
							<th class="sort-column e.name">资源名称</th>
							<th class="">资源路径</th>
							<th class="sort-column c.name">申请机构</th>
							<th class="sort-column cuser">申请人</th>
							<th class="sort-column muser">修改人</th>
							<th class="sort-column mdate">修改时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list}" var="exResAsk">
							<tr>
								<td>${exResAsk.res.name}</td>
								<td>${exResAsk.resdirPath}</td>
								<td>${exResAsk.company.name}</td>
								<td>${exResAsk.cuser}</td>
								<td>${exResAsk.muser}</td>
								<td><fmt:formatDate value="${exResAsk.mdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<shiro:hasPermission name="exask:exResAsk:view">
                                		<a id="btnView" class="btnView" href="${ctx}/exask/exResAsk/form?id=${exResAsk.id}&action=sh"
                                     		title="查看详情">查看详情</a>
                            		</shiro:hasPermission>
                            	</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 分页代码 -->
				${page.toStringPage()}
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/include/footJs.jsp"%>
<script src="/staticViews/viewBase.js"></script>
<script type="text/javascript">
    function refresh() {//刷新
        window.location = "${ctx}/exapprove/exRskApprove/index";
    }
    // 工具栏按钮绑定
    $('#btnRefresh').click(function () {
        refresh();
    });
</script>
</body>
</html>