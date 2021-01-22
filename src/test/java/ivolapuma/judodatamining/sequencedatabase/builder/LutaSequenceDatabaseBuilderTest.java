package ivolapuma.judodatamining.sequencedatabase.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ivolapuma.judodatamining.dominio.LutaAcao;
import ivolapuma.judodatamining.sequencedatabase.Sequence;
import ivolapuma.judodatamining.sequencedatabase.SequenceDatabase;
import ivolapuma.judodatamining.sequencedatabase.builder.LutaSequenceDatabaseBuilder;

public class LutaSequenceDatabaseBuilderTest {

	@Test
	public void build___listaAcoesLuta_2Lutas_1MateEmCadaLuta___retornaLista_2Sequences_1ElementCada() {
		
		List<LutaAcao> acoes = new ArrayList<>();
		// 1a luta
		acoes.add(new LutaAcao(1, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 4, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		// 2a luta
		acoes.add(new LutaAcao(2, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 4, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 5, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(10L); // tipo acao COMANDO_ARBITRO delimita um Element
		LutaSequenceDatabaseBuilder builder = new LutaSequenceDatabaseBuilder(acoes, acaoTipoQuebras, "AZUL");
		SequenceDatabase database = builder.build();
		List<Sequence> sequences = database.getSequences();
		
		assertNotNull("sequences nao pode ser null", sequences);
		assertEquals("sequences.size", 2, sequences.size());
		
		assertNotNull("1a sequence nao pode ser null", sequences.get(0));
		assertNotNull("elements da 1a sequence nao pode null", sequences.get(0).getElements());
		assertEquals("elements.size da 1a sequence", 1, sequences.get(0).getElements().size());
		assertNotNull("1o element da 1a sequence nao pode ser null", sequences.get(0).getElements().get(0));
		assertNotNull("items do 1o element da 1a sequence nao pode ser null", sequences.get(0).getElements().get(0).getItems());
		assertEquals("items.size do 1o element da 1a sequence", 3, sequences.get(0).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 1a sequence", "1 10 100", sequences.get(0).getElements().get(0).toString());
		
		assertNotNull("2a sequence nao pode ser null", sequences.get(1).getElements());
		assertEquals("elements.size da 2a sequence", 1, sequences.get(1).getElements().size());
		assertNotNull("1o element da 2a sequence nao pode ser null", sequences.get(1).getElements().get(0));
		assertNotNull("items do 1o element da 2a sequence nao pode ser null", sequences.get(1).getElements().get(0).getItems());
		assertEquals("items.size do 1o element da 2a sequence", 4, sequences.get(1).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001", sequences.get(1).getElements().get(0).toString());
	}

	@Test
	public void build___listaAcoesLuta_2Lutas_2MateNa1aLuta___retornaLista_2Sequences_2ElementsNa1aLuta() {
		
		List<LutaAcao> acoes = new ArrayList<>();
		// 1a luta
		acoes.add(new LutaAcao(1, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 4, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 5, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 6, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 7, "AZUL", "BRANCO", 3, "3D", 101, "101", 101, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 8, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), BigDecimal.ONE, "P1", BigDecimal.valueOf(10001)));
		acoes.add(new LutaAcao(1, 9, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		// 2a luta
		acoes.add(new LutaAcao(2, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 4, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 5, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(10L); // tipo acao COMANDO_ARBITRO delimita um Element
		LutaSequenceDatabaseBuilder builder = new LutaSequenceDatabaseBuilder(acoes, acaoTipoQuebras, "AZUL");
		SequenceDatabase database = builder.build();
		List<Sequence> sequences = database.getSequences();
		
		assertNotNull("sequences nao pode ser null", sequences);
		assertEquals("sequences.size", 2, sequences.size());
		
		assertEquals("elements.size da 1a sequence", 2, sequences.get(0).getElements().size());
		assertEquals("items.size do 1o element da 1a sequence", 3, sequences.get(0).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 1a sequence", "1 10 100", sequences.get(0).getElements().get(0).toString());
		assertEquals("items.size do 2o element da 1a sequence", 5, sequences.get(0).getElements().get(1).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11 101 3001 10001", sequences.get(0).getElements().get(1).toString());
		
		assertNotNull("2a sequence nao pode ser null", sequences.get(1).getElements());
		assertEquals("elements.size da 2a sequence", 1, sequences.get(1).getElements().size());
		assertNotNull("1o element da 2a sequence nao pode ser null", sequences.get(1).getElements().get(0));
		assertNotNull("items do 1o element da 2a sequence nao pode ser null", sequences.get(1).getElements().get(0).getItems());
		assertEquals("items.size do 1o element da 2a sequence", 4, sequences.get(1).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001", sequences.get(1).getElements().get(0).toString());
	}

	@Test
	public void build___listaAcoesLuta_2Lutas_2MateNa1aLuta_acoesRepetidasEntreMates___retornaLista_2Sequences_3ElementsNa1aLuta() {
		
		List<LutaAcao> acoes = new ArrayList<>();
		// 1a luta
		acoes.add(new LutaAcao(1, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 4, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 5, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 6, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 7, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 8, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 9, "AZUL", "BRANCO", 3, "3D", 101, "101", 101, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 10, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), BigDecimal.ONE, "P1", BigDecimal.valueOf(10001)));
		acoes.add(new LutaAcao(1, 11, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		// 2a luta
		acoes.add(new LutaAcao(2, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 4, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 5, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(10L); // tipo acao COMANDO_ARBITRO delimita um Element
		LutaSequenceDatabaseBuilder builder = new LutaSequenceDatabaseBuilder(acoes, acaoTipoQuebras, "AZUL");
		SequenceDatabase database = builder.build();
		List<Sequence> sequences = database.getSequences();
		
		assertNotNull("sequences nao pode ser null", sequences);
		assertEquals("sequences.size", 2, sequences.size());
		
		assertEquals("elements.size da 1a sequence", 3, sequences.get(0).getElements().size());
		assertEquals("items.size do 1o element da 1a sequence", 3, sequences.get(0).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 1a sequence", "1 10 100", sequences.get(0).getElements().get(0).toString());
		assertEquals("items.size do 2o element da 1a sequence", 2, sequences.get(0).getElements().get(1).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11", sequences.get(0).getElements().get(1).toString());
		assertEquals("items.size do 3o element da 1a sequence", 5, sequences.get(0).getElements().get(2).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11 101 3001 10001", sequences.get(0).getElements().get(2).toString());
		
		assertNotNull("2a sequence nao pode ser null", sequences.get(1).getElements());
		assertEquals("elements.size da 2a sequence", 1, sequences.get(1).getElements().size());
		assertNotNull("1o element da 2a sequence nao pode ser null", sequences.get(1).getElements().get(0));
		assertNotNull("items do 1o element da 2a sequence nao pode ser null", sequences.get(1).getElements().get(0).getItems());
		assertEquals("items.size do 1o element da 2a sequence", 4, sequences.get(1).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001", sequences.get(1).getElements().get(0).toString());
	}

	@Test
	public void build___listaAcoesLuta_2Lutas_1MateNa2aLuta_apos1oMate2AtaquesSeguidosEPontuacao___retornaLista_2Sequences_2ElementsNa2aLuta() {
		
		List<LutaAcao> acoes = new ArrayList<>();
		// 1a luta
		acoes.add(new LutaAcao(1, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 4, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 5, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 6, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 7, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 8, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 9, "AZUL", "BRANCO", 3, "3D", 101, "101", 101, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 10, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), BigDecimal.ONE, "P1", BigDecimal.valueOf(10001)));
		acoes.add(new LutaAcao(1, 11, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		// 2a luta
		acoes.add(new LutaAcao(2, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 4, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 5, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 6, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 7, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 8, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 9, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 10, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.valueOf(2), "T2", BigDecimal.valueOf(2), "D2", BigDecimal.valueOf(3002), BigDecimal.ONE, "P1", BigDecimal.valueOf(10001)));
		
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(10L); // tipo acao COMANDO_ARBITRO delimita um Element
		LutaSequenceDatabaseBuilder builder = new LutaSequenceDatabaseBuilder(acoes, acaoTipoQuebras, "AZUL");
		SequenceDatabase database = builder.build();
		List<Sequence> sequences = database.getSequences();
		
		assertNotNull("sequences nao pode ser null", sequences);
		assertEquals("sequences.size", 2, sequences.size());
		
		assertEquals("elements.size da 1a sequence", 3, sequences.get(0).getElements().size());
		assertEquals("items.size do 1o element da 1a sequence", 3, sequences.get(0).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 1a sequence", "1 10 100", sequences.get(0).getElements().get(0).toString());
		assertEquals("items.size do 2o element da 1a sequence", 2, sequences.get(0).getElements().get(1).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11", sequences.get(0).getElements().get(1).toString());
		assertEquals("items.size do 3o element da 1a sequence", 5, sequences.get(0).getElements().get(2).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11 101 3001 10001", sequences.get(0).getElements().get(2).toString());
		
		assertNotNull("2a sequence nao pode ser null", sequences.get(1).getElements());
		assertEquals("elements.size da 2a sequence", 2, sequences.get(1).getElements().size());
		assertEquals("items.size do 1o element da 2a sequence", 4, sequences.get(1).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001", sequences.get(1).getElements().get(0).toString());
		assertEquals("items.size do 1o element da 2a sequence", 6, sequences.get(1).getElements().get(1).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001 3002 10001", sequences.get(1).getElements().get(1).toString());
	}

	@Test
	public void build___listaAcoesLuta_2Lutas_buildParaJudocaAzul_comAcoesJudocaBranco___retornaLutaSequenceSomenteJudocaAzul() {
		
		List<LutaAcao> acoes = new ArrayList<>();
		// 1a luta
		acoes.add(new LutaAcao(1, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 4, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 5, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 6, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 7, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 8, "AZUL", "BRANCO", 2, "2D", 11, "11", 11, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 9, "AZUL", "BRANCO", 3, "3D", 101, "101", 101, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(1, 10, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), BigDecimal.ONE, "P1", BigDecimal.valueOf(10001)));
		acoes.add(new LutaAcao(1, 11, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		// 2a luta
		acoes.add(new LutaAcao(2, 1, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 2, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 3, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 4, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 5, "AZUL", "BRANCO", 10, "COMANDO_ARBITRO", 0, "MATE", 0, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 6, "BRANCO", "AZUL", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 7, "BRANCO", "AZUL", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 8, "BRANCO", "AZUL", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 9, "AZUL", "BRANCO", 1, "1D", 1, "1", 1, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 10, "AZUL", "BRANCO", 2, "2D", 10, "10", 10, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 11, "AZUL", "BRANCO", 3, "3D", 100, "100", 100, null, null, null, null, null, null, null, null));
		acoes.add(new LutaAcao(2, 12, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.ONE, "T1", BigDecimal.ONE, "D1", BigDecimal.valueOf(3001), null, null, null));
		acoes.add(new LutaAcao(2, 13, "AZUL", "BRANCO", 4, "4D", 1000, "1000", 1000, BigDecimal.valueOf(2), "T2", BigDecimal.valueOf(2), "D2", BigDecimal.valueOf(3002), BigDecimal.ONE, "P1", BigDecimal.valueOf(10001)));
		
		List<Long> acaoTipoQuebras = new ArrayList<>();
		acaoTipoQuebras.add(10L); // tipo acao COMANDO_ARBITRO delimita um Element
		LutaSequenceDatabaseBuilder builder = new LutaSequenceDatabaseBuilder(acoes, acaoTipoQuebras, "AZUL");
		SequenceDatabase database = builder.build();
		List<Sequence> sequences = database.getSequences();
		
		assertNotNull("sequences nao pode ser null", sequences);
		assertEquals("sequences.size", 2, sequences.size());
		
		assertEquals("elements.size da 1a sequence", 3, sequences.get(0).getElements().size());
		assertEquals("items.size do 1o element da 1a sequence", 3, sequences.get(0).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 1a sequence", "1 10 100", sequences.get(0).getElements().get(0).toString());
		assertEquals("items.size do 2o element da 1a sequence", 2, sequences.get(0).getElements().get(1).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11", sequences.get(0).getElements().get(1).toString());
		assertEquals("items.size do 3o element da 1a sequence", 5, sequences.get(0).getElements().get(2).getItems().size());
		assertEquals("items do 2o element da 1a sequence", "1 11 101 3001 10001", sequences.get(0).getElements().get(2).toString());
		
		assertNotNull("2a sequence nao pode ser null", sequences.get(1).getElements());
		assertEquals("elements.size da 2a sequence", 2, sequences.get(1).getElements().size());
		assertEquals("items.size do 1o element da 2a sequence", 4, sequences.get(1).getElements().get(0).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001", sequences.get(1).getElements().get(0).toString());
		assertEquals("items.size do 1o element da 2a sequence", 6, sequences.get(1).getElements().get(1).getItems().size());
		assertEquals("items do 1o element da 2a sequence", "1 10 100 3001 3002 10001", sequences.get(1).getElements().get(1).toString());
	}

}
