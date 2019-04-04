package FrontEnd.frontprueba1;

import java.io.File;

public class PeliculaDTO {
	
	//Entorno:
		private long id;
		private String nombre;
		private String anio;
		private long premios;
		
		//Metodos-Funciones:
		
		public long getId() {
			return this.id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		
		public String getNombre() {
			return this.nombre;
		}
		
		public void setNombre(String nombre) {
			this.nombre=nombre;
		}
		
		public String getAnio() {
			return this.anio;
		}
		
		public void setAnio(String anio) {
			this.anio=anio;
		}
		
		public long getPremios() {
			return this.premios;
		}
		
		public void setPremios(long premios) {
			this.premios=premios;
		}
		
		
		

}
