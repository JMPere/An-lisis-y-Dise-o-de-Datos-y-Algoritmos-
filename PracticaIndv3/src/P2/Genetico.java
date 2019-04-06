package P2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class Genetico implements ValuesInRangeProblemAG<Integer,List<Integer>>{

	private static Integer procesadores;
	private static List<Integer> tareas;
	
	public  Genetico(String fichero) {
		// TODO Auto-generated constructor stub
		procesadores=Ej2.getProcesadores(Ej2.leerFichero(fichero));
		tareas=StringArrayToListInt(Ej2.getTareas(Ej2.leerFichero(fichero)));
	}
	
	public List<Integer> StringArrayToListInt(String[] a) {
		List<Integer> res=new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			res.add(Integer.parseInt(a[i]));
		}
		return res;
	}
	
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {		
		List<Double> res = IntStream.range(0, procesadores)
				.boxed().map(x -> 0.0)
				.collect(Collectors.toList());
		IntStream.range(0, res.size())
		.forEach(x-> res.set(x, Double.valueOf(IntStream.range(0, cr.decode().size())
				.filter(y -> cr.decode().get(y) == x+1)
				.map(y -> tareas.get(y))
				.sum())));
		
//		for(int i = 0; i < res.size(); i++) {
//			final int indice = i;
//			Double suma = Double.valueOf(IntStream.range(0, cr.decode().size()).filter(x -> cr.decode().get(x) == indice+1).map(x -> duraciones.get(x)).sum());
//			
//			res.set(i, suma);
//		}

		return -res.stream().max(Comparator.naturalOrder()).get();
	}

	@Override
	public Integer getVariableNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMax(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMin(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getProcesadores() {
		return procesadores;
	}
	
	public List<Integer> geTareas() {
		return tareas;
	}
	
}
