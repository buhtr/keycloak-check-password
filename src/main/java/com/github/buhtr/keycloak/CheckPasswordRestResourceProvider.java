package com.github.buhtr.keycloak;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserProvider;
import org.keycloak.services.resource.RealmResourceProvider;

/**
 * @author Igor Slusarenko
 */
@Slf4j
@RequiredArgsConstructor
public class CheckPasswordRestResourceProvider implements RealmResourceProvider {

  private final KeycloakSession keycloakSession;

  @Override
  public Object getResource() {
    return this;
  }

  @Override
  public void close() {
  }

  @GET
  @Path("/{login}")
  @Produces(MediaType.APPLICATION_JSON)
  public Boolean check(@PathParam("login") String login, @QueryParam("password") String password) {
    var realm = keycloakSession.getContext().getRealm();
    var userProvider = keycloakSession.getProvider(UserProvider.class);
    var user = userProvider.getUserByUsername(realm, login);

    return user.credentialManager().isValid(UserCredentialModel.password(password));
  }

}
