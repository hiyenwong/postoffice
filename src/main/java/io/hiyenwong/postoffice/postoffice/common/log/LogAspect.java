package io.hiyenwong.postoffice.postoffice.common.log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * LOG AOP
 *
 * @author Hi Yen Wong
 * @date 5/4/2021 12:41 AM
 */
@Aspect
@Log4j2
@Component
public class LogAspect {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restControllerCut() {
    }

    @Before("restControllerCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Gson g = new Gson();
            log.debug("url={}", request.getRequestURL());
            log.debug("method={}", request.getMethod());
            log.debug("class_method={}",
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.debug("args={}", Arrays.toString(joinPoint.getArgs()));
            log.debug("request_body={}", g.toJson(request.getParameterMap()));
//            log.debug(this.getJSONParam(request));
        }
    }

    //    @SneakyThrows
    private JSONObject getJSONParam(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonParam;
    }
}
