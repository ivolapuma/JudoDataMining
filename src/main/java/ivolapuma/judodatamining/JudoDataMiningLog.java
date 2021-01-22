package ivolapuma.judodatamining;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ivolapuma.judodatamining.ferramenta.AnalisadorTecnicoTatico;

public class JudoDataMiningLog {

	private static final String NOVA_LINHA = "\n";
	private static final String SEPARADOR_CAMPO_LOG = ";";
	
	private final File logFile;

	public JudoDataMiningLog(File logFile) {
		this.logFile = logFile;
	}

	public void criarLog() throws IOException {
		StringBuffer str = new StringBuffer();
		str.append("JUDOCA").append(SEPARADOR_CAMPO_LOG);
		str.append("IND_SUSTENTACAO").append(SEPARADOR_CAMPO_LOG);
		str.append("TAM_MIN_PADRAO").append(SEPARADOR_CAMPO_LOG);
		str.append("ITENS_OBRIGATORIOS").append(SEPARADOR_CAMPO_LOG);
		str.append("NUM_PADROES").append(SEPARADOR_CAMPO_LOG);
		str.append("TEMPO_ALGORITMO (s)").append(SEPARADOR_CAMPO_LOG);
		str.append("TEMPO_TRADUCAO (s)").append(SEPARADOR_CAMPO_LOG);
		str.append("ARQUIVO");
		System.out.println(str.toString());
		FileWriter writer = new FileWriter(logFile);
		writer.write(str.toString());
		writer.write(NOVA_LINHA);
		writer.close();
	}

	public void logAnalise(AnalisadorTecnicoTatico analisador) throws IOException {
		StringBuffer str = new StringBuffer();
		str.append(analisador.getJudoca().getNome()).append(SEPARADOR_CAMPO_LOG);
		str.append(analisador.getIndiceSustentacao().toString().replace(".", ",")).append(SEPARADOR_CAMPO_LOG);
		str.append((analisador.getTamanhoMinimoPadrao() == null ? "" : analisador.getTamanhoMinimoPadrao())).append(SEPARADOR_CAMPO_LOG);
		str.append(arrayToString(analisador.getItensObrigatorios())).append(SEPARADOR_CAMPO_LOG);
		str.append(analisador.getNumeroPadroes()).append(SEPARADOR_CAMPO_LOG);
		str.append(String.valueOf(analisador.getTempoExecucaoAlgoritmo()).replace(".", ",")).append(SEPARADOR_CAMPO_LOG);
		str.append(String.valueOf(analisador.getTempoExecucaoTraducao()).replace(".", ",")).append(SEPARADOR_CAMPO_LOG);
		str.append(analisador.getTraducaoFile().getName());
		System.out.println(str.toString());
		FileWriter writer = new FileWriter(logFile, true);
		writer.write(str.toString());
		writer.write(NOVA_LINHA);
		writer.close();
	}

	private String arrayToString(int[] inteiros) {
		if (inteiros == null || inteiros.length == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i : inteiros) {
			buffer.append(i).append(" ");
		}
		return buffer.toString().trim();
	}

}
