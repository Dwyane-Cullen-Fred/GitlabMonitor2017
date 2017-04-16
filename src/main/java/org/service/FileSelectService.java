package org.service;

import org.Util.File;
import org.bean.ProjectFileCount;
import org.dao.FileSelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Created by XXH on 2017/4/11.
 */

@Service
public class FileSelectService {

    @Autowired
    private FileSelectDao fileSelectDao;
    /**
     *
     * @param project_id : 项目id
     * @param limit_line ： 提交长度限制
     * @return ：项目前端代码量
     */
    public List<ProjectFileCount> getProjectFrontFile(int project_id, int limit_line){
        List<ProjectFileCount> java_gui = fileSelectDao.selectFile(project_id, File.JAVA_GUI, limit_line);
        List<ProjectFileCount> css = fileSelectDao.selectFile(project_id, File.CSS, limit_line);
        List<ProjectFileCount> jsp =  fileSelectDao.selectFile(project_id, File.JSP, limit_line);
        List<ProjectFileCount> js = fileSelectDao.selectFile(project_id, File.JS, limit_line);

        List<ProjectFileCount> list1 = addProjectFileList(java_gui, jsp);
        List<ProjectFileCount> list2 = addProjectFileList(js, css);

        return addProjectFileList(list1, list2);
    }

    /**
     *
     * @param project_id : 项目id
     * @param limit_line : 提交长度限制
     * @return ：后端代码 : 包括 java逻辑代码 xml配置代码
     */
    public List<ProjectFileCount> getProjectEndFile(int project_id, int limit_line) {
        List<ProjectFileCount> java_gui = fileSelectDao.selectFile(project_id, File.JAVA_GUI, limit_line);
        List<ProjectFileCount> java = fileSelectDao.selectFile(project_id, File.JAVA, limit_line);
        List<ProjectFileCount> xml = fileSelectDao.selectFile(project_id, File.XML, limit_line);
        return addProjectFileList(xml,minusProjectFileList(java, java_gui));
    }

    /**
     *
     * @param list1
     * @param list2
     * @return ： 对应的2个list相应位置的元素做和
     */
    private List<ProjectFileCount> addProjectFileList(List<ProjectFileCount> list1, List<ProjectFileCount> list2){
        List<ProjectFileCount> list = new LinkedList<>();
        for (int i = 0; i < list1.size(); i++) {
            ProjectFileCount fileCount1 = list1.get(i);
            ProjectFileCount fileCount2 = list1.get(i);
            list.add(new ProjectFileCount(
                    fileCount1.getStudent(), fileCount1.getLine_count() + fileCount2.getLine_count()));
        }

        return list;
    }


    /**
     *
     * @param list1
     * @param list2
     * @return ： 2个list相应位置的元素做差
     */
    private List<ProjectFileCount> minusProjectFileList(List<ProjectFileCount> list1, List<ProjectFileCount> list2){
        List<ProjectFileCount> list = new LinkedList<>();
        for (int i = 0; i < list1.size(); i++) {
            ProjectFileCount fileCount1 = list1.get(i);
            ProjectFileCount fileCount2 = list1.get(i);
            list.add(new ProjectFileCount(
                    fileCount1.getStudent(), fileCount1.getLine_count() - fileCount2.getLine_count()));
        }

        return list;
    }
}
