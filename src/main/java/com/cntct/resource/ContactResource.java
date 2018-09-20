package com.cntct.resource;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.cntct.request.CreateContactRequest;
import com.cntct.response.ContactResponse;
import com.cntct.service.ContactService;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("/Contact Resource")
@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

  @Inject
  private ContactService contactService;
  
  @GET
  @UnitOfWork
  @ApiOperation("List Contacts")
  public List<ContactResponse> list(@QueryParam("skip") @DefaultValue("0") int skip,
                                @QueryParam("count") @DefaultValue("10") int count) {
    return contactService.list(skip, count);
  }

  @GET
  @UnitOfWork
  @ApiOperation("Get Contact")
  @Path("/{id}")
  public ContactResponse get(@PathParam("id") Long id) {
    return contactService.get(id);
  }

  @POST
  @UnitOfWork
  @ApiOperation("Create Contact")
  public ContactResponse create(@Valid CreateContactRequest createTemplateRequest) {
    return contactService.create(createTemplateRequest);
  }
  
  @PATCH
  @UnitOfWork
  @ApiOperation("Update Name")
  @Path("/{id}/name")
  public ContactResponse updateName(@PathParam("id") Long id, @NotNull String name) {
    return contactService.updateName(id, name);
  }
  
  @PATCH
  @UnitOfWork
  @ApiOperation("Update Phone")
  @Path("/{id}/phone")
  public ContactResponse updatePhone(@PathParam("id") Long id, @NotNull String phone) {
    return contactService.updatePhone(id, phone);
  }
  
  @DELETE
  @UnitOfWork
  @ApiOperation("Delete Contact")
  @Path("/{id}")
  public ContactResponse delete(@PathParam("id") Long id) {
    return contactService.delete(id);
  }
}
