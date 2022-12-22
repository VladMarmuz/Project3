package com.marmuz.project3.repositories;

import com.marmuz.project3.models.Indications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicationsRepository extends JpaRepository<Indications, Integer> {
}
