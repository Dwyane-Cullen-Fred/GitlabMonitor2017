package validCoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validCoding.bean.ProjectCode;
import validCoding.bean.ProjectContribution;
import validCoding.bean.StudentCode;
import validCoding.dao.ValidCodeAggregationDao;

import java.util.List;

/**
 * @author  王珺宇 131250044
 */
@Service
public class ValidCodeAggregationService {

    @Autowired
    private ValidCodeAggregationDao validCodeAggregationDao;


    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同作用文件的行数
     */
    public List<ProjectCode> getProjectValid(int project_id, int iteration_id) {
        return validCodeAggregationDao.getProjectValidByIDAndIteration(project_id, iteration_id);
    }


    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型代码的行数
     */
    public List<ProjectCode> getProjectComposition(int project_id, int iteration_id) {
        return validCodeAggregationDao.getProjectCompositionByIDAndIteration(project_id, iteration_id);
    }


    /**
     * @param project_id : 项目id
     * @param iteration_id ： 迭代
     * @return ： 某迭代，不同学生对该项目的贡献
     */
    public List<ProjectContribution> getProjectContribution(int project_id, int iteration_id){
        return validCodeAggregationDao.getProjectContributionByIDAndIteration(project_id, iteration_id);
    }





    /**
     * @param author : 作者
     * @param iteration_id ： 迭代
     * @return ： 该作者在该迭代期间，不同作用文件的行数、有效行数
     */
    public List<StudentCode> getStudentValid(String author, int iteration_id) {
        return validCodeAggregationDao.getStudentValidByAuthorAndIteration(author, iteration_id);
    }

    /**
     * @param author : 作者
     * @param iteration_id ： 迭代
     * @return ： 该作者在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<StudentCode> getStudentComposition(String author, int iteration_id) {
        return validCodeAggregationDao.getStudentCompositionByAuthorAndIteration(author, iteration_id);
    }




}
