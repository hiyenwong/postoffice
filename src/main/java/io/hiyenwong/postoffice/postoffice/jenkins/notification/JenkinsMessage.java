package io.hiyenwong.postoffice.postoffice.jenkins.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hiyenwong.postoffice.model.vo.response.MessageInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * jenkins notification
 *
 * @author Hi Yen Wong
 * @date 5/8/2021 12:11 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JenkinsMessage implements MessageInterface {
    private Build build;
    private String name;
    @JsonProperty("display_name")
    private String displayName;
    private Scm scm;
    private Artifacts artifacts;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Build implements Serializable {
        private static final long serialVersionUID = 5274819188169388890L;
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
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Scm implements Serializable {
        private static final long serialVersionUID = 8090384403668317006L;
        private List<?> culprits;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Artifacts implements Serializable {
        private static final long serialVersionUID = -2667770959103139247L;
        private Build build;
    }


}
