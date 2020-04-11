package com.example.demo.system.mysql.entity;

import com.example.demo.system.es.esentity.EsChapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "chapter_test")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chapterId;
    private String chapterName;
    private int courseId;
    private Date createTime;
    private int nodeId;
    private int createrId;
    private String createrName;
    private String chapterDesc;
    private int linkNum;
    private String chapterRelation;

    public static EsChapter toEsChapter(Chapter chapter) {
        EsChapter esChapter = new EsChapter();
        esChapter.setChapterDesc(chapter.getChapterDesc());
        esChapter.setChapterId(chapter.getChapterId());
        esChapter.setChapterName(chapter.getChapterName());
        esChapter.setCourseId(chapter.getCourseId());
        esChapter.setCreaterId(chapter.getCreaterId());
        esChapter.setNodeId(chapter.getNodeId());
        return esChapter;
    }

}
