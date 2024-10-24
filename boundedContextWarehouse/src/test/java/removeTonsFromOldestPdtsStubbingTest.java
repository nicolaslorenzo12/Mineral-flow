import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.common.domain.Pdt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class removeTonsFromOldestPdtsStubbingTest {

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        // Initializing Warehouse and its Pdt list for testing
       warehouse = new WarehouseStub();

        // Creating some Pdts and adding them to the warehouse
        List<Pdt> pdtStubs = new ArrayList<>();
        pdtStubs.add(new Pdt(100, LocalDateTime.now().minusDays(3)));
        pdtStubs.add(new Pdt(200, LocalDateTime.now().minusDays(1)));

        warehouse.setPdtList(pdtStubs);
    }

    @Test
    void testRemoveTonsFromOldestPdts() {
        // Act: Call the method with an amount of tons to dispatch
        int amountOfTonsToDispatch = 150;
        warehouse.removeTonsFromOldestPdts(amountOfTonsToDispatch);

        // Assert: Verify that the oldest Pdt has consumed 100 tons and the second one consumed 50 tons
        List<Pdt> pdtList = warehouse.getPdtList(); // Assuming you have this getter in Warehouse

        assertEquals(100, pdtList .get(0).getAmountOfTonsConsumed()); // Oldest Pdt should consume 100 tons
        assertEquals(50, pdtList .get(1).getAmountOfTonsConsumed()); // Next Pdt should consume 50 tons
        assertFalse(pdtList .get(1).isAllTonsConsumed()); // Second Pdt should still have some tons left
    }
}
