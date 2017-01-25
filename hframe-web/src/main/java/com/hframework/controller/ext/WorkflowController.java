package com.hframework.controller.ext;

import com.hframe.domain.model.HfpmDataField;
import com.hframe.domain.model.HfpmDataField_Example;
import com.hframe.domain.model.HfpmDataSet;
import com.hframe.service.interfaces.IHfpmDataFieldSV;
import com.hframe.service.interfaces.IHfpmDataSetSV;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.util.StringUtils;
import com.hframework.web.bean.WebContext;
import com.vaadin.data.Item;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.Messages;
import org.activiti.explorer.ui.Images;
import org.activiti.explorer.ui.custom.PrettyTimeLabel;
import org.activiti.explorer.ui.custom.UserProfileLink;
import org.jboss.netty.util.internal.ByteBufferUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2017/1/16.
 */
@Controller
public class WorkflowController {

    @Resource
    private IHfpmDataSetSV hfpmDataSetSV;

    @Resource
    private IHfpmDataFieldSV hfpmDataFieldSV;

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/modeler.html")
    public ModelAndView modeler(@ModelAttribute("modelId") String modelId, @ModelAttribute("dataSetId") String dataSetId,

                                HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ModelAndView mav = new ModelAndView();

        if(StringUtils.isBlank(modelId) && dataSetId != null && StringUtils.isNotBlank(dataSetId)) {
            HfpmDataField_Example example = new HfpmDataField_Example();
            example.createCriteria().andHfpmDataSetIdEqualTo(Long.valueOf(dataSetId)).andWorkfowModelIdIsNotNull().andWorkfowModelIdNotEqualTo("");
            List<HfpmDataField> hfpmDataFieldListByExample = hfpmDataFieldSV.getHfpmDataFieldListByExample(example);
            if(hfpmDataFieldListByExample != null && hfpmDataFieldListByExample.size() > 0) {
                modelId = hfpmDataFieldListByExample.get(0).getWorkfowModelId();
            }
        }

        mav.addObject("modelId", modelId);

        mav.setViewName("/modeler");
        return mav;
    }

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/editor-app/editor.html")
    public ModelAndView editor(@ModelAttribute("modelId") String modelId,

                               HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ModelAndView mav = new ModelAndView();
        mav.addObject("modelId",modelId);
        mav.setViewName("/editor");
        return mav;
    }

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/diagram-viewer/forwarder.html")
    public ModelAndView diagramForwarder(@ModelAttribute("_DS") String dataSet, @ModelAttribute("_DI") String dataId,

                                      HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ModelAndView mav = new ModelAndView();
        String processKey = String.valueOf(WebContext.get().getProcess(dataSet)[1]);
        ProcessDefinition processDefinition = ProcessEngines.getDefaultProcessEngine().getRepositoryService().
                createProcessDefinitionQuery().processDefinitionKey(processKey).singleResult();
        String processDefinitionId = "", processInstanceId = "";
        if(processDefinition != null) {
            processDefinitionId = processDefinition.getId();
            HistoricProcessInstance historicProcessInstance = ProcessEngines.getDefaultProcessEngine().getHistoryService()
                    .createHistoricProcessInstanceQuery().processInstanceBusinessKey(dataId)
                    .processDefinitionId(processDefinition.getId())
                    .singleResult();
            if(historicProcessInstance != null) {

                processInstanceId = historicProcessInstance.getId();
            }
        }
        mav.setViewName("redirect:/diagram-viewer/index.html?processDefinitionId="
                + processDefinitionId + "&processInstanceId=" + processInstanceId);
        return mav;
    }

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/diagram-viewer/index.html")
    public ModelAndView diagramViewer(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ModelAndView mav = new ModelAndView();
//                List<HistoricProcessInstance> processInstances = historyService .createHistoricProcessInstanceQuery() .startedBy(ExplorerApp.get().getLoggedInUser().getId()) .unfinished() .list();
        String processInstanceId = request.getParameter("processInstanceId");
        if(StringUtils.isNotBlank(processInstanceId)) {
            //自己创建的未完成流程单
            List< HistoricTaskInstance > tasks = ProcessEngines.getDefaultProcessEngine().getHistoryService().createHistoricTaskInstanceQuery()
                    .processInstanceId(processInstanceId)
//                    .orderByHistoricTaskInstanceEndTime().desc()
                    .orderByHistoricTaskInstanceStartTime().desc()
                    .list();

            List<Map<String, Object>> taskDisplays = new ArrayList<Map<String, Object>>();
            if(tasks != null) {
                ServiceFactory.getService(ExplorerApp.class).onRequestStart(request, response);
                for (HistoricTaskInstance task : tasks) {

                    Map<String ,Object> item = new HashMap<String, Object>();
                    taskDisplays.add(item);

                    if(task.getEndTime() != null) {
                        item.put("finished", "/VAADIN/themes/activiti/" + new Embedded(null, Images.TASK_FINISHED_22).getSource());
                    } else {
                        item.put("finished","/VAADIN/themes/activiti/" + new Embedded(null, Images.TASK_22).getSource());
                    }

                    item.put("name", task.getName());
                    item.put("priority",task.getPriority());

                    item.put("startDate", new PrettyTimeLabel(task.getStartTime(), true));
                    item.put("endDate", new PrettyTimeLabel(task.getEndTime(), true));

                    if(task.getDueDate() != null) {
                        Label dueDateLabel = new PrettyTimeLabel(task.getEndTime(), "尚未完成", true);
                        item.put("dueDate", dueDateLabel);
                    }



                    if(task.getAssignee() != null) {
                        UserProfileLink taskAssigneeComponent = new UserProfileLink(ProcessEngines.getDefaultProcessEngine().getIdentityService(), true, task.getAssignee());
                        if(taskAssigneeComponent != null) {
                            item.put("assigneeName", ((Button) taskAssigneeComponent.getComponent(1)).getCaption());
                            item.put("assigneePhoto","data:image/gif;base64," +
                                    new BASE64Encoder().encode(IoUtil.readInputStream(((StreamResource)((Embedded) taskAssigneeComponent.getComponent(0)).getSource()).getStreamSource().getStream(),"")));
                        }
                    }
                }
            }

            mav.addObject("tasks", taskDisplays);
        }


        mav.setViewName("/diagram-viewer/index");
        return mav;
    }


}
