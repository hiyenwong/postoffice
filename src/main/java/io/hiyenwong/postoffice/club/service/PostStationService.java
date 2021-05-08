package io.hiyenwong.postoffice.club.service;


import io.hiyenwong.postoffice.club.model.dao.PostBoyClubDao;
import io.hiyenwong.postoffice.club.model.vo.request.PostBoyClubRequestVO;
import io.hiyenwong.postoffice.club.repository.PostBoyClubRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Hi Yen Wong
 * @date 5/4/2021 12:36 AM
 */
@Service
public class PostStationService {
    @Resource
    PostBoyClubRepository postBoyClubRepository;

    /**
     * 添加配置
     *
     * @param postBoyClubRequestVO
     * @return
     */
    public PostBoyClubDao addClient(PostBoyClubRequestVO postBoyClubRequestVO) {
        PostBoyClubDao postBoyClubDao = new PostBoyClubDao();
        postBoyClubDao.setStatus(postBoyClubRequestVO.getStatus());
        postBoyClubDao.setName(postBoyClubRequestVO.getName());
        postBoyClubDao.setUrl(postBoyClubRequestVO.getUrl());
        return postBoyClubRepository.save(postBoyClubDao);
    }
}
