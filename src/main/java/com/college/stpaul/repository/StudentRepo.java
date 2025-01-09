package com.college.stpaul.repository;

import java.util.List;

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
    WHERE (:query IS NULL OR (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%))
      AND (:result IS NULL OR s.result = :result)
      AND (:currentClass IS NULL OR s.currentClass = :currentClass)
      AND (:session IS NULL OR session=:session)
    """)
    List<Student> searchStudents(
        @Param("query") String query,
        @Param("result") Result result,
        @Param("currentClass") String currentClass,
        @Param("session") String session,
        Pageable pageable);

    // single combination 

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%")
    List<Student> searchStudentByNameAndEmail(@Param("query")String query ,Pageable pageable );

    @Query("SELECT s FROM Student s WHERE s.result=:result")
    List<Student> findStudentBasedONResult(@Param("result")Result result,Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.currentClass=:currentClass")
    List<Student> findStudentBasedONClass(@Param("currentClass")String currentClass,Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.session=:session")
    List<Student> findStudentBasedONSession(@Param("session")String session,Pageable pageable);


    // duo combination 

    @Query("SELECT s FROM Student s WHERE (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%) AND s.result=:result")
    List<Student> searchStudentByNameAndEmailAndResult(@Param("query")String query,@Param("result")Result result ,Pageable pageable );

    @Query("SELECT s FROM Student s WHERE (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%) AND s.currentClass=:currentClass")
    List<Student> searchStudentByNameAndEmailAndClass(@Param("query")String query,@Param("currentClass")String currentClass ,Pageable pageable );

    @Query("SELECT s FROM Student s WHERE (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%) AND s.session=:session")
    List<Student> searchStudentByNameAndEmailAndSession(@Param("query")String query,@Param("session")String session ,Pageable pageable );

    @Query("SELECT s FROM Student s WHERE s.result=:result AND s.currentClass=:currentClass")
    List<Student> findStudentBasedONResultAndClass(@Param("result")Result result,@Param("currentClass")String currentClass,Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.result=:result AND s.session=:session")
    List<Student> findStudentBasedONResultAndSession(@Param("result")Result result,@Param("session")String session,Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.currentClass=:currentClass AND s.session=:session")
    List<Student> findStudentBasedONClassAndSession(@Param("currentClass")String currentClass,@Param("session")String session,Pageable pageable);

    // trio combinations

    @Query("SELECT s FROM Student s WHERE (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%)"+ 
    "AND s.result=:result AND s.currentClass=:currentClass")
    List<Student> searchStudentByNameAndEmailAndResultAndClass(@Param("query")String query,@Param("result")Result result,@Param("currentClass")String currentClass,Pageable pageable );

    @Query("SELECT s FROM Student s WHERE (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%)"+
    "AND s.result=:result AND s.session=:session")
    List<Student> searchStudentByNameAndEmailAndResultAndSession(@Param("query")String query,@Param("result")Result result,@Param("session")String session ,Pageable pageable );

    @Query("SELECT s FROM Student s WHERE s.currentClass=:currentClass AND s.result=:result AND s.session=:session")
    List<Student> searchStudentByResultAndClassAndSession(@Param("result")Result result,@Param("currentClass")String currentClass,@Param("session")String session ,Pageable pageable );
    // All combination 

    @Query("SELECT s FROM Student s WHERE (s.firstName LIKE %:query% OR s.sirName LIKE %:query% OR s.email LIKE %:query%)"+ 
    "AND s.result=:result AND s.currentClass=:currentClass AND s.session =:session")
    List<Student> searchStudentByNameAndEmailAndResultAndClassAndSession(@Param("query")String query,@Param("result")Result result,@Param("currentClass")String currentClass,@Param("session")String session,Pageable pageable );
}
