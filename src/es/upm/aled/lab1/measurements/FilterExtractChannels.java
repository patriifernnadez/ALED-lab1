package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	private int[] canalesGuardados; // Creamos una variable para "recordar" qué canales queremos
	
	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		this.canalesGuardados = validChannels;// El constructor guarda los canales que nos piden en nuestra variable
		
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// 3. Obtenemos todas las medidas originales
				Measurement[] medidasOriginales = eeg.getMeasurements();
				
				// 4. Preparamos una caja nueva del mismo tamaño para guardar las medidas filtradas
				Measurement[] medidasNuevas = new Measurement[medidasOriginales.length];

				// 5. Recorremos cada medida (cada instante de tiempo) original
				for (int i = 0; i < medidasOriginales.length; i++) {
					
					// Creamos un array más pequeño solo para los canales que nos interesan
					float[] valoresFiltrados = new float[canalesGuardados.length];
					
					// Rellenamos este array pequeño buscando los valores en la medida original
					for (int j = 0; j < canalesGuardados.length; j++) {
						int canalQueQueremos = canalesGuardados[j];
						valoresFiltrados[j] = medidasOriginales[i].getChannel(canalQueQueremos);
					}
					
					// Guardamos la nueva medida ya recortada en nuestra caja nueva
					medidasNuevas[i] = new Measurement(valoresFiltrados);
				}
				
				// 6. Devolvemos un nuevo modelo de EEG usando el constructor que programaste antes
				return new EEGModel(medidasNuevas);
			
	}

}
