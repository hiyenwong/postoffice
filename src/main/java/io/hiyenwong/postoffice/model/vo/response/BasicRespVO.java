package io.hiyenwong.postoffice.model.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hi Yen Wong
 * @date 5/5/2021 1:04 PM
 */
@Data
public class BasicRespVO implements Serializable {
    private static final long serialVersionUID = 7604491093849985742L;
    private String status;
    private Object data;
}
