package com.wanguo.center_manager_sys.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 描述：
 *
 * @author Badguy
 */
@Entity
@Data
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) COMMENT 'appId'")
    @NotBlank(message = "appId不能为空")
    private String appId;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '企业名'")
    @NotBlank(message = "企业名不能为空")
    private String name;

    @Column(columnDefinition = "DOUBLE COMMENT '短信费用'")
    //@NotBlank(message = "短信费用不能为空")
    private Double smsFee;

    @Column(columnDefinition = "BIGINT COMMENT '新增客户数'")
    //@NotBlank(message = "新增客户数不能为空")
    private Long numOfNewCustomer;


}
