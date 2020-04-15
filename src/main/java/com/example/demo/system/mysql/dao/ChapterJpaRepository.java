package com.example.demo.system.mysql.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Chapter;

import java.util.List;

/**
 * @author 84271
 */
public interface ChapterJpaRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByCourseId(int courseId);

}
