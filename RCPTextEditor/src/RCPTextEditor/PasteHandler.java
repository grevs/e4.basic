 
package RCPTextEditor;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

public class PasteHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part){
		if (part==null) {
			return;
		}
		EditorPart editor = (EditorPart) part.getObject();	
		editor.styledText.insert(getClipboardString());
	}		
	
	// Get String from Clipboard
    private String getClipboardString() {
        Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        String result = "";          
        Transferable contents = clpbrd.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if ( hasTransferableText ) {
          try {
            result = (String)contents.getTransferData(DataFlavor.stringFlavor);
          }
          catch (UnsupportedFlavorException ex){
            System.out.println(ex);
            ex.printStackTrace();
          }
          catch (IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
          }        
        }
     return result;
    }		
}