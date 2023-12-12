package es.studium;
public class Ciclista {
	private int dorsal;
	private String nombre;
	private String equipo;
	private double kilometros;
	private int stamina;

	public Ciclista(int dorsal, String nombre, String equipo, double kilometros) {
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.equipo = equipo;
		this.kilometros = kilometros;
		this.stamina = asignarStaminaSegunPosicion();
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public double getKilometros() {
		return kilometros;
	}

	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	private int asignarStaminaSegunPosicion() {
	    int ultimoDigito = dorsal % 10;
	    return 11 - (ultimoDigito > 8 ? 8 : ultimoDigito);
	}

}
