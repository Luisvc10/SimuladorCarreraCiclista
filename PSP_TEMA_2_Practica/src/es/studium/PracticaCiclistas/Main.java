package es.studium.PracticaCiclistas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		ArrayList<Ciclista> ciclistas = new ArrayList<>();

		// Paso 1: Leer el archivo de ciclistas y cargar los datos en el ArrayList
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\luigi\\eclipse-workspace\\PSP_TEMA_2_Practica\\src\\ciclistas.txt"));
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(", ");
				int dorsal = Integer.parseInt(datos[0]);
				String nombre = datos[1];
				String equipo = datos[2];
				int stamina = 10 - (ciclistas.size() % 8); // Calculamos la stamina según la posición dentro del equipo
				ciclistas.add(new Ciclista(dorsal, nombre, equipo, 0, stamina));
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}

		// Paso 2: Crear y ejecutar hilos para cada ciclista
		ArrayList<Thread> threads = new ArrayList<>();
		for (Ciclista c : ciclistas) {
			Thread t = new Thread(new AvanceCiclista(c));
			threads.add(t);
			t.start();
		}

		// Esperar a que todos los hilos terminen
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Paso 4: Mostrar el ranking final
		mostrarRanking(ciclistas);

		// Paso 5: Guardar el nuevo orden de los ciclistas en el archivo original
		guardarNuevoOrden(ciclistas);
	}

	private static void mostrarRanking(ArrayList<Ciclista> ciclistas) {
		// Ordenar la lista de ciclistas por la cantidad de kilómetros recorridos (de mayor a menor)
		Collections.sort(ciclistas, Comparator.comparingDouble(Ciclista::getKilometros).reversed());

		// Mostrar el ranking final
		System.out.println("\nClasificación final:");
		int posicion = 1;
		for (Ciclista c : ciclistas) {
			System.out.println(posicion + ":    " + c.getDorsal() + "- " + c.getNombre() + " " + c.getEquipo() + " " + c.getKilometros() + " Kilómetros");
			posicion++;
		}
	}

	private static void guardarNuevoOrden(ArrayList<Ciclista> ciclistas) {
	    // Escribir el nuevo orden de los ciclistas en el archivo original
	    try {
	        FileWriter fw = new FileWriter("C:\\Users\\luigi\\eclipse-workspace\\PSP_TEMA_2_Practica\\src\\ciclistas.txt");
	        for (Ciclista c : ciclistas) {
	            fw.write(c.getDorsal() + ", " + c.getNombre() + ", " + c.getEquipo() + "\n");
	        }
	        fw.close();
	    } catch (IOException e) {
	        System.err.println("Error al escribir en el archivo: " + e.getMessage());
	    }
	}
}