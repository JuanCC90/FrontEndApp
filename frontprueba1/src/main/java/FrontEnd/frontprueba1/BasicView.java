package FrontEnd.frontprueba1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import lombok.Data;

@ManagedBean
@SessionScoped
@Data
public class BasicView implements Serializable{
	
	
	private static final long serialVersionUID = 5261986308241092411L;
	private long id;
	private String nombre;
	private String anio;
	private long premios;
	
	private List<PeliculaDTO> peliculas;
	
	private PeliculaDTO pelicula;

	
	//private PeliculaService serviPeli;
	
	RestTemplate rt;
	
	public List<PeliculaDTO> getPeliculas(){
		 
		return peliculas;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
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
		this.anio=anio;
	}
	
	public long getPremios() {
		return premios;
	}
	
	public void setPremios(long premios) {
		this.premios=premios;
	}
	
	public List<PeliculaDTO> dameTodas(){
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		ResponseEntity<PeliculaDTO[]> res = rt.getForEntity("http://localhost:8080/AllPelis", PeliculaDTO[].class);
		peliculas.addAll(Lists.newArrayList(res.getBody()));
		return peliculas;
	}
	
	public List<PeliculaDTO> buscar() {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		ResponseEntity<PeliculaDTO[]> res =  rt.getForEntity("http://localhost:8080/Pelicula/busca?id="+id+"&nombre="+nombre+"&anio="+anio+"&premios="+premios, PeliculaDTO[].class);
		peliculas.addAll(Lists.newArrayList(res.getBody()));
		return peliculas;
		
	}//Fin Metodo
	
	public List<PeliculaDTO>agregar() {
		/*
		PeliculaDTO peli=new PeliculaDTO();
		peli.setId(id);
		peli.setAnio(anio);
		peli.setNombre(nombre);
		peli.setPremios(premios);
		serviPeli.setPelicula(peli);
		peliculas = new ArrayList<>();
		peliculas.addAll(serviPeli.getAll());
		*/
		pelicula=new PeliculaDTO();
		pelicula.setNombre(nombre);
		pelicula.setAnio(anio);
		pelicula.setPremios(premios);
		rt = new RestTemplate();
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		rt.postForObject("http://localhost:8080/Pelicula/post", request, PeliculaDTO[].class);
		peliculas = new ArrayList<>();
		peliculas=dameTodas();
		return peliculas;
	}
	
	
	public List<PeliculaDTO> borraPeli(long id) {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		rt.delete("http://localhost:8080/Pelicula/Delete/"+id);
		peliculas = dameTodas();
		return peliculas;
	}
	

	public List<PeliculaDTO> actualiza(long id) {
		/*
		Pelicula peli=new Pelicula();
		peli.setId(id);
		peli.setNombre(nombre);
		peli.setAnio(anio);
		peli.setPremios(premios);
		serviPeli.actualizaPelicula(peli, id);
		peliculas=new ArrayList<>();
		peliculas.addAll(serviPeli.getAll());
		*/
		pelicula = new PeliculaDTO();
		pelicula.setNombre(nombre);
		pelicula.setAnio(anio);
		pelicula.setPremios(premios);
		rt = new RestTemplate();
		HttpEntity<PeliculaDTO> request = new HttpEntity<>(pelicula);
		rt.put("http://localhost:8080/Pelicula/put/"+id,request, PeliculaDTO.class);
		
		peliculas = new ArrayList<>();
		peliculas = dameTodas();
		return peliculas;	
	}
	
	
	public void recuperaInfo(long id) {
		pelicula = new PeliculaDTO();
		rt = new RestTemplate();
		HttpEntity <PeliculaDTO> request = new HttpEntity<>(pelicula);
		pelicula = rt.getForEntity("http://localhost:8080/Pelicula/get/"+id, PeliculaDTO.class).getBody();
		this.nombre=pelicula.getNombre();
		this.premios=pelicula.getPremios();
		this.anio=pelicula.getAnio();
	}
	
	public String redirecciona() throws IOException {
		String url;
		FacesContext fc;
		peliculas = dameTodas();
		return "/inicio.xhtml?faces-redirect=true";
//		url="http://localhost:8081/inicio.xhtml";
//		fc = FacesContext.getCurrentInstance();
//		fc.getExternalContext().redirect(url);
	}
	
	public String redirecciona2() throws IOException {
		String url;
		FacesContext fc;
		peliculas = dameTodas();
		return "/detalle.xhtml?faces-redirect=true";
	}

	
	
	
	
}
