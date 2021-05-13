package io.hiyenwong.postoffice.club.controller;

import io.hiyenwong.postoffice.club.service.PostBoyService;
import io.hiyenwong.postoffice.common.type.ClientType;
import io.hiyenwong.postoffice.gitlab.model.vo.webhook.WebHookVO;
import io.hiyenwong.postoffice.jenkins.notification.JenkinsMessage;
import io.hiyenwong.postoffice.model.vo.response.BasicRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Hi Yen Wong
 * @date 5/8/2021 8:30 PM
 */
@RestController
@Api("推送服务")
@RequestMapping("/postboy")
@Log4j2
public class PostBoyController {
    @Resource
    PostBoyService postBoyService;

    @RequestMapping(value = "/sendMsgTxt//{key}", method = RequestMethod.POST)
    @ApiModelProperty(name = "",notes = "")
    public BasicRespVO receive(@PathVariable("key") Long key,
                               @RequestBody String requestBody) {
        postBoyService.sendTxt(key, requestBody);
        return new BasicRespVO();
    }

    @RequestMapping(value = "/sendMsg/gitlab/{key}", method = RequestMethod.POST)
    public BasicRespVO receive(@PathVariable("key") Long key,
                               @RequestBody WebHookVO requestBody) {
        log.debug(requestBody);
        postBoyService.sendMsg(ClientType.GIT_LAB, key, requestBody);
        return new BasicRespVO();
    }

    @RequestMapping(value = "/sendMsg/jenkins/{key}", method = RequestMethod.POST)
    public BasicRespVO receive(@PathVariable("key") Long key,
                               @RequestBody JenkinsMessage requestBody) {
        log.debug(requestBody);
        postBoyService.sendMsg(ClientType.JENKINS, key, requestBody);
        return new BasicRespVO();
    }

}
