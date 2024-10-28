import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.common.domain.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AddWarehouseActivityMockitoUnitTest {

    private Warehouse warehouse;
    private WarehouseActivityWindow warehouseActivityWindow;
    private List<Storage> storageMocks;

    @BeforeEach
    void setUp() {
        warehouseActivityWindow = mock(WarehouseActivityWindow.class);
        warehouse = new Warehouse(5, warehouseActivityWindow);
        storageMocks = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Storage storageMock = mock(Storage.class);
            storageMocks.add(storageMock);
        }
        warehouse.setStorageList(storageMocks);
    }

    @Test
    void testAddWarehouseActivity_Dispatch_WithSufficientStock() {
        // Arrange
        int amountOfTonsToDispatch = 100;
        when(warehouse.calculateAndGetCurrentStock()).thenReturn(150);

        when(storageMocks.get(0).isAllTonsConsumed()).thenReturn(true);
        when(storageMocks.get(0).getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(8));
        when(storageMocks.get(1).isAllTonsConsumed()).thenReturn(false);
        when(storageMocks.get(1).getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(1));
        when(storageMocks.get(2).isAllTonsConsumed()).thenReturn(false);
        when(storageMocks.get(2).getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(2));

        when(storageMocks.get(2).removeTonsFromPdt(amountOfTonsToDispatch)).thenReturn(30);
        when(storageMocks.get(1).removeTonsFromPdt(30)).thenReturn(0);

        WarehouseActivity expectedActivity = new WarehouseActivity(amountOfTonsToDispatch, warehouse.getWareHouseNumber(), WarehouseAction.DISPATCH);
        when(warehouseActivityWindow.addWarehouseActivity(amountOfTonsToDispatch, warehouse.getWareHouseNumber(), WarehouseAction.DISPATCH))
                .thenReturn(expectedActivity);

        // Act
        WarehouseActivity result = warehouse.addWarehouseActivity(amountOfTonsToDispatch, WarehouseAction.DISPATCH);

        // Assert
        assertEquals(expectedActivity, result);
        verify(warehouseActivityWindow, times(1)).addWarehouseActivity(amountOfTonsToDispatch,
                warehouse.getWareHouseNumber(), WarehouseAction.DISPATCH);

        InOrder inOrder = inOrder(storageMocks.get(2), storageMocks.get(1));
        inOrder.verify(storageMocks.get(2), times(1)).removeTonsFromPdt(amountOfTonsToDispatch);
        inOrder.verify(storageMocks.get(1), times(1)).removeTonsFromPdt(30);
    }

    @Test
    void testAddWarehouseActivity_Dispatch_WithInsufficientStock() {
        // Arrange
        int amountOfTonsToDispatch = 200;
        when(warehouse.calculateAndGetCurrentStock()).thenReturn(150); // Insufficient stock

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                warehouse.addWarehouseActivity(amountOfTonsToDispatch, WarehouseAction.DISPATCH));

        assertEquals("Not enough stock to dispatch 200 tons.", exception.getMessage());
        verify(warehouseActivityWindow, never()).addWarehouseActivity(amountOfTonsToDispatch, warehouse.getWareHouseNumber(),
                WarehouseAction.DISPATCH);
    }

    @Test
    void testAddWarehouseActivity_Dispatch_WithNegativeAmount() {
        // Arrange
        int amountOfTonsToDispatch = -50;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                warehouse.addWarehouseActivity(amountOfTonsToDispatch, WarehouseAction.DISPATCH));

        assertEquals("Amount of tons cannot be negative", exception.getMessage());
        verify(warehouseActivityWindow, never()).addWarehouseActivity(amountOfTonsToDispatch, warehouse.getWareHouseNumber(),
                WarehouseAction.DISPATCH);
    }

    @Test
    void testAddWarehouseActivity_NoDispatch() {
        // Arrange
        int amountOfTonsToReceive = 100;
        when(warehouse.calculateAndGetCurrentStock()).thenReturn(150);

        WarehouseActivity expectedActivity = new WarehouseActivity(warehouse.getWareHouseNumber(), amountOfTonsToReceive, WarehouseAction.RECEIVE);
        when(warehouseActivityWindow.addWarehouseActivity(amountOfTonsToReceive, warehouse.getWareHouseNumber(), WarehouseAction.RECEIVE))
                .thenReturn(expectedActivity);

        // Act
        WarehouseActivity result = warehouse.addWarehouseActivity(amountOfTonsToReceive, WarehouseAction.RECEIVE);

        // Assert
        assertEquals(expectedActivity, result);
        verify(warehouseActivityWindow, times(1)).addWarehouseActivity(amountOfTonsToReceive, warehouse.getWareHouseNumber(),
                WarehouseAction.RECEIVE);
        
        for (Storage storage : storageMocks) {
            verify(storage, never()).removeTonsFromPdt(anyInt());
        }
    }
}
