package com.example.demo.system.mysql.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Chapter;

public interface ChapterJpaRepository extends JpaRepository<Chapter, Integer> {


}
