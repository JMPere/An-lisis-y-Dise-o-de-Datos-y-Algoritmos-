package P4;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import us.lsi.graphs.GraphsReader;

public class Ej4 {

	public static void main(String[] args) {

		Graph<Monumento, Camino> g = GraphsReader.newGraph("./ficheros/P4-Grafo1.txt", Monumento::create,
				Camino::create, () -> new SimpleWeightedGraph<Monumento, Camino>(Monumento::create, Camino::create),
				Camino::getTiempo);

		Graph<Monumento, Camino> dig = GraphsReader.newGraph("./ficheros/P4-Grafo2.txt", Monumento::create,
				Camino::create,
				() -> new SimpleDirectedWeightedGraph<Monumento, Camino>(Monumento::create, Camino::create),
				Camino::getTiempo);

		apartadoA(g);
		apartadoB(dig);
		apartadoC("Sitio0", "Sitio6", g);

	}

	public static void apartadoA(Graph<Monumento, Camino> grafo) {

		ConnectivityInspector<Monumento, Camino> resG = new ConnectivityInspector<>(grafo);
		System.out.println("================================APARTADO A==================================\n");
		if (resG.isConnected()) {
			System.out.print("Están todos los sitios conectados entre sí? : Sí.");
		} else {
			System.out.print("Están todos los sitios conectados entre sí? : No.");
		}
		System.out.println("Las listas conexas son= {");
		List<Set<Monumento>> Sets = resG.connectedSets();
		for (Set<Monumento> set : Sets) {
			System.out.println(set);
		}
		System.out.println("}");
	}

	public static void apartadoB(Graph<Monumento, Camino> grafo) {

		Set<Monumento> vertices = grafo.vertexSet();
		System.out.println("================================APARTADO B==================================\n");
		System.out.print("Lista de monumentos que se pueden visitar inicialmente sin haber visto otros: ");
		System.out.println(
				vertices.stream().filter(x -> grafo.incomingEdgesOf(x).isEmpty()).collect(Collectors.toList()) + "\n");
	}

	public static void apartadoC(String m0, String m1, Graph<Monumento, Camino> grafo) {

		System.out.println("================================APARTADO C==================================\n");
		List<Monumento> extremos = new LinkedList<>();

		extremos.add(grafo.vertexSet().stream().filter(x -> x.getNombre().equals(m0)).findFirst().get());
		extremos.add(grafo.vertexSet().stream().filter(x -> x.getNombre().equals(m1)).findFirst().get());

		DijkstraShortestPath<Monumento, Camino> sub = new DijkstraShortestPath<Monumento, Camino>(grafo,
				extremos.get(0), extremos.get(1));

		if (sub.getPathEdgeList() != null) {

			double sumaTiempo = 0.0;
			for (Camino c : sub.getPathEdgeList()) {
				sumaTiempo += c.getTiempo();
			}
			System.out.println("Visita desde " + extremos.get(0) + " hasta " + extremos.get(1) + " con menor tiempo: "
					+ sumaTiempo + " mins");
			if (m0.compareTo(m1) < 0) {
				System.out.println("Ruta : " + sub.getPathEdgeList().stream()
						.map(x -> "[" + x.getOrigen() + " - " + x.getDestino() + "]").collect(Collectors.toList()));
			} else {
				System.out.println("Ruta : " + sub.getPathEdgeList().stream()
						.map(x -> "[" + x.getDestino() + " - " + x.getOrigen() + "]").collect(Collectors.toList()));
			}
		} else {
			System.out.println("No hay camino posible");
		}

	}

	public static Monumento buscarVertice(Graph<Monumento, Camino> grafo, String nombre) {
		return grafo.vertexSet().stream().filter(x -> x.getNombre().equals(nombre)).findFirst().get();
	}

}
