
package FrontEnd.frontprueba1;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadView {
/*
	private UploadedFile file;
	
	public UploadedFile getFile() {
		return file;
	}
	
	public void setFile(UploadedFile file) {
		this.file=file;
	}*/
	/*
	public void upload() {
		if(file != null) {
			FacesMessage message = new FacesMessage(file.getFileName()+" Archivo Subido Correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage(event.getFile().getFileName()+" Archivo Subido Correctamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	

	*/

}

