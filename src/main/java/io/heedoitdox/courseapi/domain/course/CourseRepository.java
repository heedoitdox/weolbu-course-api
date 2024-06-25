package io.heedoitdox.courseapi.domain.course;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select c from Course c where c.id = :id")
  Optional<Course> findByIdWithPessimisticLock(@Param("id") Long id);

}
