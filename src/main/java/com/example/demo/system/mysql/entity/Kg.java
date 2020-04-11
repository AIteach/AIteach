/**
 * 时间：2018年4月4日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap-1
 */
package com.example.demo.system.mysql.entity;

import com.example.demo.system.es.esentity.EsKg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "kg")
public class Kg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "kgId")
    private int kgId;
    private String kgName;
    private String kgRelation;
    private String kgDesc;
    private int nodeId;
    private int createrId;
    private String createrName;
    private int courseId;
    private int linkNum;
    private Date creatTime;
    private String kgUrl;
    private int kgParid;
    private int chapterId;

    public static EsKg toEsKg(Kg kg) {
        EsKg esKg = new EsKg();
        esKg.setChapterId(kg.getChapterId());
        esKg.setCourseId(kg.getCourseId());
        esKg.setCreaterId(kg.getCreaterId());
        esKg.setKgId(kg.getKgId());
        esKg.setKgDesc(kg.getKgDesc());
        esKg.setNodeId(kg.getNodeId());
        esKg.setKgName(kg.getKgName());
        return esKg;
    }
}
