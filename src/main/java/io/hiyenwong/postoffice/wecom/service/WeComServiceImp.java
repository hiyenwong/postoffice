package io.hiyenwong.postoffice.wecom.service;

import com.google.gson.Gson;
import io.hiyenwong.postoffice.common.HttpConnector;
import io.hiyenwong.postoffice.service.MessageServiceInterface;
import io.hiyenwong.postoffice.wecom.domain.WeComDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WeCom service imp
 *
 * @author Hi Yen Wong
 * @date 5/5/2021 12:50 PM
 */
@Log4j2
@Data
@Service
public class WeComServiceImp implements MessageServiceInterface {
    private String key;
    private String url;
    private final Gson gson = new Gson();
    private final HttpConnector httpConnector = HttpConnector.getInstance();

    /**
     * @param msg
     * @return
     */
    @Override
    public String send(String msg) {
        checkUrl();
        WeComDomain textContent = WeComDomain.textBuilder(msg).build();
        String weComUrl = this.url + this.key;
        log.debug(weComUrl);
        String postBody = gson.toJson(textContent);
        return httpConnector.post(weComUrl, postBody);
    }

    /**
     * @param msg
     * @param mentionList
     * @return
     */
    @Override
    public String send(String msg, String... mentionList) {
        WeComDomain textContent = WeComDomain.textBuilder(msg)
                .addUserIdForAt(mentionList).build();
        String weComUrl = this.url + this.key;
        String postBody = gson.toJson(textContent);
        return httpConnector.post(weComUrl, postBody);
    }

    private void checkUrl() {
        assert StringUtils.isEmpty(this.url) : "URL EMPTY! ";
    }
}
