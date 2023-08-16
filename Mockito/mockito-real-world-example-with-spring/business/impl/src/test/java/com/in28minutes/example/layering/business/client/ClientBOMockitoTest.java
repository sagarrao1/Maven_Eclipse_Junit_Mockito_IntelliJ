package com.in28minutes.example.layering.business.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.example.layering.business.impl.client.ClientBOImpl;
import com.in28minutes.example.layering.data.api.client.ClientDO;
import com.in28minutes.example.layering.data.api.client.ProductDO;
import com.in28minutes.example.layering.model.api.client.Amount;
import com.in28minutes.example.layering.model.api.client.Client;
import com.in28minutes.example.layering.model.api.client.CollateralType;
import com.in28minutes.example.layering.model.api.client.Currency;
import com.in28minutes.example.layering.model.api.client.Product;
import com.in28minutes.example.layering.model.api.client.ProductType;
import com.in28minutes.example.layering.model.impl.client.AmountImpl;
import com.in28minutes.example.layering.model.impl.client.ClientImpl;
import com.in28minutes.example.layering.model.impl.client.CollateralImpl;
import com.in28minutes.example.layering.model.impl.client.ProductImpl;

@ExtendWith(MockitoExtension.class)
public class ClientBOMockitoTest {

//	@Rule
//	public MockitoRule  MockitoRule = MockitoJUnit.rule();
	
	@Mock
	ProductDO productDO;

	@Mock
	ClientDO clientDO;
	
	@InjectMocks
	ClientBOImpl clientBO;
	
	
	@Captor
	ArgumentCaptor<Client> argumentCaptor;
	
	public static final int DUMMY_CLIENT_ID = 1;
	
	@Test
	public void testGetClientProductsSum() {
		List<Product> products = Arrays.asList(createProductWithAmount("5.0",100),
				createProductWithAmount("6.0",200));	
		
		when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(products);
		
		Amount clientProductsSum = clientBO.getClientProductsSum(DUMMY_CLIENT_ID);
		
		Amount expectedProuctSum = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);
		assertAmountEquals(expectedProuctSum, clientProductsSum);
		
	}
	
//	database products    are empty  (mocked)
//	userEntered products are      200, 300
//	1. product id 200 will be inserted - > userEnteredProducts.get(0)
//	2. product id 300 will be inserted - > userEnteredProducts.get(1)
//	3. verify updateProduct method never called
//	4. verify deleteProduct method never called
	
	@Test
	public void testSaveChangedProducts_UserEnteredProducts_NoDatabaseProducts() {
		List<Product> emptyProductsList = new ArrayList<Product>();
		
		when(productDO.getAllProducts(1)).thenReturn(emptyProductsList);
		
		List<Product> userEnteredProducts = Arrays.asList(createProductWithAmount("7.0",200),
				createProductWithAmount("6.0",300)); 
		
		clientBO.saveChangedProducts(1, userEnteredProducts);
		
		verify(productDO).insertProduct(1, userEnteredProducts.get(0));
		verify(productDO).insertProduct(1, userEnteredProducts.get(1));
		verify(productDO, never()).updateProduct(1, userEnteredProducts.get(0));
		verify(productDO, never()).deleteProduct(1, userEnteredProducts.get(0));
		
		verify(productDO, times(1)).insertProduct(1, userEnteredProducts.get(0));
		verify(productDO, atLeastOnce()).insertProduct(1, userEnteredProducts.get(0));	

	}
	
	
//	database products    are 100, 200  (mocked)
//	userEntered products are      200, 300
//	1. product id 200 will be updated - > userEnteredProducts.get(0)
//	2. product id 300 will be inserted - > userEnteredProducts.get(1)
//	3. product id 100 will be deleted  - > databaseProducts.get(0)
//	3. verify product id 100 updateProduct method never called
//	4. verify product id 200 deleteProduct method never called	
	
	@Test
	public void testSaveChangedProductUserEnteredProducts_PlusDatabaseProducts() {
		List<Product> databaseProducts = Arrays.asList(createProductWithAmount("5.0",100),
				createProductWithAmount("6.0",200));		
		when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(databaseProducts);
		
		List<Product> userEnteredProducts = Arrays.asList(createProductWithAmount("7.0",200),
				createProductWithAmount("6.0",300)); 
		
		clientBO.saveChangedProducts(1, userEnteredProducts);
		
		verify(productDO).updateProduct(1, userEnteredProducts.get(0));
		verify(productDO).insertProduct(1, userEnteredProducts.get(1));
		verify(productDO).deleteProduct(1, databaseProducts.get(0));
		
		verify(productDO, never()).updateProduct(1, databaseProducts.get(0));	
		verify(productDO, never()).deleteProduct(1, userEnteredProducts.get(0));
		verify(productDO, times(1)).insertProduct(1, userEnteredProducts.get(1));
		

	}
	
//	database products    are 100, 200  (mocked)
//	userEntered products are  Empty
//	1. product id 100 will be deleted - > databaseProducts.get(0)
//	2. product id 200 will be deleted - > databaseProducts.get(1)
//	3. verify product id 100 updateProduct method never called
//	4. verify product id 200 insertProduct method never called
	
	@Test
	public void testSaveChangedProduct_NoUserEnteredProducts_PlusDatabaseProductsDeleted() {
		List<Product> databaseProducts = Arrays.asList(createProductWithAmount("5.0",100),
				createProductWithAmount("6.0",200));		
		when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(databaseProducts);
		
		List<Product> emptyProductsList = new ArrayList<Product>();
		
		clientBO.saveChangedProducts(1, emptyProductsList);
		
		verify(productDO).deleteProduct(1, databaseProducts.get(0));
		verify(productDO).deleteProduct(1, databaseProducts.get(1));
		
		verify(productDO, never()).insertProduct(1, databaseProducts.get(0));		
		verify(productDO, never()).updateProduct(1, databaseProducts.get(0));	
		verify(productDO, times(1)).deleteProduct(1, databaseProducts.get(1));

	}	
	
	
//	database products    are 100, 200  (mocked)
//	userEntered products are  100,200
//	1. product id 100 will be updated - > databaseProducts.get(0)
//	2. product id 200 will be updated - > databaseProducts.get(1)
//	3. verify product id 100 deleteProduct method never called
//	4. verify product id 200 insertProduct method never called
	
	@Test
	public void testSaveChangedProduct_verifyOnlyproductsAreUpdated() {
			
		List<Product> databaseProducts = Arrays.asList(createProductWithAmount("5.0",100),
				createProductWithAmount("6.0",200));	
		
		when(productDO.getAllProducts(DUMMY_CLIENT_ID)).thenReturn(databaseProducts);
		
		List<Product> userEnteredProducts = Arrays.asList(createProductWithAmount("5.0",100),
				createProductWithAmount("6.0",200));
		
		clientBO.saveChangedProducts(1, userEnteredProducts);
		
		verify(productDO).updateProduct(1, userEnteredProducts.get(0));
		verify(productDO).updateProduct(1, userEnteredProducts.get(1));
		
		verify(productDO, never()).insertProduct(1, userEnteredProducts.get(0));		
		verify(productDO, never()).deleteProduct(1, userEnteredProducts.get(1));

	}		
	
	@Test
	public void testCalculateAndSaveClientProductSum() {
		
		List<Product> products = Arrays.asList(createProductWithAmount("5.0",100),
												createProductWithAmount("6.0",200));	
		
//		List<Collateral> collaterals =Arrays.asList( newCollateralWIthId(100),
//				newCollateralWIthId(200)); 
		
		Client client = new ClientImpl(1, null,null,null,products);		
		
		clientBO.calculateAndSaveClientProductSum(client);
		
		//verify whether below method is called
		verify(clientDO).saveClient(client);
		
		verify(clientDO).saveClient(argumentCaptor.capture());
		
		//using argument Capture and verifying
		assertEquals(new BigDecimal("11.0"), argumentCaptor.getValue().getProductAmount());
		
		
	}
	
	
	
//	private methods

	private CollateralImpl newCollateralWIthId(long id) {
		return new CollateralImpl(id, "Sagar", CollateralType.BONDS);
	}
	
	private void assertAmountEquals(Amount expectedProuctSum, Amount clientProductsSum) {
		assertEquals(expectedProuctSum.getValue(), clientProductsSum.getValue());
		assertEquals(expectedProuctSum.getCurrency(), clientProductsSum.getCurrency());
	}

	private Product createProductWithAmount(String string, long id) {
		return new ProductImpl(id, "Product 15", ProductType.BANK_GUARANTEE, 
				new AmountImpl(new BigDecimal(string), Currency.EURO) );
	}

}
