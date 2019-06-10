package com.kwdz.blog.web.common.tree;

import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/6 15:31
 */
public class ResignationMeeting {
    /**
     * id : work_hard
     * title : 工作量/工作性质
     * items : [{"name":"工作量太重"},{"name":"工作量不足"},{"name":"工作压力过大"},{"name":"工作郁闷/内容乏味"},{"name":"工作范围太窄"},{"name":"工作流程繁复冗长"}]
     * detail :
     */

    private String id;
    private String title;
    private String titleEn;
    private String detail;
    private List<ItemsBean> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public static class ItemsBean {
        /**
         * name : 工作量太重
         */

        private String name;
        private String nameEn;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }
    }
}
