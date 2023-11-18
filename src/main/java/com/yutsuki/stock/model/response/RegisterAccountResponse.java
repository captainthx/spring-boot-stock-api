package com.yutsuki.stock.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yutsuki.stock.utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterAccountResponse {
    private long id;
    private String username;
    @JsonSerialize(using = JsonUtils.JsonTimestampSerializer.class)
    private LocalDateTime cdt;
    @JsonSerialize(using = JsonUtils.JsonTimestampSerializer.class)
    private LocalDateTime udt;
}
