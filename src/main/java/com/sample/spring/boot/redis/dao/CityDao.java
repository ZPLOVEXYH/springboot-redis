package com.sample.spring.boot.redis.dao;

import com.sample.spring.boot.redis.domain.City;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityDao {

    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();

    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    City findById(@Param("id") Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
