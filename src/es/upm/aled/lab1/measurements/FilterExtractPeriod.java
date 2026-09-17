package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified period from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractPeriod implements Filter {

	// 1. Variables para "recordar" dónde empezamos y dónde acabamos
		private int inicio;
		private int fin;
	
	/**
	 * Builds the Filter from the [min, max] range defining the period that needs to
	 * be extracted. min and max are the indexes of the first and last measurements
	 * of the array obtained by calling the getMeasurements() method of EEGModel,
	 * and represent the starting and ending point of the period to be extracted.
	 * Both indexes are included and max-min must be less than the length of the
	 * Measurements array of the EGG Model.
	 * 
	 * @param min Start of the period to be extracted.
	 * @param max End of the period to be extracted.
	 */
	public FilterExtractPeriod(int min, int max) {
		// 2. El constructor guarda los límites
				this.inicio = min;
				this.fin = max;
		
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// 3. Sacamos todas las muestras originales
				Measurement[] medidasOriginales = eeg.getMeasurements();
				
				// 4. Calculamos cuántas muestras vamos a guardar en total. 
				// Le sumamos 1 porque ambos límites están incluidos (ej: de la 1 a la 3 hay 3 números: 1, 2 y 3).
				int tamanoNuevo = (fin - inicio) + 1;
				
				// 5. Preparamos la caja nueva con ese tamaño exacto
				Measurement[] medidasNuevas = new Measurement[tamanoNuevo];
				
				// 6. Recorremos solo el trozo que nos interesa y lo copiamos a la caja nueva
				for (int i = 0; i < tamanoNuevo; i++) {
					medidasNuevas[i] = medidasOriginales[inicio + i];
				}
				
				// 7. Devolvemos el modelo resultante
				return new EEGModel(medidasNuevas);
			}
		
}
