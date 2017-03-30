package org.service;

import org.bean.StudentCommit;
import org.dao.StudentCommitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<StudentCommit> getStudentCommitByDeadline(int id, int deadline_id) {
        return studentCommitDao.selectStudentCommitByDeadline(id, deadline_id);
    }
}
