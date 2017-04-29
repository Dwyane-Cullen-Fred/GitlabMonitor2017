<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目分析</title>
    <jsp:include page="head.jsp" />
    <link rel="stylesheet" href="/static/css/project/student.css">
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div id="body-bar">
        <%--左侧菜单栏--%>
        <jsp:include page="left_menu.jsp" />

        <div class="col-lg-10">
            <div class="col-lg-1"></div>
            <div class="col-lg-10">
                <div class="blank_div"></div>
                <div class="blank_div"></div>

                <%--搜索框--%>
                <div class="row">
                    <div class="col-lg-7"></div>
                    <div class="col-lg-5">
                        <div class="input-group">
                            <input id="projectId" type="text" class="form-control"
                                   placeholder="project">
                            <span class="input-group-btn">
                                <button id="search_button" class="btn btn-primary" type="button" onclick="search()">
                                    search
                                </button>
                            </span>
                        </div>
                    </div>
                </div>

                <%--主展示板--%>
                <div class="row" id="container_panel">
                    <div class="blank_div"></div>

                    <h2 id="projectName"></h2>

                    <div class="blank_div"></div>

                    <%--转换标签--%>
                    <ul id = "iteration_nav" class="nav nav-tabs">
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

                    <div class="blank_div"></div>
                    <div class="blank_div"></div>

                    <%--数值百分比转化标签--%>
                    <div class="head-info-container">
                        <h2>组员代码提交情况</h2>
                        <div class="btn-group btn-container" role="group" aria-label="...">
                            <button id="number_btn" type="button" class="btn btn-default show-type-btn" onclick="btnSwitch(this.id)">数值</button>
                            <button id="percent_btn" type="button" class="btn btn-default show-type-btn" onclick="btnSwitch(this.id)">百分比</button>
                            <button id="graph_btn" type="button" class="btn btn-default show-type-btn" onclick="btnSwitch(this.id)">图表</button>
                        </div>
                    </div>

                    <div class="blank_div"></div>

                    <%--展示图标框--%>
                    <div id="table_div">
                        <table id="projectTable" class="table">
                            <thead>
                            <tr>
                                <th>组员</th>
                                <th>提交代码总量</th>
                                <th>前端代码</th>
                                <th>后端代码</th>
                                <th>别人贡献代码</th>
                                <th>对别人贡献代码</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div id="graph_div" style="display: none">
                        <canvas id="graphCanvas1" height="100px"></canvas>
                        <canvas id="graphCanvas2" height="100px"></canvas>
                    </div>

                    <div class="blank_div"></div>
                    <div class="blank_div"></div>

                    <%--提交情况展示, 提交查看模块--%>
                    <div class="commit_situation">
                        <h2>无效文件查询</h2>
                        <div class="row commit-container">
                            <div class="btn-group my-selected-btn">
                                <button type="button" class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" id="dropDownMenu">
                                    过滤条件
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropDownMenu">
                                    <li><a id="filter1" onclick="getInvalidFile(this.id)">注释空格偏多</a></li>
                                    <li><a id="filter2" onclick="getInvalidFile(this.id)">修改次数偏少</a></li>
                                    <li><a id="filter3" onclick="getInvalidFile(this.id)">修改次数偏多</a></li>
                                </ul>
                            </div>
                            <div class="blank_div"></div>
                            <div class="blank_div"></div>
                            <div class="blank_div"></div>
                            <div id="invalid-file-show-panel" class="list-group">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
    <script src="/static/js/chart.js/dist/Chart.js" ></script>
    <script src="../../static/js/project/student.js"></script>
</body>
</html>
