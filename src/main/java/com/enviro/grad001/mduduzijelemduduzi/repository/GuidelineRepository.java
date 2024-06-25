package com.enviro.grad001.mduduzijelemduduzi.repository;

import com.enviro.grad001.mduduzijelemduduzi.model.Guideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidelineRepository extends JpaRepository<Guideline, Long> {
}
