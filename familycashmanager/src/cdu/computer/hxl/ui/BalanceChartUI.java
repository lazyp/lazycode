package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;
import cdu.computer.hxl.service.IncomeService;

public class BalanceChartUI extends BaseJPanel {

	private static final long serialVersionUID = 1L;
	private static final IncomeService inService = (IncomeService) ObjectFactory
			.getInstance("incomeService");
	private static final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");

	private String info = "2011年";

	/**
	 * Create the panel.
	 */
	public BalanceChartUI() {
		JPanel panel = new ChartPanel(createChart(createDataset(
				inService.statistiIncomeForBalance(),
				cService.statistiCostForBalance())));
		panel.setPreferredSize(new Dimension(700, 600));
		add(panel);
	}

	@Override
	protected void init() {

	}

	private XYDataset createDataset(Map<Integer, Double> incomeMap,
			Map<Integer, Double> costMap) {
		TimeSeries series1 = new TimeSeries("收入");
		Calendar calendar = Calendar.getInstance();

		info += " 总收入:" + incomeMap.get(1000);
		incomeMap.remove(1000);

		Iterator<Map.Entry<Integer, Double>> iter = incomeMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, Double> entry = iter.next();
			calendar.set(Calendar.MONTH, entry.getKey());
			calendar.set(Calendar.DATE, 1);
			series1.add(new Month(calendar.getTime()), entry.getValue());
		}

		TimeSeries series2 = new TimeSeries("支出");

		info += " 总支出:" + costMap.get(1000);
		costMap.remove(1000);

		iter = costMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, Double> entry = iter.next();
			calendar.set(Calendar.MONTH, entry.getKey());
			calendar.set(Calendar.DATE, 1);
			series2.add(new Month(calendar.getTime()), entry.getValue());
		}

		TimeSeriesCollection xyseriescollection = new TimeSeriesCollection();
		xyseriescollection.addSeries(series1);
		xyseriescollection.addSeries(series2);

		return xyseriescollection;
	}

	private JFreeChart createChart(XYDataset xydataset) {
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("收支平衡图",
				info, "金额", xydataset, true, true, false);
		// JFreeChart jfreechart = ChartFactory.createXYLineChart(
		// "Line Chart Demo 2", "X", "Y", xydataset,
		// PlotOrientation.VERTICAL, true, true, false);
		Font font1 = new Font("黑体", Font.PLAIN, 18);
		Font font2 = new Font("宋体", Font.PLAIN, 12);
		Font font3 = new Font("宋体", Font.PLAIN, 15);

		jfreechart.getTitle().setFont(font1);
		jfreechart.getLegend().setItemFont(font2);
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
		dateaxis.setTickLabelFont(font2);
		dateaxis.setLabelFont(font3);
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM"));
		xyplot.getRangeAxis().setLabelFont(font3);

		return jfreechart;
	}

}
