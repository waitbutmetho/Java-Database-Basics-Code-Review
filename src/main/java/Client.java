import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private int stylistId;
  private String name;

  public Client(String name, int stylistId) {
    this.name = name;
    this.stylistId = stylistId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylistId;
  }

  @Override
public boolean equals(Object otherClient){
  if (!(otherClient instanceof Client)) {
    return false;
  } else {
    Client newClient = (Client) otherClient;
    System.out.println(this.getStylistId());
    System.out.println(newClient.getStylistId());
    return this.getName().equals(newClient.getName()) &&
           this.getId() == newClient.getId() &&
           this.getStylistId() == newClient.getStylistId();
  }
}

  public static List<Client> all() {
    String sql = "SELECT id, name, stylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, stylistId) VALUES (:name, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("stylistId", stylistId)
        .executeUpdate()
        .getKey();
    }
  }
}
