package main;

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
import javax.swing.JSlider;
import javax.swing.JTextArea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import main.model.Dot;
import main.model.Graph;

public class Main {
	private JFrame frame;
	private JSlider sliderFirsDot;
	private JSlider sliderSecondDot;
	private JTextArea resultTextArea;
	private JButton buttonResult;
	private Graph graph;
	private BufferedReader bufferedReader;

	public static void main(String[] args) {
		new Main().start();
	}

	public void name(Graph graph, int start, int finish) {
		ArrayList<Dot> dots = graph.getDotsList();
		int min, minindex;
		int sizeDotList = dots.size();
		System.out.println(sizeDotList);
		int[][] matr = new int[sizeDotList][sizeDotList];
		int ancillary;
		int maxsize = Integer.MAX_VALUE / 2;
		int dist[] = new int[graph.getDotsList().size()];
		boolean[] used = new boolean[graph.getDotsList().size()];
		for (int i = 0; i < sizeDotList; i++) {
			for (int j = 0; j < dots.get(i).getCompaunds().size(); j++) {
				matr[i][dots.get(i).getCompaunds().get(j).getToDotName() - 1] = dots.get(i).getCompaunds().get(j)
						.getWeight();
			}
		}
		/*
		 * for (int i = 0; i < matr.length; i++) { for (int j = 0; j < matr.length; j++)
		 * { System.out.print(matr[i][j]+" "); } System.out.println(); }
		 */
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
		String[] string = new String[sizeDotList];
		for (int i = 0; i < sizeDotList; i++) {
			string[i] = "Минимальное расстояние до точки " + (i + 1) + " ровняется: " + dist[i] + "\n";
		}
		resultTextArea.append(string[finish]);
	}

	public void start() {
		frame = new JFrame();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultTextArea = new JTextArea();
		JMenuBar menuBar = new JMenuBar();
		JMenu jMenu = new JMenu("Файл");
		JMenuItem menuItem = new JMenuItem("Загрузить");
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
		jPanel.add(resultTextArea);
		frame.setJMenuBar(menuBar);
		frame.add(jPanel, BorderLayout.CENTER);
		frame.setVisible(true);

		/*
		 * Graph graph = new Graph(); Dot dot = new Dot(1); Dot dot2 = new Dot(2); Dot
		 * dot3 = new Dot(3); Dot dot4 = new Dot(4); Dot dot5 = new Dot(5); Dot dot6 =
		 * new Dot(6); dot.addCompound(new Compound(6, 14)); dot.addCompound(new
		 * Compound(3, 9)); dot.addCompound(new Compound(2, 7)); dot2.addCompound(new
		 * Compound(1, 7)); dot2.addCompound(new Compound(3, 10)); dot2.addCompound(new
		 * Compound(4, 15)); dot3.addCompound(new Compound(1, 9)); dot3.addCompound(new
		 * Compound(2, 10)); dot3.addCompound(new Compound(4, 11)); dot3.addCompound(new
		 * Compound(6, 2)); dot4.addCompound(new Compound(2, 15)); dot4.addCompound(new
		 * Compound(3, 11)); dot4.addCompound(new Compound(5, 6)); dot5.addCompound(new
		 * Compound(4, 6)); dot5.addCompound(new Compound(6, 9)); dot6.addCompound(new
		 * Compound(1, 14)); dot6.addCompound(new Compound(3, 2)); dot6.addCompound(new
		 * Compound(5, 9)); graph.addDot(dot); graph.addDot(dot2); graph.addDot(dot3);
		 * graph.addDot(dot4); graph.addDot(dot5); graph.addDot(dot6); name(graph);
		 * System.out.println(toJson(graph));
		 */
	}

	public String toJson(Object obj) {
		Gson gson = new GsonBuilder().setLenient().create();
		try {
			return gson.toJson(obj);
		} catch (JsonSyntaxException jse) {
			return null;
		}
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
			name(graph, sliderFirsDot.getValue(), sliderSecondDot.getValue());
		}
	}

	private void loadFile(File file) {
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String jString = null;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
