package com.cai.campus.push;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caiyufei
 * 2020/3/4
 */
@Data
@Builder
public class PushData {

    @Builder.Default
    private String alert = "";

    @Builder.Default
    private String title = "";

    @Builder.Default
    private Map<String, String> extras = new HashMap<>();

}