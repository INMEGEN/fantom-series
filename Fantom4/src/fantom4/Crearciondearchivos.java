package fantom4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Crearciondearchivos {
    
    public void generarArchivos1 ( JTable tabla , String a , String b ) throws IOException {
        String ruta = "/home/alex/Documents/Fantom4/" + b + "_regula_" + a + ".txt";
        String c = null ;
        String d = null ;
        File f = new File ( ruta );
        if ( f.exists() ) { 
            f.delete() ; 
        } else { 
            f.createNewFile() ; 
        }
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0 ) { c = String.valueOf(tabla.getValueAt (i , j ))  ; }
                if ( j == 1 ) { d = String.valueOf(tabla.getValueAt (i , j ))  ; }
            }
           
            wr.printf( b + " , " + c + " , " + d + "\n");

        }
        wr.close();
        bw.close();
        br.close();
        fr.close();
    }
    
    public void generarArchivos2 ( JTable tabla , String a , String b ) throws IOException {
        String ruta = "/home/alex/Documents/Fantom4/" + b + "_es_regulado_por_" + a + ".txt";
        String c = null ;
        String d = null ;
        File f = new File ( ruta );
        if ( f.exists() ) { 
            f.delete() ; 
        } else { 
            f.createNewFile() ; 
        }
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0 ) { c = String.valueOf(tabla.getValueAt (i , j ))  ; }
                if ( j == 1 ) { d = String.valueOf(tabla.getValueAt (i , j ))  ; }
                
                
            }
            
            wr.printf( b + " , " + c + " , " + d + "\n");
        }
        wr.close();
        bw.close();
        br.close();
        fr.close();
    }
    
    public void generarArchivomiRNA1 ( JTable tabla ) throws IOException {
        String ruta = "/home/alex/Documents/Fantom4/miRNAs_que_regulan_genes.txt";
        File f = new File ( ruta );
        if ( f.exists() ) {
            f.delete() ;
        } else {
            f.createNewFile() ;
        }
        String c = null ,d = null, e = null;
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0 ) { 
                    c = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 1 ) { 
                    d = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 2 ) { 
                    e = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
            }
            wr.printf( c + " , " + d + " , " + e + "\n");
        }
        wr.close();
        bw.close();
        br.close();
        fr.close();
    }
    
    public void generarArchivomiRNA2 ( JTable tabla ) throws IOException {
        String ruta = "/home/alex/Documents/Fantom4/miRNAs_que_son_regulados_por_genes.txt";
        File f = new File ( ruta );
        if ( f.exists() ) {
            f.delete() ;
        } else {
            f.createNewFile() ;
        }
        String c = null ,d = null, e = null;
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0 ) { 
                    c = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 1 ) { 
                    d = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 2 ) { 
                    e = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
            }
            wr.printf( c + " , " + d + " , " + e + "\n");
        }
        wr.close();
        bw.close();
        br.close();
        fr.close();
    }
    public void generarArchivomiRNA3 ( JTable tabla ) throws IOException {
        String ruta = "/home/alex/Documents/Fantom4/miRNAs_que_son_regulados_por_miRNAs.txt";
        File f = new File ( ruta );
        if ( f.exists() ) {
            f.delete() ;
        } else {
            f.createNewFile() ;
        }
        String c = null ,d = null, e = null;
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0 ) { 
                    c = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 1 ) { 
                    d = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 2 ) { 
                    e = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
            }
            wr.printf( c + " , " + d + " , " + e + "\n");
        }
        wr.close();
        bw.close();
        br.close();
        fr.close();
    }
    public void generarArchivomiRNA4 ( JTable tabla ) throws IOException {
        String ruta = "/home/alex/Documents/Fantom4/miRNAs_que_regulan_miRNAs.txt";
        File f = new File ( ruta );
        if ( f.exists() ) {
            f.delete() ;
        } else {
            f.createNewFile() ;
        }
        String c = null ,d = null, e = null;
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader(fr);
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        for ( int i = 0 ; i < tabla.getRowCount() ; i++ ) {
            for ( int  j = 0 ; j < tabla.getColumnCount() ; j++ ) {
                if ( j == 0 ) { 
                    c = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 1 ) { 
                    d = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
                if ( j == 2 ) { 
                    e = String.valueOf(tabla.getValueAt (i , j ))  ; 
                }
            }
            wr.printf( c + " , " + d + " , " + e + "\n");
        }
        wr.close();
        bw.close();
        br.close();
        fr.close();
    }
}