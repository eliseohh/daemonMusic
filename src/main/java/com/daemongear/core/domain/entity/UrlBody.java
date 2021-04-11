package com.daemongear.core.domain.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
public class UrlBody {

    @Id
    private Integer id;

    @NonNull
    private String url;

    @NonNull
    private String videoId;

    private Boolean isDownloaded;
}
