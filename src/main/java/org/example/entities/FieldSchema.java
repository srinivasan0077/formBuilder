package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class FieldSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldType fieldType;

    @Column(nullable = false)
    private boolean uniqueField=false;

    @Column(nullable = false)
    private boolean required=false;

    public enum FieldType{
        STRING,
        INTEGER,
        LONG,
        BOOLEAN,
        EMAIL,
        PHONE,
        DECIMAL,
        PICKLIST
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUniqueField() {
        return uniqueField;
    }

    public void setUniqueField(boolean uniqueField) {
        this.uniqueField = uniqueField;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
