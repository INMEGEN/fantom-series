package fantom4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Generacion_De_Mirnas {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db= "/home/alex/Documents/Fantom4/fantom4";
    
    DefaultTableModel genes = new DefaultTableModel();
    DefaultTableModel mirnaizquierda = new DefaultTableModel();
    DefaultTableModel mirnaderecha = new DefaultTableModel();
    String id ;
    
    public DefaultTableModel generalistagenes () {
        genes = new DefaultTableModel(); 
        try {
            
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery( "SELECT * FROM Entrez_gene ;" );
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                genes.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                genes.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return genes ;
    }
}