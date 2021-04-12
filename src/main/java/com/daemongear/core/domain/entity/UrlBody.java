package com.daemongear.core.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlBody {

    @Id
    private Integer id;

    @NonNull
    private String url;

    private Boolean isDownloaded;
}
