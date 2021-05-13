package io.hiyenwong.postoffice.postoffice.gitlab.model.vo.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hiyenwong.postoffice.model.vo.response.MessageInterface;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hi Yen Wong
 * @date 5/12/2021 1:28 PM
 */
@Data
public class WebHookVO implements Serializable, MessageInterface {
    private static final long serialVersionUID = 3415503772449166944L;
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_name")
    private String eventName;
    private String before;
    private String after;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("project_id")
    private String projectId;
    private String ref;
    private String message;
    private List<CommitVO> commits;
    @JsonProperty("total_commits_count")
    private Integer totalCommitsCount;
    private ProjectVO project;
    private RepositoryVO repository;
}
