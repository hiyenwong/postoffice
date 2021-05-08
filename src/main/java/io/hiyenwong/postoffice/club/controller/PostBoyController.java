package io.hiyenwong.postoffice.club.controller;

import io.hiyenwong.postoffice.club.service.PostBoyService;
import io.hiyenwong.postoffice.jenkins.notification.Message;
import io.hiyenwong.postoffice.model.vo.response.BasicRespVO;
import io.swagger.annotations.Api;
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

    @RequestMapping(value = "/sendMsg/{client}/{key}", method = RequestMethod.POST)
    public BasicRespVO receive(@PathVariable("client") String client, @PathVariable("key") String key,
                               @RequestBody Message requestBody) {
        log.debug(client);
        log.debug(key);
        postBoyService.sendMsg(client, key, requestBody);
        return new BasicRespVO();
    }
}
