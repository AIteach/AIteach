/**
 * 时间：2018年4月18日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.mysql.entity;

import com.example.demo.system.es.esentity.EsSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "source")
public class Source implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int kgId;
    private int nodeId;
    private int createrId;
    private String createrName;
    private String sourceName;
    private String sourceUrl;
    private String sourceDesc;
    private String type;

    public EsSource toEsSource() {
        EsSource esSource = new EsSource();
        esSource.setCreaterId(createrId);
        esSource.setId(id);
        esSource.setKgId(kgId);
        esSource.setNodeId(nodeId);
        esSource.setSourceDesc(sourceDesc);
        esSource.setSourceName(sourceName);
        return esSource;
    }

}
