package com.college.stpaul.repository;

import java.util.List;

import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.college.stpaul.constants.Result;
import com.college.stpaul.entities.Student;

public interface StudentRepo extends JpaRepository<Student,Long>{

    @Query("""
    SELECT s 
    FROM Student s 
    WHERE (:query IS NULL OR (s.firstName LIKE %:query% OR s.surname LIKE %:query% OR s.email LIKE %:query%))
      AND (:result IS NULL OR s.result = :result)
      AND (:currentClass IS NULL OR s.currentClass = :currentClass)
      AND (:session IS NULL OR session=:session)
      AND (:section IS NULL OR section=:section)
    """)
    List<Student> searchStudents(
        @Param("query") String query,
        @Param("result") Result result,
        @Param("currentClass") String currentClass,
        @Param("session") String session,
        @Param("section") String section,
        Pageable pageable);

      
        @Query("""
          SELECT s 
          FROM Student s 
          WHERE (:result IS NULL OR s.result = :result)
            AND (:currentClass IS NULL OR s.currentClass = :currentClass)
            AND (:session IS NULL OR session=:session)
            AND (:section IS NULL OR section=:section)
          """)
          List<Student> exportStudents(
              @Param("result") Result result,
              @Param("currentClass") String currentClass,
              @Param("session") String session,
              @Param("section") String section
            );


    @Query("""
              SELECT s 
              FROM Student s
              WHERE s.result=:result
              AND(:query IS NULL OR (s.firstName LIKE %:query% OR s.surname LIKE %:query% OR s.email LIKE %:query%))
              AND (:currentClass IS NULL OR s.currentClass = :currentClass)
              AND (:session IS NULL OR session=:session)
              AND (:section IS NULL OR section=:section)
        """)
    List<Student> getFailedStudents(@Param("result")Result result,
                                    @Param("query")String query,
                                    @Param("currentClass")String currentClass,
                                    @Param("session")String session,
                                    @Param("section")String section,
                                    Pageable pageable);

    @Query("SELECT COUNT(s) FROM Student s")
    long countAllStudents();
   
}
