package org.service;

import org.bean.IterationStudentCommit;
import org.bean.StudentCommit;
import org.dao.StudentCommitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by XXH on 2017/3/29.
 */

@Service
public class StudentCommitService {

    @Autowired
    private StudentCommitDao studentCommitDao;

    /**
     * @param id : 项目名称
     * @return ： 对应项目中学生每天的提交情况
     */
    public List<StudentCommit> getStudentCommitById(int id) {
        return studentCommitDao.selectStudentCommitById(id);
    }

    /**
     * @param id : 项目名称
     * @param deadline_id ： deadline的id
     * @return ： 对应项目中学生的贡献度
     */
    public List<IterationStudentCommit> getStudentCommit(int id) {
        return studentCommitDao.selectStudentCommit(id);
    }

    /**
     * @param id : 项目id
     * @param iteration_id : 迭代的id
     * @return ： 每个迭代中每个学生的贡献
     */
    public List<IterationStudentCommit> selectStudentIterationCommit(int id, int iteration_id) {
        return studentCommitDao.selectStudentIterationCommit(id, iteration_id);
    }
}
