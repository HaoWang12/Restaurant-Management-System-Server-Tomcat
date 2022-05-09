<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin Page</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/cxyStyle.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <style type="text/css">
       th,td
        {
           text-align:center;
        }
    </style>

    <script type="text/javascript">
        function doDelete(sid) {
            var flag = confirm("Are you sure you want to delete it？");
            if(flag){
                window.location.href="studentServlet.do?method=deleteStudent&sid=" + sid;
            }
        }
    </script>


</head>

    <div class="title"><h1>Customer List</h1></div>
<body>
    <div class="tableID">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>

                <c:forEach items="${pageList.pageList}" var="s">
                    <tr>
                        <th>${s.username}</th>
                        <th>${s.password}</th>
                        <th><a href="${pageContext.request.contextPath}/studentServlet.do?method=editStudent&sid=${s.username}">Edit</a></th>
                        <!-- 这里doDelete传参一定要加引号-->
                        <th><a href="javascript:void(0)" onclick="doDelete('${s.username}')">Delete</a></th>
                    </tr>
                </c:forEach>
                <tr>
                    <th colspan="3" style="text-align: right">
                        <div style="margin: 25px 0;">
                            A total of<span style="color: blue; ">${pageList.totalDataNum}</span>pieces of data,In the current article<span style="color: darkred">${pageList.currentPageNum}</span>page
                        </div>
                    </th>
<%--                    页码--%>
                    <th colspan="4" style="text-align:left; margin-left: 10px">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <c:if test="${pageList.currentPageNum gt 1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/studentServlet.do?method=findStudentByPage&currentPageNum=${pageList.currentPageNum - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${pageList.currentPageNum eq 1}">
                                    <li class="disabled">
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${pageList.totalPageNum}" var="page">
                                    <c:if test="${page == pageList.currentPageNum}">
                                        <li class="active"><a href="#">${page}</a></li>
                                    </c:if>
                                    <c:if test="${page != pageList.currentPageNum}">
                                        <li><a href="${pageContext.request.contextPath}/studentServlet.do?method=findStudentByPage&currentPageNum=${page}">${page}</a></li>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${pageList.currentPageNum lt pageList.totalPageNum}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/studentServlet.do?method=findStudentByPage&currentPageNum=${pageList.currentPageNum + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${pageList.currentPageNum eq pageList.totalPageNum}">
                                    <li class="disabled">
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </th>

                </tr>

            </tbody>

        </table>

    </div>

</body>
</html>

