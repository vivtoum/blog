package com.kwdz.blog.api.common.util.tree;

/**
 * 树节点对象，必须实现这个interface，并且实现theId、theParentId方法！
 * 分别返回节点的id，和父节点id
 */
public interface Nodeable {
    /**
     * 返回当前节点id
     */
    public String theId();

    /**
     * 返回当前节点父节点id
     */
    public String theParentId();
}
