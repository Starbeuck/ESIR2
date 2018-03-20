package net.serialisation;

import net.serialisation.data.*;
import javax.xml.bind.*;
import java.io.File;

public class App {
  static ObjectFactory objfact = new ObjectFactory();
  static CatalogData cat = objfact.createCatalogData();

  public App() {}

    public static void main(String[] args) {

      CatalogData.Book b1 = objfact.createCatalogDataBook();
      CatalogData.Book b2 = objfact.createCatalogDataBook();

      b1.setAuthor("David Bromberg");
      b1.setTitle("Programmation Android");
      b1.setGenre("Programmation");
      b1.setPrice(0);
      b1.setPublishDate("2012-01-30");
      b1.setDescription("Apprendre la programmation sur Android");

      b2.setAuthor("David Bromberg");
      b2.setTitle("Programmation Iphone");
      b2.setGenre("Programmation");
      b2.setPrice(0);
      b2.setPublishDate("2012-01-30");
      b2.setDescription("Apprendre la programmation sur Iphone");

      cat.getBook().add(b1);
      cat.getBook().add(b2);

      try {
        File f = new File("books.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(CatalogData.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(cat, f);
        jaxbMarshaller.marshal(cat, System.out);
      }
      catch(Exception ex) {

      }
    }
}