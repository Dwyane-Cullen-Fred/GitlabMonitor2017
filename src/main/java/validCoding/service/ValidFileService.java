package validCoding.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validCoding.bean.ValidFile;
import validCoding.dao.ValidFileDao;

import java.util.List;

/**
 * @author   王珺宇 131250044
 */
@Service
public class ValidFileService {

    @Autowired
    ValidFileDao validFileDao;



    /**
     * @param project_id 项目id
     * @param iteration_id 迭代id
     * @param type 选中的代码类型
     * @return 某项目某迭代，某种有码版本（排序标准：该项目单个文件的总行数；取值量：取前20）
     */
    public List<ValidFile> getProjectValidFile(int project_id, int iteration_id, String type) {
        return validFileDao.getProjectValidFile(project_id, iteration_id, type);
    }



    /**
     * @param author 学生姓名
     * @param iteration_id 迭代id
     * @param type 选中的代码类型
     * @return 某学生某迭代，写的某种有码版本（排序标准：该学生迭代器件对同一个文件总计贡献了多少代码；取值量：取前15）
     */
    public List<ValidFile> getProjectValidFile(String author, int iteration_id, String type) {
        return validFileDao.getStudentValidFile(author, iteration_id, type);
    }
}
