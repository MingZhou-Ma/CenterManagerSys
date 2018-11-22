package com.wanguo.center_manager_sys.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述：
 *
 * @author Badguy
 */
@Entity
@Data
public class BillFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Integer id;

//    @Column(columnDefinition = "DOUBLE COMMENT '短信费用'")
//    private Double smsFee;
//
//    @Column(columnDefinition = "BIGINT COMMENT '新增客户数'")
//    private Long numOfNewCustomer;

    @Column(columnDefinition = "DOUBLE COMMENT '总费用'")
    private Double totalFee;

    @Column(columnDefinition = "TIMESTAMP COMMENT '账目清零时间'")
    private Date time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id", columnDefinition = "INT COMMENT '所属企业'")
    private Enterprise enterprise;

}
