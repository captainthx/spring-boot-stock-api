package com.yutsuki.stock.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UploadImageResponse {
    private String urlPath;
    private String imageName;
}
