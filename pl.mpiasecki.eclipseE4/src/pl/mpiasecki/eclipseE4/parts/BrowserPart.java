 
package pl.mpiasecki.eclipseE4.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class BrowserPart {
	
	Text filter;
	Button search;
	Browser browser;
	Label label;
	
	@Inject
	Logger logger;
	
	@Inject
	public BrowserPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		filter = new Text(parent, SWT.SEARCH);
		filter.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		filter.setMessage("Please enter the city...");
		
		filter.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
//				logger.info(String.valueOf(filter.getText().length()));
				if(filter.getText().length() < 2) {
					search.setEnabled(false);
				}
				else {
					search.setEnabled(true);
				}
			}
		});
		
		search = new Button(parent, SWT.PUSH);
		search.setText("Search");
		search.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				browser.setUrl("https://www.google.pl/maps/place/"+filter.getText());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		browser = new Browser(parent, SWT.NONE);
		browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 0));
		
		label = new Label(parent, SWT.NONE);
		label.setText("Test label");
	}
	
	
	
	@Focus
	public void onFocus() {
		
	}
	
	
}