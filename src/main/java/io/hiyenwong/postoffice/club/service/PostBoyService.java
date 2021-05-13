package io.hiyenwong.postoffice.club.service;

import com.google.common.collect.Lists;
import io.hiyenwong.postoffice.club.model.dao.PostBoyJoinClubDao;
import io.hiyenwong.postoffice.club.repository.PostBoyRepository;
import io.hiyenwong.postoffice.common.type.ClientType;
import io.hiyenwong.postoffice.gitlab.model.vo.webhook.WebHookVO;
import io.hiyenwong.postoffice.jenkins.notification.JenkinsMessage;
import io.hiyenwong.postoffice.model.vo.response.MessageInterface;
import io.hiyenwong.postoffice.wecom.service.WeComServiceImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hi Yen Wong
 * @date 5/8/2021 11:55 PM
 */
@Service
@Log4j2
public class PostBoyService {
    @Resource
    PostBoyRepository postBoyRepository;

    @Resource
    WeComServiceImp weComServiceImp;

    /**
     * @param key
     * @param message
     */
    public void sendTxt(Long key, String message) {
        List<PostBoyJoinClubDao> postBoyDaoList = postBoyRepository.findPostBoyDaoByKey(key);
        sendMessageTxt(key, postBoyDaoList, message);
    }

    /**
     * @param client
     * @param key
     * @param message
     */
    public void sendMsg(String client, Long key, MessageInterface message) {
        List<PostBoyJoinClubDao> postBoyDaoList = postBoyRepository.findPostBoyDaoByKey(key);
        String msg = null;
        switch (client) {
            case ClientType
                    .GIT_LAB:
                msg = this.gitlabMsg(this.castWebHook(castWebHook(message)));
                break;

            case ClientType.JENKINS:
                msg = this.jenkinsMsg(this.castJenkinsWebHook(message));
                break;

            default:
                msg = message.toString();
                break;
        }
        sendMessageMd(key, postBoyDaoList, msg);
    }

    private String jenkinsMsg(JenkinsMessage message) {
        if (null == message) {
            return null;
        }

        String msg = "# PROJECT NAME: " + message.getDisplayName() + "\n"
                + "> Phase: " + message.getBuild().getPhase() + "\n"
                + "> Status: " + message.getBuild().getStatus() + "\n"
                + "> Log: `" + message.getBuild().getLog() + "` \n";

        return msg;
    }

    /**
     * @param webHookVO
     * @return
     */
    private String gitlabMsg(WebHookVO webHookVO) {

        if (null == webHookVO) {
            return null;
        }

        String msg = "# PROJECT NAME: " + webHookVO.getProject().getName() + "\n"
                + "## REF: " + webHookVO.getRef() + "\n"
                + "> Pusher: " + webHookVO.getUserName() + "\n"
                + "> Url: [" + webHookVO.getProject().getWebUrl() + "](" + webHookVO.getProject().getWebUrl() + ")\n"
                + "> Event: " + webHookVO.getEventName() + "\n"
                + "> total commits count: " + webHookVO.getTotalCommitsCount() + "\n";

        if (!webHookVO.getCommits().isEmpty()) {
            List<String> commitList = Lists.newArrayList();
            webHookVO.getCommits().forEach(commitVO -> {
                commitList.add("> 提交: " + commitVO.getAuthor().getName() + ", msg: " + commitVO.getMessage());
            });

            msg = msg + String.join("\n", commitList);
        }
        return msg;
    }

    /**
     * convert gitlab webhook
     *
     * @param messageInterface
     * @return
     */
    private WebHookVO castWebHook(MessageInterface messageInterface) {
        return (WebHookVO) messageInterface;
    }

    /**
     * convert jenkins message
     *
     * @param messageInterface
     * @return
     */
    private JenkinsMessage castJenkinsWebHook(MessageInterface messageInterface) {
        return (JenkinsMessage) messageInterface;
    }

    /**
     * send txt
     *
     * @param key
     * @param postBoyDaoList
     * @param msg
     */
    private void sendMessageTxt(Long key, List<PostBoyJoinClubDao> postBoyDaoList, String msg) {
        if (!postBoyDaoList.isEmpty()) {
            log.debug(postBoyDaoList);
            weComServiceImp.setKey(postBoyDaoList.get(0).getKey());
            weComServiceImp.setUrl(postBoyDaoList.get(0).getUrl());
            weComServiceImp.sendTxt(msg);
        } else {
            log.warn("not found key" + key);
        }
    }

    /**
     * send mark down
     *
     * @param key
     * @param postBoyDaoList
     * @param msg
     */
    private void sendMessageMd(Long key, List<PostBoyJoinClubDao> postBoyDaoList, String msg) {
        if (!postBoyDaoList.isEmpty()) {
            log.debug(postBoyDaoList);
            weComServiceImp.setKey(postBoyDaoList.get(0).getKey());
            weComServiceImp.setUrl(postBoyDaoList.get(0).getUrl());
            weComServiceImp.sendMarkDown(msg);
        } else {
            log.warn("not found key" + key);
        }
    }
}
