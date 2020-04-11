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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "linking")
public class Linking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int preId;
    private int rearId;
    private String relationship;
    private Date creatime;
    private int type; //0为关系.1为信息
    private String ResourceURl;
    private String value;

    public Linking(int preId, int rearId, int type, String value) {
        super();
        this.preId = preId;
        this.rearId = rearId;
        this.type = type;
        this.value = value;
        this.creatime = new Date();

    }
}
