package io.hiyenwong.postoffice.club.service;

import io.hiyenwong.postoffice.club.model.dao.PostBoyClubDao;
import io.hiyenwong.postoffice.club.model.dao.PostBoyDao;
import io.hiyenwong.postoffice.club.repository.PostBoyClubRepository;
import io.hiyenwong.postoffice.club.repository.PostBoyRepository;
import io.hiyenwong.postoffice.model.vo.response.MessageInterface;
import io.hiyenwong.postoffice.wecom.service.WeComServiceImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hi Yen Wong
 * @date 5/8/2021 11:55 PM
 */
@Service
@Log4j2
public class PostBoyService {
    @Resource
    PostBoyRepository postBoyRepository;

    @Resource
    PostBoyClubRepository postBoyClubRepository;

    @Resource
    WeComServiceImp weComServiceImp;

    public void sendMsg(String client, String key, MessageInterface message) {
        List<PostBoyDao> postBoyDaoList = postBoyRepository.findPostBoyDaoByKey(key);
        if (!postBoyDaoList.isEmpty()) {
            List<PostBoyClubDao> postBoyClubDaos = postBoyClubRepository
                    .findPostBoyClubDaoById(postBoyDaoList.get(0).getClubId());

            if (!postBoyClubDaos.isEmpty()) {
                weComServiceImp.setUrl(postBoyClubDaos.get(0).getUrl());
                weComServiceImp.setKey(key);
                weComServiceImp.send(message.toString());
            }
        }

    }
}
