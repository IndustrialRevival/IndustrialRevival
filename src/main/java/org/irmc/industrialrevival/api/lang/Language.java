package org.irmc.industrialrevival.api.lang;

import lombok.Getter;

@Getter
public enum Language {
    ZH_CN("zh-CN"),
    ZH_TW("zh-TW"),
    EN_US("en-US");

    private final String code;

    Language(String code) {
        this.code = code;
    }
}
