package app.persistence.dao.impl;
import app.persistence.dto.ChampionDTO;

import app.entities.Champion;
import app.persistence.dao.IDAO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ChampionDAO implements IDAO<ChampionDTO> {

    private EntityManager em;

    public ChampionDAO(EntityManager em) {
        this.em = em;
    }


    @Override
    public ChampionDTO save(ChampionDTO dto){
        Champion champion = new Champion();
        champion.setName(dto.getName());
        champion.setDescription(dto.getDescription());
        em.getTransaction().begin();
        em.persist(champion);
        em.getTransaction().commit();
        return new ChampionDTO();
    }

    @Override
    public Optional<ChampionDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<ChampionDTO> findAll() {
        return List.of();
    }

    @Override
    public ChampionDTO update(ChampionDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }


    /*
    @Override
    public Optional<MovieDTO> findById(long id) {
        return Optional.ofNullable(em.find(Movie.class, id))
                .map(movie -> {
                    MovieDTO dto = new MovieDTO();
                    dto.setTitle(movie.getTitle());
                    dto.setVote_average(movie.getVote_average());
                    dto.setId(id);
                    dto.setOverview(movie.getOverview());
                    dto.setRelease_date(movie.getRelease_date());
                    return dto;
                });
    }

    @Override
    public List<MovieDTO> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class)
                .getResultList()
                .stream()
                .map(movie -> {
                    MovieDTO dto = new MovieDTO();
                    dto.setTitle(movie.getTitle());
                    dto.setVote_average(movie.getVote_average());
                    dto.setOverview(movie.getOverview());
                    dto.setRelease_date(movie.getRelease_date());
                    dto.setId(movie.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO update(MovieDTO dto) {
        Movie movie = em.find(Movie.class, dto.getId());
        if (movie != null) {
            movie.setTitle(dto.getTitle());
            em.getTransaction().begin();
            em.merge(movie);
            em.getTransaction().commit();
            return dto;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        Optional.ofNullable(em.find(Movie.class, id)).ifPresent(em::remove);
        em.getTransaction().commit();
    }
    */

}