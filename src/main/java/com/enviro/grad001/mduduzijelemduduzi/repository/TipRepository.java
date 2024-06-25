package com.enviro.grad001.mduduzijelemduduzi.repository;

import com.enviro.grad001.mduduzijelemduduzi.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {
}
