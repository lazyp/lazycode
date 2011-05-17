package cdu.computer.hxl.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ColorBar;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.TextAnchor;

public class Bar3DChartUI {
	public Bar3DChartUI() {

	}

	private CategoryDataset createDataset(Map<String, Double> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Iterator<Map.Entry<String, Double>> iter = data.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Double> entry = iter.next();
			dataset.addValue(entry.getValue(), "Series 1", entry.getKey());
		}

		return dataset;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return The chart.
	 */

	private static JFreeChart createChart(String title, CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart3D(title, // chart
																// title
				title, // domain axis label
				"Family Cash Manager System", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips
				false // urls
				);
		Font font = new Font("宋体", Font.PLAIN, 12);
		chart.getTitle().setFont(new Font("黑体", Font.PLAIN, 18));

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// 设置横轴字体
		plot.getDomainAxis().setLabelFont(font);// 标签
		plot.getDomainAxis().setTickLabelFont(font);// 刻度
		// 设置纵轴字体
		plot.getRangeAxis().setLabelFont(font);
		plot.getRangeAxis().setTickLabelFont(font);

		CustomBarRenderer3D renderer = new CustomBarRenderer3D();
		// 显示每个柱的数值，并修改该数值的字体属性
		DecimalFormat df = new DecimalFormat("0.00%");
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(
				"{2}", df));
		renderer.setItemLabelAnchorOffset(10D);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));

		plot.setRenderer(renderer);
		ValueMarker marker = new ValueMarker(0.70, new Color(200, 200, 255),
				new BasicStroke(1.0f), new Color(200, 200, 255),
				new BasicStroke(1.0f), 1.0f);

		plot.addRangeMarker(marker, Layer.BACKGROUND);

		renderer.setBaseItemLabelsVisible(true);
		renderer.setMaximumBarWidth(0.05);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

		rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());

		return chart;

	}

	public JPanel createBar3DChartPanel(String title, Map<String, Double> data) {
		CategoryDataset dataset = createDataset(data);
		JFreeChart chart = createChart(title, dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		return chartPanel;
	}

	static class CustomBarRenderer3D extends BarRenderer3D {

		private static final long serialVersionUID = 1L;

		public CustomBarRenderer3D() {
		}

		public Paint getItemPaint(int row, int column) {

			CategoryDataset dataset = getPlot().getDataset();
			double value = dataset.getValue(row, column).doubleValue();
			if (value >= 0.50) {
				return Color.RED;
			} else {
				return Color.GREEN;
			}
		}
	}
}
