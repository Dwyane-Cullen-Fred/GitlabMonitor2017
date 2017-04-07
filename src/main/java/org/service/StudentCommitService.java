package org.service;

import org.bean.Connection;
import org.bean.IterationStudentCommit;
import org.bean.StudentCommit;
import org.bean.ValidCommit;
import org.dao.StudentCommitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public List<IterationStudentCommit> getStudentIterationCommit(int id, int iteration_id) {
        return studentCommitDao.selectStudentIterationCommit(id, iteration_id);
    }

    /**
     *
     * @param id : 项目id
     * @return ：项目中学生之间的联系
     */
    public Set<Connection> getStudentConnection(int id) {
        return new HashSet<Connection>(studentCommitDao.selectStudentConnection(id));
    }

    /**
     * @param id : 项目id
     * @return ： 项目中学生的有效提交
     */
    public List<ValidCommit> getStudentValidCommit(int id) {
        return studentCommitDao.selectStudentValidCommit(id);
    }
}
