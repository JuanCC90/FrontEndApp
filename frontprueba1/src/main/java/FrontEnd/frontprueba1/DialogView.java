package FrontEnd.frontprueba1;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.MoveEvent;
import org.primefaces.extensions.event.CloseEvent;

@ManagedBean
public class DialogView {

	public void handleClose(CloseEvent event) {
		addMessage(event.getComponent().getId()+ "closed"," ");
	}

	public void handleMove(MoveEvent event) {
		addMessage(event.getComponent().getId()+ "moved", "Left: "+ event.getLeft()+", Top: "+event.getTop());
	}
	
	public void destroy() {
		addMessage("Presione Enviar y Se Borrara El Elemento Seleccionado Por Id", " ");
	}
	
	public void addMessage(String summary, String detail) {
		FacesMessage message=new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
