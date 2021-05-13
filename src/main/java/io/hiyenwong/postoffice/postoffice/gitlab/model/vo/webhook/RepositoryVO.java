package io.hiyenwong.postoffice.postoffice.gitlab.model.vo.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Hi Yen Wong
 * @date 5/12/2021 5:18 PM
 */
@Data
public class RepositoryVO implements Serializable {
    private static final long serialVersionUID = 1388251112402855684L;
    private String name;
    private String url;
    private String description;
    private String homepage;
    @JsonProperty("git_http_url")
    private String gitHttpUrl;
    @JsonProperty("git_ssh_url")
    private String gitSshUrl;
}
