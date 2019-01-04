package thinhtv.training.JSFcustomTag.JSFCustomTagHandler;

import java.util.Collection;

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;

import thinhtv.training.JSFcustomTag.FormMultileValidator;

public class FormMultileValidatorComponentHandler extends ComponentHandler{

	public FormMultileValidatorComponentHandler(ComponentConfig config) {
		super(config);
	}

	/**
	 * <FormMultileValidator forIds="a,b,c" validator="ABC" />
	 * 
	 * dang ky attribute cho tag jsf
	 * 
	 * forIds : là id các input cần validate
	 * 
	 * validator : khao báo, phương thức sẽ gọi đến để validate
	 */
	@Override
	public void setAttributes(FaceletContext ctx, Object instance) {
		super.setAttributes(ctx, instance);
		
		/** tag lib da khai bao trong taglib.xml */
		FormMultileValidator multileValidator = (FormMultileValidator) instance;
		
		/* get attribute "forIds" in tag */
		TagAttribute forIds = this.getAttribute("forIds");
		String strForIds = forIds.getValue(ctx);
		
		if(strForIds == null || strForIds.trim().length() == 0) {
			throw new IllegalArgumentException("chua nhap id cac component can validate");
		}
		multileValidator.setForIds(strForIds);

		/* get attribute "validator" in tag */
		TagAttribute validator = this.getRequiredAttribute("validator");
		MethodExpression validatorMethod = validator.getMethodExpression(ctx, null, new Class[] {
				FacesContext.class, Collection.class, Collection.class
		});
		
		multileValidator.setValidatorMethod(validatorMethod);
	}

	
	
}
