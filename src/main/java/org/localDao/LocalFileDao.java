package org.localDao;

import org.bean.Contribution;
import org.bean.ProjectFile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class LocalFileDao extends LocalBaseDaoImpl{
    /**
     *
     * @param projectId : 项目的id
     * @param fileFeather ： 文件的类型
     * @return ： 对应的符合要求的文件数量
     */
    public List<Contribution> selectContributionByFile(int projectId, String fileFeather, String start, String end)
    {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("fileFeather", fileFeather);
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("fileCommit.selectContributionByFile", map);
    }

    /**
     * 获取别人对自己代码贡献量
     * @param projectId : 项目id
     * @param start : 起始时间
     * @param end : 结束时间
     * @return
     */
    public List<Contribution> selectContributionFromOthers(int projectId, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("fileCommit.selectContributionFromOthers", map);
    }

    /**
     * 获取自己对别人代码贡献度
     * @param projectId : 项目id
     * @param start : 起始时间
     * @param end : 结束时间
     * @return
     */
    public List<Contribution> selectContributionToOthers(int projectId, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("fileCommit.selectContributionToOthers", map);
    }

    /**
     *
     * @param projectId : 项目id
     * @param start ： 起始时间
     * @param end ： 结束时间
     * @return : 学生提交的代码量
     */
    public List<Contribution> selectCommitContribution(int projectId, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("fileCommit.selectContributionByCommit", map);
    }


    /**
     * 返回空行注释行数大于代码行数的文件
     * @param projectId : 项目id
     * @param tableName : 表名
     * @return : List
     */
    public List<ProjectFile> selectInvalidCommitByCommentAndBlank(int projectId, String tableName) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("tableName", tableName);
        return sqlSession.selectList("fileCommit.selectInvalidCommitByCommentAndBlank", map);
    }

    /**
     * 返回一经创建没有后续修改的文件
     * @param projectId : 项目id
     * @param start : 起始时间
     * @param end : 结束时间
     * @return : list
     */
    public List<ProjectFile> selectInvalidFileByFewModifyTimes(int projectId, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("fileCommit.selectInvalidFileByFewModifyTimes", map);
    }

    /**
     * 返回修改次数过多的文件
     * @param projectId : 项目id
     * @param start ： 起始时间
     * @param end : 结束时间
     * @return
     */
    public List<ProjectFile> selectInvalidFileByLargeModifyTimes(int projectId, String start, String end) {
        Map<String, String> map = new HashMap<>();
        map.put("projectId", String.valueOf(projectId));
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("fileCommit.selectInvalidFileByLargeModifyTimes", map);
    }
}
