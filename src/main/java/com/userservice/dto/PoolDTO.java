package com.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoolDTO {
    private String ip;
    private String name;
    private int port;
    private String server;
    private Boolean success;
}
