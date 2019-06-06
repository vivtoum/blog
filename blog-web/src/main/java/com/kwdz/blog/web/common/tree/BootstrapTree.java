package com.kwdz.blog.web.common.tree;

import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/6 14:59
 */
public class BootstrapTree {

    /**
     * text : Node 1
     * icon : glyphicon glyphicon-stop
     * selectedIcon : glyphicon glyphicon-stop
     * color : #000000
     * backColor : #FFFFFF
     * href : #node-1
     * selectable : true
     * state : {"checked":true,"disabled":true,"expanded":true,"selected":true}
     * tags : ["available"]
     * nodes : [{}]
     */

    private String text;
    private String icon;
    private String selectedIcon;
    private String color;
    private String backColor;
    private String href;
    private boolean selectable;
    private StateBean state;
    private List<String> tags;
    private List<NodesBean> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<NodesBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBean> nodes) {
        this.nodes = nodes;
    }

    public static class StateBean {
        /**
         * checked : true
         * disabled : true
         * expanded : true
         * selected : true
         */

        private boolean checked;
        private boolean disabled;
        private boolean expanded;
        private boolean selected;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    public static class NodesBean {
    }
}
