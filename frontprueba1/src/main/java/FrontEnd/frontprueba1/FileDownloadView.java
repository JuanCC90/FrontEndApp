package FrontEnd.frontprueba1;

import java.io.InputStream;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {

	private StreamedContent file;
	
	
	public FileDownloadView() {
		InputStream stream  = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/frontprueba1/src/main/resources/files/prueba.pdf");
		file = new DefaultStreamedContent(stream, "file/pdf", "prueba.pdf");
	}
	
	
	public StreamedContent getFile() {
		return file;
	}
	
	
}
