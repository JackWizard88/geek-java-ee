package ru.geekbrains.krylov.rest;

import ru.geekbrains.krylov.dto.CategoryDTO;
import ru.geekbrains.krylov.dto.ProductDTO;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/categories")
public interface CategoryServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDTO> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDTO findById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryDTO category);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryDTO category);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
