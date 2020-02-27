package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.dao.StrandQuestionnaireDao;
import com.lcvc.new_coronavirus_report.dao.TeacherDao;
import com.lcvc.new_coronavirus_report.model.Strand;
import com.lcvc.new_coronavirus_report.model.StrandCount;
import com.lcvc.new_coronavirus_report.model.Teacher;
import com.lcvc.new_coronavirus_report.model.base.PageObject;
import com.lcvc.new_coronavirus_report.model.exception.MyWebException;
import com.lcvc.new_coronavirus_report.model.query.StrandQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
@Validated//表示开启sprint的校检框架，会自动扫描方法里的@Valid（@Valid注解一般写在接口即可）
@Service
public class StrandService {
    @Autowired
    private StrandQuestionnaireDao strandQuestionnaireDao;
    @Autowired
    private TeacherDao teacherDao;
    /**
     * 保存填表人的表单
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Strand strand) {
        if (StringUtils.isEmpty(strand.getName())) {
            throw new MyWebException("提交失败：必须填写姓名");
        }
        if (StringUtils.isEmpty(strand.getCurrentCity())) {
            throw new MyWebException("提交失败：必须填写目前所在城市");
        }
        if (StringUtils.isEmpty(strand.getMyHealth())||strand.getMyHealth() == null) {
            throw new MyWebException("提交失败：必须填写健康状况");
        }
        if (StringUtils.isEmpty(strand.getWorkType())) {
            throw new MyWebException("提交失败：必须填写课程或岗位");
        }
        if (StringUtils.isEmpty(strand.getReason())) {
            throw new MyWebException("提交失败：必须填写无法返回原因");
        }
        if (strand.getReturnLiuZhou() == null) {
            throw new MyWebException("提交失败：必须填写本周内是否返柳");
        }
        if (strand.getReturnLiuZhouTime() == null) {
            throw new MyWebException("提交失败：必须填写计划返柳时间");
        }
        if (StringUtils.isEmpty(strand.getReturnLiuZhouWay())) {
            throw new MyWebException("提交失败：必须填写计划返柳交通工具");
        }
        strandQuestionnaireDao.save(strand);
    }

    /**
     * 分页查询所有调查表
     *
     * @param page        当前页面
     * @param limit       每页最多显示的记录数
     * @param strandQuery 查询条件类
     */
    public PageObject query(Integer page, Integer limit, StrandQuery strandQuery) {
        PageObject pageObject = new PageObject(limit, page, strandQuestionnaireDao.querySize(strandQuery));
        List<Strand> list = strandQuestionnaireDao.query(pageObject.getOffset(), pageObject.getLimit(), strandQuery);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setUnit("柳州城市职业学院");
        }
        pageObject.setList(list);
        return pageObject;
    }

    /**
     * 查询所有调查表
     *
     * @param strandQuery 查询条件类
     */
    public List<Strand> query(StrandQuery strandQuery) {
        List<Strand> list = strandQuestionnaireDao.readAll(strandQuery);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setUnit("柳州城市职业学院");
        }
        return list;
    }

    /**
     *
     */
    public StrandCount queryCount() {
        StrandCount strandCount = new StrandCount();
        StrandQuery strandQuery = null;
        //序号
        strandCount.setId(1);
        //单位
        strandCount.setUnit("柳州城市职业学院");
        //学段
        strandCount.setSchoolSection("高职");
        //教职工人数
        strandQuery = new StrandQuery();
        strandQuery.setCount(true);
        strandCount.setTeacherNum(strandQuestionnaireDao.querySize(strandQuery));

        //本周内预计返柳人数
        strandQuery = new StrandQuery();
        strandQuery.setReturnLiuZhouQuery(true);//查询本周返回柳州的记录
        strandQuery.setReturnLiuZhou(true);  //查询返回柳州
        strandQuery.setCount(true);
        strandCount.setReturnLiuZhouNum(strandQuestionnaireDao.querySize(strandQuery));

        //滞留疫区无法返回教职工人数
        strandQuery = new StrandQuery();
        strandQuery.setReturnLiuZhou(false);  //查询返回柳州
        strandQuery.setCount(true);
        strandCount.setStrandNum(strandQuestionnaireDao.querySize(strandQuery));

        return strandCount;
    }

    /**
     * 滞留模块：判断是否有人提交过
     * @param teacherName 传入教师名字
     * @return null表示没有本周的填报记录
     */
    public Boolean getStrandQuestionnaire(String teacherName){

        StrandQuery strandQuery=new StrandQuery();
        strandQuery.setWeekQuery(true);//查找本周的记录
        strandQuery.setName(teacherName);
            if (strandQuestionnaireDao.readAll(strandQuery).size()>0){
                return false;
            }
        return true;
    }
}
