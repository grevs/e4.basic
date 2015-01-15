 
package RCPTextEditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import model.EditorModel;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class EditorPart {
	public StyledText styledText = null;
	private File file = null;
	
	public EditorModel model;
	
	// Injected services
	@Inject
	  MDirtyable dirty;
	
	@Inject
	@Named(IServiceConstants.ACTIVE_SHELL) Shell shell;
	
	@Inject
		MPart part;
	
	@Inject
	public EditorPart() {
		this.model = new EditorModel("", "");
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {		
		styledText = new StyledText(parent, SWT.BORDER);
		styledText.addModifyListener(new ModifyListener(){
		      @Override
			public void modifyText(ModifyEvent event) {
		    	  dirty.setDirty(true);
		    	  model.setNewValue(styledText.getText());
		      }
		});
	}
	
	public void setFile(File file) {
		this.file = file;
		if (file!=null) {
			part.setLabel(file.getName());
		}
	}
	
	@Persist
	public void save(MDirtyable dirty, MPart part) {
		if (file!=null) {
			saveToFile(file);
		}
		else {
			saveAs();
		}		
	} 
	
	private void saveToFile(File f) {	
		try {
			FileWriter writer = new FileWriter(f);
			writer.write(styledText.getText());
			writer.close();
			setFile(f);
			dirty.setDirty(false);
		}
		catch(IOException e) {
            MessageDialog.openError(shell, "Error saving file", "File " + file.getAbsolutePath() + " could not be saved." );
		}
	}
	
	public File saveAs() {
		File file = null;
		FileDialog dialog = new FileDialog(shell);	
		String fileName = dialog.open();
		if (fileName!= null) {
				file = new File(fileName);
				if (file.exists()) {
					if (MessageDialog.openQuestion(shell, "Overwrite", "The file " + file.getAbsolutePath() + " already exists.\n"
							+ "Are you sure you want to overwrite it?")) {
						saveToFile(file);
					}
				}
				else {
					saveToFile(file);
				}
		}
		return file;
	}
	
	@Focus
	public void onFocus() {
		styledText.setFocus();
	}
}