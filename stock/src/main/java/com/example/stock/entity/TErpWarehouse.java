package com.example.stock.entity;

import java.io.Serializable;

/**
 * @Description:仓库实体
 */
public class TErpWarehouse  implements Serializable {
    private Long whouseId;

    private String whouseName;

    private Integer whouseCode;

    private Short isDefault;

    private Short property;

    private Integer shelfCount;

    private Integer skuCount;

    private Integer inventoryCount;

    private Short state;

    private Long enterpriseId;

    private String contactName;

    private String contactPhone;

    private String address;
}