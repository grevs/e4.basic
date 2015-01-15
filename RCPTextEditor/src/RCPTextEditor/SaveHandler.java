 
package RCPTextEditor;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SaveHandler {
	@Execute
	public void execute(EPartService partService, @Named(IServiceConstants.ACTIVE_PART)  MPart part){
		if (partService!=null && part!=null) {
			partService.savePart(part, false);
		}
	}	
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) @Optional MPart part) {
		if(part.getObject() instanceof EditorPart) {
			EditorPart editor = (EditorPart)part.getObject();
			if(editor.model.isChanged()) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}