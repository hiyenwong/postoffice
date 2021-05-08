package io.hiyenwong.postoffice.club.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 客户端维表
 *
 * @author Hi Yen Wong
 * @date 5/2/2021 9:57 AM
 */
@Data
@Entity
@Table(name = "post_boy_club")
public class PostBoyClubDao implements Serializable {
    private static final long serialVersionUID = 8196662055846669272L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="club_id_seq")
    @SequenceGenerator(name="club_id_seq", sequenceName = "club_id_seq")
    private Integer id;
    private String name;
    private String url;
    private Integer status;
    private String common;
}
