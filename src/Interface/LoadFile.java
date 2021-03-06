package Interface;

import data.FileIO;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Exception.FileNotFoundException;

/**
 * Interface: Load file FileChooser panel.
 * 
 * @author Tomahawkd
 *
 */

public class LoadFile extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int state;
	private FileIO file;
	
	public LoadFile(FileIO file, MainWindow window) {
		
		super();
		
		this.file = file;
		
		FileFilter filter = new FileNameExtensionFilter("Spider Data File", "sdf");
		setFileFilter(filter);
		setFileSelectionMode(FILES_AND_DIRECTORIES);
		setDialogTitle("Open File");
		setEnabled(true);
		
		state = showOpenDialog(null);
		
		execute(window);
	}
	
	private void execute(MainWindow window) {
		switch (state) {
		case JFileChooser.APPROVE_OPTION:
			
			File filePath=this.getSelectedFile();
			file.setTargetFilePath(filePath.getAbsolutePath());
			try {
				
				file.loadFile();
				new Thread(new Runnable() {
					public void run() {
						FileProcess process = new FileProcess(OperationType.LOAD);
						process.setVisible(true);
					}
				}).start();
				window.updateUI();
			} catch (FileNotFoundException e) {
				NewFile newFile = new NewFile(file);
				newFile.setVisible(true);
			} catch (ClassCastException e) {
				FileDataError error = new FileDataError();
				error.setVisible(true);
				file.setTargetFilePath("");
			} catch (ClassNotFoundException e) {
				FileDataError error = new FileDataError();
				error.setVisible(true);
				file.setTargetFilePath("");
			} catch (IOException e) {
				Error error = new Error();
				error.setVisible(true);
			}
			
			break;

		default:
			break;
		}
	}

}


