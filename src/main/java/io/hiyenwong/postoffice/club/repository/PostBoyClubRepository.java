package io.hiyenwong.postoffice.club.repository;


import io.hiyenwong.postoffice.club.model.dao.PostBoyClubDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hi Yen Wong
 * @date 5/4/2021 12:32 AM
 */
@Repository
public interface PostBoyClubRepository extends JpaRepository<PostBoyClubDao, Integer> {

}
