var colors = ["#ED1C24", "#F7941D", "#F8F200", "#39B54A", "#0054A6", "#A757A8"];



var project_valid_chart = null,
    project_composition_chart = null;

function drawProjectInfo(url, callback) {
    var xhttp;

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        var object;
        if (this.readyState === 4 && this.status === 200) {
            object = this.response;
            object = JSON.parse(object);
            if (object.length === 0) {
                document.getElementById("project_name_panel").style.display = 'none';
                document.getElementById("project_info_panel").style.display = 'none';
                callback(0);
            }
            else {
                callback(1);
                document.getElementById("project_name_panel").style.display = 'block';
                document.getElementById("project_info_panel").style.display = 'block';
                document.getElementById("project_name").innerHTML = object[0].name;
                var a = document.createElement('a');
                a.href = object[0].project_url;
                a.innerHTML = object[0].project_url;
                document.getElementById("project_URL").innerHTML = a.outerHTML;
            }
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function drawValidProject(url) {
    var xhttp;

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        var object;
        if (this.readyState == 4 && this.status == 200) {
            object = this.response;
            object = JSON.parse(object);
            if (object.length === 0) {
                document.getElementById("project_valid_panel").style.display = 'none';
                return "fail";
            }
            document.getElementById("project_valid_panel").style.display = 'block';

            var all_valid_line = 0,   all_blank_line = 0, all_comment_line = 0;
            for (i = 0; i < object.length; i++) {
                all_valid_line = all_valid_line + object[i].valid_code_line;
                all_blank_line = all_blank_line + object[i].blank_line_total;
                all_comment_line = all_comment_line + object[i].comment_line_total;
            }



            console.log("画图啦！！！");
            //画图
            if(project_valid_chart != null){
                project_valid_chart.destroy();
            }
            var ctx = document.getElementById("projectValidChart").getContext('2d');
            project_valid_chart = new Chart(ctx, {
                type : "pie",
                data : {
                    labels : ["有效代码行数","空行数","注释行数"],
                    datasets : [
                        {
                            data : [all_valid_line, all_blank_line, all_comment_line],
                            backgroundColor: [
                                "#FF6384",
                                "#36A2EB",
                                "#FFFF00"
                            ],
                            hoverBackgroundColor: [
                                "#FF6384",
                                "#36A2EB",
                                "#FFFF00"
                            ]
                        }
                    ]
                },
                option : {
                    maintainAspectRatio : false
                }
            });

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}


function drawProjectComposition(url) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        var object;
        if (this.readyState == 4 && this.status == 200) {
            object = this.response;
            object = JSON.parse(object);
            if (object.length === 0) {
                document.getElementById("project_composition_panel").style.display = "none";
                return;
            }


            document.getElementById("project_composition_panel").style.display = "block";
            var type_array = [], line_array = [];
            for (var i=0;i<object.length;i++) {
                type_array[i] = object[i].type;
                line_array[i] = object[i].line_total;
            }

            //画图
            if(project_composition_chart != null){
                project_composition_chart.destroy();
            }
            var ctx = document.getElementById("projectCompositionChart");
            project_composition_chart = new Chart(ctx, {
                type : 'doughnut',
                data : {
                    labels : type_array,
                    datasets : [
                        {
                            data : line_array,
                            backgroundColor : colors.slice(0, type_array.length)
                        },
                    ],
                    options: {
                        animation:{
                            animateScale:true
                        }
                    }
                }
            });

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

//tab 转换
function tabSwitch(iteration_id) {
    var
        length = document.getElementsByClassName("my-li").length,
        project_id = document.getElementById("project_id").value,
        projectInfo_url, validProject_url;
    if (project_id == "")
        return;
    var callback = function (result) {
        if (result === 0) {
            document.getElementById("iteration_nav").style.display = "none";
            alert("此项目不存在");
        } else {
            document.getElementById("iteration_nav").style.display = "block";
            activeTab(iteration_id);
        }
    };
    for (var i = 0; i < length; i++) {
        document.getElementsByClassName("my-li")[i].className = "my-li";
    }

    projectInfo_url = "/data/validCoding/projectInfo/" + project_id;
    switch (iteration_id) {
        case "iteration1" :
            validProject_url = "/data/validCoding/project/" + project_id + "/iteration/1";
            break;
        case "iteration2" :
            validProject_url = "/data/validCoding/project/" + project_id + "/iteration/2";
            break;
        case "iteration3" :
            validProject_url = "/data/validCoding/project/" + project_id + "/iteration/3";
            break;
    }
    drawProjectInfo(projectInfo_url, callback);
    drawValidProject(validProject_url);
    drawProjectComposition(validProject_url);

}

function search() {
    var id = document.getElementById("project_id").value;
    var callback = function (result) {
        if (result === 0) {
            alert("此项目不存在");
        } else {
            document.getElementById("iteration_nav").style.display = "block";
            activeTab("iteration1");
        }
    };
    //make every tab inactive
    var length = document.getElementsByClassName("my-li").length;
    for (var i = 0; i < length; i++) {
        document.getElementsByClassName("my-li")[i].className = "my-li";
    }
    drawProjectInfo("/data/validCoding/projectInfo/" + id, callback);
    drawValidProject("/data/validCoding/project/" + id + "/iteration/1");
    drawProjectComposition("/data/validCoding/project/" + id + "/iteration/1");
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
    document.getElementById("valid_coding_li").className = "active";
}

//key enter function
document.getElementById("project_id").addEventListener("keyup", function () {
    event.preventDefault();
    if (event.keyCode == 13)
        document.getElementById("search_button").click();
});
//active sidebar
activeSidebar();


