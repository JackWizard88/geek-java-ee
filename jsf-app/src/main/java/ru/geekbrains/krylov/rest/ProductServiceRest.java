package ru.geekbrains.krylov.rest;

import ru.geekbrains.krylov.dto.ProductDTO;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/products")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDTO> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDTO findById(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ProductDTO findByTitle(@QueryParam("title") String title);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDTO product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDTO product);

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}
