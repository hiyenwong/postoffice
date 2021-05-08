package io.hiyenwong.postoffice.model.vo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hi Yen Wong
 * @date 5/5/2021 1:05 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorRespVO extends BasicRespVO {
    private static final long serialVersionUID = -3951785792756679537L;
    private String message;
}
