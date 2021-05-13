package io.hiyenwong.postoffice.postoffice.gitlab.model.vo.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Hi Yen Wong
 * @date 5/12/2021 1:28 PM
 */
@Data
public class ProjectVO implements Serializable {
    private static final long serialVersionUID = 6995536715458711452L;
    private String name;
    @JsonProperty("web_url")
    private String webUrl;
    private String userName;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("default_branch")
    private String defaultBranch;
}
