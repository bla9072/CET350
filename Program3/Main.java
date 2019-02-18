//import java.out.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;

class Main extends Frame implements ActionListener, WindowListener{
	String path = System.getProperty("user.dir");
	File f = new File(path);
	ArrayList<String> fileNames = new ArrayList<String>(Arrays.asList(f.list()));
	List fileView = new List();
	Label sourceLabel;

	public Main(){
		// Hold the origin location for the frame
		// Set Frame size
		//int locX = 200;
		//int locY = 200;
		super();
		int height = 800;
		int width = 300;

	
		
		
		

		// Set default frame properties
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;

		this.setTitle(path);
		addWindowListener(this);

		// Create frame elements
		
		
		try{
			if (f.getParentFile().exists()) {
				fileView.add("..");
			} 
		}catch (NullPointerException err){
			// Folder is root	
		}
		
		for(int i = 0; i < fileNames.size(); i++){
			fileView.add(fileNames.get(i));
		}
		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		fileView.addActionListener(this);
		this.add(fileView, gbc);
		//this.fileView = fileView;

		sourceLabel = new Label("Source: " + path);
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		//sourceLabel.addActionListener(this);
		this.add(sourceLabel, gbc);
		
		Button targetButton = new Button("Target: ");
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		this.add(targetButton, gbc);
		
		Label targetLabel = new Label(" target label ");
		gbc.gridwidth = GridBagConstraints.NONE;
		this.add(targetLabel,gbc);

		Label fileLabel = new Label("Filename: ");
		gbc.gridwidth = GridBagConstraints.NONE;
		this.add(fileLabel,gbc);

		TextField fileTextField = new TextField("");
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		this.add(fileTextField,gbc);

		Button OKButton = new Button(" OK ");
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(OKButton,gbc);
	
		this.setVisible(true);
		this.setSize(this.getPreferredSize());
		
		//this.pack();
		//fileView.setText("file view will go here");

		// Add elements
		
		
		
	}

	public void windowOpened(WindowEvent e){

	}

	public void windowClosing(WindowEvent e){
		this.removeWindowListener(this);
		System.exit(0);
	}

	public void windowClosed(WindowEvent e){

	}

	public void windowIconified(WindowEvent e){

	}

	public void windowDeiconified(WindowEvent e){

	}

	public void windowActivated(WindowEvent e){

	}

	public void windowDeactivated(WindowEvent e){

	}

	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		File temp;
		// Clicked in file view
		if(source == this.fileView){
			temp = new File(path + "\\" + fileView.getSelectedItem());
			System.out.println(temp);
			if(fileView.getSelectedItem() == ".."){
				f = f.getParentFile();
				fileView.removeAll();	
				fileNames.removeAll(fileNames);
				fileNames = new ArrayList<String>(Arrays.asList(f.list()));
				try{
					if (f.getParentFile().exists()) {
						fileView.add("..");
					} 
				}catch (NullPointerException err){
					// Folder is root	
				}
				for(int i = 0; i < fileNames.size(); i++){
					fileView.add(fileNames.get(i));
				}
			} else { // Clicked something else in file view
				if(temp.isDirectory()){
					f = temp;
					fileView.removeAll();	
					fileNames.removeAll(fileNames);
					fileNames = new ArrayList<String>(Arrays.asList(f.list()));
					try{
						if (f.getParentFile().exists()) {
							fileView.add("..");
						} 
					}catch (NullPointerException err){
						// Folder is root	
					}
					for(int i = 0; i < fileNames.size(); i++){
						fileView.add(fileNames.get(i));
					} 
				}
				else {
					//sourceLabel.setText("Source: " + path + fileView.getSelectedItem());
					sourceLabel.setText("Source: " + temp);
				}	
			} 
			// reset path
			path = f.getPath();
		}
	}

	public static void main(String[] args){
		Main m = new Main();

		/* Without extending frame
		Frame f =  new Frame();
		
		*/	
	}

}