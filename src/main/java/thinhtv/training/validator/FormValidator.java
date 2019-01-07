package thinhtv.training.validator;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.sun.faces.util.MessageFactory;

@ManagedBean(name="formValidator")
public class FormValidator {

	public void validateInput(FacesContext context, Collection<UIComponent> components, Collection<Object> values) {
		for (UIComponent cmp : components) {
			UIInput ip = (UIInput) cmp;
			System.out.println(ip.getId() + ": SubmittedValue :" + ip.getSubmittedValue() + " , LocalValue =  " + ip.getLocalValue() + " , value:" + ip.getValue());
			if ("".equals(ip.getSubmittedValue().toString().trim())) {
				FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getLabel(context, cmp) + ": Required", "đây là trường bắt buộc nhập!");
				ip.setValid(false);
				context.addMessage(ip.getClientId(), ms);
			}
		}
	}
}
