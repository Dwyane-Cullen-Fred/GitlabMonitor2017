package org.localService;

import org.bean.AggregatedProjectCode;
import org.bean.ContributionAggregation;
import org.localDao.CodeAggregationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CodeAggregationService {

    @Autowired
    private CodeAggregationDao codeAggregationDao;

    public List<AggregatedProjectCode> getProjectCode(int projectId, int iteration) {
        return codeAggregationDao.selectAggregatedCodeFileByIDAndIteration(projectId, iteration);
    }
}
