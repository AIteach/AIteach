/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.es.esentity;

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
@Document(indexName = "node")
public class EsNode implements Serializable {  //结点信息
    @Id
    private int id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    private String value;
    private int category;
    private int symbolSize;
    private String url = "/login";
}
