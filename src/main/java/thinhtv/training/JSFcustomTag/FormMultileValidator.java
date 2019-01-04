package thinhtv.training.JSFcustomTag;

import java.util.ArrayList;
import java.util.Collection;

import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@FacesComponent("FormMultileValidator")
public class FormMultileValidator extends UIInput{

	private static final String COMPONENT_FAMILY = "cthinhtv.training.JSFcustomTag.FormMultileValidator";
	
	/**
	 * danh sach id cac componnet can validate (được set giá trị khi dùng trên html)
	 */
	private String forIds;
	
	/**
	 * phuong thức mà tag sẽ khia báo gọi đến (được set trên thẻ html)
	 */
	private MethodExpression validatorMethod;

	private transient boolean rendered = false;
	
	/* defaul constructor*/
	public FormMultileValidator() {}

	@Override
	public void validate(FacesContext context) {
		if (forIds == null) {
			throw new IllegalArgumentException("chua nhap id cac component can validate");
		}
		
		Collection<UIComponent> components = new ArrayList<>();
		Collection<Object> componentValues = new ArrayList<>();
		
		String[] forIdsArrays = forIds.split(",");
		for (String id : forIdsArrays) {
			UIComponent cmp = findComponent(id.trim());
			if(cmp == null) {
				throw new IllegalArgumentException("khoong tim thay component id : "+ id.trim());
			}
			components.add(cmp);
			
			EditableValueHolder editHolder = (EditableValueHolder) cmp;
			componentValues.add(editHolder.getValue());
		}
		
		try {
			validatorMethod.invoke(context.getELContext(), new Object[] {
					context, components, componentValues
			});
		} catch (Throwable th) {
			if (th instanceof ValidatorException) {
				handleValidatorException(context, components, (ValidatorException) th);
			} else if (th.getCause() instanceof ValidatorException) {
				handleValidatorException(context, components, (ValidatorException) th.getCause());
			}
		}
	}
	
	private void handleValidatorException(FacesContext fc, Collection<UIComponent> components,
			ValidatorException ve) {
		FacesMessage msg = ve.getFacesMessage();
		for (UIComponent cmp : components) {
			fc.addMessage(cmp.getClientId(fc), msg);
			if (cmp instanceof EditableValueHolder) {
				((EditableValueHolder) cmp).setValid(false);
			}
		}
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getRendererType() {
		return null;
	}
	
	/* loại bỏ khỏi processValidators() , không cho keeys xuất , và chiếm vị trí trong PanelGrid 
	 * nếu là processValidators(FacesContext) thì phải là true
	 * */
	@Override
	public boolean isRendered() {
		return rendered;
	}

	/* loại bỏ không hiển thị các thuộc tính con*/
	@Override
	public boolean getRendersChildren() {
		return false;
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		Object[] values = (Object[]) state;
		super.restoreState(context, values[0]);
		
		forIds =  (String) values[1];
		validatorMethod = (MethodExpression) restoreAttachedState(context, values[2]);
	}
	
	@Override
	public Object saveState(FacesContext context) {
		/* 3 giá trị tương ứng được thiết định tại restoreState() */
		Object[] values = new Object[3];
		values[0] = super.saveState(context);
		values[1] = forIds;
		values[2] = saveAttachedState(context, validatorMethod);
		return values;
	}
	
	/* vì tag luôn luông không hiển thị với tất cả các phase, tuy nhiên  processValidators() thì phải được hiên thị  -> phải update flag render*/
	@Override
	public void processValidators(FacesContext context) {
		rendered = true;
		super.processValidators(context);
		rendered = false;
	}

	
	
	// getter setter
	
	public String getForIds() {
		return forIds;
	}

	public void setForIds(String forIds) {
		this.forIds = forIds;
	}

	public MethodExpression getValidatorMethod() {
		return validatorMethod;
	}

	public void setValidatorMethod(MethodExpression validatorMethod) {
		this.validatorMethod = validatorMethod;
	}
}
