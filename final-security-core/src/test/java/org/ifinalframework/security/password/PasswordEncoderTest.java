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

package org.ifinalframework.security.password;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * PasswordEncoderTest.
 *
 * @author ilikly
 * @version 1.5.1
 * @since 1.5.1
 */
public class PasswordEncoderTest {

    @Test
    void test(){
        System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("feiyin"));
        System.out.println(NoOpPasswordEncoder.getInstance().encode("root"));
    }
}
