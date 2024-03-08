package es.studium;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Carrera {
	// ArrayList para almacenar objetos de la clase Ciclista
	private ArrayList<Ciclista> ciclistas;

	// Constructor de la clase Carrera
	public Carrera() {
		this.ciclistas = new ArrayList<>();
	}

	// Método para cargar ciclistas desde un archivo de texto
	public void cargarCiclistasDesdeArchivo(String nombreArchivo) {
		try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
			String linea;
			// Lectura de cada línea del archivo
			while ((linea = reader.readLine()) != null) {
				// Ignorar líneas en blanco
				if (linea.trim().isEmpty()) {
					continue;
				}

				// Dividir la línea en partes usando la coma como separador
				String[] datosCiclista = linea.split(", ");
				// Verificar el formato de la línea
				if (datosCiclista.length >= 3) {
					try {
						// Convertir datos y agregar nuevo ciclista a la lista
						int dorsal = Integer.parseInt(datosCiclista[0]);
						String nombre = datosCiclista[1];
						String equipo = datosCiclista[2];
						ciclistas.add(new Ciclista(dorsal, nombre, equipo, 0));
					} catch (NumberFormatException e) {
						// Manejar errores de formato
						System.err.println("Error en el formato de la línea: " + linea);
						e.printStackTrace();
					}
				} else {
					// Manejar errores de formato
					System.err.println("Error en el formato de la línea: " + linea);
				}
			}
		} catch (IOException e) {
			// Manejar errores de entrada/salida
			e.printStackTrace();
		}
	}

	// Método para simular una carrera de ciclistas
	public void simularCarrera() {
		// Tiempo inicial de la simulación
		long startTime = System.currentTimeMillis();
		// Tiempo límite de la simulación (60 segundos)
		long tiempoLimite = 60 * 1000;

		// Bucle de simulación mientras no se alcance el tiempo límite
		while (System.currentTimeMillis() - startTime < tiempoLimite) {
			// Iterar sobre cada ciclista en la lista
			for (Ciclista ciclista : ciclistas) {
				// Calcular la distancia de avance de forma aleatoria
				double distanciaAvance = (Math.random() * 5) * (ciclista.getStamina() / 12.0) * 10.0;
				// Actualizar la distancia total recorrida por el ciclista
				ciclista.setKilometros(ciclista.getKilometros() + distanciaAvance);

				// Mostrar información sobre el avance del ciclista
				System.out.println("Corredor " + ciclista.getDorsal() + " ha avanzado " +
						String.format("%.2f", distanciaAvance) +
						" y en total lleva " + String.format("%.2f", ciclista.getKilometros()));

				// Pausa de 3 segundos entre avances de ciclistas
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// Manejar interrupciones durante la pausa
					e.printStackTrace();
				}
			}
		}

		// Mostrar el ranking final y guardar información en archivo
		mostrarRanking();
		guardarCiclistasEnArchivo("ciclistas_actualizado.txt");
	}

	// Método para mostrar el ranking de la carrera
	public void mostrarRanking() {
		// Ordenar la lista de ciclistas en función de la distancia recorrida
		ciclistas.sort((c1, c2) -> Double.compare(c2.getKilometros(), c1.getKilometros()));

		// Mostrar el ranking final
		System.out.println("Clasificación final:");
		for (int i = 0; i < ciclistas.size(); i++) {
			Ciclista ciclista = ciclistas.get(i);
			System.out.println((i + 1) + ": " + ciclista.getDorsal() + "-\t" +
					ciclista.getNombre() + "\t" + ciclista.getKilometros() + " kilómetros");
		}
	}

	// Método para guardar ciclistas en un archivo de texto
	public void guardarCiclistasEnArchivo(String nombreArchivo) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
			// Escribir información de cada ciclista en el archivo
			for (Ciclista ciclista : ciclistas) {
				writer.write(ciclista.getDorsal() + ", " + ciclista.getNombre() + ", " +
						ciclista.getEquipo() + ", " + ciclista.getKilometros() + "\n");
			}
		} catch (IOException e) {
			// Manejar errores de entrada/salida
			e.printStackTrace();
		}
	}

	// Método principal (main) para probar la funcionalidad de la clase
	public static void main(String[] args) {
		// Crear una instancia de la clase Carrera
		Carrera carrera = new Carrera();
		// Cargar ciclistas desde un archivo de texto
		carrera.cargarCiclistasDesdeArchivo("ciclistas.txt");
		// Simular la carrera
		carrera.simularCarrera();
	}
}
