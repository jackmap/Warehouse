package com.example.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @Description:仓库货架实体
 * @author:     ays
 * @date:        2016年10月12日 下午8:41:45
 * Copyright (c) 2016, Sutu. All rights reserved
 */
@SuppressWarnings("serial")
public class TErpWareShelfEnity implements Serializable {
    private Long whouseId;//仓库id

    private BigDecimal price;//金额

    private Long spaceId;//仓位id

    private Integer count;//数量

    private Long goodId;//商品id

    private String SKU;//商品SKU



}