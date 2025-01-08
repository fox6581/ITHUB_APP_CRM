package com.example.demo.repository;

import com.example.demo.model.Master;
import com.example.demo.model.MasterQualificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterQualificationServiceRepository extends JpaRepository<MasterQualificationService, Long> {

//    @Query("SELECT ms.masterQualification.master, ms.masterQualification.qualification.name, ms.price, ms.estimatedTime " +
//            "FROM MasterQualificationService ms " +
//            "WHERE ms.service.id = :serviceId")

    @Query("SELECT ms.masterQualification.master, ms.masterQualification.qualification, ms.price, ms.estimatedTime " +
            "FROM MasterQualificationService ms " +
            "WHERE ms.service.id = :serviceId")
    List<Object[]> findMastersWithServiceDetailsByServiceId(@Param("serviceId") Long serviceId);
}

