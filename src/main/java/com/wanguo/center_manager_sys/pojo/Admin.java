package com.wanguo.center_manager_sys.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 描述：
 *
 * @author Badguy
 */
@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户名'")
    @NotBlank(message = "账号不能为空")
    private String username;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '密码'")
    @NotBlank(message = "密码不能为空")
    private String password;
}
