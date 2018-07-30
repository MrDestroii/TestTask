package com.test_task;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test_task.model.Compound;

class CompoundTest {

	@Test
	final void testSetToDotName() {
		Compound compound = new Compound();
		int toDotName = 2;
		compound.setToDotName(toDotName);
		assertEquals(toDotName, compound.getToDotName());
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetToDotName() {
		int toDotName = 2;
		Compound compound = new Compound();
		compound.setToDotName(toDotName);
		int toDotNameResult = compound.getToDotName();
		assertEquals(toDotName, toDotNameResult);	
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetWeight() {
		Compound compound = new Compound();
		int weight = 22;
		compound.setWeight(weight);
		assertEquals(weight, compound.getWeight());
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetWeight() {
		Compound compound = new Compound();
		int weight = 22;
		compound.setWeight(weight);
		int weightResult = compound.getWeight();
		assertEquals(weight, weightResult);
		//fail("Not yet implemented"); // TODO
	}

}
