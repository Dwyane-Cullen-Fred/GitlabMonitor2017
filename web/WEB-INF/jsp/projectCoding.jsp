<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8" />
    <title>ProjectAnalysis</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/validCoding/Library/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/validCoding/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/validCoding/css/codingAnalysis.css">

    <script src="${pageContext.request.contextPath}/static/validCoding/Library/Chart.js/Chart.js" ></script>
    <script src="${pageContext.request.contextPath}/static/validCoding/Library/jQuery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/validCoding/Library/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="codingAnalysis_top_bar.jsp" />
    <div id="body-bar">
        <!--左侧菜单栏-->
        <jsp:include page="codingAnalysis_left_menu.jsp" />
        <script>document.getElementById("valid_coding_li").classList.add("active")</script>


        <div class="col-lg-10">
            <div class="col-lg-1"></div>
            <div class="col-lg-10">
                <div class="blank_div"></div>
                <div class="blank_div"></div>

                <!--搜索框-->
                <div class="row">
                    <div class="col-lg-7"></div>
                    <div class="col-lg-5">
                        <div class="input-group">
                            <input id="project_id" type="text" class="form-control" placeholder="project id">
                            <span class="input-group-btn">
                                <button id="search_button" class="btn btn-primary" type="button" onclick="search()">
                                    search
                                </button>
                            </span>
                        </div>
                    </div>
                </div>

                <!--主展示板-->
                <div class="row" id="main_panel">
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>


                    <!--项目信息-->
                    <div id="project_info_panel">
                        <h2 id="project_name" class="name_title"></h2>
                        <div class="blank_div"></div>
                        <div class="blank_div"></div>

                        <div>
                            <h4 style="display: inline;" class="common_tittle">项目URL  </h4>
                            <code id="project_URL"></code>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>

                    <!--迭代转换标签-->
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




                    <!--=======================================valid部分=============================================-->
                    <!--valid图表转化标签-->
                    <div class="head-info-container">
                        <h4 class="common_tittle">文件组成</h4>
                        <div class="btn-group btn-container" role="group" aria-label="...">
                            <button id="valid_table_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="valid_btnSwitch(this.id)">数值</button>
                            <button id="valid_bar_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="valid_btnSwitch(this.id)">数量比较</button>
                            <button id="valid_percent_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="valid_btnSwitch(this.id)">比例</button>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <!--valid展示图标框-->
                    <div id="valid_table_div">
                        <table id="valid_table" class="table table-striped">
                            <thead>
                            <tr>
                                <th>文件作用</th>
                                <th>注释行数</th>
                                <th>空行数</th>
                                <th>总行数</th>
                                <th>有用代码行数</th>
                                <th>占全部有用代码比例</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!--valid柱状图-->
                    <div id="valid_barChart_div" class="col-lg-12" style="display: none">
                        <%--<canvas id="valid_barChart" height="100px"></canvas>--%>
                    </div>
                    <!--valid比例图-->
                    <div id="valid_percentChart_div" class="col-lg-12" style="display: none">
                        <%--<canvas id="valid_percentChart" height="100px"></canvas>--%>
                    </div>
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>
                    <!--=======================================我是分割线============================================-->




                    <!--=====================================composition部分=========================================-->
                    <!--composition转换标签-->
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>
                    <!--composition图表转化标签-->
                    <div class="head-info-container">
                        <h4 class="common_tittle">代码类型</h4>
                        <div class="btn-group btn-container" role="group" aria-label="...">
                            <button id="composition_table_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="composition_btnSwitch(this.id)">数值</button>
                            <button id="composition_bar_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="composition_btnSwitch(this.id)">数量比较</button>
                            <button id="composition_percent_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="composition_btnSwitch(this.id)">比例</button>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <!--composition展示图标框-->
                    <div id="composition_table_div">
                        <table id="composition_table" class="table table-striped">
                            <thead>
                            <tr>
                                <th>代码类型</th>
                                <th>注释行数</th>
                                <th>空行数</th>
                                <th>总行数</th>
                                <th>有用代码行数</th>
                                <th>占全部有用代码比例</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!--composition柱状图-->
                    <div id="composition_barChart_div" class="col-lg-12" style="display: none">
                        <%--<canvas id="composition_barChart" height="100px"></canvas>--%>
                    </div>
                    <!--composition比例图-->
                    <div id="composition_percentChart_div" class="col-lg-12" style="display: none">
                        <%--<canvas id="composition_percentChart"></canvas>--%>
                    </div>
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>
                    <!--=======================================我是分割线============================================-->




                    <!--=====================================contribution部分========================================-->
                    <!--contribution转换标签-->
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>
                    <!--contribution图表转化标签-->
                    <div class="head-info-container">
                        <h4 class="common_tittle">成员贡献</h4>
                        <div class="btn-group btn-container" role="group" aria-label="...">
                            <button id="contribution_table_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="contribution_btnSwitch(this.id)">数值</button>
                            <button id="contribution_bar_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="contribution_btnSwitch(this.id)">数量比较</button>
                            <button id="contribution_percent_btn" type="button" class="btn btn-default show-type-btn"
                                    onclick="contribution_btnSwitch(this.id)">比例</button>
                        </div>
                    </div>
                    <div class="blank_div"></div>
                    <!--contribution展示图标框-->
                    <div id="contribution_table_div">
                        <table id="contribution_table" class="table table-striped">
                            <thead>
                            <tr>
                                <th>学生</th>
                                <th>增加行数</th>
                                <th>删减行数</th>
                                <th>总代码行数</th>
                                <th>代码占比</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!--composition柱状图-->
                    <div  id="contribution_barChart_div" class="col-lg-12" style="display: none">
                        <%--<canvas id="contribution_barChart" height="100px"></canvas>--%>
                    </div>
                    <!--composition比例图-->
                    <div id="contribution_percentChart_div" class="col-lg-12" style="display: none">
                        <%--<canvas id="contribution_percentChart" height="100px"></canvas>--%>
                    </div>
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>
                    <!--=======================================我是分割线============================================-->




                    <!--=====================================dailyCommit部分=========================================-->
                    <div class="blank_div"></div>
                    <div class="blank_div"></div>
                    <!--dailyCommit图标签-->
                    <div  id="dailyCommit_div" class="head-info-container">
                        <h4 class="common_tittle">提交频率</h4>
                        <div class="blank_div"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">选择筛除代码文件：</label>
                            <%--<div class="col-sm-4">--%>
                            <%--<select id="selectScreenOut" name="selectScreenOut"--%>
                            <%--class="selectpicker" multiple data-actions-box="true">--%>
                            <%--<option value="txt">文本说明文件</option>--%>
                            <%--<option value="config">配置文件</option>--%>
                            <%--<option value="lib">类库</option>--%>
                            <%--<option value="compiled">编译文件</option>--%>
                            <%--<option value="others">其它</option>--%>
                            <%--<option value="core">核心代码文件</option>--%>
                            <%--</select>--%>
                            <%--</div>--%>
                            <!--checkbox-->

                            <div id="screenOut_checkbox_div" style="display: inline">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="txt_check" value="文本说明文件"> 文本说明文件
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="config_check" value="配置文件"> 配置文件
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="lib_check" value="类库"> 类库
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="compiled_check" value="编译文件"> 编译文件
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="core_check" value="核心代码文件"> 核心代码文件
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="others_check" value="其它"> 其它
                                </label>
                            </div>

                            <button id="selectScreenOut_button" class="btn btn-primary" type="button" onclick="screenOutCommitType()">
                                提交
                            </button>
                        </div>
                        <div class="blank_div"></div>
                        <!--dailyCommit折线图-->
                        <div id="dailyCommit_lineChart_div" style="display: none">
                            <%--<canvas id="dailyCommit_lineChart" height="100px"></canvas>--%>
                        </div>
                        <div class="blank_div"></div>

                    </div>

                    <div class="blank_div"></div>
                    <!--=======================================我是分割线============================================-->





                    <!--===================================核心代码文件查询部分=======================================-->
                    <!--核心代码文件查询-->
                    <div id="validFile_retrieve">
                        <h4 class="common_tittle">核心代码查看</h4>
                        <div>
                            <div class="btn-group my-selected-btn" style="margin-right:0">
                                <button id="selectType_btn" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" id="dropDownMenu" style="width: 150px">
                                    选择代码类型
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropDownMenu">
                                    <li><a id="filter1" onclick="selectValidFile('java')">Java</a></li>
                                    <li><a id="filter2" onclick="selectValidFile('html')">HTML</a></li>
                                    <li><a id="filter3" onclick="selectValidFile('css')">CSS</a></li>
                                    <li><a id="filter4" onclick="selectValidFile('jsp')">JSP</a></li>
                                    <li><a id="filter5" onclick="selectValidFile('php')">PHP</a></li>
                                    <li><a id="filter6" onclick="selectValidFile('py')">Python</a></li>
                                    <li><a id="filter7" onclick="selectValidFile('fxml')">FXML</a></li>
                                </ul>
                            </div>
                            <div class="blank_div"></div>
                            <div class="blank_div"></div>
                            <div class="blank_div"></div>
                            <div id="validFile_panel" class="list-group" style="min-height:150px">

                            </div>
                        </div>
                    </div>
                    <!--=======================================我是分割线============================================-->








                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/static/validCoding/js/projectCoding.js"></script>



</body>
</html>
