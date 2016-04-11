package com.hframe.domain.model;

import java.util.Date;

public class HfpmProgramCfg {
    private Long hfpmProgramCfgId;

    private String showName;

    private Long hfcfgProgramTemplateId;

    private Long hfcfgProgramSkinId;

    private Long hfcfgLoginPageId;

    private String bgImgUrl;

    private Long opId;

    private Date createTime;

    private Long modifyOpId;

    private Date modifyTime;

    private Integer delFlag;

    public HfpmProgramCfg(Long hfpmProgramCfgId, String showName, Long hfcfgProgramTemplateId, Long hfcfgProgramSkinId, Long hfcfgLoginPageId, String bgImgUrl, Long opId, Date createTime, Long modifyOpId, Date modifyTime, Integer delFlag) {
        this.hfpmProgramCfgId = hfpmProgramCfgId;
        this.showName = showName;
        this.hfcfgProgramTemplateId = hfcfgProgramTemplateId;
        this.hfcfgProgramSkinId = hfcfgProgramSkinId;
        this.hfcfgLoginPageId = hfcfgLoginPageId;
        this.bgImgUrl = bgImgUrl;
        this.opId = opId;
        this.createTime = createTime;
        this.modifyOpId = modifyOpId;
        this.modifyTime = modifyTime;
        this.delFlag = delFlag;
    }

    public Long getHfpmProgramCfgId() {
        return hfpmProgramCfgId;
    }

    public String getShowName() {
        return showName;
    }

    public Long getHfcfgProgramTemplateId() {
        return hfcfgProgramTemplateId;
    }

    public Long getHfcfgProgramSkinId() {
        return hfcfgProgramSkinId;
    }

    public Long getHfcfgLoginPageId() {
        return hfcfgLoginPageId;
    }

    public String getBgImgUrl() {
        return bgImgUrl;
    }

    public Long getOpId() {
        return opId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getModifyOpId() {
        return modifyOpId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setHfpmProgramCfgId(Long hfpmProgramCfgId) {
        this.hfpmProgramCfgId=hfpmProgramCfgId;
    }

    public void setShowName(String showName) {
        this.showName=showName;
    }

    public void setHfcfgProgramTemplateId(Long hfcfgProgramTemplateId) {
        this.hfcfgProgramTemplateId=hfcfgProgramTemplateId;
    }

    public void setHfcfgProgramSkinId(Long hfcfgProgramSkinId) {
        this.hfcfgProgramSkinId=hfcfgProgramSkinId;
    }

    public void setHfcfgLoginPageId(Long hfcfgLoginPageId) {
        this.hfcfgLoginPageId=hfcfgLoginPageId;
    }

    public void setBgImgUrl(String bgImgUrl) {
        this.bgImgUrl=bgImgUrl;
    }

    public void setOpId(Long opId) {
        this.opId=opId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    public void setModifyOpId(Long modifyOpId) {
        this.modifyOpId=modifyOpId;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime=modifyTime;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag=delFlag;
    }

    public HfpmProgramCfg() {
        super();
    }
}