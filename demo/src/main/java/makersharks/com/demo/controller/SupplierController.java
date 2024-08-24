package makersharks.com.demo.controller;

import makersharks.com.demo.entity.Supplier;
import makersharks.com.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<Page<Supplier>> searchSuppliers(
            @RequestParam String location,
            @RequestParam String natureOfBusiness,
            @RequestParam List<String> manufacturingProcesses,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size) {

        Page<Supplier> suppliers = supplierService.getManufacturers(location, natureOfBusiness, manufacturingProcesses,page, size);
        return ResponseEntity.ok(suppliers);
    }
//    @PostMapping("/query")
//    public ResponseEntity<Page<Supplier>> searchSupplier(
//            @RequestParam String location,
//            @RequestParam String natureOfBusiness,
//            @RequestParam List<String> manufacturingProcesses,
//
//            @RequestParam int page,
//            @RequestParam int size
//    ){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Supplier> result = supplierService.searchSuppliers(location, natureOfBusiness, manufacturingProcesses, pageable);
//        return ResponseEntity.ok(result);
//
//    }
    @PostMapping("/add")
    public ResponseEntity<Supplier>addSupplier(@RequestBody Supplier supplier) {
        Supplier newSupplier = supplierService.addSupplier(supplier);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    @PostMapping("/add-from-json")
    public ResponseEntity<String> addFromJson(){
        try{
            supplierService.addFromJson("src/main/resources/supplier.json");
            return new ResponseEntity<>("Supplier added successfully...",HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>("Failed to add suppliers : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
