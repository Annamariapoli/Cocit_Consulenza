package application;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import bean.AutoreAutoreArticolo;
import bean.Creator;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private SimpleWeightedGraph<Creator, DefaultWeightedEdge> grafo=null;
	
	public List<Creator> getAutori() throws SQLException{
		List<Creator> autori = new LinkedList<Creator>();
		autori= dao.getAllAutori();
		System.out.println(autori);
		return autori;
	}

	public List<AutoreAutoreArticolo> getCoppie() throws SQLException{
		List<AutoreAutoreArticolo> listaCoppie = dao.getCoppie();
		return listaCoppie;
	}
	
	public Creator getAutoreById(int idCreator) throws SQLException{
		Creator c = dao.getCreatorById(idCreator);
		return c;
	}
	
	public void buildGraph() throws SQLException{        //ok               
		grafo= new SimpleWeightedGraph<Creator, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		List<Creator> nodiAutori = getAutori();
		Graphs.addAllVertices(grafo,  nodiAutori);
		
		List<AutoreAutoreArticolo> aaa = getCoppie();
			for(Creator n1 : grafo.vertexSet()){
			for(Creator n2 : grafo.vertexSet()){
				if(!n1.equals(n2)){
				int numeroArticoli = calcoloNumeroArticoliComuni(n1, n2, aaa );
				if(numeroArticoli>0){
					Graphs.addEdge(grafo,  n1,  n2, numeroArticoli);
			        }
				}
     	  }
		  }
		System.out.println(grafo.toString());
	}
	
	public int calcoloNumeroArticoliComuni(Creator n1, Creator n2, List<AutoreAutoreArticolo> coppie) {  // ritorna sempre 0
		for(AutoreAutoreArticolo a_a_a : coppie){
			if(n1.getId_creator()==a_a_a.getIcCreator1()  && n2.getId_creator()==a_a_a.getIdCreator2()){
				return a_a_a.getNumeroArticoli();
			}
		}
		return 0;
	}
	
	public int getArcoPiuPesante(){     //ok
		int max =0;
		for(DefaultWeightedEdge arco : grafo.edgeSet()){
			if(grafo.getEdgeWeight(arco)> max ){
				max = (int) grafo.getEdgeWeight(arco);
			}
		}
		return max;
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
		DijkstraShortestPath<Creator, DefaultWeightedEdge> cammino = new DijkstraShortestPath<Creator, DefaultWeightedEdge>(grafo,a1, a2);
		GraphPath<Creator, DefaultWeightedEdge> path= cammino.getPath();
		if(path==null){
			return null;
		}
		autori= Graphs.getPathVertexList(path);
		return autori;
	}
}