package com.lizhi.bean;

public class BlogLabel extends CustomEntity<String> {

    /** 标签名 */
    private String lebelName ;

    /**父级id*/
    private String parentId ;

    /**父级id*/
    private String articleId ;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getLebelName() {
        return lebelName;
    }

    public void setLebelName(String lebelName) {
        this.lebelName = lebelName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "BlogLabel{" +
                "lebelName='" + lebelName + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}