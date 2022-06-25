package com.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PoolDTO implements Serializable {
    private String ip;
    private String name;
    private int port;
    private String server;
    private Boolean success;
}
