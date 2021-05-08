package io.hiyenwong.postoffice.jenkins.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * jenkins notification
 *
 * @author Hi Yen Wong
 * @date 5/8/2021 12:11 AM
 */
@Data
public class Message {
    private Build build;
    private String name;
    @JsonProperty("display_name")
    private String displayName;
    private Scm scm;
    private Artifacts artifacts;


    @Data
    public class Build {
        private String phase;
        private String log;
        private String status;
        @JsonProperty("full_url")
        private String fullUrl;
        @ApiModelProperty("请求ID")
        @JsonProperty("queue_id")
        private Integer queueId;
        private String url;
        private Long timestamp;
        private String notes;
    }

    @Data
    public class Scm {
        private List<?> culprits;
    }

    @Data
    public class Artifacts {
        private Build build;
    }


}
