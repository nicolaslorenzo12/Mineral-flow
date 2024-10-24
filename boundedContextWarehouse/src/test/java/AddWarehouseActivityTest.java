import be.kdg.prog6.boundedcontextWarehouse.domain.Warehouse;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivity;
import be.kdg.prog6.boundedcontextWarehouse.domain.WarehouseActivityWindow;
import be.kdg.prog6.common.domain.Storage;
import be.kdg.prog6.common.domain.WarehouseAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddWarehouseActivityTest {

    private Warehouse warehouse;
    private WarehouseActivityWindow warehouseActivityWindow;

    @BeforeEach
    void setUp() {
        // Initializing Warehouse and its Pdt list for testing
        warehouseActivityWindow = new WarehouseActivityWindow();
        warehouseActivityWindow.addWarehouseActivity(150, 1, WarehouseAction.RECEIVE);
        warehouseActivityWindow.addWarehouseActivity(200, 1, WarehouseAction.RECEIVE);
        warehouse = new Warehouse(warehouseActivityWindow);

        // Creating some Pdts and adding them to the warehouse
        List<Storage> storageList = new ArrayList<>();
        storageList.add(new Storage(150, LocalDateTime.now().minusDays(3)));
        storageList.add(new Storage(200, LocalDateTime.now().minusDays(1)));

        warehouse.setStorageList(storageList);
    }

    @Test
    void testDispatchMaterialCorrectlyAndActivityCreatedCorrectly() {

        //Arrange

        int amountOfTonsToDispatch = 200;

        //Act
        warehouse.addWarehouseActivity(amountOfTonsToDispatch, WarehouseAction.DISPATCH);

        //Assert

        List<Storage> storageList = warehouse.getStorageList();

        assertEquals(150, storageList.get(0).getAmountOfTonsConsumed());
        assertEquals(50, storageList.get(1).getAmountOfTonsConsumed());
        assertFalse(storageList.get(1).isAllTonsConsumed());
        assertTrue(storageList.get(0).isAllTonsConsumed());

        WarehouseActivityWindow activityWindow = warehouse.getWarehouseActivityWindow();
        List<WarehouseActivity> activities = activityWindow.getWarehouseActivityList();

        assertEquals(3, activities.size());


        WarehouseActivity activity = activities.get(2);
        assertEquals(amountOfTonsToDispatch, activity.amountOfTons());
        assertEquals(WarehouseAction.DISPATCH, activity.action());
    }


    @Test
    void testDispatchMoreThanAvailableStockAndExceptionThrownAndActivityNotAdded() {

        //Arrange
        int amountOfTonsToDispatch = 351;

        //Act and Assert

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            warehouse.addWarehouseActivity(amountOfTonsToDispatch, WarehouseAction.DISPATCH);
        });

        String expectedMessage = "Not enough stock to dispatch " + amountOfTonsToDispatch + " tons.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        WarehouseActivityWindow activityWindow = warehouse.getWarehouseActivityWindow();
        List<WarehouseActivity> activities = activityWindow.getWarehouseActivityList();

        // In the BeforeEach method we inserted two activities
        assertEquals(2, activities.size());

    }


    @Test
    void testReceivingMaterialCorrectlyAndActivityCreatedCorrectly() {
        // Arrange
        int amountOfTonsToReceive = 100;
        int initialStock = warehouse.calculateAndGetCurrentStock();

        // Act
        warehouse.addWarehouseActivity(amountOfTonsToReceive, WarehouseAction.RECEIVE);

        // Assert
        int updatedStock = warehouse.calculateAndGetCurrentStock();
        assertEquals(initialStock + amountOfTonsToReceive, updatedStock);


        WarehouseActivityWindow activityWindow = warehouse.getWarehouseActivityWindow();
        List<WarehouseActivity> activities = activityWindow.getWarehouseActivityList();

        assertEquals(3, activities.size());

        WarehouseActivity activity = activities.get(2);
        assertEquals(amountOfTonsToReceive, activity.amountOfTons());
        assertEquals(WarehouseAction.RECEIVE, activity.action());
    }


    @AfterEach
    void tearDown() {
        warehouse = null;
        warehouseActivityWindow = null;
    }
}
