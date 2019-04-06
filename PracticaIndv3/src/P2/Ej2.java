package P2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class Ej2 {

	public static void main(String[] args) {
		
		System.out.println("======================TEST ESPECÍFICO=================================");
		SolutionPLI a = AlgoritmoPLI.getSolutionFromFile("./ficheros/Especifico.txt");
		System.out.println("-------------------");	
		System.out.println("________");
		System.out.println(a.getGoal());
		for (int j = 0; j < a.getNumVar(); j++) {
			System.out.println(a.getName(j)+" = "+a.getSolution()[j]);
		}
		System.out.println("________");
		
		System.out.println("======================TEST GENÉRICO=================================");
		
		List<String> b= leerFichero("ficheros/generico.txt");
		System.out.println("\nFormato LPsolve:\n\n"+concatenarProblema(b));
		SolutionPLI c = AlgoritmoPLI.getSolution(concatenarProblema(b));
		System.out.println("-------------------");	
		System.out.println("________");
		for (int j = 0; j < c.getNumVar(); j++) {
			System.out.println(c.getName(j)+" = "+c.getSolution()[j]);
		}
		System.out.println("________");
	}
	
	public static List<String> leerFichero(String fichero) {
		List<String> res=null;
		try {
			 res = Files.readAllLines(Paths.get(fichero));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	private static String funcionObjetivo(List<String> fichero) {
		
		String res="min: T;\n\n";
		String[] tareas = getTareas(fichero);

		for (int i = 0; i < getProcesadores(fichero); i++) {
			for (int j = 0; j < tareas.length; j++) {
				res+="+"+tareas[j]+"*x"+i+j;
			}
			res+="<=T;\n";
		}
		return res+"\n";
	}
	
	private static String Restricciones(List<String> fichero) {
		
		String res="";
		
		for (int i = 0; i < getTareas(fichero).length; i++) {
			for (int j = 0; j < getProcesadores(fichero); j++) {
				res+="+x"+j+i;
			}
			res+="=1;\n";
		}
		
		return res+"\n";
	}
	
	private static String declaraVariables(List<String> fichero) {
		String res="int T;\nbin ";
		for (int i = 0; i < getProcesadores(fichero); i++) {
			for (int j = 0; j < getTareas(fichero).length; j++) {
				res+=" x"+i+j;
			}
		}
		return res+";";
	}
	
	private static String concatenarProblema(List<String> fichero) {

		String res = funcionObjetivo(fichero);
		res += Restricciones(fichero)+declaraVariables(fichero);
		return res;
	}
	
	public static String[] getTareas(List<String> fichero) {
		return fichero.get(0).split(",");
	}
	
	public static Integer getProcesadores(List<String> fichero) {
		return Integer.parseInt(fichero.get(1).trim());
	}
	
}
