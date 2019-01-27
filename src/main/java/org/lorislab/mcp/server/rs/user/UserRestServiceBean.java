/*
 * Copyright 2019 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lorislab.mcp.server.rs.user;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserRestServiceBean implements UserRestService {

    private static Map<Long, User> USERS = new ConcurrentHashMap<>();

    static {
        for (long i=0; i<10; i++) {
            User user = new User();
            user.setId(i);
            user.setName(i + UUID.randomUUID().toString());
            USERS.put(i, user);
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(USERS.values());
    }

    @Override
    public User getById(Long id) {
        return USERS.get(id);
    }

    @Override
    public User saveUser(Long id, User user) {
        USERS.put(id, user);
        return user;
    }

    @Override
    public Map<Long, User> getMapUsers() {
        return USERS;
    }

}
