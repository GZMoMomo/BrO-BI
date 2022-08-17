package com.bro.bisystem.bi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author BrO
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DbList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据库地址
     */
    @TableField(value = "`host`")
    private String host;

    /**
     * 数据库端口
     */
    @TableField(value = "`port`")
    private String port;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 数据库名称
     */
    @TableField(value = "`database`")
    private String database;

    /**
     * 构造JDBC URL
     *
     * @return JDBC URL
     */
    public String buildJdbcUrl() {
        return String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false", this.host, this.port, this.database);
    }
}
