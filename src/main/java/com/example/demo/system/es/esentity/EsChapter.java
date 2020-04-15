package com.example.demo.system.es.esentity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author 84271
 */
@Data
@ToString
@Document(indexName = "chapter")
public class EsChapter implements Serializable {
    @Id
    private int chapterId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String chapterName;
    private int courseId;

    private int nodeId;
    private int createrId;
    private String chapterDesc;


}
