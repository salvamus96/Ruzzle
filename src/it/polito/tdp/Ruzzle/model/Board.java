package it.polito.tdp.Ruzzle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 * @author Fulvio
 *
 */
public class Board {
	// elenco delle posizioni possibili, ogni posizione è caratterizzata da [row, col]
	private List<Pos> positions;
	
	// una mappa che rappresenta il contenuto di ogni cella a cui si accede tramite posizione
	// con una StringProperty posso aggangiare il contenuto all'intefaccia utente e le lettere
	// in questo modo si aggiornano in automatico con un binding
	private Map<Pos, StringProperty> cells;

	private int size;

	/**
	 * Crea una nuova scacchiera della dimensione specificata
	 * @param size
	 */
	public Board(int size) {
		this.size = size;

		this.positions = new ArrayList<>();
		// costruzione di tutte le posizioni possibili
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				this.positions.add(new Pos(row, col));
			}
		}

		this.cells = new HashMap<>();

		for (Pos p : this.positions) {
			this.cells.put(p, new SimpleStringProperty());
		}
	}
	
	/**
	 * Fornisce la {@link StringProperty} corrispondente alla {@link Pos} specificata.
	 * 
	 * Può essere usata per sapere che lettera è presente
	 * (es. {@code getCellValueProperty(p).get()}) oppure per fare un binding della proprietà 
	 * stessa sulla mappa visuale.
	 * @param p
	 * @return
	 */
	public StringProperty getCellValueProperty(Pos p) {
		return this.cells.get(p) ;
	}

	/**
	 * Restituisce la lista di oggetti {@link  Pos} che corrispondono alle posizioni lecite sulla scacchiera. 
	 * Gli elementi sono ordinati per righe.
	 * @return
	 */
	public List<Pos> getPositions() {
		return positions;
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casualmente, si
	 * potrebbe creare una variante inserendo le lettere in base alla loro 
	 * frequenza di "apparizione" nel dizionario
	 */
	public void reset() {
		for(Pos p: this.positions) {
			int random = (int)(Math.random()*26) ;
			String letter = Character.toString((char)('A'+random)) ;
			this.cells.get(p).set(letter); 
		}
	}

	public List<Pos> getAdiacenti(Pos ultima) {
		List<Pos> result = new ArrayList<>() ;
		
		for(int r = -1; r<=1; r++) {
			for(int c = -1; c<=1; c++) {
				// tutte le 9 posizioni nell'intorno della cella
				
				//if(! (r==0 && c==0))
				
				if(r!=0 || c!=0) { // escluda la cella stessa (offset 0,0)
					Pos p = new Pos(ultima.getRow()+r, ultima.getCol()+c) ;
					if(positions.contains(p)) 
						result.add(p) ;
					
				}
			}
		}
		
		return result ;
	}

	
}
