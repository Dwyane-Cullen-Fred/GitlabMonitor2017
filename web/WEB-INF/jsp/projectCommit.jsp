<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project</title>
    <jsp:include page="head.jsp"/>
    <link href="/static/css/project/projectCommit.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div id="body-bar">
        <jsp:include page="left_menu.jsp" />

        <div class="col-lg-10">
            <div class="col-lg-1"></div>
            <div class="col-lg-10">
                <div class="blank_div"></div>
                <div class="blank_div"></div>
                <div class="row">
                    <div class="col-lg-7"></div>
                    <div class="col-lg-5">
                        <div class="input-group">
                            <input id="project_id" type="text" class="form-control" placeholder="project">
                            <span class="input-group-btn">
                                <button id="search_button" class="btn btn-primary" type="button" onclick="search()">
                                    search
                                </button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="blank_div"></div>
                <div class="row container_panel">
                    <ul class="nav nav-tabs">
                        <li id="iterationAll" role="presentation" class="my-li" onclick="tabSwitch(this.id)">
                            <a href="#">总迭代</a>
                        </li>
                        <li id="iteration1" role="presentation" class="my-li" onclick="tabSwitch(this.id)">
                            <a href="#">迭代一</a>
                        </li>
                        <li id="iteration2" role="presentation" class="my-li" onclick="tabSwitch(this.id)">
                            <a href="#">迭代二</a>
                        </li>
                        <li id="iteration3" role="presentation" class="my-li" onclick="tabSwitch(this.id)">
                            <a href="#">迭代三</a>
                        </li>
                    </ul>
                    <%--project_panel--%>
                    <div id="project_panel" class="project_panel">
                        <div>
                            <h2 id="project_information"></h2>
                        </div>
                        <div>
                            <canvas id="projectChart" width="100%" height="40 px"></canvas>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <div id="valid_commit">
                        <h2>有效提交率</h2>
                        <div>
                            <canvas id="valid_commit_chart" width="100%" height="40 px"></canvas>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <%--student_panel--%>

                    <div id="student_panel" class="row student_panel">
                        <div>
                            <h2>学生提交情况</h2>
                            <h4 id="student_information"></h4>
                        </div>
                        <div>
                            <div class="col-lg-4">
                                <div>
                                    <h3 >提交数目</h3>
                                </div>
                                <div>
                                    <canvas id="studentCommit" class="pie_container"></canvas>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div>
                                    <h3>提交代码行数</h3>
                                </div>
                                <div>
                                    <canvas id="studentAdd" class="pie_container"></canvas>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div>
                                    <h3>删除代码行数</h3>
                                </div>
                                <div>
                                    <canvas id="studentDel" class="pie_container"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="blank_div"></div>
                    <%--connection_panel--%>
                    <div id="connection_panel" class="row" >
                        <h2>组内协作展示</h2>
                        <svg id = "connection_svg" class="connection_svg">

                        </svg>
                    </div>
                </div>

            </div>

        </div>
    </div>

    <script src="/static/js/chart.js/dist/Chart.js" ></script>
    <script src="/static/js/project/projectCommit.js"></script>
</body>
</html>
