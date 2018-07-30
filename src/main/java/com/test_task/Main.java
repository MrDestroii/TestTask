package com.test_task;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.test_task.model.Dot;
import com.test_task.model.Graph;

public class Main {
	private JFrame frame;
	private JMenuItem menuItem;
	private JSlider sliderFirsDot;
	private JSlider sliderSecondDot;
	private JTextArea resultTextArea;
	private JButton buttonResult;
	private Graph graph;
	private BufferedReader bufferedReader;
	private int[] result;
	public void start(){
		frame = new JFrame();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);
		JScrollPane scrollResultTextArea = new JScrollPane(resultTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JMenuBar menuBar = new JMenuBar();
		JMenu jMenu = new JMenu("Файл");
		menuItem = new JMenuItem("Загрузить");
		menuItem.addActionListener(new OpenMenuListener());
		jMenu.add(menuItem);
		menuBar.add(jMenu);
		sliderFirsDot = new JSlider();
		sliderSecondDot = new JSlider();
		JPanel jPanel = new JPanel();
		buttonResult = new JButton("Просчитать");
		jPanel.setLayout(new GridLayout(4, 1));
		jPanel.add(sliderFirsDot);
		jPanel.add(sliderSecondDot);
		jPanel.add(buttonResult);
		jPanel.add(scrollResultTextArea);
		frame.setJMenuBar(menuBar);
		frame.add(jPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public int[] calculate(Graph graph, int start, int finish) {
		ArrayList<Dot> dots = graph.getDotsList();
		int min, minindex;
		int sizeDotList = dots.size();
		int[][] matr = new int[sizeDotList][sizeDotList];
		int ancillary;
		int maxsize = Integer.MAX_VALUE / 2;
		int[] dist = new int[graph.getDotsList().size()];
		boolean[] used = new boolean[graph.getDotsList().size()];
		matr = matrx(sizeDotList, dots);
		Arrays.fill(dist, maxsize);
		Arrays.fill(used, false);
		dist[start] = 0;

		do {
			min = maxsize;
			minindex = maxsize;
			for (int i = 0; i < sizeDotList; i++) {
				if ((used[i] == false) && (dist[i] < min)) {
					min = dist[i];
					minindex = i;
				}
			}
			if (minindex != maxsize) {
				for (int i = 0; i < sizeDotList; i++) {
					if (matr[minindex][i] > 0) {
						ancillary = min + matr[minindex][i];
						if (ancillary < dist[i]) {
							dist[i] = ancillary;
						}
					}
				}
				used[minindex] = true;
			}
		} while (minindex < maxsize);
		return dist;
	}

	public int[][] matrx(int sizeDotList, ArrayList<Dot> dotList) {
		int[][] matr = new int[sizeDotList][sizeDotList];
		for (int i = 0; i < sizeDotList; i++) {
			for (int j = 0; j < dotList.get(i).getCompaunds().size(); j++) {
				matr[i][dotList.get(i).getCompaunds().get(j).getToDotName() - 1] = dotList.get(i).getCompaunds().get(j)
						.getWeight();
			}
		}
		
		 /*for (int i = 0; i < matr.length; i++) { for (int j = 0; j < matr.length; j++)
		 { System.out.print(matr[i][j]+" "); } System.out.println(); }*/
		 
		return matr;
	}

	public void conclusion(int[] dist, int sizeDotList, int finish, int ver[],JTextArea textArea) {
		String[] string = new String[sizeDotList];
		for (int i = 0; i < sizeDotList; i++) {
			string[i] = "Минимальное расстояние до точки " + (i + 1) + " ровняется: " + dist[i] + "\n";
		}
		textArea.append(string[finish]);
		textArea.append("Маршрут: ");
		for (int i = 5; i >= 0; i--) {
			if (ver[i] != 0) {
				textArea.append(String.valueOf(ver[i]) + " ");
			}
		}
		textArea.append("\n");
	}

	public int[] routeCalculation(int[] dist, int[][] matr, int sizeDotList, int finish) {
		int ver[] = new int[sizeDotList];
		int end = finish;
		ver[0] = end + 1;
		int c = 1;
		int weight = dist[finish];
		while (end > 0) {
			for (int i = 0; i < sizeDotList; i++) {
				if (matr[end][i] != 0) {
					int ancillaryInt = weight - matr[end][i];
					if (ancillaryInt == dist[i]) {
						weight = ancillaryInt;
						end = i;
						ver[c] = i + 1;
						c++;
					}
				}
			}
		}

		return ver;
	}

	public <T> T fromJson(String json, Class<T> classOfT) {
		Gson gson = new GsonBuilder().create();
		try {
			return gson.fromJson(json, classOfT);
		} catch (JsonSyntaxException jse) {
			return null;
		}
	}

	public class OpenMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}

	public class calculateMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			int sizeDotList = graph.getDotsList().size();
			int finish = sliderSecondDot.getValue();
			result = calculate(graph, sliderFirsDot.getValue(), finish);
			int[] ver = routeCalculation(result, matrx(sizeDotList, graph.getDotsList()), sizeDotList, finish);
			conclusion(result, sizeDotList, finish, ver, resultTextArea);
		}
	}

	private void loadFile(File file) {
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String jString = null;
			if (file.getPath().endsWith(".json")) {
				jString = bufferedReader.readLine();
				graph = fromJson(jString, Graph.class);
				sliderFirsDot.setMaximum(graph.getDotsList().size() - 1);
				sliderSecondDot.setMaximum(graph.getDotsList().size() - 1);
				Hashtable labels = new Hashtable();
				for (int i = 0; i < graph.getDotsList().size(); i++) {
					labels.put(i, new JLabel(String.valueOf(graph.getDotsList().get(i).getDotName())));
				}	
				sliderFirsDot.setLabelTable(labels);
				sliderFirsDot.setPaintLabels(true);
				sliderSecondDot.setLabelTable(labels);
				sliderSecondDot.setPaintLabels(true);
				buttonResult.addActionListener(new calculateMenuListener());
			} else {
				menuItem.doClick();
			}
		} catch (Exception e) {
		}
	}
}
