package org.localService;

import org.bean.Contribution;
import org.bean.ContributionAggregation;
import org.bean.ProjectFile;
import org.localDao.LocalFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.Util.Constant;
import org.Util.FileType;
import org.Util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private LocalFileDao fileDao;

    /**
     *
     * @param projectId : 项目id
     * @param iterationId : 迭代编号
     * @return
     */
    public Map<String, ContributionAggregation> getStudentContribution(int projectId, int iterationId) {
        Map<String, Integer> all = getCommitContribution(projectId, iterationId);
        Map<String, Integer> front = getStudentFrontContribution(projectId, iterationId, 3000);
        Map<String, Integer> end = getStudentEndContribution(projectId, iterationId, 3000);
        Map<String, Integer> contributionFromOthers = getContributionFromOthers(projectId, iterationId);
        Map<String, Integer> contributionToOthers = getContributionToOthers(projectId, iterationId);

        return MapUtil.mapMerge(all, front, end, contributionFromOthers, contributionToOthers);
    }

    /**
     * 前段贡献代码的数量 (html, js, css, jsp)
     * @param projectId : 项目id
     * @param iteration ： 迭代id
     * @param lineLimit ： 行数限制
     * @return Map
     */
    private Map<String, Integer> getStudentFrontContribution(int projectId, int iteration, int lineLimit)
    {
        Map<String, Integer> cssMap = getStudentContribution(projectId, iteration, FileType.CSS, lineLimit);
        Map<String, Integer> htmlMap = getStudentContribution(projectId, iteration, FileType.HTML, lineLimit);
        Map<String, Integer> jsMap = getStudentContribution(projectId, iteration, FileType.JS, lineLimit);
        Map<String, Integer> jspMap = getStudentContribution(projectId, iteration, FileType.JSP, lineLimit);

        Map<String, Integer> resultMap = MapUtil.mapAdd(cssMap, htmlMap);
        resultMap = MapUtil.mapAdd(resultMap, jsMap);
        resultMap = MapUtil.mapAdd(resultMap, jspMap);

        return resultMap;
    }


    /**
     * 返回学生后端代码贡献量
     * @param projectId ： 项目id
     * @param iteration ： 迭代id
     * @param lineLimit ： 行数限制
     * @return ： Map
     */
    private Map<String, Integer> getStudentEndContribution(int projectId, int iteration, int lineLimit) {
        Map<String, Integer> javaMap = getStudentContribution(projectId, iteration, FileType.JAVA, lineLimit);
        Map<String, Integer> xmlMap = getStudentContribution(projectId, iteration, FileType.XML, lineLimit);
        Map<String, Integer> propertyMap =
                getStudentContribution(projectId, iteration, FileType.PROPERTIES, lineLimit);

        Map<String, Integer> map =  MapUtil.mapAdd(javaMap, xmlMap);
        return MapUtil.mapAdd(map, propertyMap);
    }


    /**
     *
     * @param projectId ： 项目id
     * @param iteration ： 迭代id
     * @param type      ： 文件类型
     * @param lineLimit ： 行数限制
     * @return ： Map
     */
    private Map<String, Integer> getStudentContribution(int projectId, int iteration, FileType type, int lineLimit)
    {
        String fileFeather = "%";
        String start = "";
        String end = "";

        switch (iteration) {
            case 1: start = Constant.ITERATION1_START;
                    end = Constant.ITERATION1_END;
                    break;
            case 2: start = Constant.ITERATION2_START;
                    end = Constant.ITERATION2_END;
                    break;
            case 3: start = Constant.ITERATION3_START;
                    end = Constant.ITERATION3_END;
                    break;
            default: break;
        }
        //get the fileFeather to filter file
        switch (type) {
            case JAVA: fileFeather = "%src%java"; break;
            case XML: fileFeather = "%src%xml"; break;
            case HTML: fileFeather = "%html"; break;
            case JSP: fileFeather = "%jsp"; break;
            case JS: fileFeather = "%js"; break;
            case CSS: fileFeather = "%css"; break;
            case PROPERTIES: fileFeather = "%properties"; break;
            case php: fileFeather = "%php"; break;
            case py: fileFeather = "py"; break;
            default: fileFeather = "%"; break;
        }
        return  fileDao.selectContributionByFile(projectId, fileFeather, start, end).stream()
                .collect(Collectors.toMap(Contribution::getStudent, Contribution::getWeight));
    }

    /**
     * 别人对自己代码的贡献
     * @param projectId ： 项目id
     * @param iteration ： 迭代id
     * @return ： Map
     */
    public Map<String, Integer> getContributionFromOthers(int projectId, int iteration)
    {
        String start = "";
        String end = "";

        switch (iteration) {
            case 1:  start = Constant.ITERATION1_START;
                     end = Constant.ITERATION1_END;
                     break;
            case 2:  start = Constant.ITERATION2_START;
                     end = Constant.ITERATION2_END;
                     break;
            case 3:  start = Constant.ITERATION3_START;
                     end = Constant.ITERATION3_END;
                     break;
            default: break;
        }

        return fileDao.selectContributionFromOthers(projectId , start, end).stream()
                .collect(Collectors.toMap(Contribution::getStudent, Contribution::getWeight));
    }

    /**
     * 自己对别人代码贡献度
     * @param projectId ： 项目id
     * @param iteration ： 迭代id
     * @return ： Map
     */
    public Map<String, Integer> getContributionToOthers(int projectId, int iteration) {
        String start = "";
        String end = "";

        switch (iteration) {
            case 1 : start = Constant.ITERATION1_START;
                     end = Constant.ITERATION1_END;
                     break;
            case 2 : start = Constant.ITERATION2_START;
                     end = Constant.ITERATION2_END;
                     break;
            case 3 : start = Constant.ITERATION3_START;
                     end = Constant.ITERATION3_END;
                     break;
            default: break;
        }

        return fileDao.selectContributionToOthers(projectId, start, end).stream()
                .collect(Collectors.toMap(Contribution::getStudent, Contribution::getWeight));
    }

    /**
     *
     * @param projectId : 项目编号
     * @param iteration : 迭代id
     * @return ：每隔组员提交的代码量
     */
    public Map<String, Integer> getCommitContribution(int projectId, int iteration) {
        String start = "";
        String end = "";

        switch (iteration) {
            case 1 : start = Constant.ITERATION1_START;
                     end = Constant.ITERATION1_END;
                     break;
            case 2 : start = Constant.ITERATION2_START;
                     end = Constant.ITERATION2_END;
                     break;
            case 3 : start = Constant.ITERATION3_START;
                     end = Constant.ITERATION3_END;
                     break;
            default: break;
        }

        return fileDao.selectCommitContribution(projectId, start, end).stream()
                .collect(Collectors.toMap(Contribution::getStudent, Contribution::getWeight));
    }

    public List<ProjectFile> getInvalidFile(int projectId, int iteration, int type) {
        List<ProjectFile> list = new ArrayList<>();

        switch (type) {
            case 1 : list = getInvalidCommitByCommentAndBlank(projectId, iteration);
                     break;
            case 2 : list = getInvalidFileByFewModifyTimes(projectId, iteration);
                     break;
            case 3 : list = getInvalidFileByLargeModifyTimes(projectId, iteration);
                     break;
            default: break;
        }

        return list;
    }

    /**
     *
     * @param projectId : 项目id
     * @param iteration : 迭代id
     * @return ： list
     */
    private List<ProjectFile> getInvalidCommitByCommentAndBlank(int projectId, int iteration) {
        String tableName = "";

        switch (iteration) {
            case 1 : tableName = Constant.ITERATION1_TABLE;
                     break;
            case 2 : tableName = Constant.ITERATION2_TABLE;
                     break;
            case 3 : tableName = Constant.ITERATION3_TABLE;
                     break;
            default: break;
        }

        return fileDao.selectInvalidCommitByCommentAndBlank(projectId, tableName);
    }

    /**
     *
     * @param projectId : 项目id
     * @param iteration : 迭代id
     * @return ： list
     */
    private List<ProjectFile> getInvalidFileByFewModifyTimes(int projectId, int iteration) {
        String start = "";
        String end = "";

        switch (iteration) {
            case 1 : start = Constant.ITERATION1_START;
                     end = Constant.ITERATION1_END;
                     break;
            case 2 : start = Constant.ITERATION2_START;
                     end = Constant.ITERATION2_END;
                     break;
            case 3 : start = Constant.ITERATION3_START;
                     end = Constant.ITERATION3_END;
                     break;
            default: break;
        }
        return fileDao.selectInvalidFileByFewModifyTimes(projectId, start, end);
    }

    private List<ProjectFile> getInvalidFileByLargeModifyTimes(int projectId, int iteration) {
        String start = "";
        String end = "";

        switch (iteration) {
            case 1 : start = Constant.ITERATION1_START;
                end = Constant.ITERATION1_END;
                break;
            case 2 : start = Constant.ITERATION2_START;
                end = Constant.ITERATION2_END;
                break;
            case 3 : start = Constant.ITERATION3_START;
                end = Constant.ITERATION3_END;
                break;
            default: break;
        }
        return fileDao.selectInvalidFileByLargeModifyTimes(projectId, start, end);
    }
}
