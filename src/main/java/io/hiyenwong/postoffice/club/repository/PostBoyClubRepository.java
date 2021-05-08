package io.hiyenwong.postoffice.club.repository;


import io.hiyenwong.postoffice.club.model.dao.PostBoyClubDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hi Yen Wong
 * @date 5/4/2021 12:32 AM
 */
@Repository
public interface PostBoyClubRepository extends JpaRepository<PostBoyClubDao, Integer> {

    /**
     * 获取ID
     *
     * @param id
     * @return
     */
    @Query("SELECT pb FROM PostBoyClubDao AS pb WHERE pb.id=:id ")
    List<PostBoyClubDao> findPostBoyClubDaoById(@Param("id") Integer id);
}
