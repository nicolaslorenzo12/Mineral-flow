
import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseAction;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.common.domain.Storage;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RemoveTonsFromOldestPdtTest {


    // Single unit test using mockito. From my point of view one test is enough since there is not a bad path.

    // This test checks if the flow of the method to removeTonsFromOldestPdts actually works as expected.

    // I mock the calculation of the current stock of the warehouse and the behavior of each pdt when needed
    // to be able to pass the test and isolate the actual method I want to test. I suppose that there is
    // enough stock to dispatch because otherwise I would also be testing the addWarehouseActivity logic

    // I call the addWarehouseActivity in the test because this is the public method, even tough I test a tiny line
    // of code of that method I am concentrating on the behavior the private method to removeTonsFromOldestPdts.

    // The state of each storage is not verified because that is the problem of the Storage class itself. I am just
    // interested in how warehouse manages the flow of removing a specific amount of tons of the oldest storages that
    // are not totally consumed. Storage manages its internal attributes.
    // So that is why I consider this is a valid unit test using mockito
    @Test
    public void testAddWarehouseActivityDispatchWhenOnePdtHasBeenConsumed() {

        // Arrange
        WarehouseActivityWindow warehouseActivityWindow = mock(WarehouseActivityWindow.class);
        Warehouse warehouse = new Warehouse(warehouseActivityWindow);
        List<Storage> storageList = new ArrayList<>();

        Storage storage1 = mock(Storage.class);
        when(storage1.getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(4));
        when(storage1.isAllTonsConsumed()).thenReturn(false);
        when(storage1.removeTonsFromPdt(150)).thenReturn(100);

        Storage storage2 = mock(Storage.class);
        when(storage2.getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(3));
        when(storage2.isAllTonsConsumed()).thenReturn(false);
        when(storage2.removeTonsFromPdt(100)).thenReturn(80);

        Storage storage3 = mock(Storage.class);
        when(storage3.getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(2));
        when(storage3.isAllTonsConsumed()).thenReturn(false);
        when(storage3.removeTonsFromPdt(80)).thenReturn(0);

    // This storage is fully consumed
        Storage storage4 = mock(Storage.class);
        when(storage4.getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusHours(1));
        when(storage4.isAllTonsConsumed()).thenReturn(true);

        Storage storage5 = mock(Storage.class);
        when(storage5.getTimeOfDelivery()).thenReturn(LocalDateTime.now().minusMinutes(30));
        when(storage5.isAllTonsConsumed()).thenReturn(false);

        // I add them in different order so it is more clear that the storages are going to be
        // ordered by time of delivery when dispatching material. Storages that are totally consumed
        // will be ignored.
        storageList.add(storage2);
        storageList.add(storage4);
        storageList.add(storage5);
        storageList.add(storage1);
        storageList.add(storage3);
        warehouse.setStorageList(storageList);

        when(warehouseActivityWindow.calculateCurrentStock()).thenReturn(200);

        int amountToDispatch = 150;

        // Act
        warehouse.addWarehouseActivity(amountToDispatch, WarehouseAction.DISPATCH);

        // Assert
        InOrder inOrder = inOrder(storage1, storage2, storage3, storage4, storage5);
        inOrder.verify(storage1, times(1)).removeTonsFromPdt(amountToDispatch);
        inOrder.verify(storage2, times(1)).removeTonsFromPdt(100);
        inOrder.verify(storage3, times(1)).removeTonsFromPdt(80);
        // The fully consumed storage is never called
        inOrder.verify(storage4, times(0)).removeTonsFromPdt(anyInt());
        // This storage is never called because the amountOfTonsToDispatch were consumed by the first three storages
        inOrder.verify(storage5, times(0)).removeTonsFromPdt(anyInt());
    }
}
