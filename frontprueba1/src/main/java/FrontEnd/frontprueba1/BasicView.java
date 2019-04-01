package FrontEnd.frontprueba1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import BackEnd.backprueba1.PeliculaDTO;
import BackEnd.backprueba1.PeliculaRepository;
import BackEnd.backprueba1.PeliculaService;
import lombok.Data;

@ManagedBean
@ViewScoped
@Data
public class BasicView implements Serializable{
	
	
	private long id;
	private String nombre;
	private String anio;
	private long premios;
	
	private List<PeliculaDTO> peliculas;
	
	private PeliculaRepository pelisRepo;
	private PeliculaDTO pelicula;

	
	//private PeliculaService serviPeli;
	
	RestTemplate rt;
	
	public BasicView() {
		
	}
	
	public List<PeliculaDTO> getPeliculas(){
		return peliculas;
	}
	
	public void setPelicula(PeliculaService serviPeli) {
		//this.serviPeli=serviPeli;
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
	
	
	public void buscar() {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		ResponseEntity<PeliculaDTO[]> res =  rt.getForEntity("http://localhost:8080/Pelicula/busca?id="+id+"&nombre="+nombre+"&anio="+anio+"&premios="+premios, PeliculaDTO[].class);
		peliculas.addAll(Lists.newArrayList(res.getBody())); 
		
	}//Fin Metodo
	
	public /*List<PeliculaDTO>*/ void agregar() {
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
		/*peliculas = new ArrayList<>();
		peliculas.addAll(serviPeli.getAll());
		return peliculas;*/
	}
	
	
	public /*List<PeliculaDTO>*/ void borraPeli(long id) {
		peliculas = new ArrayList<>();
		rt = new RestTemplate();
		rt.delete("http://localhost:8080/Pelicula/Delete/"+id);
		/*peliculas.addAll(serviPeli.getAll());
		return peliculas;*/
	}
	

	public /*List<PeliculaDTO>*/ void actualiza(long id) {
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
		
		/*peliculas = new ArrayList<>();
		peliculas.addAll(serviPeli.getAll());
		return peliculas;	*/	
	}
	
	
	public void recuperaInfo(long id) {
		pelicula = new PeliculaDTO();
		//pelicula = serviPeli.buscar(id);
		this.nombre=pelicula.getNombre();
		this.premios=pelicula.getPremios();
		this.anio=pelicula.getAnio();
	}
	
	

	
	
	
	
}
