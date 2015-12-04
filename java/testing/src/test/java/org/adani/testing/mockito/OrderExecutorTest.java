package org.adani.testing.mockito;

import static org.mockito.Mockito.*;

import org.adani.testing.mocking.models.Order;
import org.adani.testing.mocking.models.OrderState;
import org.adani.testing.mocking.services.OrderExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.*; 
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderExecutorTest {

	@Mock
	OrderExecutor orderExecutor;
	
	@Mock
	Order orderToBeExecuted;
	
	@Test
	public void test_Order_Executor(){
		orderExecutor.executeOrder(orderToBeExecuted);
		verify(orderExecutor).executeOrder(orderToBeExecuted);
	}
	
	
	@Test
	public void test_Order_Executor_Method_Stub(){ 
		when(orderToBeExecuted.getState()).thenReturn(OrderState.SOLD);
		when(orderExecutor.executeOrder(orderToBeExecuted)).thenReturn(orderToBeExecuted);
		assertThat(orderToBeExecuted.getState(), is(equalTo(OrderState.SOLD)));
	}
}
