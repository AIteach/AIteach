/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.es.esentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Administrator
 */
@Document(indexName = "node")
public class EsNode {  //结点信息
    @Id
    private int id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    private String value;
    private int category;
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

    /**
     * 2
     *
     * @param name
     * @param value
     * @param category
     * @param symbolSize
     */
    public EsNode(String name, int category, int symbolSize, String url, int nodeId) {
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
        this.url = url;
        this.id = nodeId;
    }

    public EsNode(String name, int category, int symbolSize, String url) {
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
        this.url = url;

    }

    public EsNode(String name, int category, int symbolSize, String url, int nodeId, String value) {
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
        this.url = url;
        this.id = nodeId;
        this.value = value;
    }

    /**
     *
     */
    public EsNode() {
    }

    @Override
    public String toString() {
        return "Node [id=" + id + ", name=" + name + ", value=" + value + ", category=" + category + ", symbolSize="
                + symbolSize + ", url=" + url + "]";
    }

}
