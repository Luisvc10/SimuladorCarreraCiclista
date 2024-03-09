package es.studium.PracticaCiclistas;

import java.util.Random;

public class AvanceCiclista implements Runnable
{
	private Ciclista ciclista;

	public AvanceCiclista(Ciclista ciclista) 
	{
		this.ciclista = ciclista;
	}

	@Override
	public void run() 
	{
		Random rand = new Random();
		for (int i = 0; i < 20; i++) 
		{
			double avance = rand.nextInt(5) + 1; // Avance aleatorio entre 1 y 5
			avance *= (double) ciclista.getStamina() / 12 * 10; // Fórmula de avance según stamina
			ciclista.setKilometros(ciclista.getKilometros() + avance);
			System.out.println("Corredor " + ciclista.getDorsal() + " ha avanzado " + avance + " y en total lleva " + ciclista.getKilometros() + " kilómetros");
			try 
			{
				Thread.sleep(3000); // "Dormir" al ciclista durante 3 segundos
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}