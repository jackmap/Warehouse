package com.example.stock.entity;

import java.io.Serializable;

/**
 *
 * @Description:货架实体
 * @author:     ays
 * @date:        2016年10月12日 下午8:41:02
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class TErpShelf implements Serializable {
    private Long shelfId;

    private Long whouseId;

    private String shelfName;

    private String shelfCode;

    private Integer line;

    private String shousePrefix;

    private Integer shouseCount;

    private Integer tier;

    private Integer inventoryCount;

    private Short state;

    private Long enterpriseId;

    private String connector;



}