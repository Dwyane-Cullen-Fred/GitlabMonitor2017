var graph1, graph2;
var colors = ["#ff0000", "#ffff00", "#0000ff", "#00ffff", "#00ff00"];


function activeSidebar() {
    document.getElementById("student_commit_li").className = " active";
}

activeSidebar();

function activeBtn(btnId) {
    for (var i = 0; i < document.getElementsByClassName("show-type-btn").length; i++){
        document.getElementsByClassName("show-type-btn")[i].className = "btn btn-default show-type-btn";
    }
    document.getElementById(btnId).className += " active";
}

function activeIterationTab(iterationId) {
    for (var i = 0; i < document.getElementById("iteration_nav").children.length; i++) {
        document.getElementById("iteration_nav").children[i].className = "";
    }
    document.getElementById(iterationId).className = " active";
}

function getActiveIteration() {
    if (document.getElementById("iteration1").className.includes("active"))
        return 1;
    else if (document.getElementById("iteration2").className.includes("active"))
        return 2;
    else if (document.getElementById("iteration3").className.includes("active"))
        return 3;
    else return 1;
}

function removeList() {
    var list = document.getElementById("invalid-file-show-panel");
    for (var i = 0; i < document.getElementsByClassName("list-group-item my-file-a").length; ) {
        document.getElementById("invalid-file-show-panel").
                    removeChild(document.getElementsByClassName("list-group-item my-file-a")[0]);
    }
}

//ajax
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

//数值显示
function convertToNumberData(data) {
    var result = [];
    for (var i = 0; i < data.length; i++) {
        var ele = [];
        ele.push(data[i].student);
        ele.push(data[i].allCode < 0 ? 0 : data[i].allCode);
        ele.push(data[i].frontCode < 0 ? 0 : data[i].frontCode);
        ele.push(data[i].endCode < 0 ? 0 : data[i].endCode);
        ele.push(data[i].codeFromOthers < 0 ? 0 : data[i].codeFromOthers);
        ele.push(data[i].codeToOthers < 0 ? 0 : data[i].codeToOthers);
        result.push(ele);
    }
    return result;
}

//百分比显示
function convertToPercentData(data) {
    data = convertToNumberData(data);
    var result = [];
    var sum = 0;
    for (var i = 0; i < data.length; i++) {
        var ele = [];
        ele.push(data[i][0]);
        ele.push(data[i][1]);
        if (data[i][3] + data[i][2] != 0) {
            ele.push((data[i][2] * 100 / (data[i][2] + data[i][3])).toFixed(2) + "%");
            ele.push((data[i][3] * 100 / (data[i][2] + data[i][3])).toFixed(2) + "%");
            ele.push((data[i][4] * 100 / (data[i][2] + data[i][3])).toFixed(2) + "%");
        }else {
            ele.push("0%");
            ele.push("0%");
            ele.push("0%");
        }
        ele.push((data[i][5] / data[i][1] * 100).toFixed(2)  + "%");
        sum = sum + data[i][1];
        result.push(ele);
    }

    for (i = 0; i < data.length; i++) {
        result[i][1] = (result[i][1] * 100 / sum).toFixed(2) + "%"
    }

    return result;
}

function fillTable(projectId, iteration, showType) {
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("container_panel").style.display = "none";
            alert("project not exist!");
            return;
        }
        data = JSON.parse(data);
        var contribution = showType == 1 ? convertToNumberData(data) : convertToPercentData(data);
        document.getElementById("container_panel").style.display = "block";

        var i = 0, j = 0;
        var table = document.getElementById("projectTable");
        if (table.getElementsByTagName('tbody').length != 0)
            table.removeChild(table.getElementsByTagName('tbody')[0]);
        var tBody = document.createElement('tbody');
        for (i = 0; i < contribution.length; i++) {
            var tr = document.createElement('tr');
            for (j = 0; j < contribution[i].length; j++){
                var td = document.createElement('td');
                td.appendChild(document.createTextNode(contribution[i][j]));
                tr.appendChild(td);
            }
            tBody.appendChild(tr);
        }
        table.appendChild(tBody);
    };
    var url = "/data/project/" + projectId + "/iteration/" + iteration + "/contribution";
    getData(url, callback);
}

function drawGraph() {
    var projectId = document.getElementById("projectId").value;
    var iteration = getActiveIteration();
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("container_panel").style.display = "none";
            alert("project not exist!");
            return;
        }
        data = JSON.parse(data);
        var students = [], commit_all = [], commit_to_others = [], code_all = [], commit_from_others = [];
        var i = 0;
        for (i = 0; i < data.length; i++) {
            students[i] = data[i].student;
            code_all[i] = data[i].frontCode + data[i].endCode;
            commit_from_others[i] = data[i].codeFromOthers;
            commit_all[i] = data[i].allCode;
            commit_to_others[i] = data[i].codeToOthers;
        }
        var ctx1 = document.getElementById("graphCanvas1");
        var ctx2 = document.getElementById("graphCanvas2");
        if (graph1 != undefined) {
            graph1.destroy();
        }
        if (graph2 != undefined) {
            graph2.destroy();
        }
        graph1 = new Chart(ctx1, {
            type : 'bar',
            data : {
                labels : students,
                datasets: [
                    {
                        label : "提交总量",
                        backgroundColor: colors[0],
                        borderColor: colors[0],
                        borderWidth : 1,
                        data : commit_all
                    },
                    {
                        label : "修改他人代码",
                        backgroundColor: colors[1],
                        borderColor: colors[1],
                        borderWidth : 1,
                        data : commit_to_others
                    }
                ]
            }
        });
        graph2 = new Chart(ctx2, {
            type : 'bar',
            data : {
                labels : students,
                datasets: [
                    {
                        label : "代码总量",
                        backgroundColor: colors[0],
                        borderColor: colors[0],
                        borderWidth : 1,
                        data : commit_all
                    },
                    {
                        label : "其他组员修改代码",
                        backgroundColor: colors[1],
                        borderColor: colors[1],
                        borderWidth : 1,
                        data : commit_from_others
                    }
                ]
            }
        });

    };
    var url = "/data/project/" + projectId + "/iteration/" + iteration + "/contribution";
    getData(url, callback);
}

function getProjectName() {
    var callback = function (data) {
        if (data.toString() === "" || data.toString() === "[]") {
            document.getElementById("container_panel").style.display = "none";
            alert("project not exist!");
            return;
        }
        document.getElementById("projectName").innerHTML = "项目： " + data;
    };
    var projectId = document.getElementById("projectId").value;
    var url = "/data/project/" + projectId + "/projectName";
    getData(url, callback)
}


function getInvalidFile(filterId) {
    removeList();
    var callback = function (data) {
        data = JSON.parse(data);
        var div = document.getElementById("invalid-file-show-panel");
        if (data.length == 0 && document.getElementById("showFileNotExist") == undefined) {
            var h2 = document.createElement("h2");
            h2.id = "showFileNotExist";
            h2.innerHTML = "没有这样的文件";
            document.getElementsByClassName("commit_situation")[0].appendChild(h2);
        } else {
            if (data.length != 0 && document.getElementById("showFileNotExist") != undefined) {
                document.getElementsByClassName("commit_situation")[0].
                                removeChild(document.getElementById("showFileNotExist"));
            }
        }
        for (var i = 0; i < data.length && i < 10; i++) {
            var a = document.createElement("a");
            a.className = "list-group-item my-file-a";
            var href = data[i].webUrl + "/blob/" + data[i].sha + "/" + data[i].filename;
            a.setAttribute("href", href);
            a.setAttribute("target", "_blank");
            a.innerHTML = data[i].filename;
            div.appendChild(a);
        }
    };

    var projectId = document.getElementById("projectId").value;
    var iterationId = getActiveIteration();
    var typeId = 1;
    if (filterId == "filter1") {
        typeId = 1;
    } else if (filterId == "filter2") {
        typeId = 2;
    } else {
        typeId = 3;
    }
    var url = "/data/project/" + projectId + "/iteration/" + iterationId + "/invalidFile" + "/type/" + typeId;
    getData(url, callback);
}

function btnSwitch(btnId) {
    if (document.getElementById(btnId).className.includes("active"))
        return;

    var projectId = document.getElementById("projectId").value;
    var iterationId = getActiveIteration();
    if (projectId == "") return;

    if (btnId == "number_btn") {
        document.getElementById("table_div").style.display = "block";
        document.getElementById("graph_div").style.display  = "none";
        fillTable(projectId, iterationId, 1);
    } else if (btnId == "percent_btn"){
        document.getElementById("table_div").style.display = "block";
        document.getElementById("graph_div").style.display  = "none";
        fillTable(projectId, iterationId, 0);
    } else {
        document.getElementById("table_div").style.display = "none";
        document.getElementById("graph_div").style.display  = "block";
        drawGraph();
    }
    activeBtn(btnId);
}


function tabSwitch(iterationId) {
    var projectId = document.getElementById("projectId").value;
    if (projectId == "") return;

    if (iterationId == "iteration1") {
        fillTable(projectId, 1, 1);
    }else if (iterationId == "iteration2") {
        fillTable(projectId, 2, 1);
    } else if (iterationId == "iteration3") {
        fillTable(projectId, 3, 1);
    }
    document.getElementById("table_div").style.display = "block";
    document.getElementById("graph_div").style.display  = "none";

    getInvalidFile("filter1");
    activeIterationTab(iterationId);
    activeBtn("number_btn");
}

function search() {
    var projectId = document.getElementById("projectId").value;
    getProjectName();
    fillTable(projectId, 1, 1);
    activeIterationTab("iteration1");
    activeBtn("number_btn");
    getInvalidFile("filter1");
}

//key enter function
document.getElementById("projectId").addEventListener("keyup", function () {
    event.preventDefault();
    if (event.keyCode == 13)
        document.getElementById("search_button").click();
});