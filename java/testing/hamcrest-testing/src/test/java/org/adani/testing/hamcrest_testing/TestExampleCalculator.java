package org.adani.testing.hamcrest_testing;

import org.junit.Test;


import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;


public class TestExampleCalculator {

	@Test
	public void testAdd() {
		ExampleCalculator calculator = new ExampleCalculator();
		int result = calculator.add(10, 10);
		assertThat(Integer.valueOf(result), is(equalTo(Integer.valueOf(20))));
	}
	
	@Test
	public void testAddIsNotNull(){
		assertThat(new ExampleCalculator().add(10, 20), is(notNullValue()));
	}
}
