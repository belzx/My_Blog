package com.lizhi.opration;

import com.lizhi.utils.StringUtil;

public enum ImageEnum {
    gif("gif"),png("png"),jpg("jpg");

    private String name;

    ImageEnum(String name) {
        this.name = name;
    }

    public static boolean isExist(String name) {
        if( StringUtil.isNullOrEmpty(name)){
            return false;
        }

        for (ImageEnum c : ImageEnum.values()) {
            if(c.name.equals(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
