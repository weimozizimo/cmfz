package com.baizhi.cmfz.entity;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/12 19:04.
 */
public class Permission {
    private Integer permissionId;
    private String resourceName;
    private String resourceTag;
    private String resourceUrl;
    private String permissionTag;


    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceTag='" + resourceTag + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", permissionTag='" + permissionTag + '\'' +
                '}';
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceTag() {
        return resourceTag;
    }

    public void setResourceTag(String resourceTag) {
        this.resourceTag = resourceTag;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getPermissionTag() {
        return permissionTag;
    }

    public void setPermissionTag(String permissionTag) {
        this.permissionTag = permissionTag;
    }

    public Permission() {
    }

    public Permission(Integer permissionId, String resourceName, String resourceTag, String resourceUrl, String permissionTag) {
        this.permissionId = permissionId;
        this.resourceName = resourceName;
        this.resourceTag = resourceTag;
        this.resourceUrl = resourceUrl;
        this.permissionTag = permissionTag;
    }
}
