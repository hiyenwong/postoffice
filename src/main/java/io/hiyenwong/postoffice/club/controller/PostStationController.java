package io.hiyenwong.postoffice.club.controller;

import io.hiyenwong.postoffice.club.model.dao.PostBoyClubDao;
import io.hiyenwong.postoffice.club.model.vo.request.PostBoyClubRequestVO;
import io.hiyenwong.postoffice.club.service.PostBoyClubService;
import io.hiyenwong.postoffice.model.vo.response.BasicRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Hi Yen Wong
 * @date 5/2/2021 12:42 AM
 */
@RestController
@Api("配置")
public class PostStationController {
    @Resource
    PostBoyClubService postBoyClubService;

    @RequestMapping(value = "/postBoy/_list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "post station list", notes = "post station list")
    public BasicRespVO postStationList() {
        return new BasicRespVO();
    }

    @RequestMapping(value = "/postBoyClub/_add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "add club", notes = "add post boy club")
    public BasicRespVO addClient(PostBoyClubRequestVO postBoyClubRequestVO) {
        PostBoyClubDao postBoyClubDao = postBoyClubService.addClient(postBoyClubRequestVO);
        return new BasicRespVO();
    }

    @RequestMapping(value = "/postBoyClub/_list", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "post station list", notes = "post station list")
    public BasicRespVO postBoyClubList() {
        return new BasicRespVO();
    }
}
