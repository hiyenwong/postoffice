package io.hiyenwong.postoffice.postoffice.gitlab.model.vo.webhook;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hi Yen Wong
 * @date 5/12/2021 5:18 PM
 */

@Data
public class AuthorVO implements Serializable {
    private static final long serialVersionUID = -3127534111866191264L;
    private String name;
    private String email;
}
