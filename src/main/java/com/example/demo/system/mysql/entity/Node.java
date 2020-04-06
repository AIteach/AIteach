/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.mysql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Entity(name = "node")
public class Node {  //结点信息
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int category;
    private String name;
    private String value;
    private int symbolSize;
    private String url = "/login";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(int symbolSize) {
        this.symbolSize = symbolSize;
    }


    public Node() {
    }

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

    public Node(String name, int category, int symbolSize, String url, int nodeId, String value) {
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
        this.url = url;
        this.id = nodeId;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", category=" + category +
                ", symbolSize=" + symbolSize +
                ", url='" + url + '\'' +
                '}';
    }
}
