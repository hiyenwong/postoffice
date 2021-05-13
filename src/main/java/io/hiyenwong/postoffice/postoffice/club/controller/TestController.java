package io.hiyenwong.postoffice.postoffice.club.controller;

import io.hiyenwong.postoffice.jenkins.notification.JenkinsMessage;
import io.hiyenwong.postoffice.model.vo.response.BasicRespVO;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hi Yen Wong
 * @date 5/5/2021 12:51 PM
 */
@RestController
@Api("配置")
@RequestMapping("/test")
@Log4j2
public class TestController {
    @RequestMapping(value = "/receiveMsg/{client}/", method = RequestMethod.POST)
    public BasicRespVO receive(@PathVariable("client") String client, @RequestBody JenkinsMessage requestBody) {
        log.debug(client);
        log.debug("request body: " + requestBody.toString());
        return new BasicRespVO();
    }
}
