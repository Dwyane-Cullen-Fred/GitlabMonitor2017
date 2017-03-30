<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProjectCommit</title>
    <jsp:include page="head.jsp" />
    <link href="/static/css/project/allCommit.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div id="body-bar">
        <jsp:include page="left_menu.jsp" />

        <div class="col-lg-1"></div>
        <div class="col-lg-8 container-body">
            <div class="blank_div"></div>
            <div class="blank_div"></div>
            <div class="blank_div"></div>
            <div class="h-container">
                <h1>
                    提交信息总览
                </h1>
            </div>

            <div class="blank_div"></div>
            <div id="">
                <canvas id="myChart" width="100%" height="40px"></canvas>
            </div>
        </div>
        <div class="col-lg-1"></div>
    </div>

    <jsp:include page="footer.jsp"/>
    <script src="/static/js/chart.js/dist/Chart.js"> </script>
    <script src="/static/js/project/allCommit.js" > </script>
</body>
</html>
