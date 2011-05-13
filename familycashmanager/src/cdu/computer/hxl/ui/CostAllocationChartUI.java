package cdu.computer.hxl.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class CostAllocationChartUI extends BaseJPanel {

	private static final long serialVersionUID = 5557495476388906150L;
	private static final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");

	/**
	 * Create the panel.
	 */
	public CostAllocationChartUI() {

	}

	@Override
	protected void init() {
		this.setLayout(new FlowLayout());
		new ThreadExecutorUtils() {

			@Override
			protected void task() {

				addComponent(createAllocationPanel());

				addComponent(createCostMoneyAllocationPanel());
			}
		}.exec();

	}

	protected JPanel createAllocationPanel() {

		DefaultPieDataset dataset = new DefaultPieDataset();

		Map<String, Object> data = cService.statistiCostForallocation();

		Set<Map.Entry<String, Object>> dataSet = data.entrySet();
		Iterator<Map.Entry<String, Object>> iter = dataSet.iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			dataset.setValue(entry.getKey(), (Double) entry.getValue());
		}

		JFreeChart chart = ChartFactory.createPieChart("支出分布图", // chart
																// title
				dataset, // data
				true, // include legend
				true, false);

		Font font = new Font("宋体", Font.PLAIN, 12);

		chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 18));
		chart.getLegend().setItemFont(font);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(font);
		plot.setNoDataMessage("没有数据");
		plot.setCircular(false);
		plot.setLabelGap(0.02);

		JPanel panel1 = new ChartPanel(chart);
		panel1.setPreferredSize(new Dimension(400, 280));
		return panel1;
	}

	protected JPanel createCostMoneyAllocationPanel() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		Map<String, Object> data = cService.statistiCostForMoneyallocation();

		Set<Map.Entry<String, Object>> dataSet = data.entrySet();
		Iterator<Map.Entry<String, Object>> iter = dataSet.iterator();

		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			dataset.setValue(entry.getKey(), (Double) entry.getValue());
		}

		JFreeChart chart = ChartFactory.createPieChart("支出资金流向图", // chart
																	// title
				dataset, // data
				true, // include legend
				true, false);

		Font font = new Font("宋体", Font.PLAIN, 12);

		chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 18));
		chart.getLegend().setItemFont(font);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(font);
		plot.setNoDataMessage("没有数据");
		plot.setCircular(false);
		plot.setLabelGap(0.02);

		JPanel panel1 = new ChartPanel(chart);
		panel1.setPreferredSize(new Dimension(400, 280));
		return panel1;
	}

	protected void addComponent(Component comp) {
		this.add(comp);
		validate();
	}
}
