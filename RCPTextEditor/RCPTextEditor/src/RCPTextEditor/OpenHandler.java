package RCPTextEditor;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, MPart part){
		if (part==null) {
			return;
		}
		EditorPart editor = (EditorPart) part.getObject();		
		FileDialog dialog = new FileDialog(shell);		
		String fileName = dialog.open();
		if (fileName!= null) {
			try {
				editor.styledText.setText(new Scanner(new File(fileName)).useDelimiter("\\A").next());
			}
			catch(IOException e) {
				MessageDialog.openError(shell, "Error opening file", "File " + fileName + " could not be opened." );
			}
		}
	}		
}