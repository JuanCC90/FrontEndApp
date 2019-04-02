package FrontEnd.frontprueba1;

import java.io.IOException;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Data;

@ManagedBean
@SessionScoped
@Data
public class loadPage {
	
	//Atributos:
		String url;
		FacesContext fc;
		
	//Constructor:
		public loadPage() {
			
		}
		
		public void redirecciona() throws IOException {
			url="http://localhost:8081/inicio.xhtml";
			fc = FacesContext.getCurrentInstance();
			fc.getExternalContext().redirect(url);
		}
		

}
