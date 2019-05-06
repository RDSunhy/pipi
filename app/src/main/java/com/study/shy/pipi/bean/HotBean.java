package com.study.shy.pipi.bean;

import java.util.List;

public class HotBean {

    private String nextPageUrl;
    private long nextPublishTime;
    private String newestIssueType;
    private Object dialog;
    private List<IssueListBean> issueList;

    @Override
    public String toString() {
        return "HotBean{" +
                "nextPageUrl='" + nextPageUrl + '\'' +
                ", nextPublishTime=" + nextPublishTime +
                ", newestIssueType='" + newestIssueType + '\'' +
                ", dialog=" + dialog +
                ", issueList=" + issueList +
                '}';
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public String getNewestIssueType() {
        return newestIssueType;
    }

    public void setNewestIssueType(String newestIssueType) {
        this.newestIssueType = newestIssueType;
    }

    public Object getDialog() {
        return dialog;
    }

    public void setDialog(Object dialog) {
        this.dialog = dialog;
    }

    public List<IssueListBean> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueListBean> issueList) {
        this.issueList = issueList;
    }

    public static class IssueListBean {

        private long releaseTime;
        private String type;
        private long date;
        private long publishTime;
        private int count;
        private List<ItemListBean> itemList;

        @Override
        public String toString() {
            return "IssueListBean{" +
                    "releaseTime=" + releaseTime +
                    ", type='" + type + '\'' +
                    ", date=" + date +
                    ", publishTime=" + publishTime +
                    ", count=" + count +
                    ", itemList=" + itemList +
                    '}';
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            private String type;
            private DataBean data;
            private Object tag;
            private int id;
            private int adIndex;

            @Override
            public String toString() {
                return "ItemListBean{" +
                        "type='" + type + '\'' +
                        ", data=" + data +
                        ", tag=" + tag +
                        ", id=" + id +
                        ", adIndex=" + adIndex +
                        '}';
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public Object getTag() {
                return tag;
            }

            public void setTag(Object tag) {
                this.tag = tag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAdIndex() {
                return adIndex;
            }

            public void setAdIndex(int adIndex) {
                this.adIndex = adIndex;
            }

            public static class DataBean {

                private String dataType;
                private int id;
                private String title;
                private String description;
                private String library;
                private DataBean.ConsumptionBean consumption;
                private String resourceType;
                private String slogan;
                private DataBean.ProviderBean provider;
                private String category;
                private DataBean.AuthorBean author;
                private DataBean.CoverBean cover;
                private String playUrl;
                private Object thumbPlayUrl;
                private int duration;
                private DataBean.WebUrlBean webUrl;
                private long releaseTime;
                private Object campaign;
                private Object waterMarks;
                private boolean ad;
                private Object adTrack;
                private String type;
                private Object titlePgc;
                private Object descriptionPgc;
                private Object remark;
                private boolean ifLimitVideo;
                private int searchWeight;
                private int idx;
                private Object shareAdTrack;
                private Object favoriteAdTrack;
                private Object webAdTrack;
                private long date;
                private Object promotion;
                private Object label;
                private String descriptionEditor;
                private boolean collected;
                private boolean played;
                private Object lastViewTime;
                private Object playlists;
                private Object src;
                private List<DataBean.TagsBean> tags;
                private List<DataBean.PlayInfoBean> playInfo;
                private List<?> labelList;
                private List<?> subtitles;

                @Override
                public String toString() {
                    return "DataBean{" +
                            "dataType='" + dataType + '\'' +
                            ", id=" + id +
                            ", title='" + title + '\'' +
                            ", description='" + description + '\'' +
                            ", library='" + library + '\'' +
                            ", consumption=" + consumption +
                            ", resourceType='" + resourceType + '\'' +
                            ", slogan='" + slogan + '\'' +
                            ", provider=" + provider +
                            ", category='" + category + '\'' +
                            ", author=" + author +
                            ", cover=" + cover +
                            ", playUrl='" + playUrl + '\'' +
                            ", thumbPlayUrl=" + thumbPlayUrl +
                            ", duration=" + duration +
                            ", webUrl=" + webUrl +
                            ", releaseTime=" + releaseTime +
                            ", campaign=" + campaign +
                            ", waterMarks=" + waterMarks +
                            ", ad=" + ad +
                            ", adTrack=" + adTrack +
                            ", type='" + type + '\'' +
                            ", titlePgc=" + titlePgc +
                            ", descriptionPgc=" + descriptionPgc +
                            ", remark=" + remark +
                            ", ifLimitVideo=" + ifLimitVideo +
                            ", searchWeight=" + searchWeight +
                            ", idx=" + idx +
                            ", shareAdTrack=" + shareAdTrack +
                            ", favoriteAdTrack=" + favoriteAdTrack +
                            ", webAdTrack=" + webAdTrack +
                            ", date=" + date +
                            ", promotion=" + promotion +
                            ", label=" + label +
                            ", descriptionEditor='" + descriptionEditor + '\'' +
                            ", collected=" + collected +
                            ", played=" + played +
                            ", lastViewTime=" + lastViewTime +
                            ", playlists=" + playlists +
                            ", src=" + src +
                            ", tags=" + tags +
                            ", playInfo=" + playInfo +
                            ", labelList=" + labelList +
                            ", subtitles=" + subtitles +
                            '}';
                }

                public String getDataType() {
                    return dataType;
                }

                public void setDataType(String dataType) {
                    this.dataType = dataType;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getLibrary() {
                    return library;
                }

                public void setLibrary(String library) {
                    this.library = library;
                }

                public DataBean.ConsumptionBean getConsumption() {
                    return consumption;
                }

                public void setConsumption(DataBean.ConsumptionBean consumption) {
                    this.consumption = consumption;
                }

                public String getResourceType() {
                    return resourceType;
                }

                public void setResourceType(String resourceType) {
                    this.resourceType = resourceType;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public DataBean.ProviderBean getProvider() {
                    return provider;
                }

                public void setProvider(DataBean.ProviderBean provider) {
                    this.provider = provider;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public DataBean.AuthorBean getAuthor() {
                    return author;
                }

                public void setAuthor(DataBean.AuthorBean author) {
                    this.author = author;
                }

                public DataBean.CoverBean getCover() {
                    return cover;
                }

                public void setCover(DataBean.CoverBean cover) {
                    this.cover = cover;
                }

                public String getPlayUrl() {
                    return playUrl;
                }

                public void setPlayUrl(String playUrl) {
                    this.playUrl = playUrl;
                }

                public Object getThumbPlayUrl() {
                    return thumbPlayUrl;
                }

                public void setThumbPlayUrl(Object thumbPlayUrl) {
                    this.thumbPlayUrl = thumbPlayUrl;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public DataBean.WebUrlBean getWebUrl() {
                    return webUrl;
                }

                public void setWebUrl(DataBean.WebUrlBean webUrl) {
                    this.webUrl = webUrl;
                }

                public long getReleaseTime() {
                    return releaseTime;
                }

                public void setReleaseTime(long releaseTime) {
                    this.releaseTime = releaseTime;
                }

                public Object getCampaign() {
                    return campaign;
                }

                public void setCampaign(Object campaign) {
                    this.campaign = campaign;
                }

                public Object getWaterMarks() {
                    return waterMarks;
                }

                public void setWaterMarks(Object waterMarks) {
                    this.waterMarks = waterMarks;
                }

                public boolean isAd() {
                    return ad;
                }

                public void setAd(boolean ad) {
                    this.ad = ad;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Object getTitlePgc() {
                    return titlePgc;
                }

                public void setTitlePgc(Object titlePgc) {
                    this.titlePgc = titlePgc;
                }

                public Object getDescriptionPgc() {
                    return descriptionPgc;
                }

                public void setDescriptionPgc(Object descriptionPgc) {
                    this.descriptionPgc = descriptionPgc;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }

                public boolean isIfLimitVideo() {
                    return ifLimitVideo;
                }

                public void setIfLimitVideo(boolean ifLimitVideo) {
                    this.ifLimitVideo = ifLimitVideo;
                }

                public int getSearchWeight() {
                    return searchWeight;
                }

                public void setSearchWeight(int searchWeight) {
                    this.searchWeight = searchWeight;
                }

                public int getIdx() {
                    return idx;
                }

                public void setIdx(int idx) {
                    this.idx = idx;
                }

                public Object getShareAdTrack() {
                    return shareAdTrack;
                }

                public void setShareAdTrack(Object shareAdTrack) {
                    this.shareAdTrack = shareAdTrack;
                }

                public Object getFavoriteAdTrack() {
                    return favoriteAdTrack;
                }

                public void setFavoriteAdTrack(Object favoriteAdTrack) {
                    this.favoriteAdTrack = favoriteAdTrack;
                }

                public Object getWebAdTrack() {
                    return webAdTrack;
                }

                public void setWebAdTrack(Object webAdTrack) {
                    this.webAdTrack = webAdTrack;
                }

                public long getDate() {
                    return date;
                }

                public void setDate(long date) {
                    this.date = date;
                }

                public Object getPromotion() {
                    return promotion;
                }

                public void setPromotion(Object promotion) {
                    this.promotion = promotion;
                }

                public Object getLabel() {
                    return label;
                }

                public void setLabel(Object label) {
                    this.label = label;
                }

                public String getDescriptionEditor() {
                    return descriptionEditor;
                }

                public void setDescriptionEditor(String descriptionEditor) {
                    this.descriptionEditor = descriptionEditor;
                }

                public boolean isCollected() {
                    return collected;
                }

                public void setCollected(boolean collected) {
                    this.collected = collected;
                }

                public boolean isPlayed() {
                    return played;
                }

                public void setPlayed(boolean played) {
                    this.played = played;
                }

                public Object getLastViewTime() {
                    return lastViewTime;
                }

                public void setLastViewTime(Object lastViewTime) {
                    this.lastViewTime = lastViewTime;
                }

                public Object getPlaylists() {
                    return playlists;
                }

                public void setPlaylists(Object playlists) {
                    this.playlists = playlists;
                }

                public Object getSrc() {
                    return src;
                }

                public void setSrc(Object src) {
                    this.src = src;
                }

                public List<DataBean.TagsBean> getTags() {
                    return tags;
                }

                public void setTags(List<DataBean.TagsBean> tags) {
                    this.tags = tags;
                }

                public List<DataBean.PlayInfoBean> getPlayInfo() {
                    return playInfo;
                }

                public void setPlayInfo(List<DataBean.PlayInfoBean> playInfo) {
                    this.playInfo = playInfo;
                }

                public List<?> getLabelList() {
                    return labelList;
                }

                public void setLabelList(List<?> labelList) {
                    this.labelList = labelList;
                }

                public List<?> getSubtitles() {
                    return subtitles;
                }

                public void setSubtitles(List<?> subtitles) {
                    this.subtitles = subtitles;
                }

                public static class ConsumptionBean {
                    /**
                     * collectionCount : 1324
                     * shareCount : 784
                     * replyCount : 67
                     */

                    private int collectionCount;
                    private int shareCount;
                    private int replyCount;

                    public int getCollectionCount() {
                        return collectionCount;
                    }

                    public void setCollectionCount(int collectionCount) {
                        this.collectionCount = collectionCount;
                    }

                    public int getShareCount() {
                        return shareCount;
                    }

                    public void setShareCount(int shareCount) {
                        this.shareCount = shareCount;
                    }

                    public int getReplyCount() {
                        return replyCount;
                    }

                    public void setReplyCount(int replyCount) {
                        this.replyCount = replyCount;
                    }
                }

                public static class ProviderBean {
                    /**
                     * name : Vimeo
                     * alias : vimeo
                     * icon : http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png
                     */

                    private String name;
                    private String alias;
                    private String icon;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAlias() {
                        return alias;
                    }

                    public void setAlias(String alias) {
                        this.alias = alias;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public static class AuthorBean {
                    /**
                     * id : 2170
                     * icon : http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg
                     * name : 开眼动画精选
                     * description : 有趣的人永远不缺童心
                     * link :
                     * latestReleaseTime : 1557018000000
                     * videoNum : 735
                     * adTrack : null
                     * follow : {"itemType":"author","itemId":2170,"followed":false}
                     * shield : {"itemType":"author","itemId":2170,"shielded":false}
                     * approvedNotReadyVideoCount : 0
                     * ifPgc : true
                     * recSort : 0
                     * expert : false
                     */

                    private int id;
                    private String icon;
                    private String name;
                    private String description;
                    private String link;
                    private long latestReleaseTime;
                    private int videoNum;
                    private Object adTrack;
                    private DataBean.AuthorBean.FollowBean follow;
                    private DataBean.AuthorBean.ShieldBean shield;
                    private int approvedNotReadyVideoCount;
                    private boolean ifPgc;
                    private int recSort;
                    private boolean expert;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getLink() {
                        return link;
                    }

                    public void setLink(String link) {
                        this.link = link;
                    }

                    public long getLatestReleaseTime() {
                        return latestReleaseTime;
                    }

                    public void setLatestReleaseTime(long latestReleaseTime) {
                        this.latestReleaseTime = latestReleaseTime;
                    }

                    public int getVideoNum() {
                        return videoNum;
                    }

                    public void setVideoNum(int videoNum) {
                        this.videoNum = videoNum;
                    }

                    public Object getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(Object adTrack) {
                        this.adTrack = adTrack;
                    }

                    public DataBean.AuthorBean.FollowBean getFollow() {
                        return follow;
                    }

                    public void setFollow(DataBean.AuthorBean.FollowBean follow) {
                        this.follow = follow;
                    }

                    public DataBean.AuthorBean.ShieldBean getShield() {
                        return shield;
                    }

                    public void setShield(DataBean.AuthorBean.ShieldBean shield) {
                        this.shield = shield;
                    }

                    public int getApprovedNotReadyVideoCount() {
                        return approvedNotReadyVideoCount;
                    }

                    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                    }

                    public boolean isIfPgc() {
                        return ifPgc;
                    }

                    public void setIfPgc(boolean ifPgc) {
                        this.ifPgc = ifPgc;
                    }

                    public int getRecSort() {
                        return recSort;
                    }

                    public void setRecSort(int recSort) {
                        this.recSort = recSort;
                    }

                    public boolean isExpert() {
                        return expert;
                    }

                    public void setExpert(boolean expert) {
                        this.expert = expert;
                    }

                    public static class FollowBean {
                        /**
                         * itemType : author
                         * itemId : 2170
                         * followed : false
                         */

                        private String itemType;
                        private int itemId;
                        private boolean followed;

                        public String getItemType() {
                            return itemType;
                        }

                        public void setItemType(String itemType) {
                            this.itemType = itemType;
                        }

                        public int getItemId() {
                            return itemId;
                        }

                        public void setItemId(int itemId) {
                            this.itemId = itemId;
                        }

                        public boolean isFollowed() {
                            return followed;
                        }

                        public void setFollowed(boolean followed) {
                            this.followed = followed;
                        }
                    }

                    public static class ShieldBean {
                        /**
                         * itemType : author
                         * itemId : 2170
                         * shielded : false
                         */

                        private String itemType;
                        private int itemId;
                        private boolean shielded;

                        public String getItemType() {
                            return itemType;
                        }

                        public void setItemType(String itemType) {
                            this.itemType = itemType;
                        }

                        public int getItemId() {
                            return itemId;
                        }

                        public void setItemId(int itemId) {
                            this.itemId = itemId;
                        }

                        public boolean isShielded() {
                            return shielded;
                        }

                        public void setShielded(boolean shielded) {
                            this.shielded = shielded;
                        }
                    }
                }

                public static class CoverBean {
                    /**
                     * feed : http://img.kaiyanapp.com/472fb023ef1afb49a42507352da74111.png?imageMogr2/quality/60/format/jpg
                     * detail : http://img.kaiyanapp.com/472fb023ef1afb49a42507352da74111.png?imageMogr2/quality/60/format/jpg
                     * blurred : http://img.kaiyanapp.com/122009cf95c4deefa8432027bb084280.png?imageMogr2/quality/60/format/jpg
                     * sharing : null
                     * homepage : http://img.kaiyanapp.com/472fb023ef1afb49a42507352da74111.png?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                     */

                    private String feed;
                    private String detail;
                    private String blurred;
                    private Object sharing;
                    private String homepage;

                    public String getFeed() {
                        return feed;
                    }

                    public void setFeed(String feed) {
                        this.feed = feed;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public void setDetail(String detail) {
                        this.detail = detail;
                    }

                    public String getBlurred() {
                        return blurred;
                    }

                    public void setBlurred(String blurred) {
                        this.blurred = blurred;
                    }

                    public Object getSharing() {
                        return sharing;
                    }

                    public void setSharing(Object sharing) {
                        this.sharing = sharing;
                    }

                    public String getHomepage() {
                        return homepage;
                    }

                    public void setHomepage(String homepage) {
                        this.homepage = homepage;
                    }
                }

                public static class WebUrlBean {
                    /**
                     * raw : http://www.eyepetizer.net/detail.html?vid=158952
                     * forWeibo : http://www.eyepetizer.net/detail.html?vid=158952&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
                     */

                    private String raw;
                    private String forWeibo;

                    public String getRaw() {
                        return raw;
                    }

                    public void setRaw(String raw) {
                        this.raw = raw;
                    }

                    public String getForWeibo() {
                        return forWeibo;
                    }

                    public void setForWeibo(String forWeibo) {
                        this.forWeibo = forWeibo;
                    }
                }

                public static class TagsBean {
                    /**
                     * id : 14
                     * name : 动画梦工厂
                     * actionUrl : eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB%E6%A2%A6%E5%B7%A5%E5%8E%82
                     * adTrack : null
                     * desc : 有趣的人永远不缺童心
                     * bgPicture : http://img.kaiyanapp.com/afb9e7d7f061d10ade5ebcb524dc8679.jpeg?imageMogr2/quality/60/format/jpg
                     * headerImage : http://img.kaiyanapp.com/f9eae3e0321fa1e99a7b38641b5536a2.jpeg?imageMogr2/quality/60/format/jpg
                     * tagRecType : IMPORTANT
                     * childTagList : null
                     * childTagIdList : null
                     * communityIndex : 0
                     */

                    private int id;
                    private String name;
                    private String actionUrl;
                    private Object adTrack;
                    private String desc;
                    private String bgPicture;
                    private String headerImage;
                    private String tagRecType;
                    private Object childTagList;
                    private Object childTagIdList;
                    private int communityIndex;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getActionUrl() {
                        return actionUrl;
                    }

                    public void setActionUrl(String actionUrl) {
                        this.actionUrl = actionUrl;
                    }

                    public Object getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(Object adTrack) {
                        this.adTrack = adTrack;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public String getBgPicture() {
                        return bgPicture;
                    }

                    public void setBgPicture(String bgPicture) {
                        this.bgPicture = bgPicture;
                    }

                    public String getHeaderImage() {
                        return headerImage;
                    }

                    public void setHeaderImage(String headerImage) {
                        this.headerImage = headerImage;
                    }

                    public String getTagRecType() {
                        return tagRecType;
                    }

                    public void setTagRecType(String tagRecType) {
                        this.tagRecType = tagRecType;
                    }

                    public Object getChildTagList() {
                        return childTagList;
                    }

                    public void setChildTagList(Object childTagList) {
                        this.childTagList = childTagList;
                    }

                    public Object getChildTagIdList() {
                        return childTagIdList;
                    }

                    public void setChildTagIdList(Object childTagIdList) {
                        this.childTagIdList = childTagIdList;
                    }

                    public int getCommunityIndex() {
                        return communityIndex;
                    }

                    public void setCommunityIndex(int communityIndex) {
                        this.communityIndex = communityIndex;
                    }
                }

                public static class PlayInfoBean {
                    /**
                     * height : 480
                     * width : 854
                     * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=158952&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss","size":17638370},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=158952&resourceType=video&editionType=normal&source=qcloud&playUrlType=url_oss","size":17638370},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=158952&resourceType=video&editionType=normal&source=ucloud&playUrlType=url_oss","size":17638370}]
                     * name : 标清
                     * type : normal
                     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=158952&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss
                     */

                    private int height;
                    private int width;
                    private String name;
                    private String type;
                    private String url;
                    private List<DataBean.PlayInfoBean.UrlListBean> urlList;

                    @Override
                    public String toString() {
                        return "PlayInfoBean{" +
                                "height=" + height +
                                ", width=" + width +
                                ", name='" + name + '\'' +
                                ", type='" + type + '\'' +
                                ", url='" + url + '\'' +
                                ", urlList=" + urlList +
                                '}';
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public List<DataBean.PlayInfoBean.UrlListBean> getUrlList() {
                        return urlList;
                    }

                    public void setUrlList(List<DataBean.PlayInfoBean.UrlListBean> urlList) {
                        this.urlList = urlList;
                    }

                    public static class UrlListBean {
                        /**
                         * name : aliyun
                         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=158952&resourceType=video&editionType=normal&source=aliyun&playUrlType=url_oss
                         * size : 17638370
                         */

                        private String name;
                        private String url;
                        private int size;

                        @Override
                        public String toString() {
                            return "UrlListBean{" +
                                    "name='" + name + '\'' +
                                    ", url='" + url + '\'' +
                                    ", size=" + size +
                                    '}';
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public int getSize() {
                            return size;
                        }

                        public void setSize(int size) {
                            this.size = size;
                        }
                    }
                }
            }
        }
    }
}
