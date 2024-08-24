// SupplierServiceTest.java
package makersharks.com.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import makersharks.com.demo.entity.Supplier;
import makersharks.com.demo.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private SupplierService supplierService;

    @Test
    public void testAddSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName("Test Supplier");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.addSupplier(supplier);

        assertEquals("Test Supplier", result.getCompanyName());
        verify(supplierRepository).save(supplier);
    }

    @Test
    public void addSupplier_ReturnsSavedSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName("New Supplier");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier result = supplierService.addSupplier(supplier);

        assertEquals("New Supplier", result.getCompanyName());
        verify(supplierRepository).save(supplier);
    }

    @Test
    public void addFromJson_SavesSuppliersFromFile() throws IOException {
        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());
        when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(suppliers);

        supplierService.addFromJson("src/main/resources/supplier.json");

        verify(supplierRepository).saveAll(suppliers);
    }

    @Test
    public void addFromJson_ThrowsIOExceptionForInvalidFile() throws IOException {
        doThrow(new IOException("File not found")).when(objectMapper).readValue(any(File.class), any(TypeReference.class));

        IOException exception = assertThrows(IOException.class, () -> supplierService.addFromJson("invalid/path.json"));

        assertEquals("File not found", exception.getMessage());
    }

    @Test
    public void getManufacturers_ReturnsPagedSuppliers() {
        Page<Supplier> suppliers = Page.empty();
        when(supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesIn(anyString(), anyString(), anyList(), any(Pageable.class))).thenReturn(suppliers);

        Page<Supplier> result = supplierService.getManufacturers("location", "nature", List.of("process"), 0, 10);

        assertEquals(suppliers, result);
    }

    @Test
    public void getManufacturers_ReturnsEmptyPageForNoSuppliers() {
        Page<Supplier> suppliers = Page.empty();
        when(supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesIn(anyString(), anyString(), anyList(), any(Pageable.class))).thenReturn(suppliers);

        Page<Supplier> result = supplierService.getManufacturers("unknown", "unknown", List.of("unknown"), 0, 10);

        assertEquals(0, result.getTotalElements());
    }

}