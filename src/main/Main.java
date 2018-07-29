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
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import main.model.Dot;
import main.model.Graph;

public class Main {
	private JFrame frame;
	private JMenuItem menuItem;
	private JSlider sliderFirsDot;
	private JSlider sliderSecondDot;
	private JTextArea resultTextArea;
	private JButton buttonResult;
	private Graph graph;
	private BufferedReader bufferedReader;
	Main(){
		frame = new JFrame();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultTextArea = new JTextArea();
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
	public static void main(String[] args) {
		new Main();
	}

	public void calculate(Graph graph, int start, int finish) {
		ArrayList<Dot> dots = graph.getDotsList();
		int min, minindex;
		int sizeDotList = dots.size();
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

	/*
	 * public String toJson(Object obj) { Gson gson = new
	 * GsonBuilder().setLenient().create(); try { return gson.toJson(obj); } catch
	 * (JsonSyntaxException jse) { return null; } }
	 */

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
			calculate(graph, sliderFirsDot.getValue(), sliderSecondDot.getValue());
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
