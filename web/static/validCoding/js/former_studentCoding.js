var colors = ["#ED1C24", "#F7941D", "#F8F200", "#39B54A", "#0054A6", "#A757A8"];



var student_valid_chart = null,
    student_composition_chart = null;

function drawProjectInfo(url, callback) {
    var xhttp;

    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        console.log('response');
        var object;
        if (this.readyState === 4 && this.status === 200) {
            object = this.response;
            object = JSON.parse(object);
            if (object.length === 0) {
                document.getElementById("student_name_panel").style.display = 'none';
                document.getElementById("student_info_panel").style.display = 'none';
                callback(0);
            }
            else {
                callback(1);
                document.getElementById("student_name_panel").style.display = 'block';
                document.getElementById("student_info_panel").style.display = 'block';

                document.getElementById("student_name").innerHTML = object[0].author ;
                document.getElementById("project_id").innerHTML = object[0].project_id ;
                document.getElementById("project_name").innerHTML = object[0].project_name ;
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

function drawValidStudent(url) {
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
                document.getElementById("student_valid_panel").style.display = 'none';
                return "fail";
            }
            document.getElementById("student_valid_panel").style.display = 'block';

            var all_valid_line = 0,   all_blank_line = 0, all_comment_line = 0;
            for (i = 0; i < object.length; i++) {
                all_valid_line = all_valid_line + object[i].valid_code_line;
                all_blank_line = all_blank_line + object[i].blank_line_total;
                all_comment_line = all_comment_line + object[i].comment_line_total;
            }



            console.log("画图啦！！！");
            //画图
            if(student_valid_chart != null){
                student_valid_chart.destroy();
            }
            var ctx = document.getElementById("studentValidChart").getContext('2d');
            student_valid_chart = new Chart(ctx, {
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


function drawStudentComposition(url) {
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
                document.getElementById("student_composition_panel").style.display = "none";
                return;
            }


            document.getElementById("student_composition_panel").style.display = "block";
            var type_array = [], line_array = [];
            for (var i=0;i<object.length;i++) {
                type_array[i] = object[i].type;
                line_array[i] = object[i].line_total;
            }

            //画图
            if(student_composition_chart != null){
                student_composition_chart.destroy();
            }
            var ctx = document.getElementById("studentCompositionChart");
            student_composition_chart = new Chart(ctx, {
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
function tabSwitch(iteration) {
    var
        length = document.getElementsByClassName("my-li").length,
        author_name = document.getElementById("name_input").value,
        studentInfo_url, studentCoding_url;
    if (!author_name)
        return;
    activeTab(iteration);
    var callback = function (result) {
        if (result === 0) {
            document.getElementById("iteration_nav").style.display = "none";
            alert("此人不存在");
        } else {
            document.getElementById("iteration_nav").style.display = "block";
        }
    };
    // for (var i = 0; i < length; i++) {
    //     document.getElementsByClassName("my-li")[i].className = "my-li";
    // }

    studentInfo_url = "/data/validCoding/studentInfo/" + author_name;
    switch (iteration) {
        case "iteration1" :
            studentCoding_url = "/data/validCoding/student/" + author_name + "/iteration/1";
            break;
        case "iteration2" :
            studentCoding_url = "/data/validCoding/student/" + author_name + "/iteration/2";
            break;
        case "iteration3" :
            studentCoding_url = "/data/validCoding/student/" + author_name + "/iteration/3";
            break;
    }
    drawProjectInfo(studentInfo_url, callback);
    drawValidStudent(studentCoding_url);
    drawStudentComposition(studentCoding_url);

}

function search() {
    var author_name = document.getElementById("name_input").value;
    var callback = function (result) {
        if (result === 0) {
            alert("此开发者不存在");
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
    drawProjectInfo("/data/validCoding/studentInfo/" + author_name, callback);
    drawValidStudent("/data/validCoding/student/" + author_name + "/iteration/1");
    drawStudentComposition("/data/validCoding/student/" + author_name + "/iteration/1");
}

function activeTab(id) {
    for (let tab of document.getElementsByClassName("my-li")) {
        tab.className = "my-li";
    }
    // console.log(id);
    let tab = document.getElementById(id);
    console.log(tab.className);
    tab.className = "my-li active";
    console.log(tab.className);
}

//sidebar的对应属性设置为active
function activeSidebar() {
    document.getElementById("student_coding_li").className = "active";
}

//key enter function
document.getElementById("name_input").addEventListener("keyup", function () {
    event.preventDefault();
    if (event.keyCode == 13)
        document.getElementById("search_button").click();
});
//active sidebar
activeSidebar();


