package cdu.computer.hxl.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JPanel;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.IncomeService;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class IncomeAllocationChartUI extends BaseJPanel {

	private static final long serialVersionUID = -8114364422893235600L;
	private static final JPanel contentPanel = new JPanel();
	private static final IncomeService inService = (IncomeService) ObjectFactory
			.getInstance("incomeService");

	public IncomeAllocationChartUI() {
		contentPanel.setPreferredSize(new Dimension(405, 570));
		this.add(contentPanel);
	}

	@Override
	protected void init() {
		contentPanel.setLayout(new GridLayout(2, 1, 5, 5));
		
		new ThreadExecutorUtils() {

			@Override
			protected void task() {

				// DefaultPieDataset dataset = new DefaultPieDataset();
				//
				// Map<String, Object> data = inService.statistiIncome();
				//
				// Set<Map.Entry<String, Object>> dataSet = data.entrySet();
				// Iterator<Map.Entry<String, Object>> iter =
				// dataSet.iterator();
				//
				// while (iter.hasNext()) {
				// Map.Entry<String, Object> entry = iter.next();
				// dataset.setValue(entry.getKey(), (Double) entry.getValue());
				// }
				//
				// JFreeChart chart = ChartFactory.createPieChart("收入分布图", //
				// chart
				// // title
				// dataset, // data
				// true, // include legend
				// true, false);
				//
				// Font font = new Font("宋体", Font.PLAIN, 12);
				//
				// chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 18));
				// chart.getLegend().setItemFont(font);
				//
				// PiePlot plot = (PiePlot) chart.getPlot();
				// plot.setLabelFont(font);
				// plot.setNoDataMessage("没有数据");
				// plot.setCircular(false);
				// plot.setLabelGap(0.02);
				//
				// JPanel panel = new ChartPanel(chart);
				// panel.setPreferredSize(new Dimension(400, 280));
				contentPanel.removeAll();
				addComponent(createAllocationPanel());

				addComponent(createIncomeMoneyAllocationPanel());
			}
		}.exec();

	}

	protected JPanel createIncomeMoneyAllocationPanel() {

		Map<String, Double> data = inService.statistiIncomeForMoneyallocation();

		Bar3DChartUI barChart = new Bar3DChartUI();

		JPanel panel = barChart.createBar3DChartPanel("资金收入柱状图", data);
		panel.setPreferredSize(new Dimension(400, 280));
		return panel;
	}

	protected JPanel createAllocationPanel() {
		Map<String, Object> data = inService.statistiIncome();
		PieChartUI pie = new PieChartUI();
		return pie.createPieChartPanel("收入分布图", data);
	}

	protected void addComponent(Component comp) {
		contentPanel.add(comp);
		validate();
	}
}
