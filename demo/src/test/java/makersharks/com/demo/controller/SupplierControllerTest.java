// SupplierControllerTest.java
package makersharks.com.demo.controller;

import makersharks.com.demo.entity.Supplier;
import makersharks.com.demo.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @Test
    public void testAddFromJson_Success() throws IOException {
        doNothing().when(supplierService).addFromJson("src/main/resources/supplier.json");

        ResponseEntity<String> response = supplierController.addFromJson();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Supplier added successfully...", response.getBody());
    }

    @Test
    public void testAddFromJson_Failure() throws IOException {
        doThrow(new IOException("File not found")).when(supplierService).addFromJson("src/main/resources/supplier.json");

        ResponseEntity<String> response = supplierController.addFromJson();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to add suppliers : File not found", response.getBody());
    }

    @Test
    public void addSupplier_ReturnsCreatedStatus() {
        Supplier supplier = new Supplier();
        supplier.setCompanyName("New Supplier");

        when(supplierService.addSupplier(any(Supplier.class))).thenReturn(supplier);

        ResponseEntity<Supplier> response = supplierController.addSupplier(supplier);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Supplier", response.getBody().getCompanyName());
    }

    @Test
    public void searchSuppliers_ReturnsSuppliers() {
        Page<Supplier> suppliers = Page.empty();
        when(supplierService.getManufacturers(anyString(), anyString(), anyList(), anyInt(), anyInt())).thenReturn(suppliers);

        ResponseEntity<Page<Supplier>> response = supplierController.searchSuppliers("location", "nature", List.of("process"), 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suppliers, response.getBody());
    }

    @Test
    public void addFromJson_ReturnsInternalServerErrorOnIOException() throws IOException {
        doThrow(new IOException("File not found")).when(supplierService).addFromJson("src/main/resources/supplier.json");

        ResponseEntity<String> response = supplierController.addFromJson();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to add suppliers : File not found", response.getBody());
    }
}