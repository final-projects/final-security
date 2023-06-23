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

package org.ifinalframework.security.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * CurrentUser.
 *
 * @author ilikly
 * @version 1.4.0
 * @since 1.4.0
 */
@AuthenticationPrincipal
public @interface CurrentUser {
}
