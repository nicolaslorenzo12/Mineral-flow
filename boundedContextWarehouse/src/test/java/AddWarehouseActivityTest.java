
import be.kdg.prog6.boundedcontextWarehouse.BoundedContextWarehouseApplication;
import be.kdg.prog6.boundedcontextWarehouse.adapters.out.db.OrderLineJpaEntity;
import be.kdg.prog6.boundedcontextWarehouse.adapters.out.db.PurchaseOrderJpaEntity;
import be.kdg.prog6.boundedcontextWarehouse.adapters.out.db.PurchaseOrderJpaEntityRepository;
import be.kdg.prog6.boundedcontextWarehouse.ports.out.*;
import be.kdg.prog6.common.events.MaterialToBeDispatchedEvent;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = BoundedContextWarehouseApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AddWarehouseActivityTest {



}
