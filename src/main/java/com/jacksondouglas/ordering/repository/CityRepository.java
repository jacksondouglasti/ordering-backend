package com.jacksondouglas.ordering.repository;

import com.jacksondouglas.ordering.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT c FROM City c WHERE c.state.id = :stateId ORDER BY c.name")
    List<City> findByStateId(@Param("stateId") Integer stateId);
}
