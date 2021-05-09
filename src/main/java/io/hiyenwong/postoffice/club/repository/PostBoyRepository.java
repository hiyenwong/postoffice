package io.hiyenwong.postoffice.club.repository;


import io.hiyenwong.postoffice.club.model.dao.PostBoyDao;
import io.hiyenwong.postoffice.club.model.dao.PostBoyJoinClubDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * client token
 *
 * @author Hi Yen Wong
 * @date 5/4/2021 12:34 AM
 */
@Repository
public interface PostBoyRepository extends JpaRepository<PostBoyDao, Integer> {
    /**
     * @param key
     * @return
     */
    @Query("SELECT new " +
            "io.hiyenwong.postoffice.club.model.dao.PostBoyJoinClubDao(pbc.name , pbc.url  , pb.key , pb.sign) " +
            "FROM PostBoyDao as pb LEFT JOIN PostBoyClubDao as pbc ON pb.clubId=pbc.id WHERE pb.key=:key")
    List<PostBoyJoinClubDao> findPostBoyDaoByKey(@Param("key") String key);
}
