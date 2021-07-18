package cl.utem.project.cpyd.api.Scraping;

import cl.utem.project.cpyd.api.rest.vo.SismoVo;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapingWeb{
	
       ArrayList<SismoVo> sismos = new ArrayList<>();
   
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
                    
                    //SismoVo se crea con los datos extraídos de la pagina web, agregandose al ArrayList sismos
                    SismoVo sm = new SismoVo(
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
           sismos.forEach(s -> {
               System.out.println(s.getFechaLocal()+";"+s.getFechaUTC()+";"
                       + s.getLatitud()+";" +s.getLongitud()+";"+s.getProfundidad()
                       +";"+s.getMagnitud()+";"+s.getAgencia()+";"+s.getReferencia());
           });
	
    }
}