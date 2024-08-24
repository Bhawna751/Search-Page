package makersharks.com.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import makersharks.com.demo.entity.Supplier;
import makersharks.com.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, ObjectMapper objectMapper) {
        this.supplierRepository = supplierRepository;
        this.objectMapper = objectMapper;
    }

    public Page<Supplier> getManufacturers(String location, String natureOfBusiness,List<String> manufacturingProcesses, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesIn(location,natureOfBusiness,manufacturingProcesses,pageable);
    }

    public void addFromJson(String filePath) throws IOException{
        File file = new File(filePath);
        List<Supplier> suppliers = objectMapper.readValue(file, new TypeReference<List<Supplier>>() {});
        supplierRepository.saveAll(suppliers);
    }

    public Supplier addSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }
}
