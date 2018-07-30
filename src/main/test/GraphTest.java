package main.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.model.Dot;
import main.model.Graph;

class GraphTest {
	
	@Test
	final void testSetDotsList() {
		Graph graph = new Graph();
		ArrayList<Dot> dotList = new ArrayList<>();
		Dot oneDot = new Dot(1);
		Dot twoDot = new Dot(2);
		Dot threeDot = new Dot(3);
		dotList.add(oneDot);
		dotList.add(twoDot);
		dotList.add(threeDot);
		graph.setDotsList(dotList);
		assertEquals(graph.getDotsList(), dotList);
		// fail("Not yet implemented");
	}

	@Test
	final void testGetDotsList() {
		Graph graph = new Graph();
		ArrayList<Dot> dotListActual = new ArrayList<>();
		Dot oneDot = new Dot(1);
		Dot twoDot = new Dot(2);
		Dot threeDot = new Dot(3);
		dotListActual.add(oneDot);
		dotListActual.add(twoDot);
		dotListActual.add(threeDot);
		graph.setDotsList(dotListActual);
		ArrayList<Dot> dotListResult = graph.getDotsList();
		assertEquals(dotListActual, dotListResult);
		//fail("Not yet implemented");
	}

	@Test
	final void testAddDot() {
		Graph graph = new Graph();
		Dot dotActual = new Dot(1);
		graph.addDot(dotActual);
		Dot dotResult = graph.getDotsList().get(0);
		assertEquals(dotActual, dotResult);
		//fail("Not yet implemented"); // TODO
	}

}
