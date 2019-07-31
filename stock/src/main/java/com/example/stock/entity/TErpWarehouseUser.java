package com.example.stock.entity;

import java.io.Serializable;

/**
 *
 * @Description:仓库用户实体
 * @author:     ays
 * @date:        2016年10月12日 下午8:41:34
 * Copyright (c) 2016, Sutu. All rights reserved
 */
public class TErpWarehouseUser  implements Serializable {
    private Long whUserId;

    private Long whouseId;

    private Long userId;

    private Short state;

    private Long enterpriseId;

    private String contactName;

    private String contactPhone;

    private String address;
}