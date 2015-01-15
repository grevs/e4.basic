 
package RCPTextEditor;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

public class CopyHandler {
	@Execute
	public void execute(MPart part){
		if (part==null) {
			return;
		}
		EditorPart editor = (EditorPart) part.getObject();	
		Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        StringSelection stringSelection = new StringSelection (editor.styledText.getSelectionText());
        clpbrd.setContents (stringSelection, null);
	}			
}