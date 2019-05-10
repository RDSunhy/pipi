package com.study.shy.pipi.bean;

import java.util.List;

public class CategoryInfo {

    /**
     * categoryInfo : {"dataType":"CategoryInfo","id":36,"name":"生活","description":"匠心、健康、生活感悟","headerImage":"http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png","actionUrl":"eyepetizer://category/36/?title=%E7%94%9F%E6%B4%BB","follow":{"itemType":"category","itemId":36,"followed":false}}
     * tabInfo : {"tabList":[{"id":0,"name":"首页","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=36","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"全部","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=36","tabType":0,"nameType":0,"adTrack":null},{"id":2,"name":"作者","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/pgcs?id=36","tabType":0,"nameType":0,"adTrack":null},{"id":3,"name":"专辑","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/playlist?id=36","tabType":0,"nameType":0,"adTrack":null}],"defaultIdx":0}
     * tagInfo : null
     */

    private CategoryInfoBean categoryInfo;
    private TabInfoBean tabInfo;
    private Object tagInfo;

    public CategoryInfoBean getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfoBean categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public TabInfoBean getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfoBean tabInfo) {
        this.tabInfo = tabInfo;
    }

    public Object getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(Object tagInfo) {
        this.tagInfo = tagInfo;
    }

    public static class CategoryInfoBean {
        /**
         * dataType : CategoryInfo
         * id : 36
         * name : 生活
         * description : 匠心、健康、生活感悟
         * headerImage : http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png
         * actionUrl : eyepetizer://category/36/?title=%E7%94%9F%E6%B4%BB
         * follow : {"itemType":"category","itemId":36,"followed":false}
         */

        private String dataType;
        private int id;
        private String name;
        private String description;
        private String headerImage;
        private String actionUrl;
        private FollowBean follow;

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

        public String getHeaderImage() {
            return headerImage;
        }

        public void setHeaderImage(String headerImage) {
            this.headerImage = headerImage;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public FollowBean getFollow() {
            return follow;
        }

        public void setFollow(FollowBean follow) {
            this.follow = follow;
        }

        public static class FollowBean {
            /**
             * itemType : category
             * itemId : 36
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
    }

    public static class TabInfoBean {
        /**
         * tabList : [{"id":0,"name":"首页","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=36","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"全部","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=36","tabType":0,"nameType":0,"adTrack":null},{"id":2,"name":"作者","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/pgcs?id=36","tabType":0,"nameType":0,"adTrack":null},{"id":3,"name":"专辑","apiUrl":"http://baobab.kaiyanapp.com/api/v4/categories/detail/playlist?id=36","tabType":0,"nameType":0,"adTrack":null}]
         * defaultIdx : 0
         */

        private int defaultIdx;
        private List<TabListBean> tabList;

        public int getDefaultIdx() {
            return defaultIdx;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public List<TabListBean> getTabList() {
            return tabList;
        }

        public void setTabList(List<TabListBean> tabList) {
            this.tabList = tabList;
        }

        public static class TabListBean {
            /**
             * id : 0
             * name : 首页
             * apiUrl : http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=36
             * tabType : 0
             * nameType : 0
             * adTrack : null
             */

            private int id;
            private String name;
            private String apiUrl;
            private int tabType;
            private int nameType;
            private Object adTrack;

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

            public String getApiUrl() {
                return apiUrl;
            }

            public void setApiUrl(String apiUrl) {
                this.apiUrl = apiUrl;
            }

            public int getTabType() {
                return tabType;
            }

            public void setTabType(int tabType) {
                this.tabType = tabType;
            }

            public int getNameType() {
                return nameType;
            }

            public void setNameType(int nameType) {
                this.nameType = nameType;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }
        }
    }
}
