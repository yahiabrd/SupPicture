package metier;

import views.Thumbnail;
import models.PanelPictures;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainApp extends JFrame {
	
	private int widthApp = 540;
	private int heigthApp = 500;
	private String imageFolder = "images/";
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu editMenu = new JMenu("Edit");
	private JMenu helpMenu = new JMenu("help");
	private JMenuItem addItem = new JMenuItem("Add a new picture");
	private JMenuItem delItem = new JMenuItem("Delete a picture");
	private JMenuItem renItem = new JMenuItem("Rename a picture");
	private JMenuItem about = new JMenuItem("About");
	
	public static void main(String args[]) {
        new MainApp();
    }

    public MainApp() {
		menuBar.add(fileMenu);
		fileMenu.add(addItem);
		menuBar.add(editMenu);
		editMenu.add(delItem);
		editMenu.add(renItem);
		menuBar.add(helpMenu);
		helpMenu.add(about);

	    setJMenuBar(menuBar);
        setTitle("SupPicture");
        setBounds(0, 0, widthApp, heigthApp);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
       
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, widthApp, heigthApp);
        mainPanel.setBackground(new Color(41, 77, 135));
        add(mainPanel);
        mainPanel.setLayout(new FlowLayout(0));

        File mainFolder = new File(imageFolder);
        File[] listFile = mainFolder.listFiles();
        for (File file : listFile) {
        	Thumbnail img1 = new Thumbnail(file);
    		//String[] nomImage = file.getName().split("\\.");
        	img1.changeText(file.getName());
        	mainPanel.add(img1);
        	
        	img1.addMouseListener(new MouseAdapter(){ 
        		public void mousePressed(MouseEvent me) { 
        			try {
        				displayImg(file.getName());
        			}catch(Exception e) {
        				e.printStackTrace();
        			}
        		} 
           }); 
        } 
        
        addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String addMessage = JOptionPane.showInputDialog("Please enter the path of the file that you want to add: ");
				String nameFile = JOptionPane.showInputDialog("Please enter a name for this image: ");
				
				File file = new File(addMessage);
				File file2 = new File(imageFolder+nameFile);
				
				if(file.exists()) {
					try {
						Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				Thumbnail img1 = new Thumbnail(file2);
	        	img1.changeText(file2.getName());
	        	mainPanel.add(img1);
	        	mainPanel.revalidate();
	        	mainPanel.repaint();
	        	
	        	img1.addMouseListener(new MouseAdapter(){ 
	        		public void mousePressed(MouseEvent me) { 
	        			try {
	        				displayImg(file2.getName());
	        			}catch(Exception e) {
	        				e.printStackTrace();
	        			}
	        		} 
	           }); 
			}
		});
        
        delItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String deleteMessage = JOptionPane.showInputDialog("Please enter the name of the file that you want to delete: ");

		        File file = new File(imageFolder + deleteMessage);
		        try {
		        	if(file.delete()){
		    			JOptionPane.showMessageDialog(mainPanel, file.getName() + " has been successfully deleted !");
		    		}else{
		    			JOptionPane.showMessageDialog(mainPanel, "Delete operation is failed");	
		    		}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
        
        renItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String oldFileR = JOptionPane.showInputDialog("Please enter the name of the file that you want to rename: ");
				String newFileR = JOptionPane.showInputDialog("Please enter the new name for this file:");


				File oldFile = new File(imageFolder + oldFileR);
				File newFile = new File(imageFolder + newFileR);

				if (newFile.exists()) {
					try {
						JOptionPane.showMessageDialog(mainPanel, "File exists");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				boolean success = oldFile.renameTo(newFile);

				if (success) {
					JOptionPane.showMessageDialog(mainPanel, "You have successfully renamed the image");
				}else {
					JOptionPane.showMessageDialog(mainPanel, "Failed renamed");
				}
			}
		});
        
        about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainPanel, "SupPictures is a program realized by our group for this project");
			}
		});
        
    }
    
    /**
     * Methode qui va se charger d'afficher l'image quand on clique dessus
     * @param img
     */
    public void displayImg(String img) {
		try {  
			JFrame frame = new JFrame();
			this.setLayout(new BorderLayout());
			JPanel jPanelN=new JPanel();
			PanelPictures pannelC=new PanelPictures();
			File f=new File(imageFolder);
			String[] images= f.list();
			BufferedImage bi=ImageIO.read(new File(imageFolder+img));
			
			pannelC.setBi(bi);
			frame.add(pannelC,BorderLayout.CENTER);
			frame.setTitle("SupPicture");
			frame.setSize(300, 300);
			frame.setLocationRelativeTo(null);            
		    frame.setVisible(true);
		    frame.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
