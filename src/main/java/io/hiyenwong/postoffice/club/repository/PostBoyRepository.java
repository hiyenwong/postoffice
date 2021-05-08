package io.hiyenwong.postoffice.club.repository;


import io.hiyenwong.postoffice.club.model.dao.PostBoyDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT c FROM PostBoyDao as c where c.key=:key")
    List<PostBoyDao> findRouteByKey(String key);
}
