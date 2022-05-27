package front;


import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class MyTable extends AbstractTableModel{
// JTable�� ���� ���� �����͸� ��� ���� ǥ�����ִ� ��ü�̴�. �ȵ���̵忡 ���̽���Ϳ� ������ �����̴�.
   
    //�÷��� �̸��� �迭�� �ޱ����� ���� �Ѵ�.
    private String[] colNames;
    //��� ���� �������� �����ҵ����͸� �����Ѵ�.
    private Object[][] data;

    public MyTable(String[] colNames, Object[][] data) {
        this.colNames = colNames;
        this.data = data;
    }
    
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
      return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
}
