/*
 * Copyright 2020-2024 the original author or authors.
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

package org.ifinalframework.security.sso.filter;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.ifinalframework.context.exception.UnauthorizedException;
import org.ifinalframework.core.result.Result;
import org.ifinalframework.security.sso.authentication.Authentication;
import org.ifinalframework.security.sso.authentication.AuthenticationDecoder;
import org.ifinalframework.security.sso.client.AuthenticationClient;

import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AuthenticationFilter
 *
 * @author iimik
 * @since 1.5.6
 **/
@Component
@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    
    @Resource
    private AuthenticationClient authenticationClient;
    @Resource
    private AuthenticationDecoder authenticationDecoder;
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final Result<String> result = authenticationClient.authentication();

        if(!result.isSuccess()){
            throw new UnauthorizedException(result.getCode(),result.getMessage());
        }

        final Authentication authentication = authenticationDecoder.decode(result.getData());
        final List<String> authorities = Optional.ofNullable(authentication.getAuthorities()).orElse(Collections.emptyList());

        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authentication.getUser(), null, authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        filterChain.doFilter(servletRequest, servletResponse);

    }
}
