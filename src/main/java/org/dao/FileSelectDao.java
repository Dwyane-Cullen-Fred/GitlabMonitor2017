package org.dao;


import org.Util.File;
import org.bean.ProjectFileCount;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class FileSelectDao extends BaseDaoImpl{


    /**
     *
     * @param project_id : 项目的id
     * @param file ： 文件的类型
     * @param line_limit ： 提交行数限制
     * @return ： 对应的符合要求的每隔人的提交数量
     */
    public List<ProjectFileCount> selectFile(int project_id, File file, int line_limit) {
        Map<String, String> map = new HashMap<>();
        map.put("project_id", String.valueOf(project_id));
        map.put("line_limit", String.valueOf(line_limit));
        String file_feather = "";
        switch (file){
            case JAVA : file_feather = "%src%java"; break;
            case CSS:   file_feather = "%css"; break;
            case JAVA_GUI: file_feather = "%src%ui%java"; break;
            case JS: file_feather = "%js"; break;
            case JSP: file_feather = "%jsp"; break;
            case XML: file_feather = "%src%xml"; break;
            case HTML: file_feather = "%html"; break;
            case PROPERTY: file_feather = "%properties"; break;
            default: file_feather = "%";
        }
        map.put("file_feather", file_feather);
        return sqlSession.selectList("file.selectFile", map);
    }
}
