package pck.scraping;

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
	}
	
	
	public static void main(String[] args) {
		new ScrapingWeb().scraping();
		
	}
}