package com.sitop.smart365.dataservice.model;

import javax.persistence.*;

public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 品类
     */
    private String category;

    /**
     * 品名
     */
    private String name;

    /**
     * 型号
     */
    private String model;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取品牌
     *
     * @return brand - 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌
     *
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取品类
     *
     * @return category - 品类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置品类
     *
     * @param category 品类
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取品名
     *
     * @return name - 品名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置品名
     *
     * @param name 品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取型号
     *
     * @return model - 型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置型号
     *
     * @param model 型号
     */
    public void setModel(String model) {
        this.model = model;
    }
}