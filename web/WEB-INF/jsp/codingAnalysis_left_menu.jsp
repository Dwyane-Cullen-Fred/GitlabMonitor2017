<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="left-bar" class="col-md-2 col-sm-3 my-col-lg-2">
    <ul class="nav nav-pills nav-stacked">
        <!--  <li id="project_commit_li" role="presentation"><a href="/projectCommit/detail">项目分析</a></li>  -->
        <li id="valid_coding_li" role="presentation"><a href="${pageContext.request.contextPath}/validCoding/project/detail">
            项目代码分析</a></li>
        <li id="student_coding_li" role="presentation"><a href="${pageContext.request.contextPath}/validCoding/student/detail">
            学生代码分析</a></li>
    </ul>
</div>