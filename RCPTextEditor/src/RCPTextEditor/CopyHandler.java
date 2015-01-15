 
package RCPTextEditor;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

public class CopyHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part){
		if (part==null) {
			return;
		}
		EditorPart editor = (EditorPart) part.getObject();	
		Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        StringSelection stringSelection = new StringSelection (editor.styledText.getSelectionText());
        clpbrd.setContents (stringSelection, null);
	}			
}