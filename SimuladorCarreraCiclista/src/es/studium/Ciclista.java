package es.studium;
// Definición de la clase Ciclista
public class Ciclista {
	// Atributos de la clase
	private int dorsal;
	private String nombre;
	private String equipo;
	private double kilometros;
	private int stamina;

	// Constructor de la clase Ciclista
	public Ciclista(int dorsal, String nombre, String equipo, double kilometros) {
		// Inicialización de los atributos con los valores proporcionados
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.equipo = equipo;
		this.kilometros = kilometros;
		// Asignación de la stamina según la posición del último dígito del dorsal
		this.stamina = asignarStaminaSegunPosicion();
	}

	// Método para obtener el dorsal del ciclista
	public int getDorsal() {
		return dorsal;
	}

	// Método para establecer el dorsal del ciclista
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	// Método para obtener el nombre del ciclista
	public String getNombre() {
		return nombre;
	}

	// Método para establecer el nombre del ciclista
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Método para obtener el equipo del ciclista
	public String getEquipo() {
		return equipo;
	}

	// Método para establecer el equipo del ciclista
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	// Método para obtener la distancia recorrida por el ciclista
	public double getKilometros() {
		return kilometros;
	}

	// Método para establecer la distancia recorrida por el ciclista
	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}

	// Método para obtener la stamina del ciclista
	public int getStamina() {
		return stamina;
	}

	// Método para establecer la stamina del ciclista
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	// Método privado para asignar la stamina según la posición del último dígito del dorsal
	private int asignarStaminaSegunPosicion() {
		// Obtener el último dígito del dorsal
		int ultimoDigito = dorsal % 10;
		// Calcular y devolver la stamina según la posición
		return 11 - (ultimoDigito > 8 ? 8 : ultimoDigito); //limita el valor máximo de ultimoDigito a 8.
	}
}
