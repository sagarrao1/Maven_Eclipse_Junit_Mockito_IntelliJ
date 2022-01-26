package com.in28minutes.example.layering.business.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

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

//@RunWith(MockitoJUnitRunner.class)
public class ClientBOMockitoTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
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
		assertEquals(new BigDecimal("11.0"), actualAmt.getValue());
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

/*
 * @RunWith(MockitoJUnitRunner.class) public class ClientBOMockitoTest {
 * 
 * @Mock private ProductDO productDO;
 * 
 * @Mock private ClientDO clientDO;
 * 
 * @InjectMocks private ClientBO clientBO = new ClientBOImpl();
 * 
 * @Captor ArgumentCaptor<Client> clientArgumentCaptured;
 * 
 * private static final int DUMMY_CLIENT_ID = 1;
 * 
 * @Test public void testClientProductSum() {
 * 
 * List<Product> products = Arrays.asList(createProductWithAmount("5.0"),
 * createProductWithAmount("6.0"));
 * 
 * when(productDO.getAllProducts(anyInt())).thenReturn(products);
 * 
 * assertAmountEquals(new AmountImpl(new BigDecimal("11.0"), Currency.EURO),
 * clientBO.getClientProductsSum(DUMMY_CLIENT_ID)); }
 * 
 * private void assertAmountEquals(Amount expectedAmount, Amount actualAmount) {
 * assertEquals(expectedAmount.getCurrency(), actualAmount.getCurrency());
 * assertEquals(expectedAmount.getValue(), actualAmount.getValue()); }
 * 
 * private Product createProductWithAmount(String amount) { return new
 * ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, new AmountImpl(new
 * BigDecimal(amount), Currency.EURO)); }
 * 
 * @Test public void
 * saveChangedProducts_ProductInScreenAndNotInDatabase_ProductIsInserted() {
 * 
 * List<Product> screenProducts = Arrays.asList(createProduct());
 * 
 * List<Product> emptyDatabaseProducts = new ArrayList<Product>();
 * 
 * stub(productDO.getAllProducts(anyInt())).toReturn(emptyDatabaseProducts);
 * 
 * clientBO.saveChangedProducts(1, screenProducts);
 * 
 * verify(productDO).insertProduct(1, screenProducts.get(0)); }
 * 
 * private Product createProduct() { return new ProductImpl(100, "Product 15",
 * ProductType.BANK_GUARANTEE, new AmountImpl(new BigDecimal("5.0"),
 * Currency.EURO)); }
 * 
 * @Test public void saveChangedProducts_ProductInScreenAndDatabase_IsUpdated()
 * { Product screenProduct = createProductWithAmount("5.0");
 * 
 * List<Product> databaseProducts =
 * Arrays.asList(createProductWithAmount("6.0")); List<Product> screenProducts =
 * Arrays.asList(screenProduct);
 * 
 * stub(productDO.getAllProducts(anyInt())).toReturn(databaseProducts);
 * 
 * clientBO.saveChangedProducts(1, screenProducts);
 * 
 * verify(productDO).updateProduct(1, screenProduct); }
 * 
 * @Test public void
 * saveChangedProducts_ProductInDatabaseButNotInScreen_Deleted() {
 * 
 * Product productFromDatabase = createProduct();
 * 
 * List<Product> databaseProducts = Arrays.asList(productFromDatabase);
 * List<Product> emptyScreenProducts = new ArrayList<Product>();
 * 
 * stub(productDO.getAllProducts(anyInt())).toReturn(databaseProducts);
 * 
 * clientBO.saveChangedProducts(1, emptyScreenProducts);
 * 
 * verify(productDO).deleteProduct(1, productFromDatabase); }
 * 
 * @Test public void calculateAndSaveClientProductSum1() {
 * 
 * ClientImpl client = createClientWithProducts(createProductWithAmount("6.0"),
 * createProductWithAmount("6.0"));
 * 
 * clientBO.calculateAndSaveClientProductSum(client);
 * 
 * verify(clientDO).saveClient(clientArgumentCaptured.capture());
 * 
 * assertEquals(new BigDecimal("12.0"),
 * clientArgumentCaptured.getValue().getProductAmount());
 * 
 * }
 * 
 * private ClientImpl createClientWithProducts(Product... products) { ClientImpl
 * client = new ClientImpl(0, null, null, null, Arrays.asList(products)); return
 * client; }
 * 
 * }
 */