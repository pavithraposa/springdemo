package com.example.springdemo01;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "api/v1/movies")
public class MovieController {
    // Att använda en ArrayList kanske inte är det smidigaste att använda här. Det gör att våra metoder getMovieByID()
    // och updateMovieByID() är lite onödigt komplexa.
    // Uppgift: Hur kan vi lösa det med en HashMap? Tips: Vi kan också ha en separat "helper"-metod som retunerar en
    // boolean som säger om en film finns eller inte. Då slipper vi konstigheterna med att retunera en Movie när ingen
    // matchande film finns.
    ArrayList<Movie> myMovies;

    // En räknare som alltid räknas uppåt när en ny film läggs till
    int latestMovieID;

    public MovieController() {
        myMovies = new ArrayList<Movie>();
        latestMovieID = 0;
    }

    // CRUD - Create
    /* Skapa en ny film genom att skicka det här i JSON-format i bodyn av vår HTTP-request i Insomnia.
        {
          "title": "Men In Black 2",
          "rating": 5
        }
    */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Movie createMovie(@RequestBody Movie movie) {
        // Öka ID:t med 1 innan vi lägger in filmen
        // I verkligheten senare så görs detta automatiskt av en databas
        latestMovieID++;
        movie.setId(latestMovieID);
        myMovies.add(movie);
        return movie;
    }

    // CRUD - Read
    // Lista alla filmer
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ArrayList<Movie> listMovies() {
        return myMovies;
    }

    // CRUD - Read
    // Lista en specifik film
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Movie listMovies(@PathVariable("id") int id) {
        System.out.println("Getting movie with id " + id);
        return getMovieByID(id);
    }

    // CRUD - Update
    // Gör en uppdatering av en sak om en film
    // För ändringar som dessa kan vi använda antingen POST eller PATCH. PATCH är egentligen "mer rätt".
    /* Uppdatera film med id 2 genom att skicka detta i JSON-format i bodyn av vår HTTP-request i Insomnia.
       Adressen det skickas till då är http://127.0.0.1:8080/api/v1/movies/update/2.
        {
          "title": "I, Robot"
        }
    */
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public Movie listMovies(@PathVariable("id") int id, @RequestBody Movie movieChanges) {
        System.out.println("Getting movie with id " + id);
        Movie movieToUpdate = getMovieByID(id);

        if (movieChanges.getTitle() != null) {
            movieToUpdate.setTitle(movieChanges.getTitle());
        }
        if (movieChanges.getRating() != 0) {
            movieToUpdate.setRating(movieChanges.getRating());
        }

        updateMovieByID(id, movieToUpdate);

        return movieToUpdate;
    }

    // CRUD - Delete
    // Uppgift: Hur tar vi bort en film..?

    // Get a movie with a specific ID
    // Uppgift: Förenkla den här koden genom att exempelvis använda en HashMap för myMovies
    private Movie getMovieByID(int id) {
        for (int i = 0; i < myMovies.size(); i++) {
            Movie currentMovie = myMovies.get(i);
            if (currentMovie.getId() == id) {
                return myMovies.get(i);
            }
        }

        return new Movie();
    }

    // Get a movie with a specific ID
    // Uppgift: Förenkla den här koden genom att använda exempelvis en HashMap för myMovies
    private Movie updateMovieByID(int id, Movie updatedMovie) {
        for (int i = 0; i < myMovies.size(); i++) {
            Movie currentMovie = myMovies.get(i);
            if (currentMovie.getId() == id) {
                myMovies.set(i, updatedMovie);
                return myMovies.get(i);
            }
        }

        return new Movie();
    }
}
