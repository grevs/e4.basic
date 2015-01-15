package pl.mpiasecki.eclipseE4.contextfunction;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

public class ContextFunctionTestFactory extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		ICFTest test = ContextInjectionFactory.make(CFTestImple.class, context);
		MApplication application = context.get(MApplication.class);
	    IEclipseContext ctx = application.getContext();
	    ctx.set(ICFTest.class, test);
	    return test;
	}

}
