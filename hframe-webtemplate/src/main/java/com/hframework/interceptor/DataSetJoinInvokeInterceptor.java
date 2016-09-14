package com.hframework.interceptor;

import com.hframe.domain.model.HfpmModule;
import com.hframe.domain.model.HfpmProgram;
import com.hframe.service.interfaces.IHfpmModuleSV;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.generator.util.CreatorUtil;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import com.hframework.generator.web.sql.HfModelService;
import com.hframework.generator.web.sql.reverse.SQLParseUtil;
import com.hframework.web.bean.WebContextHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 数据集连带规则拦截器
 * Created by zhangquanhong on 2016/8/28.
 */
@Component
@Aspect
public class DataSetJoinInvokeInterceptor {

    @Resource
    private IHfpmModuleSV hfpmModuleSV;

    @Pointcut("execution(* com.hframe.service.impl.*.create(..))")
    private void addMethod(){ }

    @Pointcut("execution(int com.hframe.service.impl.*.update*(..))")
    private void modMethod(){ }

    @Pointcut("execution(int com.hframe.service.impl.*.batchOperate*(..))")
    private void batchOperateMethod(){ }

    @Pointcut("execution(int com.hframe.service.impl.*.delete*(..))")
    private void deleteMethod(){ }


    @AfterReturning(pointcut = "batchOperateMethod()", returning = "retVal")
    public void batchOperateAfterReturning(JoinPoint joinPoint, int retVal) {
        joinInvoke(joinPoint,retVal);
    }

    @AfterReturning(pointcut = "addMethod()", returning = "retVal")
    public void addMethodAfterReturning(JoinPoint joinPoint, int retVal) {
        joinInvoke(joinPoint,retVal);
    }

    public void joinInvoke(JoinPoint joinPoint, int retVal) {
        if(retVal > 0) {
            Object originObject = joinPoint.getArgs()[0];
//            Class<?> originClass = originObject.getClass();
            programJoinBaseEntitys(originObject);
        }
    }

    private void programJoinBaseEntitys(Object originObject) {
        try {

            if(originObject.getClass().isArray()) {
                originObject = ((Object[])originObject)[0];
            }

            if (originObject instanceof HfpmProgram) {
                HfpmProgram program = (HfpmProgram) originObject;

                final HfpmModule hframeModule = new HfpmModule();
                hframeModule.setHfpmProgramId(program.getHfpmProgramId());
                hframeModule.setHfpmModuleCode("hframe");
                hframeModule.setHfpmModuleName("框架默认");
                hframeModule.setHfpmModuleDesc("框架默认");
                hfpmModuleSV.create(hframeModule);


                WebContextHelper contextHelper = new WebContextHelper("hframe", "trunk", "hframe", "hframe");
                String sqlFile = contextHelper.programConfigRootDir + "/init/sql/entitys.sql";
                final HfModelContainer sqlFileModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                        sqlFile, program.getHfpmProgramCode(), program.getHfpmProgramName(), hframeModule.getHfpmModuleCode(),hframeModule.getHfpmModuleName());
                final HfModelContainer baseModelContainer = HfModelContainerUtil.getInstance();
                baseModelContainer.setProgram(program);
                baseModelContainer.setModuleMap(new HashMap<Long, HfpmModule>(){{
                    put(hframeModule.getHfpmModuleId(), hframeModule);
                }});
                HfModelContainer[] resultModelContainers =
                        HfModelContainerUtil.mergerModelContainer(baseModelContainer, sqlFileModelContainer);
                HfModelService.get().executeModelInsert(resultModelContainers[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
