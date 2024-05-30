package com.doupi.project.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子审核状态枚举
 *
 * @author doupi
 */
public enum InterfaceInfoStatusEnum {

    OFFLINE("待审核", 0),
    ONLINE("通过", 1);


    private final String text;

    private final int value;

    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
