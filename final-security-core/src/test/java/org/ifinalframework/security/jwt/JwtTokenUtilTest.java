/*
 * Copyright 2020-2023 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ifinalframework.security.jwt;

import java.util.Arrays;

import org.ifinalframework.json.Json;
import org.ifinalframework.security.core.SimpleTokenUser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JwtTokenUtilTest.
 *
 * @author iimik
 * @version 1.3.3
 * @since 1.3.3
 */
class JwtTokenUtilTest {

    @Test
    void createToken() {
        Assertions.assertDoesNotThrow(() -> {
            JwtTokenUtil.createToken("root", Arrays.asList("root"));
        });
    }

    @Test
    void token(){
        SimpleTokenUser user = new SimpleTokenUser();
        user.setUsername("username");
        user.setRoles(Arrays.asList("ROLE_ROOT"));
        String json = Json.toJson(user);
        String token = JwtTokenUtil.createToken(json);
        String payload = JwtTokenUtil.getPayload(token);
        Assertions.assertEquals(json,payload);
    }
}