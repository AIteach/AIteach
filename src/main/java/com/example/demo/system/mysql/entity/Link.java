/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Link { //连接类
    private int source;
    private int target;
    private String value;

}

