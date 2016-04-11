package com.hframe.domain.model;

import java.util.Date;

public class HfpmPageEvent {
    private Long hfpmPageEventId;

    private Long hfpmPageId;

    private String hfpmEventName;

    private String hfpmEventMonitorObject;

    private String hfpmEventMonitorObjectType;

    private Integer hfpmEventType;

    private String hfpmEventEffectObject;

    private Long opId;

    private Date createTime;

    private Long modifyOpId;

    private Date modifyTime;

    private Integer delFlag;

    private Long hfpmPageComponentId;

    public HfpmPageEvent(Long hfpmPageEventId, Long hfpmPageId, String hfpmEventName, String hfpmEventMonitorObject, String hfpmEventMonitorObjectType, Integer hfpmEventType, String hfpmEventEffectObject, Long opId, Date createTime, Long modifyOpId, Date modifyTime, Integer delFlag, Long hfpmPageComponentId) {
        this.hfpmPageEventId = hfpmPageEventId;
        this.hfpmPageId = hfpmPageId;
        this.hfpmEventName = hfpmEventName;
        this.hfpmEventMonitorObject = hfpmEventMonitorObject;
        this.hfpmEventMonitorObjectType = hfpmEventMonitorObjectType;
        this.hfpmEventType = hfpmEventType;
        this.hfpmEventEffectObject = hfpmEventEffectObject;
        this.opId = opId;
        this.createTime = createTime;
        this.modifyOpId = modifyOpId;
        this.modifyTime = modifyTime;
        this.delFlag = delFlag;
        this.hfpmPageComponentId = hfpmPageComponentId;
    }

    public Long getHfpmPageEventId() {
        return hfpmPageEventId;
    }

    public Long getHfpmPageId() {
        return hfpmPageId;
    }

    public String getHfpmEventName() {
        return hfpmEventName;
    }

    public String getHfpmEventMonitorObject() {
        return hfpmEventMonitorObject;
    }

    public String getHfpmEventMonitorObjectType() {
        return hfpmEventMonitorObjectType;
    }

    public Integer getHfpmEventType() {
        return hfpmEventType;
    }

    public String getHfpmEventEffectObject() {
        return hfpmEventEffectObject;
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

    public Long getHfpmPageComponentId() {
        return hfpmPageComponentId;
    }

    public void setHfpmPageEventId(Long hfpmPageEventId) {
        this.hfpmPageEventId=hfpmPageEventId;
    }

    public void setHfpmPageId(Long hfpmPageId) {
        this.hfpmPageId=hfpmPageId;
    }

    public void setHfpmEventName(String hfpmEventName) {
        this.hfpmEventName=hfpmEventName;
    }

    public void setHfpmEventMonitorObject(String hfpmEventMonitorObject) {
        this.hfpmEventMonitorObject=hfpmEventMonitorObject;
    }

    public void setHfpmEventMonitorObjectType(String hfpmEventMonitorObjectType) {
        this.hfpmEventMonitorObjectType=hfpmEventMonitorObjectType;
    }

    public void setHfpmEventType(Integer hfpmEventType) {
        this.hfpmEventType=hfpmEventType;
    }

    public void setHfpmEventEffectObject(String hfpmEventEffectObject) {
        this.hfpmEventEffectObject=hfpmEventEffectObject;
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

    public void setHfpmPageComponentId(Long hfpmPageComponentId) {
        this.hfpmPageComponentId=hfpmPageComponentId;
    }

    public HfpmPageEvent() {
        super();
    }
}