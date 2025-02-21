/*
 * Copyright 2016, Yahoo Inc.
 * Licensed under the Apache License, Version 2.0
 * See LICENSE file in project root for terms.
 */

package com.yahoo.elide.core;

import static com.yahoo.elide.core.dictionary.EntityDictionary.NO_VERSION;

import com.yahoo.elide.ElideSettingsBuilder;
import com.yahoo.elide.core.datastore.DataStoreTransaction;
import com.yahoo.elide.core.dictionary.EntityDictionary;
import com.yahoo.elide.core.security.User;
import com.yahoo.elide.jsonapi.links.DefaultJsonApiLinks;
import com.yahoo.elide.jsonapi.models.JsonApiDocument;

import jakarta.ws.rs.core.MultivaluedMap;

import java.util.UUID;

/**
 * Utility subclass that helps construct RequestScope objects for testing.
 */
public class TestRequestScope extends RequestScope {

    private MultivaluedMap queryParamOverrides = null;

    public TestRequestScope(String baseURL,
                            DataStoreTransaction transaction,
                            User user,
                            EntityDictionary dictionary) {
        super(baseURL, null, NO_VERSION, new JsonApiDocument(), transaction, user, null, null, UUID.randomUUID(),
                new ElideSettingsBuilder(null)
                        .withEntityDictionary(dictionary)
                        .withJsonApiLinks(new DefaultJsonApiLinks())
                        .withJsonApiPath("/json")
                        .build());
    }

    public TestRequestScope(DataStoreTransaction transaction,
                        User user,
                        EntityDictionary dictionary) {
        super(null, null, NO_VERSION, new JsonApiDocument(), transaction, user, null, null, UUID.randomUUID(),
                new ElideSettingsBuilder(null)
                .withEntityDictionary(dictionary)
                .build());
    }

    public TestRequestScope(EntityDictionary dictionary,
                            String path,
                            MultivaluedMap<String, String> queryParams) {
        super(null, path, NO_VERSION, new JsonApiDocument(), null, null, queryParams, null, UUID.randomUUID(),
                new ElideSettingsBuilder(null)
                        .withEntityDictionary(dictionary)
                        .build());
    }

    public void setQueryParams(MultivaluedMap<String, String> queryParams) {
        this.queryParamOverrides = queryParams;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParams() {
        if (queryParamOverrides != null) {
            return queryParamOverrides;
        }
        return super.getQueryParams();
    }
}
