package io.hiyenwong.postoffice.club.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Hi Yen Wong
 * @date 5/10/2021 12:36 AM
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostBoyJoinClubDao implements Serializable {
    private static final long serialVersionUID = 8079946775294618777L;
    private String name;
    private String url;
    private String key;
    private String sign;
}
