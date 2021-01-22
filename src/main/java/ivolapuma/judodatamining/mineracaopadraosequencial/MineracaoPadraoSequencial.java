package ivolapuma.judodatamining.mineracaopadraosequencial;

import java.io.File;

import ivolapuma.judodatamining.exception.JudoDataMiningException;

public interface MineracaoPadraoSequencial {

	/**
	 * Executa o algoritmo de mineracao de padrao sequencial.
	 * 
	 * @param database Arquivo que contem a base de dados sequencial a ser processada pelo algoritmo. 
	 * @param resultado Arquivo que sera gerado com os padroes encontrados.
	 * @param indiceSustentacao Valor que representa o Indice de Sustentacao minimo dos padroes encontrados. Deve ser entre 0.0 e 1.0.
	 * @throws JudoDataMiningException 
	 */
	public void executar(File database, File resultado, double indiceSustentacao) throws JudoDataMiningException;
	
}
