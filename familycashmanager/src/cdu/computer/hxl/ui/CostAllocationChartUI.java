package cdu.computer.hxl.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JPanel;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class CostAllocationChartUI extends BaseJPanel {

	private static final long serialVersionUID = 5557495476388906150L;
	private static final JPanel contentPanel = new JPanel();
	private static final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");

	/**
	 * Create the panel.
	 */
	public CostAllocationChartUI() {
		contentPanel.setPreferredSize(new Dimension(405, 570));
		this.add(contentPanel);
	}

	@Override
	protected void init() {
		contentPanel.setLayout(new GridLayout(2, 1, 5, 5));
		new ThreadExecutorUtils() {

			@Override
			protected void task() {
				contentPanel.removeAll();
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
		contentPanel.add(comp);
		validate();
	}
}
