/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando Landeros
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import javax.swing.JPanel;
import javax.swing.UIManager;

public class Imagen
{
        private JFrame frame;
        private JLabel labelImagen;
        private JButton botonAtras, botonAdelante;
        private JPanel panelComponentes, panelImagen, panelContenidoDirectorio;
        private JFileChooser fileChooser;
        private JScrollPane scroll;
        private JMenuBar barraMenu;
        private JMenu menuArchivo;
        private JMenuItem itemArchivo;
        private String[] arregloImagenes;
        private int contador = 0;
        private String contenidoDirectorio;
       
        public static void main(String arg[])
        {
                try
                {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
        catch (Exception e)
                {
                        e.printStackTrace();
                }
                new Imagen();
        }
         public Imagen()
        {
                //Creando frame principal y obteniendo un Contenedor y un manejador BorderLayout
                frame = new JFrame( "Cómico!" );
                frame.getContentPane().setLayout( new BorderLayout() );
               
                /**Creacion de componentes*/
               
                //Creando la barra de menus
                barraMenu = new JMenuBar();
                frame.setJMenuBar(barraMenu);          
                //Construyendo el primer menu
                menuArchivo = new JMenu("Archivo");
                barraMenu.add(menuArchivo);
                //Creando items del menu
                itemArchivo = new JMenuItem("Abrir directorio...");
                menuArchivo.add(itemArchivo);
               
                //Label
                labelImagen = new JLabel();
                scroll = new JScrollPane(labelImagen);
               
                //Botones
                botonAdelante = new JButton("Siguiente");
                botonAtras = new JButton( "Anterior" );
                               
                //Creando paneles y añadiendolos al frame principal
                panelComponentes = new JPanel();
                panelComponentes.setLayout( new FlowLayout() );
                panelComponentes.add( botonAtras);
                panelComponentes.add( botonAdelante);
                panelComponentes.setBorder( BorderFactory.createTitledBorder ( "Controles de imagen" ) );
                frame.add( panelComponentes , BorderLayout.SOUTH );
                panelImagen = new JPanel();
                panelImagen.setLayout( new BorderLayout() );
                panelImagen.setBorder( BorderFactory.createTitledBorder ( "Visualizacion de la imagen" ) );
                panelImagen.add( scroll, BorderLayout.CENTER );
                frame.add( panelImagen , BorderLayout.CENTER );
               
               
                //Crear panel para el contenido del directorio
                panelContenidoDirectorio = new JPanel();
                panelContenidoDirectorio.setLayout( new BorderLayout() );
               
                //Estableciendo visibilidad, tamaño y cierrre de la aplicacion
                frame.setVisible( true );
                frame.setBounds(500,200, 500, 800);
                frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
               
               
                //Creando FileChooser
                fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               
                //Action Listener para el boton atras
                botonAtras.addActionListener (new ActionListener ()
                {
                   public void actionPerformed (ActionEvent e )
                   {                       
                         //Disminuyendo y validando contador y el string contenidoDirectorio y arregloImagenes
                           if(arregloImagenes!= null && contenidoDirectorio != null )
                                {
                                        contador--;
                                        if(contador == -1)
                                        {
                                                contador = arregloImagenes.length -1;
                                        }
                                        else
                                        {
                                                //Creando y seteando objeto ico en labelImagen
                                                ImageIcon ico = new ImageIcon(contenidoDirectorio+"/"+arregloImagenes[contador]);
                                                labelImagen.setIcon(ico);
                                        }
                                }
                   }
                });
                //Accion de botonAdelante
                botonAdelante.addActionListener (new ActionListener ()
                {
                   public void actionPerformed (ActionEvent e)
                   {                   
                         //Aumentando y validando contador y el string contenidoDirectorio y arregloImagenes
                           if(arregloImagenes != null && contenidoDirectorio != null)
                                {                                  
                                        contador++;
                                        if(contador == arregloImagenes.length)
                                        {
                                                contador = 0;
                                        }
                                        else
                                        {
                                        //Creando y seteando objeto ico en labelImagen
                                        ImageIcon ico = new ImageIcon(contenidoDirectorio+"/"+arregloImagenes[contador]);
                                        labelImagen.setIcon(ico);
                                        }
                                }
                   }
                });
                //Accion de itemArchivo
                itemArchivo.addActionListener (new ActionListener ()
                {
                   public void actionPerformed (ActionEvent e)
                   {
                         //Valor que tomara el fileChooser
                                int regresaValor = fileChooser.showDialog( null, "Abrir imagen" );     
                                //Accion del fileChooser
                                if(regresaValor == JFileChooser.APPROVE_OPTION)
                                {
                                        //Crear propiedades para ser utilizadas por fileChooser
                                        File archivoElegido = fileChooser.getSelectedFile();
                                        //Obteniendo el contenido del directorio seleccionado
                                        contenidoDirectorio = archivoElegido.getParent();
                                        //Usando contenidoDirectorio para usar propiedades de File
                                        File direccion = new File( contenidoDirectorio );
                                        if(direccion.isDirectory())
                                        {
                                                contador = 0;
                                                arregloImagenes = null;
                                                //Pasando el contenido del directorio al arreglo
                                                arregloImagenes = direccion.list( new FilenameFilter()
                                                {                                                      
                                                        @Override
                                                        //Sobrescribiendo el metodo accept para ser usado con un filtro de archivos
                                                        public boolean accept(File dir, String name)
                                                        {
                                                                if (name.endsWith(".jpg") ||
                                                                                name.endsWith(".jpeg") ||
                                                                                name.endsWith(".gif") ||
                                                                                name.endsWith(".png"))
                                                                                {
                                                                                        return true;
                                                                                }
                                                                                else
                                                                                {
                                                                                        return false;
                                                                                }                                                              
                                                        }
                                                });                                            
                                        }                                      
                                }
                   }
                });
        }
}