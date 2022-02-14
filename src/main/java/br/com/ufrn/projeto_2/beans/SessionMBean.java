package br.com.ufrn.projeto_2.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Paulo Lima (paulo.xavier.700@ufrn.edu.br, plimayep@gmail.com)
 */
@ManagedBean
@ViewScoped
public class SessionMBean {
	public Integer timeToRefresh = 0;
	
	/**
	 * @return the timeToRefresh
	 */
	public Integer getTimeToRefresh() {
		return timeToRefresh;
	}

	/**
	 * @param timeToRefresh the timeToRefresh to set
	 */
	public void setTimeToRefresh(Integer timeToRefresh) {
		this.timeToRefresh = timeToRefresh;
	}

	public void sessionHasExpired() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
		

		Integer userIdLogged = (Integer) httpSession.getAttribute("userIdLogged");

		if (userIdLogged == null) {
			timeToRefresh = 0;
		} else {
			timeToRefresh = httpSession.getMaxInactiveInterval();
		}
	}
}
