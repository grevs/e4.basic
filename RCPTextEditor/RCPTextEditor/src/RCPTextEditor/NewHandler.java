 
package RCPTextEditor;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

public class NewHandler {
	@Execute
	public void execute(MPart part) {
		if (part != null) {
			EditorPart editor = (EditorPart) part.getObject();
			editor.styledText.setText("");
		}
		
	}		
}