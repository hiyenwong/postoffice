package io.hiyenwong.postoffice.postoffice.club.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * 客户端维表
 *
 * @author Hi Yen Wong
 * @date 5/2/2021 9:57 AM
 */
@Data
@Entity
@Table(name = "post_boy_club")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBoyClubDao implements Serializable {
    private static final long serialVersionUID = 8196662055846669272L;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "post_boy_club_sequence")
    @SequenceGenerator(name = "post_boy_club_sequence", sequenceName = "post_boy_club_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, updatable = false)
    private Integer id;
    private String name;
    private String url;
    private Integer status;
    private String common;
    private String type;
}
