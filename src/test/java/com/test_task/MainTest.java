package com.test_task;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import javax.swing.JTextArea;

import org.junit.Test;

import com.test_task.model.Compound;
import com.test_task.model.Dot;
import com.test_task.model.Graph;

public class MainTest {

	@Test
	public final void testStart() {
		assertNotNull(new Main());
	}

	@Test
	public final void testCalculate() {
		Main main = new Main();
		Graph graph = new Graph();
		Dot dot = new Dot(1);
		Dot dot2 = new Dot(2);
		Dot dot3 = new Dot(3);
		Dot dot4 = new Dot(4);
		Dot dot5 = new Dot(5);
		Dot dot6 = new Dot(6);
		dot.addCompound(new Compound(6, 14));
		dot.addCompound(new Compound(3, 9));
		dot.addCompound(new Compound(2, 7));
		dot2.addCompound(new Compound(1, 7));
		dot2.addCompound(new Compound(3, 10));
		dot2.addCompound(new Compound(4, 15));
		dot3.addCompound(new Compound(1, 9));
		dot3.addCompound(new Compound(2, 10));
		dot3.addCompound(new Compound(4, 11));
		dot3.addCompound(new Compound(6, 2));
		dot4.addCompound(new Compound(2, 15));
		dot4.addCompound(new Compound(3, 11));
		dot4.addCompound(new Compound(5, 6));
		dot5.addCompound(new Compound(4, 6));
		dot5.addCompound(new Compound(6, 9));
		dot6.addCompound(new Compound(1, 14));
		dot6.addCompound(new Compound(3, 2));
		dot6.addCompound(new Compound(5, 9));
		graph.addDot(dot);
		graph.addDot(dot2);
		graph.addDot(dot3);
		graph.addDot(dot4);
		graph.addDot(dot5);
		graph.addDot(dot6);
		int start = 0;
		int finish = 4;
		int result = 20;
		int actual[] = main.calculate(graph, start, finish);
		assertEquals(result, actual[finish]);
	}

	@Test
	public final void testMatrx() {
		Main main = new Main();
		Graph graph = new Graph();
		Dot dot = new Dot(1);
		Dot dot2 = new Dot(2);
		Dot dot3 = new Dot(3);
		Dot dot4 = new Dot(4);
		Dot dot5 = new Dot(5);
		Dot dot6 = new Dot(6);
		dot.addCompound(new Compound(6, 14));
		dot.addCompound(new Compound(3, 9));
		dot.addCompound(new Compound(2, 7));
		dot2.addCompound(new Compound(1, 7));
		dot2.addCompound(new Compound(3, 10));
		dot2.addCompound(new Compound(4, 15));
		dot3.addCompound(new Compound(1, 9));
		dot3.addCompound(new Compound(2, 10));
		dot3.addCompound(new Compound(4, 11));
		dot3.addCompound(new Compound(6, 2));
		dot4.addCompound(new Compound(2, 15));
		dot4.addCompound(new Compound(3, 11));
		dot4.addCompound(new Compound(5, 6));
		dot5.addCompound(new Compound(4, 6));
		dot5.addCompound(new Compound(6, 9));
		dot6.addCompound(new Compound(1, 14));
		dot6.addCompound(new Compound(3, 2));
		dot6.addCompound(new Compound(5, 9));
		graph.addDot(dot);
		graph.addDot(dot2);
		graph.addDot(dot3);
		graph.addDot(dot4);
		graph.addDot(dot5);
		graph.addDot(dot6);
		ArrayList<Dot> dotList = graph.getDotsList();
		int sizeDotList = dotList.size();
		int[][] extMatr = new int[sizeDotList][sizeDotList];
		for (int i = 0; i < sizeDotList; i++) {
			for (int j = 0; j < dotList.get(i).getCompaunds().size(); j++) {
				extMatr[i][dotList.get(i).getCompaunds().get(j).getToDotName() - 1] = dotList.get(i).getCompaunds().get(j)
						.getWeight();
			}
		}
		assertArrayEquals(extMatr , main.matrx(sizeDotList, dotList));
	}

	@Test
	public final void testConclusion() {
		Main main = new Main();
		Graph graph = new Graph();
		Dot dot = new Dot(1);
		Dot dot2 = new Dot(2);
		Dot dot3 = new Dot(3);
		Dot dot4 = new Dot(4);
		Dot dot5 = new Dot(5);
		Dot dot6 = new Dot(6);
		dot.addCompound(new Compound(6, 14));
		dot.addCompound(new Compound(3, 9));
		dot.addCompound(new Compound(2, 7));
		dot2.addCompound(new Compound(1, 7));
		dot2.addCompound(new Compound(3, 10));
		dot2.addCompound(new Compound(4, 15));
		dot3.addCompound(new Compound(1, 9));
		dot3.addCompound(new Compound(2, 10));
		dot3.addCompound(new Compound(4, 11));
		dot3.addCompound(new Compound(6, 2));
		dot4.addCompound(new Compound(2, 15));
		dot4.addCompound(new Compound(3, 11));
		dot4.addCompound(new Compound(5, 6));
		dot5.addCompound(new Compound(4, 6));
		dot5.addCompound(new Compound(6, 9));
		dot6.addCompound(new Compound(1, 14));
		dot6.addCompound(new Compound(3, 2));
		dot6.addCompound(new Compound(5, 9));
		graph.addDot(dot);
		graph.addDot(dot2);
		graph.addDot(dot3);
		graph.addDot(dot4);
		graph.addDot(dot5);
		graph.addDot(dot6);
		JTextArea textArea = new JTextArea();
		int[] dist = main.calculate(graph, 0, 4);
		int sizeDotList = graph.getDotsList().size();
		int matr[][] = main.matrx(sizeDotList, graph.getDotsList());
		int[] ver = main.routeCalculation(dist, matr, sizeDotList, 4);
		main.conclusion(dist, sizeDotList, 4, ver, textArea);
		String s = "Минимальное расстояние до точки 5 ровняется: 20\n" + "Маршрут: 1 3 6 5 \n" + "";
		assertEquals(s, textArea.getText());
	}

	@Test
	public final void testRouteCalculation() {
		Main main = new Main();
		Graph graph = new Graph();
		Dot dot = new Dot(1);
		Dot dot2 = new Dot(2);
		Dot dot3 = new Dot(3);
		Dot dot4 = new Dot(4);
		Dot dot5 = new Dot(5);
		Dot dot6 = new Dot(6);
		dot.addCompound(new Compound(6, 14));
		dot.addCompound(new Compound(3, 9));
		dot.addCompound(new Compound(2, 7));
		dot2.addCompound(new Compound(1, 7));
		dot2.addCompound(new Compound(3, 10));
		dot2.addCompound(new Compound(4, 15));
		dot3.addCompound(new Compound(1, 9));
		dot3.addCompound(new Compound(2, 10));
		dot3.addCompound(new Compound(4, 11));
		dot3.addCompound(new Compound(6, 2));
		dot4.addCompound(new Compound(2, 15));
		dot4.addCompound(new Compound(3, 11));
		dot4.addCompound(new Compound(5, 6));
		dot5.addCompound(new Compound(4, 6));
		dot5.addCompound(new Compound(6, 9));
		dot6.addCompound(new Compound(1, 14));
		dot6.addCompound(new Compound(3, 2));
		dot6.addCompound(new Compound(5, 9));
		graph.addDot(dot);
		graph.addDot(dot2);
		graph.addDot(dot3);
		graph.addDot(dot4);
		graph.addDot(dot5);
		graph.addDot(dot6);
		int sizeDotList = graph.getDotsList().size();
		int[] dist = main.calculate(graph, 0, 4);
		int[][] matr = main.matrx(sizeDotList, graph.getDotsList());
		int[] extVer = {5, 6, 3, 1, 0, 0, };
		int[] ver = main.routeCalculation(dist, matr, sizeDotList, 4);
		assertArrayEquals(extVer,ver);
	}

	@Test
	public final void testFromJson() {
		Main main = new Main();
		Graph graph = new Graph();
		Dot dot = new Dot(1);
		Dot dot2 = new Dot(2);
		Dot dot3 = new Dot(3);
		Dot dot4 = new Dot(4);
		Dot dot5 = new Dot(5);
		Dot dot6 = new Dot(6);
		dot.addCompound(new Compound(6, 14));
		dot.addCompound(new Compound(3, 9));
		dot.addCompound(new Compound(2, 7));
		dot2.addCompound(new Compound(1, 7));
		dot2.addCompound(new Compound(3, 10));
		dot2.addCompound(new Compound(4, 15));
		dot3.addCompound(new Compound(1, 9));
		dot3.addCompound(new Compound(2, 10));
		dot3.addCompound(new Compound(4, 11));
		dot3.addCompound(new Compound(6, 2));
		dot4.addCompound(new Compound(2, 15));
		dot4.addCompound(new Compound(3, 11));
		dot4.addCompound(new Compound(5, 6));
		dot5.addCompound(new Compound(4, 6));
		dot5.addCompound(new Compound(6, 9));
		dot6.addCompound(new Compound(1, 14));
		dot6.addCompound(new Compound(3, 2));
		dot6.addCompound(new Compound(5, 9));
		graph.addDot(dot);
		graph.addDot(dot2);
		graph.addDot(dot3);
		graph.addDot(dot4);
		graph.addDot(dot5);
		graph.addDot(dot6);
		String json = "{\"dotsList\":[{\"name\":1,\"compounds\":[{\"toDotName\":6,\"weight\":14},{\"toDotName\":3,\"weight\":9},{\"toDotName\":2,\"weight\":7}]},{\"name\":2,\"compounds\":[{\"toDotName\":1,\"weight\":7},{\"toDotName\":3,\"weight\":10},{\"toDotName\":4,\"weight\":15}]},{\"name\":3,\"compounds\":[{\"toDotName\":1,\"weight\":9},{\"toDotName\":2,\"weight\":10},{\"toDotName\":4,\"weight\":11},{\"toDotName\":6,\"weight\":2}]},{\"name\":4,\"compounds\":[{\"toDotName\":2,\"weight\":15},{\"toDotName\":3,\"weight\":11},{\"toDotName\":5,\"weight\":6}]},{\"name\":5,\"compounds\":[{\"toDotName\":4,\"weight\":6},{\"toDotName\":6,\"weight\":9}]},{\"name\":6,\"compounds\":[{\"toDotName\":1,\"weight\":14},{\"toDotName\":3,\"weight\":2},{\"toDotName\":5,\"weight\":9}]}]}";
		Graph graphActual = main.fromJson(json, Graph.class);
		assertEquals(graph.getClass(), graphActual.getClass());
	}

}
