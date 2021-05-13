package io.hiyenwong.postoffice;

import io.hiyenwong.postoffice.club.model.dao.PostBoyClubDao;
import io.hiyenwong.postoffice.club.model.dao.PostBoyDao;
import io.hiyenwong.postoffice.club.repository.PostBoyClubRepository;
import io.hiyenwong.postoffice.club.repository.PostBoyRepository;
import io.hiyenwong.postoffice.common.type.ClubType;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Post office
 *
 * @author hyanwang
 */
@SpringBootApplication
@Log4j2
public class PostofficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostofficeApplication.class, args);
    }

//    /**
//     * 数据初始化
//     *
//     * @param postBoyClubRepository
//     * @param postBoyRepository
//     * @return
//     */
//    @Bean
//    CommandLineRunner commandLineRunner(
//            PostBoyClubRepository postBoyClubRepository,
//            PostBoyRepository postBoyRepository
//    ) {
//
//        String wecomUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=";
//        String wecomKey = "3a48d648-96a7-44a2-9c5f-921bd3420faa";
//        long dateId = System.currentTimeMillis();
//        return args -> {
//            PostBoyDao postBoyDao = new PostBoyDao();
//            PostBoyClubDao postBoyClubDao = new PostBoyClubDao();
//            postBoyClubDao.setName("jenkins_dev");
//            postBoyClubDao.setUrl(wecomUrl);
//            postBoyClubDao.setStatus(1);
//            postBoyClubDao.setCommon("jenkins dev");
//            postBoyClubDao.setType(ClubType.WE_COM);
//            postBoyDao.setKey(wecomKey);
//            postBoyDao.setStatus(1);
//            postBoyDao.setCommon("jenkins_dev");
//            log.debug(dateId);
//            postBoyDao.setPbKey(589134639533334528L);
//            PostBoyClubDao postBoyClubDao1 = postBoyClubRepository.save(postBoyClubDao);
//            log.debug(postBoyClubDao1);
//            log.debug(postBoyDao);
//            postBoyDao.setClubId(postBoyClubDao1.getId());
//            postBoyRepository.save(postBoyDao);
//        };
//    }

}
