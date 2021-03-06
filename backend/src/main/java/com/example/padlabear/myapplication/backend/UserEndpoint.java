package com.example.padlabear.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "userApi",
        version = "v1",
        resource = "user",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.padlabear.example.com",
                ownerName = "backend.myapplication.padlabear.example.com",
                packagePath = ""
        )
)
public class UserEndpoint {

    private static final Logger logger = Logger.getLogger(UserEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    static {
        ObjectifyService.register(User.class);
    }

    /**
     * Returns the {@link User} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code User} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "user/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public User get(@Named("id") final String id) throws NotFoundException {
        logger.info("Getting User with ID: " + id);
        User user = ofy().load().type(User.class).id(id).now();
        if (user == null) {
            throw new NotFoundException("Could not find User with ID: " + id);
        }
        return user;
    }

    /**
     * Inserts a new {@code User}.
     */
    @ApiMethod(name = "insert")
    public void insert(@Named("id") final String id,
                       @Named("firstName") final String firstName,
                       @Named("lastName") final String lastName,
                       @Named("age") final String age,
                       @Named("location") final String location) {
        final String registered = dateFormat.format(new Date());
        final User user = new User(id, firstName, lastName, age, location, registered);
        ofy().save().entity(user).now();
        logger.info("Created User.");
    }

    /**
     * Updates an existing {@code User}.
     *
     * @param id   the ID of the entity to be updated
     * @param user the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code User}
     */
    @ApiMethod(
            name = "update",
            path = "user/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public User update(@Named("id") final String id, final User user) throws NotFoundException {
        checkExists(id);
        ofy().save().entity(user).now();
        logger.info("Updated User: " + user);
        return ofy().load().entity(user).now();
    }

    /**
     * Deletes the specified {@code User}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code User}
     */
    @ApiMethod(
            name = "remove",
            path = "user/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") final String id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(User.class).id(id).now();
        logger.info("Deleted User with ID: " + id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "user",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<User> list(@Nullable @Named("cursor") final String cursor,
                                         @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<User> query = ofy().load().type(User.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        final QueryResultIterator<User> queryIterator = query.iterator();
        final List<User> userList = new ArrayList<>(limit);
        while (queryIterator.hasNext()) {
            userList.add(queryIterator.next());
        }
        return CollectionResponse.<User>builder().setItems(userList)
                .setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(final String id) throws NotFoundException {
        try {
            ofy().load().type(User.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find User with ID: " + id);
        }
    }
}