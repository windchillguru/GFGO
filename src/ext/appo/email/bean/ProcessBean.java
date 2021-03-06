/** *********************************************************************** */
/*                                                                          */
/* Copyright (c) 2008-2012 YULONG Company */
/* 宇龙计算机通信科技（深圳）有限公司版权所有 2008-2012 */
/*                                                                          */
/* PROPRIETARY RIGHTS of YULONG Company are involved in the */
/* subject matter of this material. All manufacturing, reproduction, use, */
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts */
/* the terms of the license. */
/* 本软件文档资料是宇龙公司的资产，任何人士阅读和使用本资料必须获得 */
/* 相应的书面授权，承担保密责任和接受相应的法律约束。 */
/*                                                                          */
/** *********************************************************************** */

/**
 * <pre>
 * 系统缩写：PLM 
 * 系统名称：产品生命周期管理系统 
 * 组件编号：C_系统管理
 * 组件名称：系统管理
 * 文件名称：ProcessBean.java 
 * 作         者: 裴均宇
 * 生成日期：2011-09-23
 * </pre>
 */

/**
 * <pre>
 * 修改记录：01 
 * 修改日期：2011-09-23
 * 修  改   人：裴均宇
 * 关联活动：IT-DB00040918 C_系统管理_邮件跟催_代码开发_peijunyu
 * 修改内容：初始化
 * </pre>
 */
package ext.appo.email.bean;

/**
 * 此类用于封装项目超期发送邮件通知配置文件信息
 */
public class ProcessBean
{
    
    /**
     * 流程模板名称
     */
    private String processName = "";
    
    /**
     * 活动名称
     */
    private String activityName = "";
    
    /**
     * 是否检查超期时间，0为不检查
     */
    private String type = "";
    
    /**
     * 发送邮件超期时间间隔
     */
    private String endTimeSpace = "";
    
    /**
     * 发送主管邮件超期时间间隔
     */
    private String managerEndTimeSpace = "";
    
    public String getProcessName()
    {

        return processName;
    }
    
    public void setProcessName(String processName)
    {

        this.processName = processName;
    }
    
    public String getActivityName()
    {

        return activityName;
    }
    
    public void setActivityName(String activityName)
    {

        this.activityName = activityName;
    }
    
    public String getType()
    {

        return type;
    }
    
    public void setType(String type)
    {

        this.type = type;
    }
    
    public String getEndTimeSpace()
    {

        return endTimeSpace;
    }
    
    public void setEndTimeSpace(String endTimeSpace)
    {

        this.endTimeSpace = endTimeSpace;
    }
    
    public String getManagerEndTimeSpace()
    {

        return managerEndTimeSpace;
    }
    
    public void setManagerEndTimeSpace(String managerEndTimeSpace)
    {

        this.managerEndTimeSpace = managerEndTimeSpace;
    }
    
}
