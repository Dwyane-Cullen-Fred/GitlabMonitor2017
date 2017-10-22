var valid_barChart, valid_percentChart,
    composition_barChart, composition_percentChart,
    contribution_barChart, contribution_percentChart;

var colors = [ "#ED1C24", "#F7941D", "#F8F200", "#28ff00",
    "#0054A6", "#A757A8",  "#33B9D7", "#D95531", "#44A622", "#F69696" ];

var current_project_id;
var current_iteration_id;
var commit_screenOut_list = [];
var selected_type = "java";








/*=================================================AJAX获取数据========================================================*/

/**
 *  AJAX获取数据
 */
function getData(url, callback) {
    var xhttp, object;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            object = this.response;
            callback(object);
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

/*====================================================================================================================*/






/*===============================================展示项目基本信息======================================================*/

/**
 * 展示项目名和url
 */
function drawBasicInfo() {
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        document.getElementById("project_name").innerHTML = data[0].name;
        var a = document.createElement('a');
        a.href = data[0].project_url;
        a.innerHTML = data[0].project_url;
        document.getElementById("project_URL").innerHTML = a.outerHTML;
    };

    var url = "/validCodingData/projectInfo/" + current_project_id;
    getData(url, callback)
}

/*====================================================================================================================*/






/*=================================================填充各种表格========================================================*/

/**
 *  填充valid有效代码表格
 */
function fillValidTable(){

    var callback = function (data) {
        if (data.length === 0) {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        document.getElementById("main_panel").style.display = "block";
        //配置好table数据，数据类型为 validCoding/bean/ProjectCode
        var tableData = [];
        var useful_total = 0;
        for (var i = 0; i < data.length; i++){  //所有有用代码求和，便于计算比例
            useful_total = useful_total + data[i].useful_line;
        }
        for (var i = 0; i < data.length; i++) {
            var ele = [];
            ele.push(data[i].type);
            ele.push(data[i].comment_line);
            ele.push(data[i].blank_line);
            ele.push(data[i].line_total);
            ele.push(data[i].useful_line);
            if(useful_total === 0){   //分母是0
                ele.push( (useful_total*100).toFixed(2) + "%" );
            }
            else{
                ele.push( (data[i].useful_line/useful_total*100).toFixed(2) + "%" );
            }
            tableData.push(ele);
        }
        //画表
        var table = document.getElementById("valid_table");
        if (table.getElementsByTagName('tbody').length !== 0)
            table.removeChild(table.getElementsByTagName('tbody')[0]);
        var tBody = document.createElement('tbody');
        for (var i = 0; i < tableData.length; i++) {
            var tr = document.createElement('tr');
            for (var j = 0; j < tableData[i].length; j++){
                var td = document.createElement('td');
                td.appendChild(document.createTextNode(tableData[i][j]));
                tr.appendChild(td);
            }
            tBody.appendChild(tr);
        }
        table.appendChild(tBody);
    };

    var url = "/validCodingData/projectValid/" + current_project_id + "/iteration/" + current_iteration_id ;
    getData(url, callback);
}

/**
 *  填充composition代码组成表格
 */
function fillCompositionTable(){

    var callback = function (data) {
        if (data.length === 0) {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        document.getElementById("main_panel").style.display = "block";
        //配置好table数据，数据类型为 validCoding/bean/ProjectCode
        var tableData = [];
        var useful_total = 0;
        for (var i = 0; i < data.length; i++){  //所有有用代码求和，便于计算比例
            useful_total = useful_total + data[i].useful_line;
        }
        for (var i = 0; i < data.length; i++) {
            var ele = [];
            ele.push(data[i].type);
            ele.push(data[i].comment_line);
            ele.push(data[i].blank_line);
            ele.push(data[i].line_total);
            ele.push(data[i].useful_line);
            if(useful_total === 0){   //分母是0
                ele.push( (useful_total*100).toFixed(2) + "%" )
            }
            else{
                ele.push( (data[i].useful_line/useful_total*100).toFixed(2) + "%" )
            }
            tableData.push(ele);
        }
        //画表
        var i = 0, j = 0;
        var table = document.getElementById("composition_table");
        if (table.getElementsByTagName('tbody').length !== 0)
            table.removeChild(table.getElementsByTagName('tbody')[0]);
        var tBody = document.createElement('tbody');
        for (i = 0; i < tableData.length; i++) {
            var tr = document.createElement('tr');
            for (j = 0; j < tableData[i].length; j++){
                var td = document.createElement('td');
                td.appendChild(document.createTextNode(tableData[i][j]));
                tr.appendChild(td);
            }
            tBody.appendChild(tr);
        }
        table.appendChild(tBody);
    };

    var url = "/validCodingData/projectComposition/" + current_project_id + "/iteration/" + current_iteration_id ;
    getData(url, callback);
}

/**
 *  填充contribution成员贡献表格
 */
function fillContributionTable(){

    var callback = function (data) {
        if (data.length === 0) {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        document.getElementById("main_panel").style.display = "block";
        //配置好table数据，数据类型为 validCoding/bean/ProjectContribution
        var tableData = [];
        var all_line = 0;
        for (var i = 0; i < data.length; i++){  //所有代码求和，便于计算比例
            all_line = all_line + data[i].line_total;
        }
        for (var i = 0; i < data.length; i++) {
            var ele = [];
            ele.push(data[i].author);
            ele.push(data[i].add_line);
            ele.push(data[i].delete_line);
            ele.push(data[i].line_total);
            if(all_line === 0){   //分母是0
                ele.push( (all_line*100).toFixed(2) + "%" )
            }
            else{
                ele.push( (data[i].line_total/all_line*100).toFixed(2) + "%" )
            }
            tableData.push(ele);
        }
        //画表
        var i = 0, j = 0;
        var table = document.getElementById("contribution_table");
        if (table.getElementsByTagName('tbody').length !== 0)
            table.removeChild(table.getElementsByTagName('tbody')[0]);
        var tBody = document.createElement('tbody');
        for (i = 0; i < tableData.length; i++) {
            var tr = document.createElement('tr');
            for (j = 0; j < tableData[i].length; j++){
                var td = document.createElement('td');
                td.appendChild(document.createTextNode(tableData[i][j]));
                tr.appendChild(td);
            }
            tBody.appendChild(tr);
        }
        table.appendChild(tBody);
    };

    var url = "/validCodingData/projectContribution/" + current_project_id + "/iteration/" + current_iteration_id ;
    getData(url, callback);
}

/*====================================================================================================================*/






/*====================================================画图============================================================*/

/**
 * 有效代码柱状图
 */
function drawValidBarChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var type = [], all_line = [], useful_line = [];
        for (var i = 0; i < data.length; i++) {
            type[i] = data[i].type;
            all_line[i] = data[i].line_total;
            useful_line[i] = data[i].useful_line;
        }
        //画图
        var canvas = document.createElement('canvas');
        canvas.style.height = '100px';
        let div = document.getElementById('valid_barChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);
        // var ctx = document.getElementById("valid_barChart");
        // if (valid_barChart != undefined) {
        //     valid_barChart.destroy();
        // }
        valid_barChart = new Chart(canvas, {
            type : 'bar',
            data : {
                labels : type,
                datasets: [
                    {
                        label : "总行数",
                        backgroundColor: "#33B9D7",
                        borderColor: "#33B9D7",
                        borderWidth : 1,
                        data : all_line
                    },
                    {
                        label : "有用代码行数",
                        backgroundColor: "#F69696",
                        borderColor: "#F69696",
                        borderWidth : 1,
                        data : useful_line
                    }
                ]
            }
        });
    };

    var url = "/validCodingData/projectValid/" + current_project_id + "/iteration/" + current_iteration_id;
    getData(url, callback);
}

/**
 * 代码组成柱状图
 */
function drawCompositionBarChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var type = [], all_line = [], useful_line = [];
        for (var i = 0; i < data.length; i++) {
            type[i] = data[i].type;
            all_line[i] = data[i].line_total;
            useful_line[i] = data[i].useful_line;
        }
        //画图
        var canvas = document.createElement('canvas');
        canvas.style.height = '100px';
        var div = document.getElementById('composition_barChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);
        // var ctx = document.getElementById("composition_barChart");
        // if (composition_barChart != undefined) {
        //     composition_barChart.destroy();
        // }
        composition_barChart = new Chart(canvas, {
            type : 'bar',
            data : {
                labels : type,
                datasets: [
                    {
                        label : "总行数",
                        backgroundColor: "#D95531",
                        borderColor: "#D95531",
                        borderWidth : 1,
                        data : all_line
                    },
                    {
                        label : "有用代码行数",
                        backgroundColor: "#28ff00",
                        borderColor: "#28ff00",
                        borderWidth : 1,
                        data : useful_line
                    }
                ]
            }
        });
    };

    var url = "/validCodingData/projectComposition/" + current_project_id + "/iteration/" + current_iteration_id;
    getData(url, callback);
}

/**
 * 成员贡献柱状图
 */
function drawContributionBarChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var student = [], add_line = [], delete_line = [], all_line = [];
        for (var i = 0; i < data.length; i++) {
            student[i] = data[i].author;
            add_line[i] = data[i].add_line;
            delete_line[i] = data[i].delete_line;
            all_line[i] = data[i].line_total;
        }
        //画图
        var canvas = document.createElement('canvas');
        canvas.style.height = '100px';
        var div = document.getElementById('contribution_barChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);
        // var ctx = document.getElementById("contribution_barChart");
        // if (contribution_barChart != undefined) {
        //     contribution_barChart.destroy();
        // }
        contribution_barChart = new Chart(canvas, {
            type : 'bar',
            data : {
                labels : student,
                datasets: [
                    {
                        label : "增加行数",
                        backgroundColor: "#44A622",
                        borderColor: "#44A622",
                        borderWidth : 1,
                        data : add_line
                    },
                    {
                        label : "删减行数",
                        backgroundColor: "#ED1C24",
                        borderColor: "#ED1C24",
                        borderWidth : 1,
                        data : delete_line
                    },
                    {
                        label : "总行数",
                        backgroundColor: "#F8F200",
                        borderColor: "#F8F200",
                        borderWidth : 1,
                        data : all_line
                    }
                ]
            }
        });
    };

    var url = "/validCodingData/projectContribution/" + current_project_id + "/iteration/" + current_iteration_id;
    getData(url, callback);
}



/**
 * 有效代码比例图
 */
function drawValidPercentChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var type = [], useful_line = [];
        for (var i = 0; i < data.length; i++) {
            type[i] = data[i].type;
            useful_line[i] = data[i].useful_line;
        }
        //画图
        var canvas = document.createElement('canvas');
        canvas.style.height = '100px';
        var div = document.getElementById('valid_percentChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);
        // var ctx = document.getElementById("valid_percentChart");
        // if (valid_percentChart !== undefined) {
        //     valid_percentChart.destroy();
        // }
        valid_percentChart = new Chart(canvas, {
            type : 'doughnut',
            data : {
                labels : type,
                datasets : [
                    {
                        data : useful_line,
                        backgroundColor : colors.slice(0, type.length)
                    },
                ],
                options: {
                    animation:{
                        animateScale:true
                    }
                }
            }
        });
    };

    var url = "/validCodingData/projectValid/" + current_project_id + "/iteration/" + current_iteration_id;
    getData(url, callback);
}

/**
 * 代码组成比例图
 */
function drawCompositionPercentChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var type = [], useful_line = [];
        for (var i = 0; i < data.length; i++) {
            type[i] = data[i].type;
            useful_line[i] = data[i].useful_line;
        }

        //画图
        var canvas = document.createElement('canvas');
        canvas.style.height = '100px';
        var div = document.getElementById('composition_percentChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);
        // var ctx = document.getElementById("composition_percentChart");
        // if (composition_percentChart != undefined) {
        //     composition_percentChart.destroy();
        // }
        composition_percentChart = new Chart(canvas, {
            type : 'doughnut',
            data : {
                labels : type,
                datasets : [
                    {
                        data : useful_line,
                        backgroundColor : colors.slice(0, type.length)
                    },
                ],
                options: {
                    animation:{
                        animateScale:true
                    }
                }
            }
        });
    };

    var url = "/validCodingData/projectComposition/" + current_project_id + "/iteration/" + current_iteration_id;
    getData(url, callback);
}

/**
 * 成员贡献比例图
 */
function drawContributionPercentChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("main_panel").style.display = "none";
            alert("Project Does Not Exist !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var student = [], all_line = [];
        for (var i = 0; i < data.length; i++) {
            student[i] = data[i].author;
            all_line[i] = data[i].line_total;
        }
        //画图
        var canvas = document.createElement('canvas');
        canvas.style.height = '100px';
        var div = document.getElementById('contribution_percentChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);

        // var ctx = document.getElementById("contribution_percentChart");
        // if (contribution_percentChart != undefined) {
        //     contribution_percentChart.destroy();
        // }
        contribution_percentChart = new Chart(canvas, {
            type : 'pie',
            data : {
                labels : student,
                datasets : [
                    {
                        data : all_line,
                        backgroundColor : colors.slice(0, student.length),
                        hoverBackgroundColor: colors.slice(0, student.length)
                    },
                ],
                option : {
                    maintainAspectRatio : false
                }
            }
        });
    };

    var url = "/validCodingData/projectContribution/" + current_project_id + "/iteration/" + current_iteration_id;
    getData(url, callback);
}




/**
 * 每日提交折线图
 */
function drawCommitLineChart(){
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("dailyCommit_lineChart_div").style.display = "none";
            alert("无相关提交数据 !");
            return;
        }
        data = JSON.parse(data);
        //预处理数据
        var days = [], daily_add =[], daily_delete = [], daily_commit = [];
        for (var i = 0; i < data.length; i++) {
            days[i] = data[i].commit_day;
            daily_add[i] = data[i].day_add;
            daily_delete[i] = 0-data[i].day_delete;
            daily_commit[i] = data[i].day_total;
        }
        //画图
        var canvas = document.createElement('canvas'); //canvas每次再次重置，就不需要destroy chart了
        canvas.style.height = '100px';
        var div = document.getElementById('dailyCommit_lineChart_div');
        div.innerHTML = '';
        div.appendChild(canvas);
        // var ctx = document.getElementById("dailyCommit_lineChart");
        // if (composition_percentChart != undefined) {
        //     composition_percentChart.destroy();
        // }
        dailyCommit_lineChart = new Chart(canvas, {
            type: "line",
            data: {
                labels: days,
                datasets: [
                    {
                        label: "add line",
                        data: daily_add,
                        fill: false,
                        lineTension: 0.1,
                        borderColor: "#F69696",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: "#F69696",
                        pointBackgroundColor: "#F69696"
                    },
                    {
                        label: "delete line",
                        data: daily_delete,
                        fill: false,
                        lineTension: 0.1,
                        borderColor: "#33B9D7",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: "#33B9D7",
                        pointBackgroundColor: "#33B9D7"
                    },
                    {
                        label: "final commit",
                        data: daily_commit,
                        fill: false,
                        lineTension: 0.1,
                        borderColor: "#28ff00",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        pointBorderColor: "#28ff00",
                        pointBackgroundColor: "#28ff00"
                    }
                ]
            },
            option: {
                maintainAspectRatio: false,
                scales: {
                    xAxes: [{
                        gridLines : {
                            display : false,
                            color: "rgb(0, 0, 0)",
                            show : false
                        },
                        tick : {
                            display : false
                        }
                    }],
                    yAxes: [{
                        gridLines: {
                            display : false,
                            color: "rgb(0, 0, 0)",
                            show : false
                        },
                        tick : {
                            display : false
                        }
                    }]
                }
            }
        });
    };

    var url = "/validCodingData/projectDailyCommit/GetJson?project_id="+current_project_id
        +"&iteration_id="+current_iteration_id + "&screenOut=" ;
    if(commit_screenOut_list.length !== 0){
        url = url + commit_screenOut_list[0];
        if(commit_screenOut_list.length >= 2){
            for(var i=2;i<commit_screenOut_list.length;i++){
                url = url + "," + commit_screenOut_list[i];
            }
        }

    }
    getData(url, callback);
}




/**
 * 填充查询有效文件的列表
 */
function fillValidFileList(){

    removeList();
    var callback = function (data) {
        data = JSON.parse(data);
        if (data.length == 0 && document.getElementById("FileNotExist") == undefined) {
            var h4 = document.createElement("h4");
            h4.id = "FileNotExist";
            h4.innerHTML = "没有这样的文件";
            h4.style.color="red";
            document.getElementById("validFile_panel").appendChild(h4);
        } else {
            if (data.length != 0 && document.getElementById("FileNotExist") != undefined) {
                document.getElementById("validFile_panel").
                removeChild(document.getElementById("FileNotExist"));
            }
        }

        for (var i = 0; i < data.length && i < 10; i++) {
            var a = document.createElement("a");
            a.className = "list-group-item my-file-a";
            var href = data[i].project_url + "/blob/" + data[i].sha + "/" + data[i].file_name;
            a.setAttribute("href", href);
            a.setAttribute("target", "_blank");
            a.innerHTML = data[i].file_name;
            var div = document.getElementById("validFile_panel");
            div.appendChild(a);
        }
    };


    var url = "/validCodingData/projectValidFile/GetJson?project_id=" + current_project_id
        +"&iteration_id=" + current_iteration_id + "&selectedValidType="+selected_type ;
    getData(url, callback);
}

/*====================================================================================================================*/
















/*=================================================按钮切换与激活======================================================*/

/**
 * 切换valid有效代码按钮
 */
function valid_btnSwitch(btn_id) {
    if (document.getElementById(btn_id).className.includes("active"))
        return;

    if (current_project_id == "") return;
    if (btn_id == "valid_table_btn") {
        document.getElementById("valid_table_div").style.display = "block";
        document.getElementById("valid_barChart_div").style.display  = "none";
        document.getElementById("valid_percentChart_div").style.display  = "none";
        fillValidTable();
    } else if (btn_id == "valid_bar_btn"){
        document.getElementById("valid_table_div").style.display = "none";
        document.getElementById("valid_barChart_div").style.display  = "block";
        document.getElementById("valid_percentChart_div").style.display  = "none";
        drawValidBarChart();
    } else {
        document.getElementById("valid_table_div").style.display = "none";
        document.getElementById("valid_barChart_div").style.display  = "none";
        document.getElementById("valid_percentChart_div").style.display  = "block";
        drawValidPercentChart();
    }
    activeBtn(btn_id);
}

/**
 * 切换composition代码组成按钮
 */
function composition_btnSwitch(btn_id) {
    if (document.getElementById(btn_id).className.includes("active"))
        return;

    if (current_project_id == "") return;
    if (btn_id == "composition_table_btn") {
        document.getElementById("composition_table_div").style.display = "block";
        document.getElementById("composition_barChart_div").style.display  = "none";
        document.getElementById("composition_percentChart_div").style.display  = "none";
        fillCompositionTable();
    } else if (btn_id == "composition_bar_btn"){
        document.getElementById("composition_table_div").style.display = "none";
        document.getElementById("composition_barChart_div").style.display  = "block";
        document.getElementById("composition_percentChart_div").style.display  = "none";
        drawCompositionBarChart();
    } else {
        document.getElementById("composition_table_div").style.display = "none";
        document.getElementById("composition_barChart_div").style.display  = "none";
        document.getElementById("composition_percentChart_div").style.display  = "block";
        drawCompositionPercentChart();
    }
    activeBtn(btn_id);
}

/**
 * 切换contribution成员贡献按钮
 */
function contribution_btnSwitch(btn_id) {
    if (document.getElementById(btn_id).className.includes("active"))
        return;

    if (current_project_id == "") return;
    if (btn_id == "contribution_table_btn") {
        document.getElementById("contribution_table_div").style.display = "block";
        document.getElementById("contribution_barChart_div").style.display  = "none";
        document.getElementById("contribution_percentChart_div").style.display  = "none";
        fillContributionTable();
    } else if (btn_id == "contribution_bar_btn"){
        document.getElementById("contribution_table_div").style.display = "none";
        document.getElementById("contribution_barChart_div").style.display  = "block";
        document.getElementById("contribution_percentChart_div").style.display  = "none";
        drawContributionBarChart();
    } else {
        document.getElementById("contribution_table_div").style.display = "none";
        document.getElementById("contribution_barChart_div").style.display  = "none";
        document.getElementById("contribution_percentChart_div").style.display  = "block";
        drawContributionPercentChart();
    }
    activeBtn(btn_id);
}

/**
 * 激活当前按钮
 */
function activeBtn(btn_id) {
    for (var i = 0; i < document.getElementsByClassName("show-type-btn").length; i++){
        document.getElementsByClassName("show-type-btn")[i].className = "btn btn-default show-type-btn";
    }
    document.getElementById(btn_id).className += " active";
}

/*====================================================================================================================*/





/*=================================================迭代切换与激活======================================================*/

/**
 * 迭代切换
 */
function tabSwitch(iteration) {
    if (current_project_id == "") return;

    if (iteration == "iteration1") {
        current_iteration_id = 1;
    }else if (iteration == "iteration2") {
        current_iteration_id = 2;
    } else if (iteration == "iteration3") {
        current_iteration_id = 3;
    } else{
        current_iteration_id = 1;
    }

    
    activeIterationTab();
    //初始化只显示表格不显示两种图
    document.getElementById("valid_table_div").style.display  = "block";
    document.getElementById("valid_barChart_div").style.display  = "none";
    document.getElementById("valid_percentChart_div").style.display  = "none";
    activeBtn("valid_table_btn");
    fillValidTable();

    document.getElementById("composition_table_div").style.display  = "block";
    document.getElementById("composition_barChart_div").style.display  = "none";
    document.getElementById("composition_percentChart_div").style.display  = "none";
    activeBtn("composition_table_btn");
    fillCompositionTable();

    document.getElementById("contribution_table_div").style.display  = "block";
    document.getElementById("contribution_barChart_div").style.display  = "none";
    document.getElementById("contribution_percentChart_div").style.display  = "none";
    activeBtn("contribution_table_btn");
    fillContributionTable();



    document.getElementById("dailyCommit_div").style.display = "block";
    document.getElementById("dailyCommit_lineChart_div").style.display = "block";
    drawCommitLineChart();


    document.getElementById("validFile_retrieve").style.display = "block";
    document.getElementById("validFile_panel").style.display = "block";
    fillValidFileList();

}

/**
 * 激活当前迭代的tab
 */
function activeIterationTab() {
    for (var i = 0; i < document.getElementById("iteration_nav").children.length; i++) {
        document.getElementById("iteration_nav").children[i].classList.remove('active');
    }
    document.getElementById('iteration' + current_iteration_id).classList.add('active');
}

/*====================================================================================================================*/




/*======================================================搜索==========================================================*/

/**
 * 搜索按钮响应
 */
function search() {
    current_project_id = document.getElementById("project_id").value;
    document.getElementById("main_panel").style.display = "block";
    drawBasicInfo();
    tabSwitch("iteration1");
}

/*====================================================================================================================*/


/**
 * 提交筛选结果以重新画dailyCommit图
 */
function screenOutCommitType(){
    commit_screenOut_list = []; //清空
    var checkbox_div = document.getElementById("screenOut_checkbox_div");
    for(var eachLabel of checkbox_div.children) {
        var checkItem = eachLabel.firstElementChild;
        if( checkItem.checked ) {
            commit_screenOut_list.push(checkItem.value);
        }
    }
    // $(function(){
    //         $('input').each(function(i){
    //             if (this.checked)
    //             selectedValues.push( $(this).val() );
    //         });
    // });

    // console.log(JSON.stringify(selectedValues));
    document.getElementById("dailyCommit_div").style.display = "block";
    document.getElementById("dailyCommit_lineChart_div").style.display = "block";
    drawCommitLineChart();

}



/**
 * 选中文件类型，填充查询有效文件的列表
 */
function selectValidFile(selected){
    selected_type = selected;
    document.getElementById("selectType_btn").innerHTML = selected_type;
    fillValidFileList();
}

/**
 * 刷新时删除原来的有效文件表
 */
function removeList() {
    var list = document.getElementById("validFile_panel");
    for (var i = 0; i < document.getElementsByClassName("list-group-item my-file-a").length; ) {
        document.getElementById("validFile_panel").
        removeChild(document.getElementsByClassName("list-group-item my-file-a")[0]);
    }
}







/**
 * 搜索时  设置按回车出发搜索方法
 */
$(document).ready(function() {
    document.getElementById("project_id").addEventListener("keyup", function () {
        event.preventDefault();
        if (event.keyCode === 13)
            document.getElementById("search_button").click();
    });
});