 
package RCPTextEditor;

import java.io.FileWriter;
import java.io.IOException;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SaveHandler {
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
				FileWriter writer = new FileWriter(fileName);
				writer.write(editor.styledText.getText());
				writer.close();
			}
			catch(IOException e) {
	            MessageDialog.openError(shell, "Error saving file", "File " + fileName + " could not be saved." );

			}
		}
	}		
}