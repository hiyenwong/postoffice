package io.hiyenwong.postoffice.club.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBoyDao implements Serializable {
    private static final long serialVersionUID = -5893923220428634182L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_boy_id_seq")
    @SequenceGenerator(name = "post_boy_id_seq", sequenceName = "post_boy_id_seq")
    private Integer id;
    private String sign;
    private Integer clubId;
    private String common;
    private Integer status;
    private String key;

    @JoinTable(name = "post_boy_club")
    @JoinColumn(name = "club_id" ,referencedColumnName = "id")
    @ManyToOne
    private PostBoyClubDao postBoyClubDao;
}
