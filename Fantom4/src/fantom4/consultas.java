package fantom4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class consultas {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db= "/home/alex/Documents/Fantom4/fantom4";
    DefaultTableModel chip = new DefaultTableModel();
    DefaultTableModel chipaffected = new DefaultTableModel();
    DefaultTableModel mirna = new DefaultTableModel();
    DefaultTableModel mirnaffected = new DefaultTableModel();
    DefaultTableModel perturbation = new DefaultTableModel();
    DefaultTableModel perturbationaffected = new DefaultTableModel();
    DefaultTableModel others = new DefaultTableModel();
    DefaultTableModel othersafected = new DefaultTableModel();
    DefaultTableModel ids = new DefaultTableModel();
    DefaultTableModel genes = new DefaultTableModel();

    DefaultTableModel mirnas = new DefaultTableModel();
    DefaultTableModel mirnasderecha = new DefaultTableModel();
    DefaultTableModel mirnasizquierda = new DefaultTableModel();
    
    
    
    DefaultTableModel mirnaizquierda = new DefaultTableModel();
    DefaultTableModel mirnaderecha = new DefaultTableModel();    
    String id ;
    String nombre ;    
    public consultas() {
      try{
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:" + this.db );
         System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
      }
      catch(Exception e) {
          System.out.println(e);
      }
    }    
    public String obtenerid(String str) {
        ids = new DefaultTableModel() ;
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT feature_id "
                            + "from Entrez_gene "
                            + "where (primary_name = \'"+str+"\' );");
            ResultSetMetaData rsmd = a.getMetaData();
            int CanColumns = rsmd.getColumnCount();
            for( int i=1 ; i <= CanColumns ; i++){
                ids.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                ids.addRow(fila);
            }
            if ( ids.getRowCount() == 0  ) {
                this.id = null ;
            } else {
                this.id = String.valueOf( ids.getValueAt( 0 , 0 ) ) ;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id ;
    }    
    public DefaultTableModel mirna() {
        mirna = new DefaultTableModel(); 
        try {            
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT miRBase_pre.primary_name , miRNA_targets.weight "
                            + "FROM Entrez_gene , miRNA_targets , miRBase_pre "
                            + "where miRBase_pre.feature_id = miRNA_targets.feature1_id "
                            + "AND "
                            + "mirNA_targets.feature2_id = Entrez_gene.feature_id "
                            + "AND "
                            + "Entrez_gene.feature_id="+id+";");
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                mirna.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                mirna.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mirna ;
    }
    public void mirna2( String id_gen , String nombre_gen ) {
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT miRBase_pre.primary_name , miRNA_targets.weight "
                            + "FROM Entrez_gene , miRNA_targets , miRBase_pre "
                            + "where miRBase_pre.feature_id = miRNA_targets.feature1_id "
                            + "AND "
                            + "mirNA_targets.feature2_id = Entrez_gene.feature_id "
                            + "AND "
                            + "Entrez_gene.feature_id="+id_gen+";");
            ResultSetMetaData rsmd = a.getMetaData();
            Object[] fila2 = new Object[3];
            while (a.next()){
                Object[] fila = new Object[2];
                for ( int i = 0 ; i < 2 ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                fila2 [ 0 ] = fila [ 0 ] ;
                fila2 [ 1 ] = nombre_gen ;
                fila2 [ 2 ] = fila [ 1 ]  ;
                mirnaizquierda.addRow(fila2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void mirna3( String id_gen , String nombre_gen ) {
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, miRNA_targets.weight\n" +
                            "FROM miRNA_targets LEFT JOIN entrez_gene ON miRNA_targets.feature2_id = entrez_gene.feature_id\n" +
                            "WHERE (miRNA_targets.feature1_id = '"+ id_gen +"') ;");
            ResultSetMetaData rsmd = a.getMetaData();
            Object[] fila2 = new Object[3];
            while (a.next()){
                Object[] fila = new Object[2];
                for ( int i = 0 ; i < 2 ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                fila2 [ 0 ] = nombre_gen ;
                fila2 [ 1 ] = fila [ 0 ] ;
                fila2 [ 2 ] = fila [ 1 ]  ;
                mirnaderecha.addRow(fila2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }   
    public DefaultTableModel generalistagenes() {
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
    public DefaultTableModel mirnasizquierda( JTable tabla  ) {
        mirnaizquierda = new DefaultTableModel();
        for ( int k = 1 ; k <= 3 ; k ++ ) {
                        mirnaizquierda.addColumn ( k ) ;
        }
        for ( int i = 0 ; i < 39971 - 39970 ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0  ) {
                    String gen = String.valueOf( tabla.getValueAt ( i , j + 1 ) );
                    String idgen = String.valueOf ( tabla.getValueAt (i, j) ) ;                    
                    mirna2 (idgen , gen ) ;
                    System.out.println("Generando red de mirnas que afectan a el gen: " + gen );
                }
            }
        }
        return mirnaizquierda;
    }
    public DefaultTableModel mirnasderecha( JTable tabla  ) {
        mirnaderecha = new DefaultTableModel();
        for ( int k = 1 ; k <= 3 ; k ++ ) {
            mirnaderecha.addColumn ( k ) ;
        }        
        for ( int i = 0 ; i < tabla.getRowCount() - 39970 ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0  ) {
                    String gen = String.valueOf( tabla.getValueAt ( i , j + 1 ) );
                    String idgen = String.valueOf ( tabla.getValueAt (i, j) ) ;
                    mirna3 (idgen , gen ) ;
                    System.out.println("Generando red de mirnas afectados por el gen: " + gen );
                }
            }
        }
        return mirnaderecha;
    }
    
    public DefaultTableModel generalistamirnas() {
        mirnas = new DefaultTableModel(); 
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery( "select * from  miRBase_pre ;" );
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 3;
            for(int i=1;i<=CanColumns;i++){
                mirnas.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                mirnas.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mirnas ;
    }
    public DefaultTableModel mirnastomirnaizquierda  ( JTable tabla  ) {
        mirnasizquierda = new DefaultTableModel();
        for ( int k = 1 ; k <= 3 ; k ++ ) {
                        mirnasizquierda.addColumn ( k ) ;
        }
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0  ) {
                    String miRNA = String.valueOf( tabla.getValueAt ( i , j + 1 ) );
                    System.out.println("Generando red de mirnas que afectan a el miRNA: " + miRNA );
                    String idmiRNA = String.valueOf ( tabla.getValueAt (i, j) ) ;
                    mirnas_mirnas ( idmiRNA , miRNA ) ;
                }
            }
        }
        return mirnasizquierda;
    }
    public void mirnas_mirnas ( String id_miRNA , String nombre_miRNA ) {
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "select miRBase_pre.primary_name , miRBase_mature.primary_name\n" +
                            "from miRBase_pre , miRBase_mature , miRNA_pre2mature\n" +
                            "where miRBase_mature.feature_id = miRNA_pre2mature.feature2_id\n" +
                            "and\n" +
                            "miRBase_pre.feature_id = mirna_pre2mature.feature1_id\n" +
                            "and\n" +
                            "miRNA_pre2mature.feature1_id = '"+ id_miRNA +"' ;");
            ResultSetMetaData rsmd = a.getMetaData();
            Object[] fila2 = new Object[3];
            while (a.next()){
                Object[] fila = new Object[2];
                for ( int i = 0 ; i < 2 ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                fila2 [ 0 ] = fila [ 0 ];
                fila2 [ 1 ] = fila [ 1 ];
                fila2 [ 2 ] =  0 ;
                mirnasizquierda.addRow(fila2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public DefaultTableModel mirnastomirnaderecha( JTable tabla  ) {
        mirnasderecha = new DefaultTableModel();
        for ( int k = 1 ; k <= 3 ; k ++ ) {
            mirnasderecha.addColumn ( k ) ;
        }
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0  ) {
                    String miRNA = String.valueOf( tabla.getValueAt ( i , j + 1 ) );
                    System.out.println("Generando red de mirnas que son afectados por el miRNA: " + miRNA );
                    String idmiRNA = String.valueOf ( tabla.getValueAt (i, j) ) ;                    
                    mirnaAffectedbymirna ( idmiRNA , miRNA ) ;
                }
            }
        }
        return mirnasderecha;
    }
    public void mirnaAffectedbymirna( String id_miRNA , String miRNA ) {
        mirnaffected = new DefaultTableModel(); 
        try {
            
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT miRBase_pre.primary_name, miRNA_targets.weight\n" +
                            "FROM miRNA_targets LEFT JOIN miRBase_pre ON miRNA_targets.feature2_id = miRBase_pre.feature_id\n" +
                            "WHERE (miRNA_targets.feature1_id = '"+ id_miRNA +"') ;");
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                mirnasderecha.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                mirnasderecha.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public DefaultTableModel mirnaAffected() {
        mirnaffected = new DefaultTableModel(); 
        try {
            
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, miRNA_targets.weight\n" +
                            "FROM miRNA_targets LEFT JOIN entrez_gene ON miRNA_targets.feature2_id = entrez_gene.feature_id\n" +
                            "WHERE (miRNA_targets.feature1_id = '"+ id +"') ;");
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                mirnaffected.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                mirnaffected.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mirnaffected ;
    }
    
    public DefaultTableModel perturbation() {
        perturbation = new DefaultTableModel(); 
        try {
            
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, sirna_perturbation.weight "
                            + "FROM entrez_gene LEFT JOIN sirna_perturbation ON sirna_perturbation.feature1_id = entrez_gene.feature_id "
                            + "WHERE (sirna_perturbation.feature2_id = '"+ id +"') ;");
            
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                perturbation.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                perturbation.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return perturbation ;
    }
    public DefaultTableModel perturbationAffected() {
        perturbationaffected = new DefaultTableModel(); 
        try {
            
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, sirna_perturbation.weight "
                            + "FROM sirna_perturbation LEFT JOIN entrez_gene ON sirna_perturbation.feature2_id = entrez_gene.feature_id "
                            + "WHERE (sirna_perturbation.feature1_id = '"+ id +"') ;");
           
            ResultSetMetaData rsmd = a.getMetaData();             
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                perturbationaffected.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                perturbationaffected.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return perturbationaffected ;
    }
    
    public DefaultTableModel chip() {
        chip.getDataVector().removeAllElements();
        chip = new DefaultTableModel(); 
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, pubmedid.weight\n" +
                            "FROM entrez_gene LEFT JOIN pubmedid ON pubmedid.feature1_id = entrez_gene.feature_id\n" +
                            "WHERE (pubmedid.feature2_id = '"+ id +"') ;");
            ResultSetMetaData rsmd = a.getMetaData();
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                chip.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                chip.addRow(fila);
            }
            
            ResultSet a1 = statement.executeQuery(
                    "SELECT entrez_gene.primary_name,chip_chip.weight\n" +
                            "FROM entrez_gene LEFT JOIN chip_chip ON chip_chip.feature1_id = entrez_gene.feature_id\n" +
                            "WHERE (chip_chip.feature2_id = "+id+") ;");    
            while (a1.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                chip.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return chip ;
    }
    public DefaultTableModel chipAffected() {
        chipaffected.getDataVector().removeAllElements();
        chipaffected = new DefaultTableModel(); 
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, pubmedid.weight "
                            + "FROM pubmedid LEFT JOIN entrez_gene ON pubmedid.feature2_id = entrez_gene.feature_id "
                            + "WHERE "
                            + "(pubmedid.feature1_id = "+id+") ;");
            ResultSetMetaData rsmd = a.getMetaData();
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                chipaffected.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                chipaffected.addRow(fila);
            }
            
            ResultSet a1 = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, chip_chip.weight "
                            + "FROM chip_chip LEFT JOIN entrez_gene ON chip_chip.feature2_id = entrez_gene.feature_id "
                            + "WHERE "
                            + "(chip_chip.feature1_id = "+id+") ;");    
            while (a1.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                chipaffected.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return chipaffected ;
    }
    
    public DefaultTableModel others() {
        others.getDataVector().removeAllElements();
        others = new DefaultTableModel(); 
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, goldstd_tf.sym_value "
                            + "FROM entrez_gene LEFT JOIN goldstd_tf ON goldstd_tf.feature1_id = entrez_gene.feature_id "
                            + "WHERE (goldstd_tf.feature2_id = "+id+") ;");
            ResultSetMetaData rsmd = a.getMetaData();
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                others.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                others.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return others ;
    }
    public DefaultTableModel othersAffected() {
        othersafected.getDataVector().removeAllElements();
        othersafected = new DefaultTableModel(); 
        try {
            statement = connection.createStatement();
            ResultSet a = statement.executeQuery(
                    "SELECT entrez_gene.primary_name, goldstd_tf.sym_value "
                            + "FROM goldstd_tf LEFT JOIN entrez_gene ON goldstd_tf.feature2_id = entrez_gene.feature_id "
                            + "WHERE (goldstd_tf.feature1_id = "+id+") ;");
            ResultSetMetaData rsmd = a.getMetaData();
            int CanColumns = 2;
            for(int i=1;i<=CanColumns;i++){
                othersafected.addColumn(rsmd.getColumnLabel(i));
            }
            while (a.next()){
                Object[] fila = new Object[CanColumns];
                for ( int i = 0 ; i < CanColumns ; i++ ) {
                    fila [ i ] = a.getObject( i + 1 );
                }
                othersafected.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return othersafected ;
    }
    public void desconectar() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Desconectado de la base de datos [ " + this.db + "]");
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public ResultSet getResultSet() {
        return resultSet;
    }
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    public Statement getStatement() {
        return statement;
    }
    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    public DefaultTableModel getChip() {
        return chip;
    }
    public void setChip(DefaultTableModel chip) {
        this.chip = chip;
    }
    public DefaultTableModel getMirna() {
        return mirna;
    }
    public void setMirna(DefaultTableModel mirna) {
        this.mirna = mirna;
    }
    public DefaultTableModel getIds() {
        return ids;
    }
    public void setIds(DefaultTableModel ids) {
        this.ids = ids;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public DefaultTableModel getChipaffected() {
        return chipaffected;
    }
    public void setChipaffected(DefaultTableModel chipaffected) {
        this.chipaffected = chipaffected;
    }
    public DefaultTableModel getPerturbation() {
        return perturbation;
    }
    public void setPerturbation(DefaultTableModel perturbation) {
        this.perturbation = perturbation;
    }
    public DefaultTableModel getMirnaffected() {
        return mirnaffected;
    }
    public void setMirnaffected(DefaultTableModel mirnaffected) {
        this.mirnaffected = mirnaffected;
    }
    public DefaultTableModel getPerturbationaffected() {
        return perturbationaffected;
    }
    public void setPerturbationaffected(DefaultTableModel perturbationaffected) {
        this.perturbationaffected = perturbationaffected;
    }
    public DefaultTableModel getOthers() {
        return others;
    }
    public void setOthers(DefaultTableModel others) {
        this.others = others;
    }
    public DefaultTableModel getOthersafected() {
        return othersafected;
    }
    public void setOthersafected(DefaultTableModel othersafected) {
        this.othersafected = othersafected;
    }
    public DefaultTableModel getGenes() {
        return genes;
    }
    public void setGenes(DefaultTableModel genes) {
        this.genes = genes;
    }
    public DefaultTableModel getMirnaizquierda() {
        return mirnaizquierda;
    }
    public void setMirnaizquierda(DefaultTableModel mirnaizquierda) {
        this.mirnaizquierda = mirnaizquierda;
    }
    public DefaultTableModel getMirnaderecha() {
        return mirnaderecha;
    }
    public void setMirnaderecha(DefaultTableModel mirnaderecha) {
        this.mirnaderecha = mirnaderecha;
    }
}