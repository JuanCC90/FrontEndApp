package FrontEnd.frontprueba1;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {

	private StreamedContent file;
	
	
	public FileDownloadView() {
		InputStream stream  = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:\\Users\\Admin\\Desktop\\prueba.pdf");
		file = new DefaultStreamedContent(stream, "file/pdf", "prueba.pdf");
	}
	
	
	
	
	
}
