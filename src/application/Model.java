package application;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import bean.Creator;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private UndirectedGraph<Creator, DefaultEdge> grafo=null;
	
	public List<Creator> getAutori() throws SQLException{
		List<Creator> autori = new LinkedList<Creator>();
		autori= dao.getAllAutori();
		return autori;
	}

	public int artCom(Creator a1, Creator a2) throws SQLException{
		int num = dao.contoArticoliComune(a1, a2);
		return num;
	}
	
	public void buildGraph() throws SQLException{                              //NON PARTE
		grafo= new SimpleGraph<Creator, DefaultEdge>(DefaultEdge.class);
		List<Creator> nodi = getAutori();
		Graphs.addAllVertices(grafo, nodi);
		for(Creator a1 : nodi){
			for(Creator a2 : nodi){
				if(!a1.equals(a2)){
					int num = artCom(a1, a2);
					if(num>0){
					grafo.addEdge(a1, a2);
				    }
				}			
			}
		}
		System.out.println(grafo.toString());
	}

	public List<Creator > getCamminoMinimo(Creator a1 , Creator a2){
		List<Creator> autori = new LinkedList<Creator>();
		int id1= a1.getId_creator() ;
		int id2= a2.getId_creator() ;
		if(a1==null || a2==null){
			return null;
		}
		if(a1.equals(a2)){
			return null;
		}
		DijkstraShortestPath<Creator, DefaultEdge> cammino = new DijkstraShortestPath<Creator, DefaultEdge>(grafo,a1, a2);
		GraphPath<Creator, DefaultEdge> path= cammino.getPath();
		if(path==null){
			return null;
		}
		autori= Graphs.getPathVertexList(path);
		return autori;
	}

public  static void main(String [] args) throws SQLException{
	Model m = new Model();
	m.buildGraph();
	
}



}