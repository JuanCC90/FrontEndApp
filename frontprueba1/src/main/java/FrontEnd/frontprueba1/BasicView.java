package FrontEnd.frontprueba1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import lombok.Data;

@ManagedBean(value = "basicView")
@SessionScoped
@Data
public class BasicView implements Serializable {

	// Entorno:
	private static final long serialVersionUID = 5261986308241092411L;
	private long id;
	private String nombre;
	private String anio;
	private long premios;
	public UploadedFile documento;
	public String cadena;
	public File filecito;
	private byte[] archivo;
	public byte[] otroArchivo;
	private StreamedContent file;
	private List<PeliculaDTO> peliculas;
	private List<PeliculaDTO> peliculitas;
	private PeliculaDTO pelicula;
	public PeliculaDTO peliArchivo;
	private String rutaArchivos = ".//src//main//resources//files//";
	public RestTemplate rt;

	// Metodos:
	public List<PeliculaDTO> getPeliculas() {
		return peliculas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public long getPremios() {
		return premios;
	}

	public void setPremios(long premios) {
		this.premios = premios;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setDocumento(UploadedFile doc) {
		this.documento = doc;
	}

	public UploadedFile getDocumento() {
		return this.documento;
	}

	public byte[] getOtroArchivo() {
		return this.otroArchivo;
	}

	public void setOtroArchivo(byte[] archivo) {
		this.otroArchivo = archivo;
	}

	public void setFilecito(File filecito) {
		this.filecito = filecito;
	}

	public File getFilecito() {
		return this.filecito;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public PeliculaDTO devuelveLeer() {
		return pelicula;
	}

	public List<PeliculaDTO> dameTodas() {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		ResponseEntity<PeliculaDTO[]> res = rt.getForEntity("http://localhost:8080/AllPelis", PeliculaDTO[].class);
		peliculas.addAll(Lists.newArrayList(res.getBody()));
		return peliculas;
	}

	public List<PeliculaDTO> buscar() {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		ResponseEntity<PeliculaDTO[]> res = rt.getForEntity("http://localhost:8080/Pelicula/busca?id=" + id + "&nombre="
				+ nombre + "&anio=" + anio + "&premios=" + premios, PeliculaDTO[].class);
		peliculas.addAll(Lists.newArrayList(res.getBody()));
		return peliculas;
	}// Fin Metodo

	public List<PeliculaDTO> buscarId(long id) {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		ResponseEntity<PeliculaDTO[]> res = rt.getForEntity("http://localhost:8080/Pelicula/busca?id=" + id,
				PeliculaDTO[].class);
		peliculas.addAll(Lists.newArrayList(res.getBody()));
		return peliculas;

	}// Fin Metodo

	public List<PeliculaDTO> agregar() {
		/*
		 * PeliculaDTO peli=new PeliculaDTO(); peli.setId(id); peli.setAnio(anio);
		 * peli.setNombre(nombre); peli.setPremios(premios);
		 * serviPeli.setPelicula(peli); peliculas = new ArrayList<>();
		 * peliculas.addAll(serviPeli.getAll());
		 */
		pelicula = new PeliculaDTO();
		pelicula.setNombre(nombre);
		pelicula.setAnio(anio);
		pelicula.setPremios(premios);
		rt = new RestTemplate();
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		rt.postForObject("http://localhost:8080/Pelicula/post", request, PeliculaDTO[].class);
		peliculas = new ArrayList<>();
		peliculas = dameTodas();
		return peliculas;
	}

	public List<PeliculaDTO> borraPeli(long id) {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		rt.delete("http://localhost:8080/Pelicula/Delete/" + id);
		peliculas = dameTodas();
		return peliculas;
	}

	public List<PeliculaDTO> actualiza(long id) {
		pelicula = new PeliculaDTO();
		pelicula.setNombre(nombre);
		pelicula.setAnio(anio);
		pelicula.setPremios(premios);
		rt = new RestTemplate();
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		rt.put("http://localhost:8080/Pelicula/put/" + id, request, PeliculaDTO.class);
		peliculas = new ArrayList<>();
		peliculas = dameTodas();
		return peliculas;
	}

	public void recuperaInfo(long id) {
		pelicula = new PeliculaDTO();
		rt = new RestTemplate();
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/" + id, PeliculaDTO.class).getBody();
		this.nombre = pelicula.getNombre();
		this.premios = pelicula.getPremios();
		this.anio = pelicula.getAnio();
	}

	public String redirecciona1() throws IOException {
		String url;
		FacesContext fc;
		peliculas = dameTodas();
		return "/inicio.xhtml?faces-redirect=true";
	}

	public String redirecciona2(long id) throws IOException {
		pelicula = new PeliculaDTO();
		rt = new RestTemplate();
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/" + id, PeliculaDTO.class).getBody();
		return "/detalle.xhtml?faces-redirect=true";
	}

	public String redirecciona3(long id) throws IOException {
		pelicula = new PeliculaDTO();
		rt = new RestTemplate();
		pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/" + id, PeliculaDTO.class).getBody();
		otroArchivo = pelicula.getArchivo();
		return "/cargaArchivo.xhtml?faces-redirect=true";
	}

	public void convierteArchivo(long id) throws IOException {
		InputStream is = documento.getInputstream();
		archivo = IOUtils.toByteArray(is);
		rt = new RestTemplate();
		pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/" + id, PeliculaDTO.class).getBody();
		pelicula.setArchivo(archivo);
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		rt.put("http://localhost:8080/Pelicula/put/" + id, request, PeliculaDTO.class);
		otroArchivo = pelicula.getArchivo();
	}


	public void descargar(long id) {
		rt = new RestTemplate();
		pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/" + id, PeliculaDTO.class).getBody();
		otroArchivo = pelicula.getArchivo();
		InputStream targetStream = new ByteArrayInputStream(otroArchivo);
		file = new DefaultStreamedContent(targetStream, "application/pdf", "archivoFinal.pdf");
	}

/*	
	public void convierteArchivo(long id) throws IOException {
		 ByteArrayOutputStream ous = null; InputStream ios = null;
		  
		  try { byte[] buffer = new byte[(int)filecito.length()]; ous = new
		  ByteArrayOutputStream(); ios = new FileInputStream(filecito); int read = 0;
		  while((read = ios.read(buffer)) !=-1) { ous.write(buffer,0,read); } }finally
		 { try { if(ous != null) { ous.close(); } }catch(IOException ioe) {
		  ioe.printStackTrace(); } try { if(ios != null) { ios.close(); }
		  }catch(IOException ioe) { ioe.printStackTrace(); } rt = new RestTemplate();
		  pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/" + id,
		  PeliculaDTO.class).getBody(); pelicula.setArchivo(ous.toByteArray()); }
	}*/
	
	
	
	
	
	
	/*
	 * public void descargaArchivo() { InputStream stream =
	 * FacesContext.getCurrentInstance().getExternalContext()
	 * .getResourceAsStream("C:\\Users\\Admin\\Desktop\\prueba.pdf"); file = new
	 * DefaultStreamedContent(stream, "file/pdf", "prueba.pdf"); }
	 */

	/*
	 * public void devuelveFichero(long id) { pelicula = new PeliculaDTO(); rt = new
	 * RestTemplate(); pelicula =
	 * rt.getForEntity("http://localhost:8080/Pelicula/get/" + id,
	 * PeliculaDTO.class).getBody(); otroArchivo = pelicula.getArchivo(); try {
	 * FileOutputStream stream = new
	 * FileOutputStream("/frontprueba1/src/main/resources/files");
	 * stream.write(otroArchivo); }catch(IOException ioe) { ioe.printStackTrace(); }
	 * 
	 * }
	 * 
	 */



	/*
	 * public void leerBytesdeArchivo(File documento, long id) {
	 * 
	 * FileInputStream fileInputStream = null; byte[] archivo = null; try { File doc
	 * = new File(documento.getAbsolutePath()); archivo = new byte[(int)
	 * doc.length()]; fileInputStream = new FileInputStream(doc);
	 * fileInputStream.read(archivo);
	 * 
	 * }catch(IOException ioe) { ioe.printStackTrace(); }finally {
	 * if(fileInputStream != null) { try { fileInputStream.close();
	 * }catch(IOException ioe2) { ioe2.printStackTrace(); } } }
	 * 
	 * }
	 * 
	 * 
	 */

	/*
	 * public void subeArchivo(byte [] archivo) { pelicula = new PeliculaDTO(); rt =
	 * new RestTemplate(); pelicula.setNombre(nombre); pelicula.setAnio(anio);
	 * pelicula.setPremios(premios); pelicula.setArchivo(archivo); HttpEntity
	 * <PeliculaDTO> request = new HttpEntity<>(pelicula); pelicula =
	 * rt.postForEntity("http://localhost:8080/Pelicula/Enviar/"+archivo, request,
	 * PeliculaDTO.class).getBody();
	 * 
	 * }
	 */

	/*
	 * public void convierteDoc(byte[] archivo) { byte[] byteArray = null; try {
	 * InputStream inputStream = new FileInputStream(archivo); String
	 * inputStreamToString = inputStream.toString(); byteArray =
	 * inputStreamToString.getBytes(); inputStream.close();
	 * }catch(FileNotFoundException fnfe) {
	 * System.out.println("Archivo no Encontrado: "+fnfe); }catch(IOException ioe) {
	 * System.out.println("IO Exception: "+ioe); } archivo = byteArray; }
	 */

	/*
	 * public void copiaArchivo(String fileName, InputStream in) {
	 * 
	 * try { OutputStream out = new FileOutputStream(new
	 * File(rutaArchivos+fileName)); int read = 0; byte[] bytes = new byte[10024];
	 * while((read= in.read(bytes)) != -1) { out.write(bytes, 0, read); }
	 * in.close(); out.flush(); out.close();
	 * System.out.println("Archivo creado correctamente"); DateFormat dateFormat =
	 * new SimpleDateFormat("yyyy-MM-dd HH_mm_ss"); Date date = new Date(); String
	 * ruta1 = rutaArchivos + fileName; String ruta2 = rutaArchivos +
	 * dateFormat.format(date)+"-"+fileName;
	 * System.out.println("Archivo: "+ruta1+" Renombrado a: "+ruta2); File archivo =
	 * new File(ruta1); archivo.renameTo(new File(ruta2)); }catch(IOException ioe) {
	 * System.out.println(ioe.getMessage()); } }
	 */

	/*
	 * public void subeArchivo(FileUploadEvent event) {
	 * 
	 * try {
	 * copyFile(event.getFile().getFileName(),event.getFile().getInputstream());
	 * FacesMessage message = new FacesMessage("Archivo subido correctamente");
	 * FacesContext.getCurrentInstance().addMessage(null, message);
	 * }catch(IOException ioe) { ioe.printStackTrace(); } }
	 * 
	 * 
	 * public String getString(){ System.Text.ASCIIEncoding codificador = new
	 * System.Text.ASCIIEncoding(); return codificador.GetString(otroArchivo); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}//Fin Programa
