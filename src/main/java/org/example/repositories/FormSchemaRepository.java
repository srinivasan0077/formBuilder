package org.example.repositories;

import org.example.entities.FieldSchema;
import org.example.entities.FormSchema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormSchemaRepository extends JpaRepository<FormSchema,Long> {

    @Query("SELECT fs FROM FormSchema f JOIN f.fieldSchemas fs WHERE f.id = :id")
    List<FieldSchema> findFieldSchemasByFormId(@Param("id") Long id, Pageable pageable);

}
