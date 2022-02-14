package br.com.ufrn.projeto_2.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
public class FacesContextCustom {
	public static void addMessage(String form, String message, Severity severity) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage("validateEditFormMessage", new FacesMessage(severity, message, ""));
	}
}
