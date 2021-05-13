package io.hiyenwong.postoffice.postoffice.club.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

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
    @GeneratedValue(strategy = SEQUENCE, generator = "post_boy_sequence")
    @SequenceGenerator(name = "post_boy_sequence", sequenceName = "post_boy_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;
    @Column(name = "pb_key")
    private Long pbKey;
    private String sign;
    @Column(name = "club_id")
    private Integer clubId;
    private String common;
    private Integer status;
    private String key;
}
