var colors = ["#ff0000", "#ffff00", "#0000ff", "#00ffff", "#808080"];
var project_line_chart = null,
    student_total_commit_chart = null,
    student_total_add_chart = null,
    student_total_del_char = null;

function drawProject(url) {
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
                return "fail";
            }
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

//sidebar的对应属性设置为active
function activeSidebar() {
    document.getElementById("project_commit_li").className = "active";
}

//tab的对应属性设置为active
function activeTab() {
    document.getElementById("iterationAll").className = "my-li active";
}

//tab 转换
function tabSwitch(id) {
    var
        length = document.getElementsByClassName("my-li").length,
        project_id = document.getElementById("project_id").value,
        project_url, student_url;
    for (var i = 0; i < length; i++) {
        document.getElementsByClassName("my-li")[i].className = "my-li";
    }
    document.getElementById(id).className += " active";
    switch (id) {
        case "iterationAll" :
            project_url = "/data/project/" + project_id;
            student_url = "/data/project/" + project_id + "/studentCommit";
            break;
        case "iteration1" :
            project_url = "/data/project/" + project_id + "/iteration/1";
            student_url = "/data/project/" + project_id + "/studentCommit/iteration/1";
            break;
        case "iteration2" :
            project_url = "/data/project/" + project_id + "/iteration/2";
            student_url = "/data/project/" + project_id + "/studentCommit/iteration/2";
            break;
        case "iteration3" :
            project_url = "/data/project/" + project_id + "/iteration/3";
            student_url = "/data/project/" + project_id + "/studentCommit/iteration/3";
            break;
    }
    drawProject(project_url);
    drawStudentCommit(student_url);
}

function search() {
    var id;
    id = document.getElementById("project_id").value;

    drawProject("/data/project/" + id);
    drawStudentCommit("/data/project/" + id + "/studentCommit");
    activeTab();
}

document.getElementById("project_id").addEventListener("keyup", function () {
    event.preventDefault();
    if (event.keyCode == 13)
        document.getElementById("search_button").click();
});

activeSidebar();