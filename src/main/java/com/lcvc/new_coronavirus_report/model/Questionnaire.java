package com.lcvc.new_coronavirus_report.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 负责接收调查问卷表单
 * 统计1月16日后的信息
 */
public class Questionnaire {
    private Integer id;//主键
    //基本信息
    private String identity;//填表人身份。"teacher"表示教师；"student"表示学生
    private String teacherNumber;//教师：教工号
    private String studentNumber;//学生：学号
    private String tel;//手机号，为保证准确性，依旧采用人工录入
    private String department;//教师：所属部门
    private String workType;//教师：岗位或所授学科.工作岗位
    private Boolean practice;//学生：是否正在实习。true表示有，false表示没有。
    private String practiceWorkStatus;//请选择您当前的实习情况：'在家休息','在宿舍休息','在家远程上班','在单位上班'。只有选择了正在实习才要求填
    private String identityCard;//身份证号。前端运行，建议先读取该教师/学生的信息，如果有身份证号则直接加载到身份证表单里。

    //表单取消下面字段填写，由数据库直接获取
    private String sex;//性别。两个选项：男，女。取消表单该字段
    private String name;//姓名。取消表单该字段

    //当天健康情况汇报，以及和另外7个表的重叠
    private String myHealth;//当天本人健康状况。单选列表：健康，发热，咳嗽，发热和咳嗽。该字段用于所有表相关的“是否有咳嗽、胸闷、发烧等不适症状”说明
    private String myfamilyHealth;//当天家庭成员健康状况。单选列表：健康，发热，咳嗽，发热和咳嗽。
    private Boolean touchHuBeiPerson;//当天是否密切接触来自或到达过武汉及湖北其他地区人员。true表示有，false表示没有。
    private String touchHuBeiPersonName;//接触过疫区人员的姓名，可以写多个。只有在touchHuBeiPerson为true的前提下才填写
    private Boolean confirmIll;//是否为疑似病例或确诊病例。两个选项：医院已确诊；否，身体健康。true表示有，false表示没有。

    //发热的话必须要填
    private String temperature;//体温，如果发热的话必须要填

    //如果密切接触过疫区人员要填的字段，如果发热的话必须要填
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date touchHuBeiTime;//密切接触的时间。接触过疫区人员，都要填；否则为空
    private String touchHuBeiDescription;//密切接触过程的具体描述。接触过疫区人员，都要填；否则为空
    private String schoolClass;//年级班级（专业）。接触过疫区人员，都要填；否则为空。取消表单该字段，从数据库获取

    //处理广东、浙江、河南、湖南省等相关信息
    private Boolean comeFromGZHH;//是否来自广东、浙江、河南、湖南省。
    private Boolean arriveGZHH;//是否到过广东、浙江、河南、湖南省。
    private String province;//如果去过/来自广东、浙江、河南、湖南省等，要填是哪个省份的


    //来自武汉湖北，或是去过武汉湖北，当前依旧停留在武汉，相关字段
    private Boolean comefromHuBei;//是否来自湖北(不包括武汉市)。true表示是，false表示不是
    private Boolean comefromWuHan;//是否来自武汉市。true表示是，false表示不是
    private Boolean arriveHuBei;//1月16日后，是否去过湖北(不包括武汉市)。true表示是，false表示不是
    private Boolean arriveWuHan;//1月16日后，是否到过武汉。true表示是，false表示不是
    private Boolean stayInHubei;//现在是否仍在湖北出差、休假、旅游、探亲等短时停留。必须是到达过武汉的（arriveWuHan）人才进一步填




    private String epidemicArea;//疫区居住地。只要去过湖北或武汉，或是来自湖北或武汉,接触过疫区人员，都要填；否则为空
    private String addressInLiuZhou;//柳州居住地。只要去过湖北或武汉，或是来自湖北或武汉，接触过疫区人员，都要填；否则为空
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arriveLiuZhou;//到达柳州的时间。包括武汉。只要去过湖北或武汉，或是来自湖北或武汉，都要填；否则为空
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveLiuZhou;//离开柳州的时间。包括武汉。只要去过湖北或武汉都要填；否则为空
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveHubei;//离开湖北的时间。包括武汉。只要去过湖北或武汉，或是来自湖北或武汉，都要填；否则为空。备注：目前也用于离开广东、浙江、河南、湖南省的时间，只要有这个条件的都写
    private String leaveHubeiWay;//离开湖北的方式。提示用户填（用文本框）：车次/航班/汽车/自驾。只要去过湖北或武汉，或是来自湖北或武汉，都要填；否则为空
    private String leaveTogetherPersonName;//离开时同行的人姓名，可以多写。只要去过湖北或武汉，或是来自湖北或武汉，都要填；否则为空
    private String manageMethods;//管控措施（如为居家隔离，请询问是否有社区每日随访、是否有外出买菜、下楼活动等情况）。只要去过湖北或武汉，或是来自湖北或武汉，都要填；否则为空
    private String registeredPlace;//户口所在地。只要去过湖北或武汉，或是来自湖北或武汉，都要填；否则为空

    private String intro;//其他说明。用于备注字段
    //非表单字段，系统录入
    private String ip;//ip地址，由系统自动填入
    private Date createTime;//填表时间，由数据库写入

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getTouchHuBeiPersonName() {
        return touchHuBeiPersonName;
    }

    public void setTouchHuBeiPersonName(String touchHuBeiPersonName) {
        this.touchHuBeiPersonName = touchHuBeiPersonName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisteredPlace() {
        return registeredPlace;
    }

    public void setRegisteredPlace(String registeredPlace) {
        this.registeredPlace = registeredPlace;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Boolean getPractice() {
        return practice;
    }

    public void setPractice(Boolean practice) {
        this.practice = practice;
    }

    public Boolean getComeFromGZHH() {
        return comeFromGZHH;
    }

    public void setComeFromGZHH(Boolean comeFromGZHH) {
        this.comeFromGZHH = comeFromGZHH;
    }

    public Boolean getArriveGZHH() {
        return arriveGZHH;
    }

    public void setArriveGZHH(Boolean arriveGZHH) {
        this.arriveGZHH = arriveGZHH;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean getComefromHuBei() {
        return comefromHuBei;
    }

    public void setComefromHuBei(Boolean comefromHuBei) {
        this.comefromHuBei = comefromHuBei;
    }

    public Boolean getComefromWuHan() {
        return comefromWuHan;
    }

    public void setComefromWuHan(Boolean comefromWuHan) {
        this.comefromWuHan = comefromWuHan;
    }

    public Boolean getArriveHuBei() {
        return arriveHuBei;
    }

    public void setArriveHuBei(Boolean arriveHuBei) {
        this.arriveHuBei = arriveHuBei;
    }

    public Boolean getArriveWuHan() {
        return arriveWuHan;
    }

    public void setArriveWuHan(Boolean arriveWuHan) {
        this.arriveWuHan = arriveWuHan;
    }

    public String getEpidemicArea() {
        return epidemicArea;
    }

    public void setEpidemicArea(String epidemicArea) {
        this.epidemicArea = epidemicArea;
    }

    public String getAddressInLiuZhou() {
        return addressInLiuZhou;
    }

    public void setAddressInLiuZhou(String addressInLiuZhou) {
        this.addressInLiuZhou = addressInLiuZhou;
    }

    public Date getArriveLiuZhou() {
        return arriveLiuZhou;
    }

    public void setArriveLiuZhou(Date arriveLiuZhou) {
        this.arriveLiuZhou = arriveLiuZhou;
    }

    public Date getLeaveLiuZhou() {
        return leaveLiuZhou;
    }

    public void setLeaveLiuZhou(Date leaveLiuZhou) {
        this.leaveLiuZhou = leaveLiuZhou;
    }

    public Date getLeaveHubei() {
        return leaveHubei;
    }

    public void setLeaveHubei(Date leaveHubei) {
        this.leaveHubei = leaveHubei;
    }

    public String getLeaveHubeiWay() {
        return leaveHubeiWay;
    }

    public void setLeaveHubeiWay(String leaveHubeiWay) {
        this.leaveHubeiWay = leaveHubeiWay;
    }

    public String getLeaveTogetherPersonName() {
        return leaveTogetherPersonName;
    }

    public void setLeaveTogetherPersonName(String leaveTogetherPersonName) {
        this.leaveTogetherPersonName = leaveTogetherPersonName;
    }

    public String getManageMethods() {
        return manageMethods;
    }

    public void setManageMethods(String manageMethods) {
        this.manageMethods = manageMethods;
    }

    public Boolean getTouchHuBeiPerson() {
        return touchHuBeiPerson;
    }

    public void setTouchHuBeiPerson(Boolean touchHuBeiPerson) {
        this.touchHuBeiPerson = touchHuBeiPerson;
    }

    public Boolean getConfirmIll() {
        return confirmIll;
    }

    public void setConfirmIll(Boolean confirmIll) {
        this.confirmIll = confirmIll;
    }

    public String getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(String myHealth) {
        this.myHealth = myHealth;
    }

    public String getMyfamilyHealth() {
        return myfamilyHealth;
    }

    public void setMyfamilyHealth(String myfamilyHealth) {
        this.myfamilyHealth = myfamilyHealth;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTouchHuBeiTime() {
        return touchHuBeiTime;
    }

    public void setTouchHuBeiTime(Date touchHuBeiTime) {
        this.touchHuBeiTime = touchHuBeiTime;
    }

    public String getTouchHuBeiDescription() {
        return touchHuBeiDescription;
    }

    public void setTouchHuBeiDescription(String touchHuBeiDescription) {
        this.touchHuBeiDescription = touchHuBeiDescription;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Boolean getStayInHubei() {
        return stayInHubei;
    }

    public void setStayInHubei(Boolean stayInHubei) {
        this.stayInHubei = stayInHubei;
    }

    public String getPracticeWorkStatus() {
        return practiceWorkStatus;
    }

    public void setPracticeWorkStatus(String practiceWorkStatus) {
        this.practiceWorkStatus = practiceWorkStatus;
    }


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
