<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/validCoding/Library/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/validCoding/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/validCoding/css/former_codingAnalysis.css">
</head>
<body>
    <jsp:include page="codingAnalysis_top_bar.jsp" />
    <div id="body-bar">
        <jsp:include page="codingAnalysis_left_menu.jsp" />
        <script>document.getElementById("valid_coding_li").classList.add("active")</script>

        <div class="col-md-10 col-sm-9">
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
                <div id="project_name_panel">
                    <h2 id="project_name" class="name_title"></h2>
                </div>
                <div class="blank_div"></div>

                <div class="row container_panel">
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

                    <div id="project_info_panel" class="project_info_panel">
                        <div class="blank_div"></div>
                        <div>
                            <h4 style="display: inline;" class="common_tittle">项目URL  </h4>
                            <code id="project_URL"></code>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>

                    <div class="row">
                        <%--project_valid_panel--%>
                        <div id="project_valid_panel" class="col-sm-6 col-xs-12 project_valid_panel" style="overflow: hidden;  margin-bottom: 20px;">
                            <div>
                                <h4 class="common_tittle">有效代码</h4>
                            </div>
                            <div>
                                <div class="col-sm-12 col-xs-8 col-xs-offset-1">
                                    <canvas id="projectValidChart"></canvas>
                                </div>
                            </div>
                        </div>

                        <%--project_composition_panel--%>
                        <div id="project_composition_panel" class="col-sm-6 col-xs-12 project_composition_panel" style="overflow: hidden;">
                            <div>
                                <h4 class="common_tittle">代码组成</h4>
                            </div>
                            <div>
                                <div class="col-sm-12 col-xs-8 col-xs-offset-1">
                                    <canvas id="projectCompositionChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="blank_div"></div>
                </div>

            </div>

        </div>
    </div>


    <script src="${pageContext.request.contextPath}/static/validCoding/Library/Chart.js/Chart.js" ></script>
    <script src="${pageContext.request.contextPath}/static/validCoding/js/former_projectCoding.js"></script>
</body>
</html>
