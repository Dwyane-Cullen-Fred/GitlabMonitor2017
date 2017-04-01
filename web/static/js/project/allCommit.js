function draw() {

    var xhttp, days = [], commit = [], add_line = [], delete_line = [], file = [];
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            object = JSON.parse(this.response);

            for (var i in object) {
                days[i] = object[i].day.month + "-" + object[i].day.day;
                commit[i] = object[i].commit_count;
                add_line[i] = object[i].add_line;
                file[i] = object[i].java_file;
            }

            var commit_sum = commit.reduce(function (a, b) {
                return a + b;
            }, 0);

            var add_sum = add_line.reduce(function (a, b) {
                return a + b;
            }, 0);

            for (i = 0; i < object.length; i++) {
                commit[i] = commit[i] / commit_sum * 100;
                add_line[i] = add_line[i] / add_sum * 100;
            }

            var ctx = document.getElementById("myChart").getContext("2d");
            new Chart(ctx, {
                type: "line",
                data: {
                    labels: days,
                    datasets: [
                        {
                            label: "commit",
                            data: commit,
                            fill: false,
                            lineTension: 0.1,
                            borderColor: "rgba(255,0,0,1)",
                            borderCapStyle: 'butt',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "rgba(255,0,0,1)",
                            pointBackgroundColor: "#fff"
                        },
                        {
                            label: "add_line",
                            data: add_line,
                            fill: false,
                            lineTension: 0.1,
                            borderColor: "rgba(0,0,255,1)",
                            borderCapStyle: 'butt',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "rgba(0,0,255,1)",
                            pointBackgroundColor: "#fff"
                        }
                    ]
                },
                option: {
                    maintainAspectRatio: false,
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
    }
    xhttp.open("GET", "/data/dayCommitData", true);
    xhttp.send();
}

//设置menu对应的选项为active
function avtiveMenu() {
    document.getElementById("all_commit_li").className = "active";
}

draw();
avtiveMenu();