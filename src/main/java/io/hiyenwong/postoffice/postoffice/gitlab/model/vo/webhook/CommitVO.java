package io.hiyenwong.postoffice.postoffice.gitlab.model.vo.webhook;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hi Yen Wong
 * @date 5/12/2021 1:29 PM
 */
@Data
public class CommitVO implements Serializable {
    private static final long serialVersionUID = -1481698784988673525L;
    private String id;
    private String message;
    private String timestamp;
    private AuthorVO author;
}
