package ua.nure.cs.sort;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import ua.nure.cs.util.ArrayUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SortFrame extends ApplicationFrame {

    public SortFrame(String title) {

        super(title);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "log(n)",
                "log(T(n))",
                createSortDataSet(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        XYPlot plot = chart.getXYPlot();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(3.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
        try {
            ChartUtils.saveChartAsJPEG(new File("math_collection_test/src/main/resources/sort.jpeg"), chart, 500, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XYSeriesCollection createSortDataSet() {

        Map<String, Long> sortTest1 = ArrayUtils.testSortMethods(10000, true);
        Map<String, Long> sortTest2 = ArrayUtils.testSortMethods(30000, true);
        Map<String, Long> sortTest3 = ArrayUtils.testSortMethods(90000, true);
        Map<String, Long> sortTest4 = ArrayUtils.testSortMethods(270000, true);
        Map<String, Long> sortTest5 = ArrayUtils.testSortMethods(810000, true);

        XYSeries selectionSortSeries = new XYSeries("Selection sort");
        XYSeries mergeSortSeries = new XYSeries("Merge sort");
        XYSeries heapSortSeries = new XYSeries("Heap sort");

        selectionSortSeries.add(Math.log(10000), Math.log(sortTest1.get("selection")));
        selectionSortSeries.add(Math.log(30000), Math.log(sortTest2.get("selection")));
        selectionSortSeries.add(Math.log(90000), Math.log(sortTest3.get("selection")));
        selectionSortSeries.add(Math.log(270000), Math.log(sortTest4.get("selection")));
        selectionSortSeries.add(Math.log(810000), Math.log(sortTest5.get("selection")));

        mergeSortSeries.add(Math.log(10000), Math.log(sortTest1.get("merge")));
        mergeSortSeries.add(Math.log(30000), Math.log(sortTest2.get("merge")));
        mergeSortSeries.add(Math.log(90000), Math.log(sortTest3.get("merge")));
        mergeSortSeries.add(Math.log(270000), Math.log(sortTest4.get("merge")));
        mergeSortSeries.add(Math.log(810000), Math.log(sortTest5.get("merge")));

        heapSortSeries.add(Math.log(10000), Math.log(sortTest1.get("heap")));
        heapSortSeries.add(Math.log(30000), Math.log(sortTest2.get("heap")));
        heapSortSeries.add(Math.log(90000), Math.log(sortTest3.get("heap")));
        heapSortSeries.add(Math.log(270000), Math.log(sortTest4.get("heap")));
        heapSortSeries.add(Math.log(810000), Math.log(sortTest5.get("heap")));

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(selectionSortSeries);
        dataset.addSeries(mergeSortSeries);
        dataset.addSeries(heapSortSeries);

        return dataset;
    }

    public void showSortFrame() {
        SortFrame sortFrame = new SortFrame("Array Sorting Testing");
        sortFrame.pack();
        RefineryUtilities.centerFrameOnScreen(sortFrame);
        sortFrame.setVisible(true);
    }
}
