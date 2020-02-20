package com.lcvc.new_coronavirus_report.service;

import com.lcvc.new_coronavirus_report.dao.StrandQuestionnaireDao;
import com.lcvc.new_coronavirus_report.model.Strand;
import com.lcvc.new_coronavirus_report.model.StrandCount;
import com.lcvc.new_coronavirus_report.model.base.PageObject;
import com.lcvc.new_coronavirus_report.model.exception.MyWebException;
import com.lcvc.new_coronavirus_report.model.query.StrandQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
@Validated//表示开启sprint的校检框架，会自动扫描方法里的@Valid（@Valid注解一般写在接口即可）
@Service
public class StrandService {
    @Autowired
    private StrandQuestionnaireDao strandQuestionnaireDao;

    /**
     * 保存填表人的表单
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(@NotNull Strand strand) {
        if (strand.getName() == null) {
            throw new MyWebException("提交失败：必须填写姓名");
        }
        if (strand.getCurrentCity() == null) {
            throw new MyWebException("提交失败：必须填写目前所在城市");
        }
        if (strand.getMyHealth() == null) {
            throw new MyWebException("提交失败：必须填写健康状况");
        }
        if (strand.getWorkType() == null) {
            throw new MyWebException("提交失败：必须填写课程或岗位");
        }
        if (strand.getReason() == null) {
            throw new MyWebException("提交失败：必须填写无法返回原因");
        }
        if (strand.getReturnLiuZhou() == null) {
            throw new MyWebException("提交失败：必须填写本周内是否返柳");
        }
        if (strand.getReturnLiuZhou()) {
            if (strand.getReturnLiuZhouTime() == null) {
                throw new MyWebException("提交失败：必须填写计划返柳时间");
            }
            if (strand.getReturnLiuZhouWay() == null) {
                throw new MyWebException("提交失败：必须填写计划返柳交通工具");
            }
        }
        strandQuestionnaireDao.save(strand);
    }

    /**
     * 分页查询所有调查表
     * @param page 当前页面
     * @param limit  每页最多显示的记录数
     * @param strandQuery 查询条件类
     */
    public PageObject query(Integer page, Integer limit, StrandQuery strandQuery){
        PageObject pageObject = new PageObject(limit,page, strandQuestionnaireDao.querySize(strandQuery));
        List<Strand> list = strandQuestionnaireDao.query(pageObject.getOffset(),pageObject.getLimit(),strandQuery);
        for (int i = 0; i <list.size() ; i++) {
                list.get(i).setUnit("柳州城市职业学院");
        }
        pageObject.setList(list);
        return pageObject;
    }

    /**
     * 查询所有调查表
     * @param strandQuery 查询条件类
     */
    public List<Strand> query(StrandQuery strandQuery){
        List<Strand> list= strandQuestionnaireDao.readAll(strandQuery);
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setUnit("柳州城市职业学院");
        }
        return list;
    }

    /**
     */
    public StrandCount queryCount(){
        StrandCount strandCount =new StrandCount();
        StrandQuery strandQuery=null;
        //序号
        strandCount.setId(1);
        //单位
        strandCount.setUnit("柳州城市职业学院");
        //学段
        strandCount.setSchoolSection("高职");
        //教职工人数
        strandQuery=new StrandQuery();
        strandQuery.setWeekQuery(true);
        strandCount.setTeacherNum(strandQuestionnaireDao.querySize(strandQuery));

        //本周内预计返柳人数
        strandQuery =new StrandQuery();
        strandQuery.setReturnLiuZhouQuery(true);//查询本周返回柳州的记录
        strandQuery.setReturnLiuZhou(true);  //查询返回柳州
        strandCount.setReturnLiuZhouNum(strandQuestionnaireDao.querySize(strandQuery));

        //滞留疫区无法返回教职工人数
        strandCount.setStrandNum(strandCount.getTeacherNum()- strandCount.getReturnLiuZhouNum());

        return strandCount;
      }
}
