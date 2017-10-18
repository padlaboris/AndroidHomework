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
import com.sun.org.apache.bcel.internal.generic.LSHL;

import java.util.ArrayList;
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
        name = "simpleUserApi",
        version = "v1",
        resource = "simpleUser",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.padlabear.example.com",
                ownerName = "backend.myapplication.padlabear.example.com",
                packagePath = ""
        )
)
public class SimpleUserEndpoint {

    private static final Logger logger = Logger.getLogger(SimpleUserEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(SimpleUser.class);
    }

    /**
     * Returns the {@link SimpleUser} with the corresponding ID.
     *
     * @param id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code SimpleUser} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "simpleUser/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public SimpleUser get(@Named("id") String id) throws NotFoundException {
        logger.info("Getting SimpleUser with ID: " + id);
        SimpleUser simpleUser = ofy().load().type(SimpleUser.class).id(id).now();
        if (simpleUser == null) {
            throw new NotFoundException("Could not find SimpleUser with ID: " + id);
        }
        return simpleUser;
    }

    /**
     * Inserts a new {@code SimpleUser}.
     */
    @ApiMethod(
            name = "insert")
    public void insert(@Named("id") String id, @Named("FirstName") String FirstName, @Named("LastName") String LastName, @Named("Age") String Age, @Named("Location") String Location) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that simpleUser.id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        SimpleUser simpleUser = new SimpleUser(id, FirstName, LastName, Age, Location);
        ofy().save().entity(simpleUser).now();
        logger.info("Created SimpleUser");

    }

    /**
     * Updates an existing {@code SimpleUser}.
     *
     * @param id         the ID of the entity to be updated
     * @param simpleUser the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code SimpleUser}
     */
    @ApiMethod(
            name = "update",
            path = "simpleUser/{id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public SimpleUser update(@Named("id") String id, SimpleUser simpleUser) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(id);
        ofy().save().entity(simpleUser).now();
        logger.info("Updated SimpleUser: " + simpleUser);
        return ofy().load().entity(simpleUser).now();
    }

    /**
     * Deletes the specified {@code SimpleUser}.
     *
     * @param id the ID of the entity to delete
     * @throws NotFoundException if the {@code id} does not correspond to an existing
     *                           {@code SimpleUser}
     */
    @ApiMethod(
            name = "remove",
            path = "simpleUser/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("id") String id) throws NotFoundException {
        checkExists(id);
        ofy().delete().type(SimpleUser.class).id(id).now();
        logger.info("Deleted SimpleUser with ID: " + id);
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
            path = "simpleUser",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<SimpleUser> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<SimpleUser> query = ofy().load().type(SimpleUser.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<SimpleUser> queryIterator = query.iterator();
        List<SimpleUser> simpleUserList = new ArrayList<SimpleUser>(limit);
        while (queryIterator.hasNext()) {
            simpleUserList.add(queryIterator.next());
        }
        return CollectionResponse.<SimpleUser>builder().setItems(simpleUserList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String id) throws NotFoundException {
        try {
            ofy().load().type(SimpleUser.class).id(id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find SimpleUser with ID: " + id);
        }
    }
}