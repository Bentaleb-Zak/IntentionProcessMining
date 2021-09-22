package com;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public LineChart_AWT( String applicationTitle , String xlabel, String ylabel, String chartTitle, int x[], double y[] , int l ) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         xlabel,
         ylabel,
         createDataset(x, y, l),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset(int x[], double y[] , int l) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      
      for(int i = 0; i < l; i++) {
    	  dataset.addValue(y[i], "distance", Integer.toString(x[i]));
      }
      
      return dataset;
   }
 
}