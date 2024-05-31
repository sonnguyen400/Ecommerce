package DAO;

import com.nhs.individual.DAO.DAOImp.ProductDAO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class productDAOTestCase {
    @Mock
    ProductDAO productDAO;
}
