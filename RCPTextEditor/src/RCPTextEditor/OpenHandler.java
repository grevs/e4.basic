package RCPTextEditor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.inject.Named;

import model.EditorModel;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			EPartService partService, MApplication application,EModelService modelService) {
	    
		FileDialog dialog = new FileDialog(shell);		
		String fileName = dialog.open();
		if (fileName!= null) {
			try {
				File file = new File(fileName);
				Scanner scanner =  new Scanner(file);
				String text = scanner.useDelimiter("\\A").next();
				scanner.close();				
				// create part
				MPart part = MBasicFactory.INSTANCE.createPart();
				part.setLabel(file.getName());
				part.setCloseable(true);
				part.getTags().add(EPartService.REMOVE_ON_HIDE_TAG );
			    part.setContributionURI("bundleclass://RCPTextEditor/RCPTextEditor.EditorPart");
			    // get part stack and show new part 
			    List<MPartStack> stacks = modelService.findElements(application, null, MPartStack.class, null);
			    stacks.get(0).getChildren().add(part);
			    partService.showPart(part, PartState.ACTIVATE);
			    ((EditorPart)part.getObject()).styledText.setText(text);
			    ((EditorPart)part.getObject()).model = new EditorModel(text, text);
			    ((EditorPart)part.getObject()).setFile(file);
			    part.setDirty(false);
			}
			catch(IOException e) {
				MessageDialog.openError(shell, "Error opening file", "File " + fileName + " could not be opened." );
			}
		}
	}	
}