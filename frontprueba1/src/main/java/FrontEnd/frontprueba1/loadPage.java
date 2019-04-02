package FrontEnd.frontprueba1;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class loadPage {
	
	//Atributos:
		String url="http://localhost:8081/inicio.xhtml";
		FacesContext fc = FacesContext.getCurrentInstance();
		
	//Constructor:
		public loadPage() {
			super();
		}
		
		public void redirecciona() throws IOException {
			fc.getExternalContext().dispatch(url);
		}
		

}
