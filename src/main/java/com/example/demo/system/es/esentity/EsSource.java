/**
 * 时间：2018年4月18日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.es.esentity;

import com.example.demo.system.mysql.entity.Source;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@ToString
@Document(indexName = "source_test")
public class EsSource implements Serializable {
    @Id
    private int id;
    private int kgId;
    private int nodeId;
    private int createrId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String sourceName;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String sourceDesc;

    public void change(Source source) {
        this.createrId = source.getCreaterId();
        this.id = source.getId();
        this.kgId = source.getKgId();
        this.nodeId = source.getNodeId();
        this.sourceDesc = source.getSourceDesc();
        this.sourceName = source.getSourceName();
    }

}
