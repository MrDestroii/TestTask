package main.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.model.Compound;
import main.model.Dot;

class DotTest {

	@Test
	final void testSetDotName() {
		Dot dot = new Dot(1);
		int nameDotActual = 2;
		dot.setDotName(nameDotActual);
		assertEquals(dot.getDotName(), nameDotActual);
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetDotName() {
		Dot dot = new Dot();
		int expectedName = 1;
		dot.setDotName(expectedName);
		int resultName = dot.getDotName();
		assertEquals(expectedName, resultName);
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetCompounds() {
		Dot dot = new Dot(1);
		Compound compound = new Compound(2,22);
		ArrayList<Compound> compoundsList = new ArrayList<>();
		compoundsList.add(compound);
		dot.setCompounds(compoundsList);
		assertEquals(dot.getCompaunds(), compoundsList);
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetCompaunds() {
		Dot dot = new Dot(1);
		Compound compound = new Compound(2,22);
		ArrayList<Compound> compoundsList = new ArrayList<>();
		compoundsList.add(compound);
		dot.setCompounds(compoundsList);
		ArrayList<Compound> compoundsListResult = dot.getCompaunds();
		assertEquals(compoundsList, compoundsListResult);
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testAddCompound() {
		Dot dot = new Dot(1);
		Compound compound = new Compound(2,22);
		dot.addCompound(compound);
		Compound compoundResult = dot.getCompaunds().get(0);
		assertEquals(compound, compoundResult);
		//fail("Not yet implemented"); // TODO
	}

}
