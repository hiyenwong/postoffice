package io.hiyenwong.postoffice.club.controller;

import io.hiyenwong.postoffice.jenkins.notification.Message;
import io.hiyenwong.postoffice.model.vo.response.BasicRespVO;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hi Yen Wong
 * @date 5/5/2021 12:51 PM
 */
@RestController
@Api("配置")
@RequestMapping("/test")
@Log4j2
public class TestController {
    @PostMapping("/receiveMsg")
    public BasicRespVO receive(@RequestBody Message requestBody) {
        log.debug("request body: " + requestBody.toString());
        return new BasicRespVO();
    }
}
