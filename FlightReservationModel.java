package ca1;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class FlightReservationModel extends AbstractTableModel {
	
	private String[] columnNames = {"Departure",
            "Destination",
            "Flight Num",
            "Number of Txx",
            "First Class Available"};
	
	private Object[][] data = {
		    {"Dublin", "Copenhagen", "SK538", new Integer(200), new Boolean(false)},
		    {"Dublin", "Oslo", "DY1363",new Integer(27), new Boolean(false)},
		    {"San Francisco","Dublin", "EI147 ", new Integer(30),new Boolean(true)},
		    {"Edinburgh","Dublin", "EI147",new Integer(50), new Boolean(false)},
		    {"New York","Dublin", "EI109",new Integer(40), new Boolean(true)},
		   
		};

	DefaultTableModel model = new DefaultTableModel(data, columnNames);
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Class getColumnClass(int c)              
	{
		return getValueAt(0, c).getClass();
	}
	
	@Override
	public boolean isCellEditable(int row,  int col)
	{                              
		if (col < 3)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
	
	
}
