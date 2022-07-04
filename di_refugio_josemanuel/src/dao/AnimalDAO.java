package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Animal;
import model.Vaccine;
import util.ConnectionManager;

/**
 * DAO class for Animals
 *
 * @author josem
 */
public class AnimalDAO {

    /**
     * Method to return a collection of all animals on a ollection
     */
    public Collection<Animal> getAll() {
        List animals = new ArrayList();
        ResultSet rs = null;

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentence = connection.createStatement()) {
            rs = sentence.executeQuery("select * from animal");
            this.addAnimal(rs, animals);
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return animals;
    }

    /**
     * Method to get all the races name
     */
    public ObservableList<String> getRacesName() {
        ObservableList<String> races = FXCollections.observableArrayList();

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentence = connection.createStatement()) {
            ResultSet rs = sentence.executeQuery("select nombre from raza");

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                races.add(nombre);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        }

        return races;
    }

    /**
     * Method to get the race name of a given animal
     */
    public String getRaceName(Animal nAnimal) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String raceName = "";
        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select nombre, id from raza");
            rs = ps.executeQuery();
            while (rs.next()) {
                if ((rs.getInt(2) + "").equals(nAnimal.getIdRaza())) {
                    raceName = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }

        return raceName;

    }

    /**
     * Method to register a pet and add his essential vaccines
     */
    public void registerPet(Animal nAnimal) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int raceID = 0;
        int petID = 0;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select nombre, id from raza");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(nAnimal.getIdRaza())) {
                    raceID = rs.getInt(2);
                }
            }

            ps = connection.prepareStatement("INSERT into animal (nombre,sexo,fecha_nac,color_predominante,id_raza_predominante,peso,fecha_arribo,caracteristicas) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nAnimal.getNombre());
            ps.setString(2, nAnimal.getSexo().toString());
            Date date = Date.valueOf(nAnimal.getFechaNacimiento());
            ps.setDate(3, date);
            ps.setString(4, nAnimal.getColor());
            ps.setInt(5, raceID);
            ps.setDouble(6, nAnimal.getPeso());
            date = Date.valueOf(LocalDate.now());
            ps.setDate(7, date);
            ps.setString(8, nAnimal.getCaracteristicas());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                petID = rs.getInt(1);
            }

            this.essencialVaccinate(petID, raceID);

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
    }

    /**
     * Method to modify a pet
     */
    public void modifyPet(Animal nAnimal) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int razaID = 0;
        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select nombre, id from raza");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(nAnimal.getIdRaza())) {
                    razaID = rs.getInt(2);
                }
            }

            ps = connection.prepareStatement("UPDATE animal set nombre = ?,sexo = ?,fecha_nac = ?,color_predominante = ?,id_raza_predominante = ?,peso = ?,caracteristicas = ? where id = ?");

            ps.setString(1, nAnimal.getNombre());
            ps.setString(2, nAnimal.getSexo().toString());
            Date date = Date.valueOf(nAnimal.getFechaNacimiento());
            ps.setDate(3, date);
            ps.setString(4, nAnimal.getColor());
            ps.setInt(5, razaID);
            ps.setDouble(6, nAnimal.getPeso());
            ps.setString(7, nAnimal.getCaracteristicas());
            ps.setInt(8, nAnimal.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
    }

    /**
     * Method to adopt a pet
     */
    public void adoptPet(String id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("UPDATE animal set fecha_adopcion = ? where id = ?");
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, Integer.parseInt(id));

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
    }

    /**
     * Method to return a all adopted pets
     */
    public Collection<Animal> getAdoptedPets() {
        List animals = new ArrayList();
        ResultSet rs = null;

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {
            rs = sentencia.executeQuery("select * from animal where fecha_adopcion IS NOT NULL");
            this.addAnimal(rs, animals);
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return animals;
    }

    /**
     * Method to obtain the information of the oldest pet to be adopted
     */
    public Collection<String> getPetToAdopt(String choice) {
        List<String> animalInformation = new ArrayList();
        ResultSet rs = null;
        PreparedStatement ps = null;
        int specieID = 0;
        int raceID = 0;
        int animalID = 0;
        String name = "";
        String specie = "";

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            if (choice.equals("Animal")) {
                ps = connection.prepareStatement("select id, nombre, id_raza_predominante from animal where fecha_adopcion IS NULL order by fecha_arribo LIMIT 1");
                rs = ps.executeQuery();
                if (rs.next()) {
                    animalID = rs.getInt(1);
                    name = rs.getString(2);
                    raceID = rs.getInt(3);
                    animalInformation.add(String.valueOf(animalID));
                    animalInformation.add(name);
                }

                ps = connection.prepareStatement("select e.tipo from raza r, especie e where r.idespecie = e.id and r.id = ?");
                ps.setInt(1, raceID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    specie = rs.getString(1);
                    animalInformation.add(specie);
                }

            } else {
                ps = connection.prepareStatement("select id from especie where tipo = ?");
                ps.setString(1, choice);
                rs = ps.executeQuery();
                if (rs.next()) {
                    specieID = rs.getInt(1);
                }

                ps = connection.prepareStatement("select a.id, a.nombre from animal a, raza r where a.fecha_adopcion IS NULL and r.id = a.id_raza_predominante and r.idespecie = ? order by a.fecha_arribo LIMIT 1");
                ps.setInt(1, specieID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    animalID = rs.getInt(1);
                    name = rs.getString(2);
                    animalInformation.add(String.valueOf(animalID));
                    animalInformation.add(name);
                    animalInformation.add(choice);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return animalInformation;
    }

    /**
     * Method to vaccinate a pet
     */
    public void vaccinatePet(Integer petID, String vacName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int raceID = 0;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {

            ps = connection.prepareStatement("select id_raza_predominante from animal where id = ?");
            ps.setInt(1, petID);
            rs = ps.executeQuery();
            if (rs.next()) {
                raceID = rs.getInt(1);
            }
            this.vaccinate(petID, raceID, vacName);

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }

    }

    /**
     * Method to get all species name
     */
    public ObservableList<String> getAllSpecie() {
        ObservableList<String> species = FXCollections.observableArrayList();

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentence = connection.createStatement()) {
            ResultSet rs = sentence.executeQuery("select tipo from especie");

            while (rs.next()) {
                String specie = rs.getString("tipo");
                species.add(specie);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        }

        return species;
    }

    /**
     * Method to get all species that can be adopted. This method has an
     * "Animal" too
     */
    public ObservableList<String> getSpecieForAdopt() {
        ObservableList<String> species = FXCollections.observableArrayList();

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentence = connection.createStatement()) {
            ResultSet rs = sentence.executeQuery("select tipo from especie");
            species.add("Animal");
            while (rs.next()) {
                String specie = rs.getString(1);
                species.add(specie);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        }

        return species;
    }

    /**
     * Method to return a collection of animals that has been filtered by three
     * parameters
     */
    public Collection<Animal> getSearch(String specie, String race, String color) {
        List<Animal> animals = new ArrayList();
        List<Integer> idRaces = new ArrayList();
        ResultSet rs = null;
        String specieParameter;
        String colorParameter;
        int razaID = 0;

        if (!specie.equals("")) {
            specieParameter = "and e.tipo = '" + specie + "'";
        } else {
            specieParameter = "";
        }
        if (!color.equals("")) {
            colorParameter = " and color_predominante = '" + color + "'";
        } else {
            colorParameter = "";
        }

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement statement = connection.createStatement()) {
            if (!race.equals("")) {
                rs = statement.executeQuery("select id from raza where nombre = '" + race + "'");
                while (rs.next()) {
                    razaID = rs.getInt(1);
                }

                rs = statement.executeQuery("select * from animal where id_raza_predominante = " + razaID + colorParameter);
                this.addAnimal(rs, animals);
            } else {
                rs = statement.executeQuery("select r.id from especie e, raza r where e.id = r.idespecie " + specieParameter);
                while (rs.next()) {
                    idRaces.add(rs.getInt(1));
                }

                for (int i = 0; i < idRaces.size(); i++) {
                    rs = statement.executeQuery("select * from animal where id_raza_predominante = " + idRaces.get(i) + colorParameter);
                    this.addAnimal(rs, animals);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return animals;

    }

    /**
     * Method to return all vaccines according to a specific specie
     */
    public ObservableList<String> getAllVaccinate(Integer petID) {
        ObservableList<String> vaccinates = FXCollections.observableArrayList();
        ResultSet rs = null;
        PreparedStatement ps = null;
        int specieID = 0;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select r.idespecie from animal a, raza r where a.id = ? and a.id_raza_predominante = r.id");
            ps.setInt(1, petID);
            rs = ps.executeQuery();
            rs.next();
            specieID = rs.getInt(1);

            ps = connection.prepareStatement("select v.nombre from vacuna v, vacuna_especie ve where v.id = ve.id_vacuna and id_especie = ?");
            ps.setInt(1, specieID);
            rs = ps.executeQuery();

            while (rs.next()) {
                String vaccinate = rs.getString(1);
                vaccinates.add(vaccinate);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return vaccinates;
    }

    /**
     * Method to add an animal on a list of animals
     */
    private void addAnimal(ResultSet rs, List<Animal> animals) {
        LocalDate adoptLocalDate = null;
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                char sexoChar = sexo.charAt(0);
                Date fechaNacimiento = rs.getDate("fecha_nac");
                LocalDate bornLocalDate = fechaNacimiento.toLocalDate();
                String color = rs.getString("color_predominante");
                int idRaza = rs.getInt("id_raza_predominante");
                double peso = rs.getDouble("peso");
                Date fechaArribo = rs.getDate("fecha_arribo");
                LocalDate arriveLocalDate = fechaArribo.toLocalDate();
                Date fechaAdopcion = rs.getDate("fecha_adopcion");
                if (fechaAdopcion != null) {
                    adoptLocalDate = fechaAdopcion.toLocalDate();
                } else {
                    adoptLocalDate = null;
                }
                String caracteristicas = rs.getString("caracteristicas");
                Animal animal = new Animal(id, nombre, sexoChar, bornLocalDate, color, idRaza + "", peso, arriveLocalDate, adoptLocalDate, caracteristicas);
                animals.add(animal);
            }
        } catch (SQLException ex) {
            System.err.println("Error SQL" + ex.getMessage());
        }
    }

    /**
     * Method to insert the essencial vaccines to a pet
     */
    public void essencialVaccinate(Integer petID, Integer raceID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int specieID = 0;
        List<Integer> essencialVac = new ArrayList<>();

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select idespecie from raza where id = ?");
            ps.setInt(1, raceID);
            rs = ps.executeQuery();
            rs.next();
            specieID = rs.getInt(1);

            ps = connection.prepareStatement("select v.id from vacuna v, vacuna_especie ve where v.id = ve.id_vacuna and ve.id_especie = ? and v.escencial = 1");
            ps.setInt(1, specieID);
            rs = ps.executeQuery();
            while (rs.next()) {
                essencialVac.add(rs.getInt(1));
            }

            for (int i = 0; i < essencialVac.size(); i++) {
                ps = connection.prepareStatement("INSERT INTO dosis (id_animal, id_vacuna, fecha) VALUES (?,?,?)");
                ps.setInt(1, petID);
                ps.setInt(2, essencialVac.get(i));
                ps.setDate(3, Date.valueOf(LocalDate.now()));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
    }

    /**
     * Method to vaccinate a pet with a specific vaccine
     */
    public void vaccinate(Integer petID, Integer raceID, String vacName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int specieID = 0;
        int vaccinateID = 0;

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select idespecie from raza where id = ?");
            ps.setInt(1, raceID);
            rs = ps.executeQuery();
            rs.next();
            specieID = rs.getInt(1);

            ps = connection.prepareStatement("select v.id from vacuna v, vacuna_especie ve where v.id = ve.id_vacuna and ve.id_especie = ? and v.nombre = ?");
            ps.setInt(1, specieID);
            ps.setString(2, vacName);
            rs = ps.executeQuery();
            rs.next();
            vaccinateID = rs.getInt(1);

            ps = connection.prepareStatement("INSERT INTO dosis (id_animal, id_vacuna, fecha) VALUES (?,?,?)");
            ps.setInt(1, petID);
            ps.setInt(2, vaccinateID);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
    }

    /**
     * Method to get all vaccine of a specific pet 
     */
    public Collection<Vaccine> showVaccinateInfo(Integer petID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Vaccine> vaccineNames = new ArrayList();

        try ( Connection connection = ConnectionManager.getInstance().getConnection()) {
            ps = connection.prepareStatement("select v.nombre from vacuna v, dosis d where v.id = d.id_vacuna and d.id_animal = ?");
            ps.setInt(1, petID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vaccine nvaccine = new Vaccine(rs.getString(1));
                vaccineNames.add(nvaccine);
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return vaccineNames;
    }

    /**
     * Method to return all the pets on a observablelist
     */
    public ObservableList<Animal> getAnimalsName() {
        ObservableList<Animal> animals = FXCollections.observableArrayList();
        ResultSet rs = null;

        try ( Connection connection = ConnectionManager.getInstance().getConnection();  Statement sentencia = connection.createStatement()) {
            rs = sentencia.executeQuery("select * from animal");
            this.addAnimal(rs, animals);
        } catch (SQLException e) {
            System.err.println("Error SQL" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error SQL: " + ex.getMessage());
            }
        }
        return animals;
    }
}
