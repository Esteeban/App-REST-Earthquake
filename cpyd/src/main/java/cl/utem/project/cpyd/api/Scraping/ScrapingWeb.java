package cl.utem.project.cpyd.api.Scraping;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapingWeb{
	
	/**
	 * Método que obtiene el codigo HTML de la página web
	 * @param url de la página
	 * @return codigo html
	 */
       ArrayList<sismo> sismos = new ArrayList<>();
    
        public class sismo 
        {
            private String fechaLocal;
            private String fechaUTC;
            private float latitud;
            private float longitud;
            private float profundidad;
            private String magnitud;
            private String agencia;
            private String referencia;

            private sismo(String fechaLocal, String fechaUTC, float latitud, float longitud, float profundidad, String magnitud, String agencia, String referencia) {
                this.fechaLocal=fechaLocal;
                this.fechaUTC=fechaUTC;
                this.latitud=latitud;
                this.longitud=longitud;
                this.profundidad=profundidad;
                this.magnitud=magnitud;
                this.agencia=agencia;
                this.referencia=referencia;
            }

            public String getFechaLocal(){
                return fechaLocal;
            }

            public void setFechaLocal(String fechaLocal) {
                this.fechaLocal = fechaLocal;
            }

            public String getFechaUTC() {
                return fechaUTC;
            }

            public void setFechaUTC(String fechaUTC) {
                this.fechaUTC = fechaUTC;
            }

            public float getLatitud() {
                return latitud;
            }

            public void setLatitud(float latitud) {
                this.latitud = latitud;
            }

            public float getLongitud() {
                return longitud;

            }
            public void setLongitud(float longitud) {
                this.longitud = longitud;
            }

            public float getProfundidad() {
                return profundidad;
            }

            public void setProfundidad(float profundidad) {
                this.profundidad = profundidad;
            }

            public String getMagnitud() {
                return magnitud;
            }

            public void setMagnitud(String magnitud) {
                this.magnitud = magnitud;
            }

            public String getAgencia() {
                return agencia;
            }

            public void setAgencia(String agencia) {
                this.agencia = agencia;
            }

            public String getReferencia() {
                return referencia;
            }

            public void setReferencia(String referencia) {
                this.referencia = referencia;
            }


        }
   
        /**
         * 
         * @param url de la página
         * @return conection html
         */
        public static Document getHTML(String url) {
		Document html = null;
		try {
			html = Jsoup.connect(url).get();
		}catch(Exception e) {
			System.out.println("error");
		}
		return html;
	}
	public void scraping() {

                Document htmlSismo = ScrapingWeb.getHTML("http://www.sismologia.cl/links/ultimos_sismos.html");
                
                Element tabla = htmlSismo.select("table").get(0); //Obteniendo primera tabla que es la de sismos
                Elements filas = tabla.select("tr");
                
                for (int i=1;i<filas.size();i++)
                {
                    Element fila = filas.get(i);
                    Elements columnas = fila.select("td");
                    
                    sismo sm = new sismo(
                            columnas.get(0).text(),
                            columnas.get(1).text(),
                            Float.parseFloat(columnas.get(2).text()),
                            Float.parseFloat(columnas.get(3).text()),
                            Float.parseFloat(columnas.get(4).text()),
                            columnas.get(5).text(),
                            columnas.get(6).text(),
                            columnas.get(7).text()
                    );
                    
                   sismos.add(sm);
                }
                
                //PRUEBA
                for(sismo s : sismos)
                {
                    System.out.println(s.getFechaLocal()+";"+s.getFechaUTC()+";" 
                            + s.getLatitud()+";" +s.getLongitud()+";"+s.getProfundidad()
                            +";"+s.getMagnitud()+";"+s.getAgencia()+";"+s.getReferencia());
                }
	
    }

	public static void main(String[] args) {
		new ScrapingWeb().scraping();
		
	}
}