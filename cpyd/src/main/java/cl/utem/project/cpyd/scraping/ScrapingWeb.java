package cl.utem.project.cpyd.scraping;

import cl.utem.project.cpyd.db.model.Sismo;
import cl.utem.project.cpyd.db.repository.SismoRepository;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapingWeb {

    @Autowired
    private transient SismoRepository sismoRepository;

    /**
     * Método que obtiene el codigo HTML de la página web
     *
     * @param url de la página
     * @return codigo html
     */
    ArrayList<Sismo> sismos = new ArrayList<>();

    /**
     *
     * @param url de la página
     * @return conection html
     */
    public static Document getHTML(String url) {
        Document html = null;
        try {
            html = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println("error");
        }
        return html;
    }

    public void scraping() {
        /*
                Elements articulos = ScrapingWeb.getHTML("http://www.sismologia.cl/links/ultimos_sismos.html").select("a");
		
                for (Element sismo : articulos) {
			try {
				String urlSismo = sismo.select("a").attr("abs:href");
				Document htmlSismo = ScrapingWeb.getHTML(urlSismo);
				
				String Hora = htmlSismo.select("tr").text();
				System.out.println("Sismo:"+Hora);
				System.out.println("-------------");
			}catch(Exception e){
				System.out.println("error");
			}
		}
                /*
            
         */
        Document htmlSismo = ScrapingWeb.getHTML("http://www.sismologia.cl/links/ultimos_sismos.html");

        Element tabla = htmlSismo.select("table").get(0); //Obteniendo primera tabla que es la de sismos
        Elements filas = tabla.select("tr");

        for (int i = 1; i < filas.size(); i++) {
            Element fila = filas.get(i);
            Elements columnas = fila.select("td");

            Sismo sm = new Sismo(
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
        for (Sismo s : sismos) {
            System.out.println(s.getFechaLocal() + ";" + s.getFechaUTC() + ";" + s.getLatitud() + ";" + s.getLongitud() + ";" + s.getProfundidad() + ";" + s.getMagnitud() + ";" + s.getAgencia() + ";" + s.getReferencia());
            sismoRepository.save(s);
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new ScrapingWeb().scraping();

    }
}
