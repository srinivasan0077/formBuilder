package org.example.services;

import org.example.entities.FieldSchema;
import org.example.entities.FormSchema;
import org.example.repositories.FieldSchemaRepository;
import org.example.repositories.FormSchemaRepository;
import org.example.utils.ProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormSchemaService {
    @Autowired
    private FormSchemaRepository repository;

    @Autowired
    private FieldSchemaRepository fieldSchemaRepository;

    @Autowired
    private ProjectHelper projectHelper;

    public FormSchema createFormSchema(FormSchema formSchema) throws Exception {
        if(formSchema.getFieldSchemas()!=null && formSchema.getFieldSchemas().size()>50){
            throw new Exception("Field Collection should not exceed 50!");
        }
        return repository.save(formSchema);
    }

    public FormSchema updateFormSchema(FormSchema formSchema) throws Exception {
        FormSchema formSchemaFromDB=repository.findById(formSchema.getId()).orElse(null);
        if(formSchemaFromDB==null){
            throw new Exception("Form Schema Not Found!");
        }else if(formSchema.getFieldSchemas()!=null && formSchema.getFieldSchemas().size()>5){
            throw new Exception("Field Collection should not exceed 50!");
        }
        projectHelper.merge(formSchemaFromDB,formSchema);
        return repository.save(formSchemaFromDB);
    }

    public FormSchema getFormSchema(Long id){
        Optional<FormSchema> formSchemaOptional=repository.findById(id);
        return formSchemaOptional.orElse(null);
    }

    public FieldSchema createFieldSchema(FieldSchema fieldSchema){
        return fieldSchemaRepository.save(fieldSchema);
    }

    public FieldSchema getFieldSchema(Long id){
        Optional<FieldSchema> fieldSchemaOptional=fieldSchemaRepository.findById(id);
        return fieldSchemaOptional.orElse(null);
    }

    public List<FieldSchema> getFieldSchemasByFormId(Long id,Pageable pageable){
        return repository.findFieldSchemasByFormId(id,pageable);
    }

    public List<FieldSchema> getFieldSchemas(Pageable pageable){
        return fieldSchemaRepository.findAll(pageable).getContent();
    }

    public List<FormSchema> getFormSchemas(Pageable pageable){
        return repository.findAll(pageable).getContent();
    }

}
