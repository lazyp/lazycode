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

		Map<String, Object> data = cService.statistiCostForallocation();
		PieChartUI pie = new PieChartUI();
		return pie.createPieChartPanel("支出分布图", data);

	}

	protected JPanel createCostMoneyAllocationPanel() {

		Map<String, Double> data = cService.statistiCostForMoneyallocation();

		Bar3DChartUI barChart = new Bar3DChartUI();

		JPanel panel = barChart.createBar3DChartPanel("资金流向柱状图", data);
		panel.setPreferredSize(new Dimension(400, 280));
		return panel;
	}

	protected void addComponent(Component comp) {
		this.add(comp);
		validate();
	}
}
