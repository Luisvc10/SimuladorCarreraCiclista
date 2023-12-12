package es.studium;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Carrera {
	private ArrayList<Ciclista> ciclistas;

	public Carrera() {
		this.ciclistas = new ArrayList<>();
	}

	public void cargarCiclistasDesdeArchivo(String nombreArchivo) {
		try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				String[] datosCiclista = linea.split(", ");
				if (datosCiclista.length >= 3) {
					try {
						int dorsal = Integer.parseInt(datosCiclista[0]);
						String nombre = datosCiclista[1];
						String equipo = datosCiclista[2];
						ciclistas.add(new Ciclista(dorsal, nombre, equipo, 0));
					} catch (NumberFormatException e) {
						System.err.println("Error en el formato de la línea: " + linea);
						e.printStackTrace();
					}
				} else {
					System.err.println("Error en el formato de la línea: " + linea);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void simularCarrera() {
		long startTime = System.currentTimeMillis();
		long tiempoLimite = 60 * 1000; // 60 segundos

		while (System.currentTimeMillis() - startTime < tiempoLimite) {
			for (Ciclista ciclista : ciclistas) {
				double distanciaAvance = (Math.random() * 5) * (ciclista.getStamina() / 12.0) * 10.0;
				ciclista.setKilometros(ciclista.getKilometros() + distanciaAvance);

				System.out.println("Corredor " + ciclista.getDorsal() + " ha avanzado " +
						String.format("%.2f", distanciaAvance) +
						" y en total lleva " + String.format("%.2f", ciclista.getKilometros()));

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		mostrarRanking();
		guardarCiclistasEnArchivo("ciclistas_actualizado.txt");
	}


	public void mostrarRanking() {
		ciclistas.sort((c1, c2) -> Double.compare(c2.getKilometros(), c1.getKilometros()));

		System.out.println("Clasificación final:");
		for (int i = 0; i < ciclistas.size(); i++) {
			Ciclista ciclista = ciclistas.get(i);
			System.out.println((i + 1) + ": " + ciclista.getDorsal() + "-\t" +
					ciclista.getNombre() + "\t" + ciclista.getKilometros() + " kilómetros");
		}
	}

	public void guardarCiclistasEnArchivo(String nombreArchivo) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
	        for (Ciclista ciclista : ciclistas) {
	            writer.write(ciclista.getDorsal() + ", " + ciclista.getNombre() + ", " +
	                    ciclista.getEquipo() + ", " + ciclista.getKilometros() + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		Carrera carrera = new Carrera();
		carrera.cargarCiclistasDesdeArchivo("ciclistas.txt");
		carrera.simularCarrera();
	}
}
