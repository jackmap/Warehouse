package com.example.stock.entity;

import java.io.Serializable;



/**
 * @Description:仓位实体
 */
public class TErpSpace  implements Serializable {
    private Long spaceId;

    private Long shelfId;

    private String spaceCode;

    private Integer line;

    private Integer tier;

    private Integer inventoryCount;

    private String sukDescription;

    private Short state;

    private Long enterpriseId;
}