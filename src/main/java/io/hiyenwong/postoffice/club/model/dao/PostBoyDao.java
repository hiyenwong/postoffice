package io.hiyenwong.postoffice.club.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 客户端密钥配置
 *
 * @author Hi Yen Wong
 * @date 5/2/2021 9:57 AM
 */
@Entity
@Data
@Table(name = "post_boy")
public class PostBoyDao implements Serializable {
    private static final long serialVersionUID = -5893923220428634182L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_boy_id_seq")
    @SequenceGenerator(name = "post_boy_id_seq", sequenceName = "post_boy_id_seq")
    private Integer id;
    private String token;
    private String sign;
    private Integer clubId;
    private String common;
    private Integer status;
    private String key;
    @ManyToOne
    @JoinTable(name="club_id")
    private PostBoyClubDao postBoyClubDao;
}
