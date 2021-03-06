/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.mysql.entity;

import com.example.demo.system.es.esentity.EsNode;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "node")
public class Node implements Serializable {  //结点信息
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int category;
    private String name;
    private String value;
    private int symbolSize;
    private String url = "/login";

    public Node(String name, int category, int symbolSize, String url, int nodeId) {
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
        this.url = url;
        this.id = nodeId;
    }

    public Node(String name, int category, int symbolSize, String url) {
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
        this.url = url;
    }

    public  EsNode toEs() {
        EsNode esNode = new EsNode();
        esNode.setValue(this.value);
        esNode.setUrl(this.url);
        esNode.setSymbolSize(this.symbolSize);
        esNode.setName(this.name);
        esNode.setId(this.id);
        esNode.setCategory(this.category);
        return esNode;
    }

}
