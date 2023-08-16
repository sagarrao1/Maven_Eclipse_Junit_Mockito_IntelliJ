package com.in28minutes.example.layering.business.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.example.layering.business.api.client.ClientBO;
import com.in28minutes.example.layering.business.impl.client.ClientBOImpl;
import com.in28minutes.example.layering.data.api.client.ClientDO;
import com.in28minutes.example.layering.data.api.client.ProductDO;
import com.in28minutes.example.layering.model.api.client.Amount;
import com.in28minutes.example.layering.model.api.client.Client;
import com.in28minutes.example.layering.model.api.client.Currency;
import com.in28minutes.example.layering.model.api.client.Product;
import com.in28minutes.example.layering.model.api.client.ProductType;
import com.in28minutes.example.layering.model.impl.client.AmountImpl;
import com.in28minutes.example.layering.model.impl.client.ClientImpl;
import com.in28minutes.example.layering.model.impl.client.ProductImpl;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class ClientBOMockitoTest_OLd {

//	@Rule
//	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	private ProductDO productDO;

	@Mock
	private ClientDO clientDO;

	@InjectMocks
	ClientBO clientBOImpl = new ClientBOImpl();
	
	@Captor
	ArgumentCaptor<Client> clientArgumentCaptor;

	private static final int DUMMY_CLIENT_ID = 1;
	
	@Test
	public void testClientProductSum() {
		
		List<Product> products = Arrays.asList(createNewProduct("5.0"), createNewProduct("6.0"));
		System.out.println(products.size());
		when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(products);		
		
		Amount actualAmt = clientBOImpl.getClientProductsSum(DUMMY_CLIENT_ID);
		System.out.println(actualAmt.getValue());
		assertAmountEquals( new AmountImpl(new BigDecimal("11.0"), Currency.EURO),  actualAmt);
		
	}

	private void assertAmountEquals(Amount expectedAmt, Amount actualAmt) {
		assertEquals(expectedAmt.getValue(), actualAmt.getValue());
		assertEquals(expectedAmt.getCurrency(), actualAmt.getCurrency());
	}
	
	
	private Product createNewProduct(String amt) {
		return new ProductImpl(100, "gold", ProductType.BANK_GUARANTEE, 
				new AmountImpl(new BigDecimal(amt), Currency.EURO));
	}

	@Test
   public void saveChangedProducts_ProductInScreenAndNotInDatabase_ProductIsInserted() {
	
		List<Product> dataBaseproducts = Arrays.asList(createNewProduct("5.0"));	   
		List<Product> userEnteredProducts = Arrays.asList(createNewProduct("6.0"));
		List<Product> emptyProducts =   new ArrayList<Product>(); // Arrays.asList();
		
		when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(emptyProducts);
		clientBOImpl.saveChangedProducts(DUMMY_CLIENT_ID, userEnteredProducts);
		
		//Different ways of verifying the method
		verify(productDO).insertProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
		verify(productDO,Mockito.times(1)).insertProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
		verify(productDO,Mockito.atLeastOnce()).insertProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
		
		
		//verifying the methods which are not called
		verify(productDO, Mockito.never()).deleteProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
		verify(productDO, Mockito.never()).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
   }
   
	@Test
	   public void saveChangedProducts_ProductInScreenAndInDatabase_IsUpdated() {
		
			List<Product> dataBaseproducts = Arrays.asList(createNewProduct("5.0"));	   
			List<Product> userEnteredProducts = Arrays.asList(createNewProduct("6.0"));
			List<Product> emptyProducts =   new ArrayList<Product>(); // Arrays.asList();
			
			when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(dataBaseproducts);
			clientBOImpl.saveChangedProducts(DUMMY_CLIENT_ID, userEnteredProducts);
			
			//Different ways of verifying the method
			verify(productDO).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			verify(productDO,Mockito.times(1)).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			verify(productDO,Mockito.atLeastOnce()).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			
			
			//verifying the methods which are not called
			verify(productDO, Mockito.never()).deleteProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			verify(productDO, Mockito.never()).insertProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
	   }

	@Test
	   public void saveChangedProducts_ProductInDatabase_ButNOtInScreen_Deleted() {
		
			List<Product> dataBaseproducts = Arrays.asList(createNewProduct("5.0"));	   
			List<Product> userEnteredProducts = Arrays.asList(createNewProduct("6.0"));
			List<Product> emptyProducts =   new ArrayList<Product>(); // Arrays.asList();
			
			when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(dataBaseproducts);
			clientBOImpl.saveChangedProducts(DUMMY_CLIENT_ID, emptyProducts);
			
			//Different ways of verifying the method
			verify(productDO).deleteProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
			verify(productDO,Mockito.times(1)).deleteProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
			verify(productDO,Mockito.atLeastOnce()).deleteProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
			
			
			//verifying the methods which are not called
			verify(productDO, Mockito.never()).updateProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
			verify(productDO, Mockito.never()).insertProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
	   }
	
	@Test
	   public void saveChangedProducts_MultipleProductInDatabaseAndScreen_IsUpdated() {
		
			List<Product> dataBaseproducts = Arrays.asList(createNewProduct("5.0"),createNewProduct("3.0"));	   
			List<Product> userEnteredProducts = Arrays.asList(createNewProduct("1.0"),createNewProduct("6.0"));
			
			
			when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(dataBaseproducts);
			clientBOImpl.saveChangedProducts(DUMMY_CLIENT_ID, userEnteredProducts);
			
			//Different ways of verifying the method
			verify(productDO).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			verify(productDO,Mockito.times(1)).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			verify(productDO,Mockito.atLeastOnce()).updateProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			
			
//			//verifying the methods which are not called
			verify(productDO, Mockito.never()).deleteProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
			verify(productDO, Mockito.never()).deleteProduct(DUMMY_CLIENT_ID, dataBaseproducts.get(0));
			verify(productDO, Mockito.never()).insertProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
			verify(productDO, Mockito.never()).insertProduct(DUMMY_CLIENT_ID, userEnteredProducts.get(0));
	   }
	
	
	@Test
	public void testCalculateAndSaveClientProductSum() {
		
		List<Product> products = Arrays.asList(createNewProduct("8.0"), createNewProduct("6.0"));
		
		ClientImpl client = new ClientImpl(0, null, null, null, products);		
		
		clientBOImpl.calculateAndSaveClientProductSum(client);
		
		//verify whether below method is called
		verify(clientDO).saveClient(client);
		
		verify(clientDO).saveClient(clientArgumentCaptor.capture());
		
		//using argument Capture and verifying
		assertEquals(new BigDecimal("14.0"), clientArgumentCaptor.getValue().getProductAmount());
		
	}
	
	
}
