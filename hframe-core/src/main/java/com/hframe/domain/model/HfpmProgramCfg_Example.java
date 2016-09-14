package com.hframe.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HfpmProgramCfg_Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HfpmProgramCfg_Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andHfpmProgramCfgIdIsNull() {
            addCriterion("hfpm_program_cfg_id is null");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdIsNotNull() {
            addCriterion("hfpm_program_cfg_id is not null");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdEqualTo(Long value) {
            addCriterion("hfpm_program_cfg_id =", value, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdNotEqualTo(Long value) {
            addCriterion("hfpm_program_cfg_id <>", value, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdGreaterThan(Long value) {
            addCriterion("hfpm_program_cfg_id >", value, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hfpm_program_cfg_id >=", value, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdLessThan(Long value) {
            addCriterion("hfpm_program_cfg_id <", value, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdLessThanOrEqualTo(Long value) {
            addCriterion("hfpm_program_cfg_id <=", value, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdIn(List<Long> values) {
            addCriterion("hfpm_program_cfg_id in", values, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdNotIn(List<Long> values) {
            addCriterion("hfpm_program_cfg_id not in", values, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdBetween(Long value1, Long value2) {
            addCriterion("hfpm_program_cfg_id between", value1, value2, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramCfgIdNotBetween(Long value1, Long value2) {
            addCriterion("hfpm_program_cfg_id not between", value1, value2, "hfpmProgramCfgId");
            return (Criteria) this;
        }

        public Criteria andShowNameIsNull() {
            addCriterion("show_name is null");
            return (Criteria) this;
        }

        public Criteria andShowNameIsNotNull() {
            addCriterion("show_name is not null");
            return (Criteria) this;
        }

        public Criteria andShowNameEqualTo(String value) {
            addCriterion("show_name =", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotEqualTo(String value) {
            addCriterion("show_name <>", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameGreaterThan(String value) {
            addCriterion("show_name >", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameGreaterThanOrEqualTo(String value) {
            addCriterion("show_name >=", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameLessThan(String value) {
            addCriterion("show_name <", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameLessThanOrEqualTo(String value) {
            addCriterion("show_name <=", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameLike(String value) {
            addCriterion("show_name like", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotLike(String value) {
            addCriterion("show_name not like", value, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameIn(List<String> values) {
            addCriterion("show_name in", values, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotIn(List<String> values) {
            addCriterion("show_name not in", values, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameBetween(String value1, String value2) {
            addCriterion("show_name between", value1, value2, "showName");
            return (Criteria) this;
        }

        public Criteria andShowNameNotBetween(String value1, String value2) {
            addCriterion("show_name not between", value1, value2, "showName");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdIsNull() {
            addCriterion("hfcfg_program_template_id is null");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdIsNotNull() {
            addCriterion("hfcfg_program_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdEqualTo(Long value) {
            addCriterion("hfcfg_program_template_id =", value, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdNotEqualTo(Long value) {
            addCriterion("hfcfg_program_template_id <>", value, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdGreaterThan(Long value) {
            addCriterion("hfcfg_program_template_id >", value, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hfcfg_program_template_id >=", value, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdLessThan(Long value) {
            addCriterion("hfcfg_program_template_id <", value, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("hfcfg_program_template_id <=", value, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdIn(List<Long> values) {
            addCriterion("hfcfg_program_template_id in", values, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdNotIn(List<Long> values) {
            addCriterion("hfcfg_program_template_id not in", values, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdBetween(Long value1, Long value2) {
            addCriterion("hfcfg_program_template_id between", value1, value2, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("hfcfg_program_template_id not between", value1, value2, "hfcfgProgramTemplateId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdIsNull() {
            addCriterion("hfcfg_program_skin_id is null");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdIsNotNull() {
            addCriterion("hfcfg_program_skin_id is not null");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdEqualTo(Long value) {
            addCriterion("hfcfg_program_skin_id =", value, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdNotEqualTo(Long value) {
            addCriterion("hfcfg_program_skin_id <>", value, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdGreaterThan(Long value) {
            addCriterion("hfcfg_program_skin_id >", value, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hfcfg_program_skin_id >=", value, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdLessThan(Long value) {
            addCriterion("hfcfg_program_skin_id <", value, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdLessThanOrEqualTo(Long value) {
            addCriterion("hfcfg_program_skin_id <=", value, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdIn(List<Long> values) {
            addCriterion("hfcfg_program_skin_id in", values, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdNotIn(List<Long> values) {
            addCriterion("hfcfg_program_skin_id not in", values, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdBetween(Long value1, Long value2) {
            addCriterion("hfcfg_program_skin_id between", value1, value2, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgProgramSkinIdNotBetween(Long value1, Long value2) {
            addCriterion("hfcfg_program_skin_id not between", value1, value2, "hfcfgProgramSkinId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdIsNull() {
            addCriterion("hfcfg_login_page_id is null");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdIsNotNull() {
            addCriterion("hfcfg_login_page_id is not null");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdEqualTo(Long value) {
            addCriterion("hfcfg_login_page_id =", value, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdNotEqualTo(Long value) {
            addCriterion("hfcfg_login_page_id <>", value, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdGreaterThan(Long value) {
            addCriterion("hfcfg_login_page_id >", value, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hfcfg_login_page_id >=", value, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdLessThan(Long value) {
            addCriterion("hfcfg_login_page_id <", value, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdLessThanOrEqualTo(Long value) {
            addCriterion("hfcfg_login_page_id <=", value, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdIn(List<Long> values) {
            addCriterion("hfcfg_login_page_id in", values, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdNotIn(List<Long> values) {
            addCriterion("hfcfg_login_page_id not in", values, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdBetween(Long value1, Long value2) {
            addCriterion("hfcfg_login_page_id between", value1, value2, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andHfcfgLoginPageIdNotBetween(Long value1, Long value2) {
            addCriterion("hfcfg_login_page_id not between", value1, value2, "hfcfgLoginPageId");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlIsNull() {
            addCriterion("bg_img_url is null");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlIsNotNull() {
            addCriterion("bg_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlEqualTo(String value) {
            addCriterion("bg_img_url =", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlNotEqualTo(String value) {
            addCriterion("bg_img_url <>", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlGreaterThan(String value) {
            addCriterion("bg_img_url >", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("bg_img_url >=", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlLessThan(String value) {
            addCriterion("bg_img_url <", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlLessThanOrEqualTo(String value) {
            addCriterion("bg_img_url <=", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlLike(String value) {
            addCriterion("bg_img_url like", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlNotLike(String value) {
            addCriterion("bg_img_url not like", value, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlIn(List<String> values) {
            addCriterion("bg_img_url in", values, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlNotIn(List<String> values) {
            addCriterion("bg_img_url not in", values, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlBetween(String value1, String value2) {
            addCriterion("bg_img_url between", value1, value2, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andBgImgUrlNotBetween(String value1, String value2) {
            addCriterion("bg_img_url not between", value1, value2, "bgImgUrl");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNull() {
            addCriterion("op_id is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("op_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(Long value) {
            addCriterion("op_id =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(Long value) {
            addCriterion("op_id <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(Long value) {
            addCriterion("op_id >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("op_id >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(Long value) {
            addCriterion("op_id <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(Long value) {
            addCriterion("op_id <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<Long> values) {
            addCriterion("op_id in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<Long> values) {
            addCriterion("op_id not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(Long value1, Long value2) {
            addCriterion("op_id between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(Long value1, Long value2) {
            addCriterion("op_id not between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdIsNull() {
            addCriterion("modify_op_id is null");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdIsNotNull() {
            addCriterion("modify_op_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdEqualTo(Long value) {
            addCriterion("modify_op_id =", value, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdNotEqualTo(Long value) {
            addCriterion("modify_op_id <>", value, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdGreaterThan(Long value) {
            addCriterion("modify_op_id >", value, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("modify_op_id >=", value, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdLessThan(Long value) {
            addCriterion("modify_op_id <", value, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdLessThanOrEqualTo(Long value) {
            addCriterion("modify_op_id <=", value, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdIn(List<Long> values) {
            addCriterion("modify_op_id in", values, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdNotIn(List<Long> values) {
            addCriterion("modify_op_id not in", values, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdBetween(Long value1, Long value2) {
            addCriterion("modify_op_id between", value1, value2, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyOpIdNotBetween(Long value1, Long value2) {
            addCriterion("modify_op_id not between", value1, value2, "modifyOpId");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdIsNull() {
            addCriterion("hfcfg_db_connect_id is null");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdIsNotNull() {
            addCriterion("hfcfg_db_connect_id is not null");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdEqualTo(Long value) {
            addCriterion("hfcfg_db_connect_id =", value, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdNotEqualTo(Long value) {
            addCriterion("hfcfg_db_connect_id <>", value, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdGreaterThan(Long value) {
            addCriterion("hfcfg_db_connect_id >", value, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hfcfg_db_connect_id >=", value, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdLessThan(Long value) {
            addCriterion("hfcfg_db_connect_id <", value, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdLessThanOrEqualTo(Long value) {
            addCriterion("hfcfg_db_connect_id <=", value, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdIn(List<Long> values) {
            addCriterion("hfcfg_db_connect_id in", values, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdNotIn(List<Long> values) {
            addCriterion("hfcfg_db_connect_id not in", values, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdBetween(Long value1, Long value2) {
            addCriterion("hfcfg_db_connect_id between", value1, value2, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfcfgDbConnectIdNotBetween(Long value1, Long value2) {
            addCriterion("hfcfg_db_connect_id not between", value1, value2, "hfcfgDbConnectId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdIsNull() {
            addCriterion("hfpm_program_id is null");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdIsNotNull() {
            addCriterion("hfpm_program_id is not null");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdEqualTo(Long value) {
            addCriterion("hfpm_program_id =", value, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdNotEqualTo(Long value) {
            addCriterion("hfpm_program_id <>", value, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdGreaterThan(Long value) {
            addCriterion("hfpm_program_id >", value, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hfpm_program_id >=", value, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdLessThan(Long value) {
            addCriterion("hfpm_program_id <", value, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdLessThanOrEqualTo(Long value) {
            addCriterion("hfpm_program_id <=", value, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdIn(List<Long> values) {
            addCriterion("hfpm_program_id in", values, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdNotIn(List<Long> values) {
            addCriterion("hfpm_program_id not in", values, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdBetween(Long value1, Long value2) {
            addCriterion("hfpm_program_id between", value1, value2, "hfpmProgramId");
            return (Criteria) this;
        }

        public Criteria andHfpmProgramIdNotBetween(Long value1, Long value2) {
            addCriterion("hfpm_program_id not between", value1, value2, "hfpmProgramId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}