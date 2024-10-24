import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.common.domain.Pdt;
import be.kdg.prog6.common.domain.WarehouseAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class removeTonsFromOldestPdtsStubbingTest {

    private Warehouse warehouse;
    private WarehouseActivityWindow warehouseActivityWindow;

    @BeforeEach
    void setUp() {
        // Initializing Warehouse and its Pdt list for testing
        warehouseActivityWindow = new WarehouseActivityWindow();
        warehouseActivityWindow.addWarehouseActivity(200, 1, WarehouseAction.RECEIVE);
        warehouseActivityWindow.addWarehouseActivity(150, 1, WarehouseAction.RECEIVE);
        warehouse = new WarehouseStub(warehouseActivityWindow);

        // Creating some Pdts and adding them to the warehouse
        List<Pdt> pdtList = new ArrayList<>();
        pdtList.add(new Pdt(100, LocalDateTime.now().minusDays(3)));
        pdtList.add(new Pdt(200, LocalDateTime.now().minusDays(1)));

        warehouse.setPdtList(pdtList);
    }

    @Test
    void testRemoveTonsFromOldestPdts() {
        // Act: Call the method with an amount of tons to dispatch
        int amountOfTonsToDispatch = 150;
        warehouse.addWarehouseActivity(amountOfTonsToDispatch, WarehouseAction.DISPATCH);

        // Assert: Verify the expected behavior regarding Pdts
        List<Pdt> pdtList = warehouse.getPdtList(); // Assuming you have this getter in Warehouse

        // Verify that the oldest Pdt has consumed 100 tons and the second one consumed 50 tons
        assertEquals(100, pdtList.get(0).getAmountOfTonsConsumed()); // First Pdt should be fully consumed
        assertEquals(50, pdtList.get(1).getAmountOfTonsConsumed()); // Second Pdt should have 50 tons consumed
        assertFalse(pdtList.get(1).isAllTonsConsumed()); // Second Pdt should still have some tons left
        assertTrue(pdtList.get(0).isAllTonsConsumed());

        // Assert: Verify that a new WarehouseActivity was added
        WarehouseActivityWindow activityWindow = warehouse.getWarehouseActivityWindow(); // Assuming you have a getter for this
        List<WarehouseActivity> activities = activityWindow.getWarehouseActivityList(); // Assuming a getter exists

        // Verify that the activity list has one new entry
        assertEquals(3, activities.size()); // There should be one activity

        // Verify the properties of the added activity
        WarehouseActivity activity = activities.get(2);
        assertEquals(amountOfTonsToDispatch, activity.amountOfTons());
        assertEquals(WarehouseAction.DISPATCH, activity.action());
    }
}
