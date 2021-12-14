package com.ms.commons.enums;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
public enum StatusEnum {
    A("这是测试",111);
    private final String message;
    private final Integer code;
}
