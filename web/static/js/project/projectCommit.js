var colors = ["#ff0000", "#ffff00", "#0000ff", "#00ffff", "#808080"];
/**
 * 4个节点时的x，y坐标
 */
var nodesFourXY = [
    {x : 1/5, y : 1/5},
    {x : 1/5, y : 4/5},
    {x : 4/5, y : 1/5},
    {x : 4/5, y : 4/5}
];
/**
 * 3个节点时的x，y坐标
 */
var nodesThreeXY = [
    {x : 1/2, y : 1/5},
    {x : 1/5, y : 4/5},
    {x : 4/5, y : 4/5}
];
var project_line_chart = null,
    student_total_commit_chart = null,
    student_total_add_chart = null,
    student_total_del_char = null,
    student_valid_commit_chart = null;

function drawProject(url, callback) {
    var xhttp;

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        var object, days = [], commit = [], add_line = [], delete_line = [], file = [];
        if (this.readyState == 4 && this.status == 200) {
            object = this.response;
            if (object.toString() == "[]") {
                document.getElementById("project_panel").style.display = 'none';
                callback(0);
                return;
            }
            callback(1);
            object = JSON.parse(object);
            document.getElementById("project_panel").style.display = 'block';
            document.getElementById("project_information").innerHTML = object[0].team + " 项目组";
            for (i = 0; i < object.length; i++) {
                days[i] = object[i].day.month + "-" + object[i].day.day;
                commit[i] = object[i].commit_count;
                add_line[i] = object[i].add_line / 100;
                delete_line[i] = object[i].delete_line;
                file[i] = object[i].java_file;
            }
            if (project_line_chart != null) {
                project_line_chart.destroy();
            }
            var ctx = document.getElementById("projectChart").getContext('2d');
            project_line_chart = new Chart(ctx, {
                type : "line",
                data : {
                    labels : days,
                    datasets : [
                        {
                            data : commit,
                            label : "commit",
                            fill : false,
                            borderColor: "rgba(255,0,0,1)"
                        },
                        {
                            data : add_line,
                            label : "add_line/100",
                            fill : false,
                            borderColor : "rgba(0, 255, 0, 1)"
                        }
                    ]
                },
                option : {
                    maintainAspectRatio : false,
                    scales: {
                        xAxes: [{
                            gridLines : {
                                display : false,
                                color: "rgba(0, 0, 0, 0)",
                                show : false
                            },
                            tick : {
                                display : false
                            }
                        }],
                        yAxes: [{
                            gridLines: {
                                display : false,
                                color: "rgba(0, 0, 0, 0)",
                                show : false
                            },
                            tick : {
                                display : false
                            }
                        }]
                    }
                }
            });
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function drawStudentCommit(url) {
    var xhttp;

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        var object, students = [], total_commit = [], total_add = [], total_delete = [], student = "";
        if (this.readyState == 4 && this.status == 200) {
            object = this.response;
            if (object.toString() === "[]") {
                document.getElementById("student_panel").style.display = 'none';
                return "fail";
            }
            document.getElementById("student_panel").style.display = 'block';

            object = JSON.parse(object);
            for (i = 0; i < object.length; i++ ) {
                students[i] = object[i].student;
                total_commit[i] = object[i].total_commit;
                total_add[i] = object[i].total_add;
                total_delete[i] = object[i].total_delete;
                if (i != object.length - 1) {
                    student = student + students[i] + ", ";
                } else {
                    student += students[i]
                }
            }
            document.getElementById("student_information").innerHTML = "组员：" + student;
        }

        if (student_total_commit_chart != null) {
            student_total_commit_chart.destroy();
        }

        if (student_total_add_chart != null) {
            student_total_add_chart.destroy();
        }

        if (student_total_del_char != null) {
            student_total_del_char.destroy();
        }

        var commit_ctx = document.getElementById("studentCommit");
        var add_ctx = document.getElementById("studentAdd");
        var del_ctx = document.getElementById("studentDel");
        student_total_commit_chart = new Chart(commit_ctx, {
            type : "pie",
            data : {
                labels : students,
                datasets : [
                    {
                        data : total_commit,
                        backgroundColor : colors.slice(0, 4)
                    }

                ]
            },
            option : {
                maintainAspectRatio : false
            }
        });
        student_total_add_chart = new Chart(add_ctx, {
            type : "pie",
            data : {
                labels : students,
                datasets : [
                    {
                        data : total_add,
                        backgroundColor : colors.slice(0, 4)
                    }

                ]
            },
            option : {
                maintainAspectRatio : false
            }
        });
        student_total_del_char = new Chart(del_ctx, {
            type : "pie",
            data : {
                labels : students,
                datasets : [
                    {
                        data : total_delete,
                        backgroundColor : colors.slice(0, 4)
                    }

                ]
            },
            option : {
                maintainAspectRatio : false
            }
        });
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

//draw connection of team (team has three or four members)
function drawConnection(url) {
    var xhttp, object;
    var set = new Set(), name_array = [], nodes = [], lines = [], lines_length = 0;
    var radius = 40, min_stroke_width = Number.MAX_SAFE_INTEGER;
    var i = 0;

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            object = this.response;
            if (object.toString() === "[]") {
                document.getElementById("connection_panel").style.display = "none";
                return;
            }

            document.getElementById("connection_panel").style.display = "block";
            object = JSON.parse(object);
            for (i = 0; i < object.length; i++) {
                if (!set.has(object[i].member1)) {
                    set.add(object[i].member1);
                }
                if (!set.has(object[i].member2)) {
                    set.add(object[i].member2);
                }
            }
            name_array = Array.from(set);
            var svg = document.getElementById("connection_svg");
            var bounds = svg.getBoundingClientRect();
            var svg_width = bounds.right - bounds.left, svg_height = bounds.bottom - bounds.top;

            //init nodes
            if (name_array.length == 3) {
                for (i = 0; i < nodesThreeXY.length; i++) {
                    nodes[i].x = nodesThreeXY[i].x * svg_width;
                    nodes[i].y = nodesThreeXY[i].y * svg_height;
                    nodes[i].name = name_array[i];
                }
            } else if (name_array.length == 4) {
                for (i = 0; i < nodesFourXY.length; i++) {
                    nodes[i] = {
                        x : nodesFourXY[i].x * svg_width,
                        y : nodesFourXY[i].y * svg_height,
                        name : name_array[i]};
                }
            } else {
                return;
            }

            //init lines
            for (i = 0; i < nodes.length; i++) {
                for (var j = i + 1; j < nodes.length; j++) {
                    lines[lines_length] = {x1: nodes[i].x, y1: nodes[i].y, x2: nodes[j].x, y2: nodes[j].y};
                    for (var k = 0; k < object.length; k++) {
                        if ((nodes[i].name == object[k].member1 && nodes[j].name == object[k].member2) ||
                                nodes[i].name == object[k].member2 && nodes[j].name == object[k].member1){
                            lines[lines_length].count = object[k].connectionCount;
                            if(object[k].connectionCount < min_stroke_width) {
                                min_stroke_width = object[k].connectionCount;
                            }
                            break;
                        }
                    }
                    lines_length = lines_length + 1;
                }
            }

            //create line elements
            for (i = 0; i < lines.length; i++) {
                var line = document.createElementNS('http://www.w3.org/2000/svg', 'line');
                line.setAttribute("class", "link");
                line.setAttribute("stroke-width", 10 * lines[i].count / min_stroke_width);
                line.setAttribute("x1", lines[i].x1);
                line.setAttribute("y1", lines[i].y1);
                line.setAttribute("x2", lines[i].x2);
                line.setAttribute("y2", lines[i].y2);
                svg.appendChild(line);
            }

            //create circle elements
            for (i = 0; i < nodes.length; i++) {
                var g = document.createElementNS("http://www.w3.org/2000/svg", 'g');
                var circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
                var text = document.createElementNS('http://www.w3.org/2000/svg', 'text');
                circle.setAttribute("class", "node");
                text.setAttribute("class", "circle_text");
                text.setAttribute("x", nodes[i].x / svg_width * 100 + "%");
                text.setAttribute("y", nodes[i].y / svg_height * 100 + "%");
                text.innerHTML = name_array[i];
                circle.setAttribute("r", radius);
                circle.setAttribute("cx", nodes[i].x);
                circle.setAttribute("cy", nodes[i].y);
                g.appendChild(circle);
                g.appendChild(text);
                svg.appendChild(g);
            }


        }
    }
    xhttp.open("GET", url, true);
    xhttp.send();
}

function drawValidCommit(url) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        var object, members = [], total_add = [], valid_add = [], ctx;
        var i = 0;
        if (this.readyState = 4 && this.status == 200) {
            object = this.response;
            if (object.toString() === "[]" || object.toString() === "") {
                document.getElementById("valid_commit").style.display = "none";
                return;
            }
            object = JSON.parse(object);
            document.getElementById("valid_commit").style.display = "block";
            for (; i < object.length; i++) {
                members[i] = object[i].member;
                total_add[i] = object[i].total_add;
                valid_add[i] = object[i].valid_add;
            }
            if (student_valid_commit_chart != null) {
                student_valid_commit_chart.destroy();
            }
            ctx = document.getElementById("valid_commit_chart");

            student_valid_commit_chart = new Chart(ctx, {
                type : 'bar',
                data : {
                    labels : members,
                    datasets : [
                        {
                            label : "总代码",
                            backgroundColor : colors[0],
                            data : total_add,
                            borderWidth : 1
                        },
                        {
                            label : "有效代码",
                            backgroundColor : colors[1],
                            data : valid_add,
                            borderWidth : 1
                        }
                    ]
                }
            });
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

//tab 转换
function tabSwitch(id) {
    var
        length = document.getElementsByClassName("my-li").length,
        project_id = document.getElementById("project_id").value,
        project_url, student_url,conection_url = "",valid_commit = "";
    if (project_id == "")
        return;
    var callback = function (result) {
        if (result === 0) {
            alert("此项目不存在");
        } else {
            activeTab(id);
        }
    };
    for (var i = 0; i < length; i++) {
        document.getElementsByClassName("my-li")[i].className = "my-li";
    }
    switch (id) {
        case "iterationAll" :
            project_url = "/data/project/" + project_id;
            student_url = "/data/project/" + project_id + "/studentCommit";
            valid_commit = "/data/project/" + project_id + "/studentValidCommit"
            conection_url = "/data/project/" + project_id + "/studentConnection";
            break;
        case "iteration1" :
            project_url = "/data/project/" + project_id + "/iteration/1";
            student_url = "/data/project/" + project_id + "/studentCommit/iteration/1";
            document.getElementById("connection_panel").style.display = "none";
            document.getElementById("valid_commit").style.display = "none";
            break;
        case "iteration2" :
            project_url = "/data/project/" + project_id + "/iteration/2";
            student_url = "/data/project/" + project_id + "/studentCommit/iteration/2";
            document.getElementById("connection_panel").style.display = "none";
            document.getElementById("valid_commit").style.display = "none";
            break;
        case "iteration3" :
            project_url = "/data/project/" + project_id + "/iteration/3";
            student_url = "/data/project/" + project_id + "/studentCommit/iteration/3";
            document.getElementById("connection_panel").style.display = "none";
            document.getElementById("valid_commit").style.display = "none";
            break;
    }
    drawProject(project_url, callback);
    drawStudentCommit(student_url);
    if (conection_url != "") {
        drawConnection(conection_url);
    }
    if (valid_commit != ""){
        drawValidCommit(valid_commit);
    }
}

function search() {
    var id = document.getElementById("project_id").value;
    var callback = function (result) {
        if (result === 0) {
            alert("此项目不存在");
        } else {
            activeTab("iterationAll");
        }
    };
    //make every tab inactive
    for (var i = 0; i < length; i++) {
        document.getElementsByClassName("my-li")[i].className = "my-li";
    }
    drawProject("/data/project/" + id, callback);
    drawValidCommit("/data/project/" + id + "/studentValidCommit");
    drawStudentCommit("/data/project/" + id + "/studentCommit");
    drawConnection("/data/project/" + id + "/studentConnection");
}

function activeTab(id) {
    var length = document.getElementsByClassName("my-li").length;
    for (var i = 0; i < length; i++) {
        document.getElementsByClassName("my-li")[i].className = "my-li";
    }
    document.getElementById(id).className = "my-li active";
}

//sidebar的对应属性设置为active
function activeSidebar() {
    document.getElementById("project_commit_li").className = "active";
}

//key enter function
document.getElementById("project_id").addEventListener("keyup", function () {
    event.preventDefault();
    if (event.keyCode == 13)
        document.getElementById("search_button").click();
});
//active sidebar
activeSidebar();