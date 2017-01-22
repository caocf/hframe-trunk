package com.hframework.controller.ext;

import com.hframe.domain.model.HfpmDataField;
import com.hframe.domain.model.HfpmDataField_Example;
import com.hframe.domain.model.HfpmDataSet;
import com.hframe.service.interfaces.IHfpmDataFieldSV;
import com.hframe.service.interfaces.IHfpmDataSetSV;
import com.hframework.common.util.StringUtils;
import com.hframework.web.bean.WebContext;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        mav.setViewName("/diagram-viewer/index");
        return mav;
    }


}
