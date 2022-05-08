
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit Page</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/cxyStyle.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <style type="text/css">
        td,th{
            padding: 10px;
        }
    </style>
</head>
<body>

<div class="editID">

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Edit Customer</h3>
        </div>
        <div class="panel-body">

            <form method="post" action="${pageContext.request.contextPath}/studentServlet.do?method=updateStudent" role="form">
                <table class="editTable">
                    <tr>
                        <td>Username：</td>
                        <td><input disabled="disabled" type="text" name="snum" id="snum"
                                   placeholder="${student.username}"></td>
                    </tr>
                    <tr>
                        <td>Password：</td>
                        <td><input type="text" name="password" id="password" placeholder="${student.password}">
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td align="center" width="80%">
                            <input type="hidden" name="sid" value="${student.username}">
                            <button type="submit" class="btn btn-success">Submit</button>
                        </td>
                        <td align="center">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/indexServlet.do" role="button">Back</a>
                        </td>

                    </tr>

                </table>
            </form>
        </div>
    </div>

</div>

</body>
</html>
