package org.example.controller;

import org.example.entities.FieldSchema;
import org.example.entities.FormSchema;
import org.example.services.FormSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RequestHandler {

    @Autowired
    private FormSchemaService formSchemaService;

    @GetMapping("/process")
    @ResponseBody
    public ResponseEntity<String> sendResponse(){
        return new ResponseEntity<String>("Code Works! Good Job",HttpStatus.OK);
    }

    @PostMapping(value="api/v1/form_schema",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<FormSchema> createFormSchema(@RequestBody FormSchema formSchema) throws Exception {
        FormSchema resultFormSchema=formSchemaService.createFormSchema(formSchema);
        return new ResponseEntity<FormSchema>(resultFormSchema,HttpStatus.OK);
    }

    @PostMapping(value="api/v1/form_schema/{id}",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<FormSchema> editFormSchema(@PathVariable("id") Long id,@RequestBody FormSchema formSchema) throws Exception {
        formSchema.setId(id);
        FormSchema resultFormSchema=formSchemaService.updateFormSchema(formSchema);
        return new ResponseEntity<FormSchema>(resultFormSchema,HttpStatus.OK);
    }

    @PostMapping(value = "api/v1/field_schema",consumes = "application/json")
    @ResponseBody
    public ResponseEntity<FieldSchema> createFieldSchema(@RequestBody FieldSchema fieldSchema){
        FieldSchema resultFieldSchema=formSchemaService.createFieldSchema(fieldSchema);
        return new ResponseEntity<FieldSchema>(resultFieldSchema,HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/field_schema/{id}")
    @ResponseBody
    public ResponseEntity<FieldSchema> getFieldSchema(@PathVariable("id") Long id){
        FieldSchema resultFieldSchema=formSchemaService.getFieldSchema(id);
        return new ResponseEntity<FieldSchema>(resultFieldSchema,HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/form_schema/{id}")
    @ResponseBody
    public ResponseEntity<FormSchema> getFormSchema(@PathVariable("id") Long id){
        FormSchema resultFormSchema=formSchemaService.getFormSchema(id);
        return new ResponseEntity<FormSchema>(resultFormSchema,HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/form_schema/{id}/field_schemas")
    @ResponseBody
    public ResponseEntity<List<FieldSchema>> getFieldSchemasUnderForm(@PathVariable("id") Long id,@RequestParam(defaultValue = "0",name="page") Integer page,@RequestParam(defaultValue = "2",name="count") Integer count){
        List<FieldSchema> resultFieldSchemas=formSchemaService.getFieldSchemasByFormId(id,PageRequest.of(page,count));
        return new ResponseEntity<List<FieldSchema>>(resultFieldSchemas,HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/form_schema")
    @ResponseBody
    public ResponseEntity<List<FormSchema>> getFormSchema(@RequestParam(defaultValue = "0",name="page") Integer page,@RequestParam(defaultValue = "2",name="count") Integer count){
        List<FormSchema> resultFormSchemas=formSchemaService.getFormSchemas(PageRequest.of(page,count));
        return new ResponseEntity<List<FormSchema>>(resultFormSchemas,HttpStatus.OK);
    }

    @GetMapping(value = "api/v1/field_schemas")
    @ResponseBody
    public ResponseEntity<List<FieldSchema>> getFieldSchemas(@RequestParam(defaultValue = "0",name = "page") Integer page,@RequestParam(defaultValue = "2",name="count") Integer count){
        List<FieldSchema> resultFieldSchemas=formSchemaService.getFieldSchemas(PageRequest.of(page,count));
        return new ResponseEntity<List<FieldSchema>>(resultFieldSchemas,HttpStatus.OK);
    }


}
