package validCoding.service;

import org.bean.DayCommit;
import org.bean.ProjectCommit;
import org.dao.ProjectCommitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validCoding.bean.AggregatedProjectCode;
import validCoding.bean.AggregatedStudentCode;
import validCoding.dao.CodeAggregationDao;

import java.util.List;

/**
 * @author  王珺宇 131250044
 */
@Service
public class CodeAggregationService {

    @Autowired
    private CodeAggregationDao codeAggregationDao;


    /**
     * @param id : 项目id
     * @param iteration_id ： 迭代数字
     * @return ： 该项目在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<AggregatedProjectCode> getAggregatedProjectCodeByNameAndIteration(int id, int iteration_id) {
        return codeAggregationDao.getAggregatedProjectCodeByNameAndIteration(id, iteration_id);
    }




    /**
     * @param author : 作者名字
     * @param iteration ： 迭代
     * @return ： 该作者在该迭代期间，不同类型文件的行数、有效行数
     */
    public List<AggregatedStudentCode> getAggregatedStudentCodeByAuthorAndIteration(String author, String iteration) {
        return codeAggregationDao.selectAggregatedStudentCodeByAuthorAndIteration(author, iteration);
    }


}
