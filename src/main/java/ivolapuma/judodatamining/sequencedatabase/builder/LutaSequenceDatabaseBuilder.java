package ivolapuma.judodatamining.sequencedatabase.builder;

import java.util.ArrayList;
import java.util.List;

import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.sequencedatabase.Element;
import ivolapuma.judodatamining.sequencedatabase.Sequence;
import ivolapuma.judodatamining.sequencedatabase.SequenceDatabase;

public class LutaSequenceDatabaseBuilder {

	private List<LutaAcao> lutaAcoes;
	private List<Long> acaoTipoQuebras;
	private String judoca;

	public LutaSequenceDatabaseBuilder(List<LutaAcao> lutaAcoes, List<Long> acaoTipoQuebras, String judoca) {
		if (lutaAcoes == null) {
			throw new IllegalArgumentException("Lista de Açoes de Luta esta nula.");
		} else if (lutaAcoes.size() <= 0) {
			throw new IllegalArgumentException("Lista de Açoes de Luta esta vazia.");
		} else if (acaoTipoQuebras == null) {
			throw new IllegalArgumentException("Lista de Tipo de Açoes de Quebra de Element esta nula.");
		} else if (acaoTipoQuebras.size() <= 0) {
			throw new IllegalArgumentException("Lista de Tipo de Açoes de Quebra de Element esta vazia.");
		} else if (judoca == null || "".equals(judoca)) {
			throw new IllegalArgumentException("Nome do Judoca esta nulo ou vazio.");
		}
		this.lutaAcoes = lutaAcoes;
		this.acaoTipoQuebras = acaoTipoQuebras;
		this.judoca = judoca;
	}

	public SequenceDatabase build() {
		
		SequenceDatabase database = new SequenceDatabase();
		List<Sequence> sequences = new ArrayList<Sequence>();
		
		Sequence sequence = null;
		Element element = null;
		List<Long> items = null;
		long luta = -1;
		
		int i = 0;
		while (i < lutaAcoes.size()) {
			
			// 1a acao
			if (luta == -1) { 
				sequence = new Sequence();
				element = new Element();
				luta = lutaAcoes.get(0).getLuta();
			}
			
			LutaAcao lutaAcao = lutaAcoes.get(i);
			if (!judoca.equals(lutaAcao.getJudocaFaz())) {
				i++;
				continue;
			}
			items = extraiItemsDaLutaAcao(lutaAcao);
			
			// quebra de tipo de acao (element)
			if (acaoTipoQuebras.contains(lutaAcao.getAcaoTipoId())) {
				adicionaElementNaSequence(element, sequence);
				element = new Element();
			}
			// quebra de acao repetida (item ja incluido no element)
			else if (isItemRepetidoNoElement(items, element)) {
				adicionaElementNaSequence(element, sequence);
				element = new Element();
				adicionaItemsNoElement(items, element);
			}
			// quebra de luta (sequence) 
			else if (luta != lutaAcao.getLuta()) {
				adicionaElementNaSequence(element, sequence);
				adicionaSequenceNaLista(sequence, sequences);
				sequence = new Sequence();
				element = new Element();
				adicionaItemsNoElement(items, element);
				luta = lutaAcao.getLuta();
			}
			// adiciona acao no element
			else {
				adicionaItemsNoElement(items, element);	
			}
			
			i++;
		}
		
		adicionaElementNaSequence(element, sequence);
		adicionaSequenceNaLista(sequence, sequences);
		database.addSequences(sequences);
		
		return database;
	}

	private boolean isItemRepetidoNoElement(List<Long> items, Element element) {
		for (Long item : items) {
			if (element.getItems().contains(item)) {
				return true;
			}
		}
		return false;
	}

	private List<Long> extraiItemsDaLutaAcao(LutaAcao lutaAcao) {
		List<Long> items = new ArrayList<Long>();
		if (lutaAcao.getAcaoTecnicaDirecaoMps() != null) {
			items.add(lutaAcao.getAcaoTecnicaDirecaoMps().longValue());
			if (lutaAcao.getPontuacaoMps() != null) {
				items.add(lutaAcao.getPontuacaoMps().longValue());
			}
		} else {
			items.add(lutaAcao.getAcaoMps());
		}
		return items;
	}

	private void adicionaItemsNoElement(List<Long> items, Element element) {
		if (items.size() > 0) {
			for (Long item : items) {
				element.addItem(item);
			}
		}
	}

	private void adicionaSequenceNaLista(Sequence sequence, List<Sequence> sequences) {
		if (sequence.getElements().size() > 0) {
			sequences.add(sequence);	
		}
	}

	private void adicionaElementNaSequence(Element element, Sequence sequence) {
		if (element.getItems().size() > 0) {
			sequence.add(element);	
		}
	}
	
}
