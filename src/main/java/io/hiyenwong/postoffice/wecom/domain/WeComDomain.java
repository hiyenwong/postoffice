package io.hiyenwong.postoffice.wecom.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * WeCom Domain
 *
 * @author Hi Yen Wong
 * @date 1/13/2021 11:57 PM
 */
@ToString
public class WeComDomain {
    public final static String MSG_TYPE_TEXT = "text";
    public final static String MSG_TYPE_MARKDOWN = "markdown";
    public final static String MSG_TYPE_IMAGE = "image";
    public final static String MSG_TYPE_NEWS = "news";

    /**
     * 推送robot key（不填则由实现决定如何处理）
     * 优先使用pushKey
     */
    @Getter
    private String robotKey;
    /**
     * 消息类型枚举
     */
    private String msgtype;
    //region 不同消息类型用到的不同字段。每次仅需要实例化1个即可
    /**
     * type=text时需要去构建的实体
     */
    private TextType text;
    /**
     * type=markdown时需要去构建的实体
     */
    private MarkdownType markdown;
    /**
     * type=image时需要去构建的实体
     */
    private ImageType image;
    /**
     * type=news时需要去构建的实体
     */
    private NewsType news;
    //endregion

    /**
     * text type build
     *
     * @param content 消息内容
     * @return 返回builder
     */
    public static TextBuilder textBuilder(String content) {
        return new TextBuilder(content);
    }

    /**
     * 构建一个Markdown类型消息实体
     *
     * @param content 消息内容（Markdown格式）
     * @return 返回builder
     */
    public static MarkdownBuilder markdownBuilder(String content) {
        return new MarkdownBuilder(content);
    }

    /**
     * 构建一个Image类型消息实体
     *
     * @param base64 图片内容的base64编码；无需增加类似data:image/png;base64,的头。这一点要注意，因为在线转换工具大多会带上这个前缀
     * @param md5    图片内容（base64编码前）的md5值;
     * @return 返回builder
     */
    public static ImageBuilder imageBuilder(String base64, String md5) {
        return new ImageBuilder(base64, md5);
    }

    /**
     * 构建一个news类型消息实体
     *
     * @param title       标题，不超过128个字节，超过会自动截断
     * @param url         点击后跳转的链接。
     * @param description 描述，不超过512个字节，超过会自动截断 非必填
     * @param picUrl      图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150。 非必填
     * @return 返回builder
     */
    public static NewsBuilder newsBuilder(String title, String url, String description, String picUrl) {
        return new NewsBuilder(title, url, description, picUrl);
    }

    public static NewsBuilder newsBuilder(String title, String url, String description) {
        return new NewsBuilder(title, url, description, null);
    }

    public static NewsBuilder newsBuilder(String title, String url) {
        return new NewsBuilder(title, url, null, null);
    }

    //region 消息实体类

    @AllArgsConstructor
    private static class TextType {
        /**
         * 文本内容，最长不超过2048个字节，必须是utf8编码
         */
        private String content;
        /**
         * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
         */
        private List<String> mentioned_list;
        /**
         * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
         */
        private List<String> mentioned_mobile_list;
    }

    @AllArgsConstructor
    private static class MarkdownType {
        /**
         * markdown内容，最长不超过4096个字节，必须是utf8编码
         */
        private String content;
    }

    @AllArgsConstructor
    private static class ImageType {
        /**
         * 图片内容的base64编码
         */
        private String base64;
        /**
         * 图片内容（base64编码前）的md5值
         */
        private String md5;
    }

    @AllArgsConstructor
    private static class NewsType {
        /**
         * 图文消息，一个图文消息支持1到8条图文
         */
        private List<Article> articles;

        /**
         * 图文消息实体
         */
        @AllArgsConstructor
        private static class Article {
            /**
             * 标题，不超过128个字节，超过会自动截断
             */
            private String title;
            /**
             * 描述，不超过512个字节，超过会自动截断
             * 非必填
             */
            private String description;
            /**
             * 点击后跳转的链接。
             */
            private String url;
            /**
             * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150。
             * 非必填
             */
            private String picurl;
        }
    }
    //endregion


    //region 各类构造方法，用于构建不同的消息类型实体

    private WeComDomain(NewsType news) {
        this.msgtype = MSG_TYPE_NEWS;
        this.news = news;
    }

    private WeComDomain(NewsType news, String robotKey) {
        this.msgtype = MSG_TYPE_NEWS;
        this.news = news;
        this.robotKey = robotKey;
    }

    private WeComDomain(ImageType image) {
        this.msgtype = MSG_TYPE_IMAGE;
        this.image = image;
    }

    private WeComDomain(ImageType image, String robotKey) {
        this.msgtype = MSG_TYPE_IMAGE;
        this.image = image;
        this.robotKey = robotKey;
    }

    private WeComDomain(MarkdownType markdown) {
        this.msgtype = MSG_TYPE_MARKDOWN;
        this.markdown = markdown;
    }

    private WeComDomain(MarkdownType markdown, String robotKey) {
        this.msgtype = MSG_TYPE_MARKDOWN;
        this.markdown = markdown;
        this.robotKey = robotKey;
    }

    private WeComDomain(TextType text) {
        this.msgtype = MSG_TYPE_TEXT;
        this.text = text;
    }

    private WeComDomain(TextType text, String robotKey) {
        this.msgtype = MSG_TYPE_TEXT;
        this.text = text;
        this.robotKey = robotKey;
    }
    //endregion

    //region 不同消息类型的Builder

    /**
     * Text类型消息Builder
     */
    public static class TextBuilder {
        /**
         * 当需要@all时候需要填入mentioned_list或mentioned_mobile_list中的
         */
        private static final String AT_ALL = "@all";
        private String content;
        private List<String> mentionedList;
        private List<String> mentionedMobileList;


        /**
         * 构造方法，消息体必填
         *
         * @param content 消息体
         */
        private TextBuilder(String content) {
            this.content = content;
        }

        /**
         * 添加userId，用于在消息中@某人
         *
         * @param mentioned 企业微信userId
         * @return 返回建造者本身
         */
        public TextBuilder addUserIdForAt(String... mentioned) {
            if (mentioned != null && mentioned.length > 0) {
                if (mentionedList == null) {
                    mentionedList = new ArrayList<>();
                }
                mentionedList.addAll(Arrays.asList(mentioned));
            }
            return this;
        }

        /**
         * 添加手机号，用于添加某人
         * 当无法获取到userId的时候，则可以添加手机号（需要是企业微信绑定的）
         *
         * @param mobiles 企业微信userId
         * @return 返回建造者本身
         */
        public TextBuilder addMobileForAt(String... mobiles) {
            if (mobiles != null && mobiles.length > 0) {
                if (mentionedMobileList == null) {
                    mentionedMobileList = new ArrayList<>();
                }
                mentionedMobileList.addAll(Arrays.asList(mobiles));
            }
            return this;
        }

        public TextBuilder atAll() {
            addMobileForAt(AT_ALL);
            return this;
        }

        public WeComDomain build() {
            return new WeComDomain(new TextType(content, mentionedList, mentionedMobileList));
        }

        public WeComDomain build(String robotKey) {
            return new WeComDomain(new TextType(content, mentionedList, mentionedMobileList), robotKey);
        }
    }

    /**
     * Markdown类型消息Builder
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MarkdownBuilder {
        /**
         * markdown内容，最长不超过4096个字节，必须是utf8编码
         */
        private String content;

        public WeComDomain build() {
            return new WeComDomain(new MarkdownType(content));
        }

        public WeComDomain build(String robotKey) {
            return new WeComDomain(new MarkdownType(content), robotKey);
        }

    }

    /**
     * Image类型消息Builder
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ImageBuilder {
        /**
         * 图片内容的base64编码
         */
        private String base64;
        /**
         * 图片内容（base64编码前）的md5值
         */
        private String md5;

        public WeComDomain build() {
            return new WeComDomain(new ImageType(base64, md5));
        }

        public WeComDomain build(String robotKey) {
            return new WeComDomain(new ImageType(base64, md5), robotKey);
        }
    }

    /**
     * News类型消息Builder
     */
    public static class NewsBuilder {
        /**
         * 图文消息，一个图文消息支持1到8条图文
         */
        private List<NewsType.Article> articles;

        /**
         * 构造方法
         *
         * @param title       标题
         * @param url         跳转地址
         * @param description 描述（非必填）
         * @param picUrl      图片地址（非必填）
         */
        private NewsBuilder(String title, String url, String description, String picUrl) {
            this.articles = new ArrayList<>(Collections.singletonList(new NewsType.Article(title, description, url, picUrl)));
        }

        /**
         * 新增一个图文
         *
         * @param title       标题
         * @param url         跳转地址
         * @param description 描述（可为空）
         * @param picUrl      图片地址
         * @return 返回builder
         */
        public NewsBuilder addArticles(String title, String url, String description, String picUrl) {
            articles.add(new NewsType.Article(title, description, url, picUrl));
            return this;
        }

        /**
         * 新增一个图文
         *
         * @param title       标题
         * @param url         跳转地址
         * @param description 描述（可为空）
         * @return 返回builder
         */
        public NewsBuilder addArticles(String title, String url, String description) {
            return addArticles(title, url, description, null);
        }

        /**
         * 新增一个图文
         *
         * @param title 标题
         * @param url   跳转地址
         * @return 返回builder
         */
        public NewsBuilder addArticles(String title, String url) {
            return addArticles(title, url, null, null);
        }

        public WeComDomain build() {
            return new WeComDomain(new NewsType(articles));
        }

        public WeComDomain build(String robotKey) {
            return new WeComDomain(new NewsType(articles), robotKey);
        }
    }
    //endregion
}
