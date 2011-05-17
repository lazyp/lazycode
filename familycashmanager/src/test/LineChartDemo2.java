package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.*;
import org.jfree.ui.*;

public class LineChartDemo2 extends ApplicationFrame {

	/** 
	 *  
	 */
	private static final long serialVersionUID = 1L;

	public LineChartDemo2(String s) {
		super(s);
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = createChart(xydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(800, 670));
		setContentPane(chartpanel);
	}

	private static XYDataset createDataset() {
		TimeSeries xyseries = new TimeSeries("收入");

		xyseries.add(new Month(1, 2011), 13.5);
		xyseries.add(new Month(2, 2011), 32);
		xyseries.add(new Month(3, 2011), 100);
		xyseries.add(new Month(4, 2011), 100);
		xyseries.add(new Month(5, 2011), 100);
		xyseries.add(new Month(6, 2011), 100);
		xyseries.add(new Month(7, 2011), 130);
		xyseries.add(new Month(8, 2011), 300);

		xyseries.add(new Month(9, 2011), 100);
		xyseries.add(new Month(10, 2011), 130);
		xyseries.add(new Month(11, 2011), 100);
		TimeSeries xyseries1 = new TimeSeries("支出");
		xyseries1.add(new Month(1, 2011), 52);
		xyseries1.add(new Month(2, 2011), 12);
		xyseries1.add(new Month(3, 2011), 222);
		xyseries1.add(new Month(4, 2011), 22);
		xyseries1.add(new Month(5, 2011), 212);
		xyseries1.add(new Month(6, 2011), 232);
		xyseries1.add(new Month(7, 2011), 222);
		xyseries1.add(new Month(8, 2011), 209);
		xyseries1.add(new Month(9, 2011), 200);
		xyseries1.add(new Month(10, 2011), 22);
		xyseries1.add(new Month(11, 2011), 23);

		TimeSeriesCollection xyseriescollection = new TimeSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);

		return xyseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset) {
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("收支平衡图",
				"date", "amount", xydataset, true, true, false);
		// JFreeChart jfreechart = ChartFactory.createXYLineChart(
		// "Line Chart Demo 2", "X", "Y", xydataset,
		// PlotOrientation.VERTICAL, true, true, false);
		jfreechart.getTitle().setFont(new Font("黑体", Font.PLAIN, 18));
		jfreechart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		jfreechart.setBackgroundPaint(Color.white);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
				.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);

		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setTickLabelFont(new Font("宋体",Font.PLAIN , 12));
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM"));

		return jfreechart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[]) {
		LineChartDemo2 linechartdemo2 = new LineChartDemo2("Line Chart Demo 2");
		linechartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(linechartdemo2);
		linechartdemo2.setVisible(true);
	}
}
