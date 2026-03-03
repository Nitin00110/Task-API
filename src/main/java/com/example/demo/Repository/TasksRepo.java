package com.example.demo.Repository;


import com.example.demo.Entities.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<Tasks,Long> {
    Page<Tasks> findByQuadrant(String quadrant,
                               Pageable pageable);
}
