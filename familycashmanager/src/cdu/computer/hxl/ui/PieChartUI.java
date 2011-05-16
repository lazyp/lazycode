package cdu.computer.hxl.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartUI {
	public PieChartUI() {

	}

	private DefaultPieDataset createDataset(Map<String, Object> data) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		Set<Map.Entry<String, Object>> dataSet = data.entrySet();
		Iterator<Map.Entry<String, Object>> iter = dataSet.iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			dataset.setValue(entry.getKey(), (Double) entry.getValue());
		}
		return dataset;

	}

	public JPanel createPieChartPanel(String title, Map<String, Object> data) {

		JFreeChart chart = ChartFactory.createPieChart(title, // chart
				// title
				createDataset(data), // data
				true, // include legend
				true, false);

		Font font = new Font("宋体", Font.PLAIN, 12);

		chart.getTitle().setFont(new Font("黑体", Font.PLAIN, 18));
		chart.getLegend().setItemFont(font);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(font);
		plot.setNoDataMessage("没有数据");
		plot.setCircular(false);
		plot.setLabelGap(0.02);

		JPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(400, 280));
		return panel;
	}
}
