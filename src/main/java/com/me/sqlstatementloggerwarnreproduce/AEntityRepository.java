package com.me.sqlstatementloggerwarnreproduce;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AEntityRepository extends JpaRepository<AEntity, Long> {

  @Query(value = """
      (SELECT * FROM a_entity a1 ORDER BY id ASC LIMIT 100)
      UNION
      (SELECT * FROM a_entity a2 ORDER BY id ASC LIMIT 100)
      LIMIT 100
      """, nativeQuery = true)
  List<AEntity> findWithNativeQuery();
}
