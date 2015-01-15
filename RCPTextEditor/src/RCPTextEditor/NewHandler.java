 
package RCPTextEditor;

import java.util.List;

import model.EditorModel;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class NewHandler {	
	private static int counter = 1; // counter for new files
	@Execute
	public void execute(EPartService partService, MApplication application,EModelService modelService) {
	    // create part
		MPart part = MBasicFactory.INSTANCE.createPart();
	    part.setLabel("New file " + counter++ );
	    part.setCloseable(true);
	    part.getTags().add(EPartService.REMOVE_ON_HIDE_TAG );
	    part.setContributionURI("bundleclass://RCPTextEditor/RCPTextEditor.EditorPart");
	    ((EditorPart)part.getObject()).model = new EditorModel("", "");
	    // get part stack and show new part 
	    List<MPartStack> stacks = modelService.findElements(application, null, MPartStack.class, null);
	    stacks.get(0).getChildren().add(part);
	    partService.showPart(part, PartState.ACTIVATE);
	}
}