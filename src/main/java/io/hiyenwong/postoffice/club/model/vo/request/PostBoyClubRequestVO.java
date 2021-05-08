package io.hiyenwong.postoffice.club.model.vo.request;

import lombok.Data;

/**
 * @author Hi Yen Wong
 * @date 5/6/2021 12:26 AM
 */

@Data
public class PostBoyClubRequestVO {
    private String url;
    private String name;
    private Integer status;
    private String common;
}
