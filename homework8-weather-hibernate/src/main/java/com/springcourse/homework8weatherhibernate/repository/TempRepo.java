package com.springcourse.homework8weatherhibernate.repository;

import com.springcourse.homework8weatherhibernate.model.Temp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepo extends JpaRepository<Temp, Long> {

}
