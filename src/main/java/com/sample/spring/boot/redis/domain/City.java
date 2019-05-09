package com.sample.spring.boot.redis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;
    private Integer provinceId;
    private String cityName;
    private String description;

}
