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

    public EsKg toEsKg() {
        EsKg esKg = new EsKg();
        esKg.setChapterId(chapterId);
        esKg.setCourseId(courseId);
        esKg.setCreaterId(createrId);
        esKg.setKgId(kgId);
        esKg.setKgDesc(kgDesc);
        esKg.setNodeId(nodeId);
        esKg.setKgName(kgName);
        return esKg;
    }
}
