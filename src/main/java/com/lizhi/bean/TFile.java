package com.lizhi.bean;


public class TFile extends CustomEntity<String>{

    public static final int SOURCE_WEB = 0;

    public static final int SOURCE_LOCAL = 1;

    //文件路径
    private String fileUrl;

    //文件类型
    private String fileType;

    //文件md5值，没有则为空
    private String md5;

    //文件创建人
    private String creatorUserid;

    //文件对应的腾讯桶中有几个对象使用同一个文件
    private Integer  linknum;

    //文件来源.web 互联网，在文件桶上没有存储的
    private Integer  source = SOURCE_WEB;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public void setLinknum(Integer linknum) {
        this.linknum = linknum;
    }

    public Integer getLinknum() {
        return linknum;
    }

    public String getFileUrl() {
        return fileUrl;
    }


    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }


    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getCreatorUserid() {
        return creatorUserid;
    }

    public void setCreatorUserid(String creatorUserid) {
        this.creatorUserid = creatorUserid;
    }

    @Override
    public String toString() {
        return "TFile{" +
                "fileUrl='" + fileUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                ", md5='" + md5 + '\'' +
                ", creatorUserid='" + creatorUserid + '\'' +
                ", linknum=" + linknum +
                ", source=" + source +
                '}';
    }
}