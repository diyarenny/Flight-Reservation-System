package ca1;

//imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class FlightReservationFrame extends JFrame {

	//panels
	private JPanel pnl = new JPanel();
	private JPanel pnl2 = new JPanel();
	private JPanel pnl3 = new JPanel();
	
	//comboBox
	private JComboBox cb = new JComboBox();
	private JComboBox cb2 = new JComboBox();
	
	//textField
	private JTextField textField = new JTextField(10);
	
	//buttons
	private JButton btn = new JButton("Search");
	private JButton btn2 = new JButton("Show All");
	private JButton btn3 = new JButton("Purchase");
	
	//labels
	private JLabel lbl = new JLabel("Departure: ");
	private JLabel lbl2 = new JLabel("Number of Tickets: ");
	private JLabel lbl3 = new JLabel("Destination: ");
	private JLabel lbl4 = new JLabel("Today's date: ");
	private JLabel dateLabel = new JLabel(" ");
	
	private String[] departure = {"Copenhagen", "Dublin", "Edinburgh", "London", "New York", "Oslo", "San Francisco"};
	private String[] destinations = {"Copenhagen", "Dublin", "Edinburgh", "London", "New York", "Oslo", "San Francisco"};
	
	public FlightReservationFrame()
	{
		FlightReservationModel tm = new FlightReservationModel();  //creates a new flight object
		JTable tbl = new JTable(tm);
        tbl.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tbl.setFillsViewportHeight(true);
        
        //create JComboBox
        cb = new JComboBox(departure);
        cb2 = new JComboBox(destinations);
        cb.setSelectedIndex(1);
        
        //Formats and displays today's date
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        String dateString = formatter.format(today);
        pnl3.add(lbl4);
        pnl3.add(dateLabel);
        dateLabel.setText(dateString);
        dateLabel.setForeground(Color.blue);
        lbl4.setForeground(Color.blue);
        
      //add sorting
       tbl.setAutoCreateRowSorter(true);
        
        
        TableColumn column = null; 
    	for (int i = 0; i < tbl.getColumnCount(); i++) { 
    		column = tbl.getColumnModel().getColumn(i); 
    		if (i == 0) { 
    		      column.setPreferredWidth(100); //first column is twice bigger than the rest 
    		} else { 
    		      column.setPreferredWidth(50); 
    		} 
    	} 
    	
    	//sorting 
    	TableRowSorter<TableModel>sorter = new TableRowSorter<TableModel>(tbl.getModel());
    	tbl.setRowSorter(sorter); 
    	
    	
    	
    	//action listener for the search button
    	btn .addActionListener(new ActionListener()
    			{
    		@Override
    		public void actionPerformed(ActionEvent e) 
    		{
    			
    			if(cb.getSelectedItem() == cb2.getSelectedItem())
    			{
		            JOptionPane.showMessageDialog(FlightReservationFrame.this, "Departure and destination of cities cannot be the same." , "Warning Message",
			        JOptionPane.WARNING_MESSAGE);
    			}
    			else{
    			RowFilter rf = null;
    			try
    			{
    				ArrayList<RowFilter<Object,Object>> rowFil = new ArrayList<RowFilter<Object,Object>>(2);
    				rowFil.add(RowFilter.regexFilter(cb.getSelectedItem().toString(), 0));
    				rowFil.add(RowFilter.regexFilter(cb2.getSelectedItem().toString(), 1));
    				rf = RowFilter.andFilter(rowFil);

    			}
    			catch (java.util.regex.PatternSyntaxException a)
    			{
    				return;
    			}
    			sorter.setRowFilter(rf);
    			}
    		}  
    		});
				
				
				
				 //action listener for the show all button
				btn2.addActionListener(new ActionListener() 
		    	{ 
		      @Override
		    	public void actionPerformed(ActionEvent e) 
		    		{
		    			
						sorter.setRowFilter(null);
			     }                                               
		          });  
				
		            
				
				 //action listener for the purchase button
				btn3.addActionListener(new ActionListener() 
		    	{ 
		      @Override
		    	public void actionPerformed(ActionEvent e) 
		    		{
		    			
						try
						{
						     Integer.parseInt(textField.getText());
						}
						catch(NumberFormatException error)
						{
							JOptionPane.showMessageDialog(FlightReservationFrame.this, "You must enter a number! ");
							
						}
		    			
			     }                                               
		          });  
				
		//adds components		
		JScrollPane srl = new JScrollPane(tbl);
		pnl3.add(lbl2);
	    pnl3.add(textField);
	    pnl3.add(btn3);
	    pnl3.add(lbl4);
	    pnl3.add(dateLabel);
	    pnl2.add(lbl);
	    pnl2.add(cb);
	    pnl2.add(lbl3);
	    pnl2.add(cb2);
	    pnl2.add(btn);
	    pnl2.add(btn2);
		pnl.add(srl);
		add(pnl , BorderLayout.CENTER);
		add(pnl2 ,BorderLayout.NORTH);
		add(pnl3, BorderLayout.SOUTH);
	 
		//set frame properties
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setTitle("Flight Reservation");

    		
	}
	
	//main method
	public static void main(String[] args) {
		
		new FlightReservationFrame();
		

	}}
    			

    						
	

